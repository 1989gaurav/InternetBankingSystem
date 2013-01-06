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
 * Servlet implementation class for Servlet: TransactionHistory
 * 
 */
public class TransactionHistory extends DBConnect implements
        javax.servlet.Servlet
{
	static final long	serialVersionUID	= 1L;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	private long	  accountNum;
	private String	  endDate;
	private String	  startDate;
	private String	  userID;
	
	public TransactionHistory()
	{
		super();
		accountNum = 0;
		startDate = "";
		endDate = "";
		userID = "";
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	/*
	 * Parameters: "acnum_trans_history" : Account Number "startdt_trans" :
	 * Start Date "enddt_trans" : End Date You need to search for all the
	 * transactions done from the table remittance for the given date range. For
	 * making a query of date range, you can do select * from remittance where
	 * date>=start_date and date<=enddate. these type of queries are also valid
	 * Reponse: JSON STring "{'success':true/false,'size': <no. of
	 * records>,'data':[{'id':1,'to':'<issued to a/c num>','tid':'<transaction
	 * id>','amount':'<amount>','date':'<date of transfer>'},{....2nd record
	 * and so on}]}"
	 */

	@Override
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		String responsePacket = "";
		String query = "";
		ResultSet rset;
		String tempPacket = "";
		long acIssuedTo = 0;
		String dateOfTransfer = "";
		float amount = 0;
		int transactionID = 0;
		
		PrintWriter pw = response.getWriter();
		// pw.println("check1");
		
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
				// pw.println("check2");
				response.sendRedirect("index.jsp");
			else
			// the user is logged on with givenID
			{
				// pw.println("check3");
				accountNum = Long.parseLong((request
				        .getParameter("acnum_trans_history")));
				startDate = request.getParameter("startdt_trans");
				endDate = request.getParameter("enddt_trans");
				// pw.println("check4");
				// create table remittance(accountIssueFrom
				// bigint,accountIssueTo bigint,amount float,dateIssue
				// date,transactionID int)
				
				query = "select * from remittance where accountIssueFrom ="
				        + accountNum + " and dateIssue <='" + endDate
				        + "' and dateIssue>='" + startDate + "'";
				// pw.println(query);
				rset = ExecuteQuery(query, draftStatusStatement, true);
				// pw.println("check5");
				int id = 0;
				
				while (rset.next()) // drafts exists
				{
					if(id != 0)
						tempPacket += ",";
					
					// pw.println("check6");
					
					id++;
					acIssuedTo = rset.getLong("accountIssueTo");
					transactionID = rset.getInt("transactionID");
					amount = rset.getFloat("amount");
					dateOfTransfer = rset.getDate("dateIssue").toString();
					
					tempPacket += "{'id':" + id + ",'to':'" + acIssuedTo
					        + "','tid':'" + transactionID + "','amount':'"
					        + amount + "','date':'" + dateOfTransfer + "'}";
					
					// pw.println(tempPacket);
				}
				if(id == 0) // no such transaction on this accountNum
					responsePacket = "{success:false}";
				else
					responsePacket = "{'success':true,'size':" + id
					        + ",'data':[" + tempPacket + "]}";
				// pw.println("check7");
				// pw.println(responsePacket);
			}
		}
		catch (Exception ex)
		{
			// response.sendRedirect("index.jsp");
		}
		
		// Creating Writer Object
		
		pw.print(responsePacket);
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}
}