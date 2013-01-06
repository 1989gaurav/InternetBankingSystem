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
 * Servlet implementation class for Servlet: SendBiller
 * 
 */
public class SendBiller extends DBConnect implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	private String	  UserID;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SendBiller()
	{
		super();
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
			HttpSession sendSession = CreateHttpSession(request);
			// Retrieve value of UserID from cookie
			UserID = GetFromCookie("loginsession", sendSession);
			
			if(UserID.equals("F"))
				// Currently not logged in so redirect to home page
				response.sendRedirect("index.jsp");
			else
			{
				// If currently logged in
				// then get values from database and generate JSON String
				
				// Create connection variable
				Connection sendConnection = getDBConnection();
				// Create statement for connection
				Statement sendStatement = CreateQueryStatement(sendConnection);
				
				// Create a result statement to store result
				
				// variables to store value retrieved from database for a
				// particular table
				int count = 1; // Counter to count number of account found
				String nick = "";
				String query = ""; // Query string
				boolean isFirst = true;
				
				query = "select nick from billerMapping where billerMapping.userID="
				        + UserID;
				// pw.println(query);
				ResultSet sendResult = ExecuteQuery(query, sendStatement, true);
				
				while (sendResult.next())
				{
					// account number mapped on this userID
					nick = sendResult.getString("nick");
					
					if(isFirst)
					{
						responsePacket = responsePacket + "{'id':" + count
						        + ",'nick':'" + nick + "'}";
						isFirst = false;
					}
					else
						responsePacket = responsePacket + "," + "{'id':"
						        + count + ",'nick':'" + nick + "'}";
					
					// increase number of accounts mapped
					count++;
					
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