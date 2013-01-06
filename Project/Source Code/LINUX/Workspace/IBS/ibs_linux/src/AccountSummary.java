import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: AccountSummary
 * 
 */
public class AccountSummary extends DBConnect implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	/**
	 * contains the userID of logged-on user
	 */
	private String	  UserID;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public AccountSummary()
	{
		super();
		
	}
	
	// Json String
	// "{'success': true,'size': <no. of records sending>,'data' :
	// [{'id':1,'acnum':'<A/c num1>','actype':'<type of a/c1>' ,'holder':'<any 1
	// holder of a/c>','balance':'balance1'},
	// { <similarly for 2nd record and so on>}]}"
	
	public String createJSONString(int dataCount, long accountNumber,
	        String accountType, String holder, float balance)
	{
		String Packet = "{'id':" + dataCount + ",'acnum':'" + accountNumber
		        + "','actype':'" + accountType + "','holder':'" + holder
		        + "','balance':'" + balance + "'}";
		return Packet;
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		PrintWriter pw = response.getWriter();
		String responsePacket = "";
		try
		{
			// Creating a session variable for current session
			HttpSession summarySession = CreateHttpSession(request);
			// Retrieve value of UserID from cookie
			UserID = GetFromCookie("loginsession", summarySession);
			
			if(UserID.equals("F"))
				// Currently not logged in so redirect to home page
				response.sendRedirect("index.jsp");
			else
			{
				// If currently logged in
				// then get values from database and generate JSON String
				
				// Create connection variable
				Connection summaryConnection = getDBConnection();
				// Create statement for connection
				Statement summaryStatement1 = CreateQueryStatement(summaryConnection);
				
				// Create a result statement to store result
				
				// variables to store value retrieved from database for a
				// particular table
				int count = 1; // Counter to count number of account found
				long accountNumber; // Account number
				String accountType; // Account type
				String holder; // Account holder name
				float balance; // Account balance
				String query = ""; // Query string
				boolean isFirst = true;
				
				query = "select accountNumber from accountMapping where accountMapping.userID="
				        + UserID;
				// pw.println(query);
				ResultSet accountResult = ExecuteQuery(query,
				        summaryStatement1, true);
				
				while (accountResult.next())
				{
					// account number mapped on this userID
					accountNumber = accountResult.getLong("accountNumber");
					query = "select accountType,holder1,balance from accountDetails where accountDetails.accountNumber="
					        + accountNumber;
					// pw.println(query);
					Statement summaryStatement2 = CreateQueryStatement(summaryConnection);
					ResultSet accountDetailsResult = ExecuteQuery(query,
					        summaryStatement2, true);
					
					if(accountDetailsResult.next())
					{
						accountType = accountDetailsResult
						        .getString("accountType");
						holder = accountDetailsResult.getString("holder1");
						balance = accountDetailsResult.getFloat("balance");
						
						if(isFirst)
						{
							responsePacket = responsePacket
							        + createJSONString(count, accountNumber,
							                accountType, holder, balance);
							isFirst = false;
						}
						else
							responsePacket = responsePacket
							        + ","
							        + createJSONString(count, accountNumber,
							                accountType, holder, balance);
						
						// increase number of accounts mapped
						count++;
					}
				}
				
				if(count == 1)
					// count==1 means no account found
					responsePacket = "{'success':false,'size':0}";
				else
				{
					// count-1 accounts found
					// adding specification in the end and in starting of
					// responsepacket
					responsePacket = "{'success':true,'size':" + (count - 1)
					        + ",'data':[" + responsePacket;
					responsePacket = responsePacket + "]}";
				}
				
				pw.println(responsePacket);
			}
		}
		catch (SQLException sx)
		{
			// pw.println("Sql Error Occured");
		}
		catch (Exception ex)
		{
			response.sendRedirect("index.jsp");
		}
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
	}
	
}