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
 * Servlet implementation class for Servlet: PersonalLoginValidation
 * 
 */
public class PersonalLoginValidation extends DBConnect implements
        javax.servlet.Servlet
{
	static final long	serialVersionUID	= 1L;
	
	/**
	 * Type of Customer
	 */
	private int	      choice;
	private int	      count;
	/**
	 * Contains the password
	 */
	private String	  Password;	              // contains the password
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	/**
	 * Contains the UserID
	 */
	private String	  UserID;
	
	public PersonalLoginValidation()
	{
		super();
		UserID = "";
		Password = "";
		count = 0;
		choice = 0;
	}
	
	/**
	 * It returns a JSON response that can be read by AJAX event handlers to
	 * decide whether request was successful or a failure
	 * 
	 * @param validateStatus
	 *            Indicates whether the User id authenticated or not
	 * @param s
	 *            Refers to the Database Statement Connection
	 * @param isAlreadyBlocked
	 *            Tells whether the UserID has already been blocked or not
	 * @return The JSON response in the format "{success: true/false,
	 *         data:UserID/BlockCount}"
	 */
	public String CreateJSONPacket(boolean validateStatus, Statement s,
	        boolean isAlreadyBlocked)
	{
		String packet = "";
		if(validateStatus)
			packet = "{success:true,data:'" + UserID + "'}";
		else
		{
			packet = "{success:false,data:" + GetBlockCount(s) + "}";
			if(!isAlreadyBlocked)
				UpdateBlockCount(s);
		}
		return packet;
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
		
		// Retrieving Username & Password entered in the form
		UserID = request.getParameter("puser");
		Password = request.getParameter("ppass");
		choice = Integer.parseInt(request.getParameter("choice"));
		// Making Database Connection
		Connection loginConnection = getDBConnection();
		// Creating SQL Statement to execute Query
		Statement loginStatement = CreateQueryStatement(loginConnection);
		// Building Query
		String query = "";
		if(choice == 1)
			query = "select * from ploginInfo where ploginInfo.userName='"
			        + UserID + "' and ploginInfo.password='" + Password + "'";
		else if(choice == 2)
			query = "select * from cloginInfo where cloginInfo.userName='"
			        + UserID + "' and cloginInfo.password='" + Password + "'";
		// Execute Query
		ResultSet validate = ExecuteQuery(query, loginStatement, true);
		// Taking corresponding operation
		String responsePacket = "";
		// Creating HttpSession Variable for Session Validation
		HttpSession loginSession = CreateHttpSession(request);
		
		try
		{
			if(validate.next())
			{
				// Valid User
				int userNum = validate.getInt("userID");
				int blockValue = GetBlockCount(loginStatement);
				if(blockValue < 3)
				{
					responsePacket = CreateJSONPacket(true, loginStatement,
					        false); // creating JSON Packet to send back data
					ResetBlockCount(loginStatement); // Resetting the number
					// of invalid attempts
					loginSession = AddToCookie("loginsession", "" + userNum,
					        loginSession); // Applying Session Settings
					if(choice == 1)
						loginSession = AddToCookie("type", "Personal",
						        loginSession); // Saving to session the type of
					// customer
					else if(choice == 2)
						loginSession = AddToCookie("type", "Corporate",
						        loginSession); // Saving to session the type of
					// customer
				}
				else
				{
					responsePacket = CreateJSONPacket(false, loginStatement,
					        true); // creating corresponding JSON Packet
					loginSession = AddToCookie("loginsession", "F",
					        loginSession); // Applying Session Settings
				}
			}
			else
			{
				// Invalid User
				responsePacket = CreateJSONPacket(false, loginStatement, false); // creating
				// corresponding
				// JSON
				// Packet
				loginSession = AddToCookie("loginsession", "F", loginSession); // Applying
				// Session
				// Settings
			}
		}
		catch (SQLException ex)
		{
			// Error Condition
		}
		
		// Creating Writer Object
		PrintWriter pw = response.getWriter();
		pw.print(responsePacket);
	}
	
	/**
	 * Retrieves the BlockCount indicating the number of successive invalid
	 * logon attempts on a user account
	 * 
	 * @param s
	 *            Refers to the Database Statement connection
	 * @return the BlockCount of the corresponding User
	 */
	public int GetBlockCount(Statement s)
	{
		count = 0;
		String query = "";
		if(choice == 1)
			query = "select * from ploginInfo where ploginInfo.userName='"
			        + UserID + "'";
		else if(choice == 2)
			query = "select * from cloginInfo where cloginInfo.userName='"
			        + UserID + "'";
		ResultSet blockCount = ExecuteQuery(query, s, true);
		try
		{
			if(blockCount.next())
				// Invalid Password
				// Retrieving the block count
				count = blockCount.getInt("blockstatus");
		}
		catch (SQLException ex)
		{
			// Error Condition
		}
		return count;
	}
	
	/**
	 * Upon successful login it is required to reset the Block Count for a user.
	 * This function does the same
	 * 
	 * @param s
	 *            Refers to the Database Statement connection
	 */
	public void ResetBlockCount(Statement s) // It resets the block count on
	// correct login before count <
	// 3
	{
		String query = "";
		if(choice == 1)
			query = "update ploginInfo set ploginInfo.blockstatus=0 where ploginInfo.userName='"
			        + UserID + "'";
		else if(choice == 2)
			query = "update cloginInfo set cloginInfo.blockstatus=0 where cloginInfo.userName='"
			        + UserID + "'";
		ExecuteQuery(query, s, false);
	}
	
	/**
	 * Updates & reflects the changes in the User Table regarding his block
	 * status
	 * 
	 * @param s
	 *            Refers to the Database Statement connection
	 */
	
	public void UpdateBlockCount(Statement s)
	{
		String query = "";
		count++;
		// Incrementing the count by 1
		if(choice == 1)
			query = "update ploginInfo set ploginInfo.blockstatus=" + count
			        + " where ploginInfo.userName='" + UserID + "'";
		else if(choice == 2)
			query = "update cloginInfo set cloginInfo.blockstatus=" + count
			        + " where cloginInfo.userName='" + UserID + "'";
		ExecuteQuery(query, s, false);
	}
}