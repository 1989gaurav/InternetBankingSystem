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
 * Servlet implementation class for Servlet: DraftHistory This Servlet gives the
 * history of issued drafts from an account.
 * 
 */
public class DraftHistory extends DBConnect implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	/**
	 * Account number of the user
	 */
	private long	  accountNum;
	/**
	 * UserName of the user
	 */
	private String	  userID;
	
	/**
	 * Default Constructor for this class
	 */
	public DraftHistory()
	{
		super();
		userID = "";
		accountNum = 0;
	}
	
	// json response : "{'success': true/false,'size':<no . of draft
	// records>,'data':[{'id':1,'num':'<draft number>','favour':'<in favour
	// of>','amount':'<amount>','status':'<status>'},{.... 2nd record .... and
	// so on}]}"
	
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
		// A ResponsePacket to store the return values
		
		String responsePacket = "";
		String query = "";
		int draftNum = 0;
		String favour = "";
		float amt = 0;
		String status = "";
		String tempPacket = "";
		
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
				        .getParameter("acnum_history")));
				
				query = "select * from draft where acNumber =" + accountNum;
				rset = ExecuteQuery(query, draftStatusStatement, true);
				int id = 0;
				
				while (rset.next()) // drafts exists
				{
					if(id != 0)
						tempPacket += ",";
					
					id++;
					status = rset.getString("status");
					draftNum = rset.getInt("draftNum");
					amt = rset.getFloat("amount");
					favour = rset.getString("favour");
					
					tempPacket += "{'id':" + id + ",'num':'" + draftNum
					        + "','favour':'" + favour + "','amount':'" + amt
					        + "','status':'" + status + "'}";
					
				}
				if(id == 0) // no drafts on this accountNum
					responsePacket = "{success:false}";
				else
					// make response packet
					responsePacket = "{'success':true,'size':" + id
					        + ",'data':[" + tempPacket + "]}";
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