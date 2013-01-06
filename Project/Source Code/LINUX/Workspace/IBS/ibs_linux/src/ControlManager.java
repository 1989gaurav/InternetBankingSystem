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
 * Servlet implementation class for Servlet: ControlManager
 * 
 */
public class ControlManager extends DBConnect implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	/**
	 * Account Number of the user.
	 */
	private long	  accountNum;
	/**
	 * UserID of the user.
	 */
	private String	  UserID;
	
	/**
	 * Initialize the Account number to 0 and userid to empty string
	 */
	public ControlManager()
	{
		super();
		accountNum = 0;
		UserID = "";
		
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
		
		// A ResponsePacket to store the return values
		String responsePacket = "";
		// /Query string to be executed againest the database
		String query = "";
		// Resultset of query.
		ResultSet rset;
		
		// HttpSession variable to store the value of the current session to get
		// values of the loginSession and Type
		HttpSession currentSession = CreateHttpSession(request);
		
		/**
		 * Userid: Get the value from cookie and save in UserID
		 */
		
		UserID = GetFromCookie("loginsession", currentSession);
		
		/**
		 * Create connection from database to load values from database
		 */
		Connection cmConnection = getDBConnection();
		// Create Statement corresponding to connection to execute query
		Statement cmStatement = CreateQueryStatement(cmConnection);
		
		try
		{
			if(UserID.equals("F"))
				response.sendRedirect("index.jsp");
			else
			// the user is logged on with givenID
			{
				
				accountNum = Long.parseLong((request.getParameter("mapAC")));
				query = "select * from accountMapping where userID=" + UserID
				        + " and accountNumber=" + accountNum;
				
				rset = ExecuteQuery(query, cmStatement, true);
				if(rset.next())
					responsePacket = "{success:true}";
				else
				{
					// map accountnum from table accountDetails
					
					query = "select * from accountDetails where accountNumber = "
					        + accountNum;
					rset = ExecuteQuery(query, cmStatement, true);
					if(rset.next())
					{
						/**
						 * ID Proof of the person logged in
						 */
						String IDproof1 = rset.getString("IDproof1");
						String IDproof2 = rset.getString("IDproof2");
						String IDproof3 = rset.getString("IDproof3");
						
						// map userid from table myprofile
						query = "select * from myProfile where userID = "
						        + UserID;
						rset = ExecuteQuery(query, cmStatement, true);
						rset.next();
						String IDproof = rset.getString("IDproof");
						
						if(IDproof.equals(IDproof1) || IDproof.equals(IDproof2)
						        || IDproof.equals(IDproof3))
						{
							
							query = "insert into accountMapping values("
							        + UserID + "," + accountNum + ")";
							rset = ExecuteQuery(query, cmStatement, false);
							
							responsePacket = "{success:true}";
						}
					}
					else
						// there is no such account with given accountNumber
						responsePacket = "{success:false}";
				}
			}
			
		}
		catch (Exception ex)
		{
			
			response.sendRedirect("index.jsp");
		}
		
		// Creating Writer Object
		PrintWriter pw = response.getWriter();
		pw.print(responsePacket);
	}
}