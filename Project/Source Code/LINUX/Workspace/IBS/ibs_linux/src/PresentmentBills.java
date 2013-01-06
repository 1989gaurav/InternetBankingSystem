import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: PresentmentBills
 * 
 */
public class PresentmentBills extends DBConnect implements
        javax.servlet.Servlet
{
	static final long	serialVersionUID	= 1L;
	
	private String	  UserID;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public PresentmentBills()
	{
		super();
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		// to presentment of bills
		// 1. bill is paid or not
		// 2. If paid then don't pay it
		// 2. if not paid then
		// get value of amount and bill number
		
		// To check bill is paid or not
		// Using userID and nick get original provider using table billerMapping
		// Using accountMapping table get all accountNumber mapped to this id
		// and from billerInfo
		// select all those accountNumber mapped to this ID and having provider
		// as came from first query
		// if transactionID is not 0 then show error
		// otherwise return amount and bill number
		
		PrintWriter pw = response.getWriter();
		try
		{
			// Create a session variable
			HttpSession generateSession = CreateHttpSession(request);
			// HttpSession summarySession = CreateHttpSession(request);
			// Get value of cookie for this session
			UserID = GetFromCookie("loginsession", generateSession);
			// UserID = GetFromCookie("loginsession", summarySession);
			
			if(UserID.equals("F"))
				// User is not currently logged in
				response.sendRedirect("index.jsp");
			else
			{
				
				// Get parameter from request
				String nick = request.getParameter("biller_nick_present");
				
				// local variables
				long billNumber;
				float amount;
				String provider;
				String responsePacket = "";
				String query = "";
				
				// Create connection variable
				Connection billConnection = getDBConnection();
				// Create statement for connection
				Statement billStatement = CreateQueryStatement(billConnection);
				// Result Set
				ResultSet billResult;
				
				// To get original name of the provider of that nick
				query = "select provider from billerMapping where billerMapping.userID="
				        + UserID + " and billerMapping.nick='" + nick + "'";
				billResult = ExecuteQuery(query, billStatement, true);
				
				if(billResult.next())
				{
					provider = billResult.getString("provider");
					
					query = "select billInfo.billNumber,billInfo.amountPaid from accountMapping full join billInfo on accountMapping.accountNumber=billInfo.accountNumber where userID="
					        + UserID
					        + " and provider='"
					        + provider
					        + "' and transactionID=0";
					billResult = ExecuteQuery(query, billStatement, true);
					
					if(billResult.next())
					{
						// one entry is there to make payment of bills
						amount = billResult.getFloat("amountPaid");
						billNumber = billResult.getLong("billNumber");
						
						responsePacket = "{success:true,'num':'" + billNumber
						        + "','amt':'" + amount + "'}";
					}
					else
						// no entry having userID and provide having any bill to
						// make payment
						responsePacket = "{success:false,'data':'No bill of "
						        + nick + " is there to pay '}";
				}
				else
					// no such nick mapped with this user id
					responsePacket = "{success:false,'data':'No bill of "
					        + nick
					        + " is added to this userID first add it using add biller '}";
				pw.println(responsePacket);
			}
		}
		catch (Exception ex)
		{
			response.sendRedirect("index.jsp");
		}
		
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}
}