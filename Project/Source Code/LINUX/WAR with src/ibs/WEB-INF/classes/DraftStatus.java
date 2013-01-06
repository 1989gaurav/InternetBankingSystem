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
 * This Class is Derived from the class DBConnect Every function and every
 * variable defined here is available in all object. This class is for
 * retrieving status of draft issued from particular account.
 * 
 * 
 * @author Teenovators
 * @version 1.0, 03 NOV 2008
 * @see DBConnect
 */

public class DraftStatus extends DBConnect implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	/**
	 * Contains the Account Number
	 */
	private long	  accountNum;
	
	/**
	 * Contains the Draft Number
	 */
	private int	      draftNumber;
	
	/**
	 * Contains the UserID
	 */
	private String	  userID;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */

	/**
	 * Default Constructor.
	 */
	public DraftStatus()
	{
		super();
		userID = "";
		draftNumber = 0;
		accountNum = 0;
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */

	// JSON string response: "{success: true/false, data:'<status of that
	// Draft>'}
	
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
		String query = "";
		ResultSet rset;
		
		// HttpSession variable to store the value of the current session to get
		// values of the loginSession and Type
		HttpSession currentSession = CreateHttpSession(request);
		
		// Get the value from cookie and save in UserID
		userID = GetFromCookie("loginsession", currentSession);
		
		// Create connection from database to load values from database
		Connection draftStatusConnection = getDBConnection();
		// Create Statement corresponding to connection to execute query
		Statement draftStatusStatement = CreateQueryStatement(draftStatusConnection);
		
		try
		{
			if(userID.equals("F"))
				response.sendRedirect("index.jsp");
			else
			// the user is logged on with givenID
			{
				accountNum = Long.parseLong((request
				        .getParameter("acnum_limit")));
				draftNumber = Integer
				        .parseInt(request.getParameter("draftNum"));
				
				query = "select status from draft where acNumber ="
				        + accountNum + " and draftNum = " + draftNumber;
				rset = ExecuteQuery(query, draftStatusStatement, true);
				
				if(rset.next()) // draft exists
				{
					String status = rset.getString("status");
					responsePacket = "{success:true,data:'" + status + "'}";
				}
				else
					// no such draft on this accountNum
					responsePacket = "{success:false}";
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