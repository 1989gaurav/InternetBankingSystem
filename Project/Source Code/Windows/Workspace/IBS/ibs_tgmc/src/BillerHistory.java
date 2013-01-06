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
 * Servlet implementation class for Servlet: BillerHistory.
 * This servlet will take as input a paid bill number and will generate 
 * the receipt on demannd of the user. This class will also display the data
 * related to the bill.
 * 
 */
public class BillerHistory extends DBConnect implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	/**
	 * Username of the user querying about the bill.
	 */
	private String	  userID;
	/**
	 * Default constructor.
	 */
	public BillerHistory()
	{
		super();
		userID = "";
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
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
	/*
	 * JSON string : "{'success':true/false,size:<total no. of
	 * records>,'data':[{'id':1,'biller':<biller nickname>,'tid':Transaction
	 * ID},{2nd record and so on}]}"
	 */
	@Override
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		String responsePacket = "";
		String query = "";
		ResultSet rset;
		String tempPacket = "";
		
		String biller = "";
		int transactionID = 0;
		
		PrintWriter pw = response.getWriter();
		
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
				query = "select acmap.accountNumber as MAcNum,acmap.userID,b.accountNumber as BAcNum,b.provider,b.TransactionID from accountMapping as acmap full join billInfo as b on acmap.accountNumber=b.accountNumber where userID="
				        + userID+" and transactionID<>0";
				// pw.println(query);
				rset = ExecuteQuery(query, draftStatusStatement, true);
				// pw.println("check5");
				
				int id = 0;
				// "{'success':true/false,size:<total no. of
				// records>,'data':[{'id':1,'biller':<biller
				// name>,'tid':Transaction ID},{2nd record and so on}]}"
				// create table accountMapping (userID int,accountNumber bigint)
				// create table billInfo(accountNumber bigint,provider
				// varchar(80),billNumber bigint,amountPaid
				// float,dateTransaction date,timeTransaction time,TransactionID
				// int)
				
				while (rset.next())
				{
					if(id != 0)
						tempPacket += ",";
					
					id++;
					biller = rset.getString("provider");
					transactionID = rset.getInt("transactionID");
					
					tempPacket += "{'id':" + id + ",'biller':'" + biller
					        + "','tid':'" + transactionID + "'}";
				}
				if(id == 0) // no such entry on this userID
					responsePacket = "{success:false}";
				else
					responsePacket = "{'success':true,'size':" + id
					        + ",'data':[" + tempPacket + "]}";
				// pw.println(responsePacket);
			}
		}
		catch (Exception ex)
		{
			response.sendRedirect("index.jsp");
		}
		
		pw.print(responsePacket);
	}
	
}