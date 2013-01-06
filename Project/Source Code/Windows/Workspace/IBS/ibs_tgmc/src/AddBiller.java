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
 * Servlet implementation class for Servlet: AddBiller
 * This Servlet will provide the service to add a biller to the user's
 * biller list. 
 * 
 */
public class AddBiller extends DBConnect implements javax.servlet.Servlet{
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
	public AddBiller()
	{
		super();
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
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
				String provider = request.getParameter("provider");
				String nickName = request.getParameter("title_biller");
				
				// Local variables
				String query = "";
				String responsePacket = "";
				// Get connection from database pool
				Connection billerConnection = getDBConnection();
				// Create a connection statement
				Statement paymentStatement = CreateQueryStatement(billerConnection);
				ResultSet paymentResult;
				
				// To add biller
				// provider should not mapped already
				// if mapped already then don't map it
				// if not mapped then
				//nick assigned to the provide should not assigned to other provider already 
				// map that provider to the userID make entry in billerMapping
				// table
				
				query = "select * from billerMapping where billerMapping.userID="
				        + UserID
				        + " and billerMapping.provider='"
				        + provider
				        + "'";
				// pw.println(query);
				paymentResult = ExecuteQuery(query, paymentStatement, true);
				
				if(paymentResult.next())
					// entry exists in the table already so no need to be mapped
					// again
					responsePacket = "{success:false}";
				else
				{
					query = "select * from billerMapping where billerMapping.userID="
				        + UserID
				        + " and billerMapping.nick='"
				        + nickName
				        + "'";
					paymentResult = ExecuteQuery(query, paymentStatement, true);
					
					if(paymentResult.next())
					{
						//This nick is already mapped to some other provider
						responsePacket = "{success:false}";
					}
					else
					{
						query = "insert into billerMapping values(" + UserID + ",'"
					        + provider + "','" + nickName + "')";
						// 	pw.println(query);
						ExecuteQuery(query, paymentStatement, false);
					
						responsePacket = "{success:true}";
					}
					
				}
				
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
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}
}