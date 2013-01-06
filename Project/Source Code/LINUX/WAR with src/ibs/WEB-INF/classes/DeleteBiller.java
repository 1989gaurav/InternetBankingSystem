import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: DeleteBiller
 * This servlet will remove a biller from the user's biller list.
 * 
 */
public class DeleteBiller extends DBConnect implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	/**
	 * Username of the user.
	 */
	
	private String	  UserID;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	
	/**
	 * Default constructor for the servlet class.
	 */
	
	public DeleteBiller()
	{
		super();
	}
	
	
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		PrintWriter pw = response.getWriter();
		
		try
		{
			// Create a session variable
			HttpSession draftSession = CreateHttpSession(request);
			// Get value of cookie for this session
			UserID = GetFromCookie("loginsession", draftSession);
			
			// pw.println("starting ");
			if(UserID.equals("F"))
				// User is not currently logged in
				response.sendRedirect("index.jsp");
			else
			{
				
				// Get parameter from request
				String nick = request.getParameter("nick_biller");
				
				// Local variables
				String query = "";
				String responsePacket = "";
				// Get connection from database pool
				Connection billerConnection = getDBConnection();
				// Create a connection statement
				Statement paymentStatement = CreateQueryStatement(billerConnection);
				// entry exists in the table already so delete it from table
				query = "delete from billerMapping where billerMapping.userID="
				        + UserID + " and billerMapping.nick='" + nick + "'";
				ExecuteQuery(query, paymentStatement, false);
				responsePacket = "{success:true}";
				
				pw.println(responsePacket);
			}
		}
		catch (Exception ex)
		{
			// exception occurred
			response.sendRedirect("index.jsp");
			
		}
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */

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