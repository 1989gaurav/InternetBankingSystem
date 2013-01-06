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
 * Servlet implementation class for Servlet: ChequeBookIssue
 * 
 */
public class ChequeBookIssue extends DBConnect implements javax.servlet.Servlet
{
	static final long	serialVersionUID	= 1L;
	
	private long	  accountNum;
	
	private String	  modeCheque;	              // Mode of collection
	                                              
	private int	      quantity;	              // Number of cheques
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 * 
	 * Parameters: "acCheque" - A/c num (long) "qtyCheque" - Number of cheques
	 * (int) "modeCheque" - Mode of collection (string) Method : POST Response:
	 * JSON String : "{success: true/false, data: <chequebook number>}"
	 * 
	 */
	private String	  userID;
	
	public ChequeBookIssue()
	{
		super();
		userID = "";
		accountNum = 0;
		quantity = 0;
		modeCheque = "";
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
	@Override
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String responsePacket = "";
		String query = "";
		int chequeNum = 0;
		int chequeBookNum = 0;
		int StartNum = 0;
		int EndNum = 0;
		ResultSet rset;
		
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
				response.sendRedirect("index.jsp");
			else
			// the user is logged on with givenID
			{
				accountNum = Long.parseLong(request.getParameter("acCheque"));
				// pw.println("check2");
				quantity = Integer.parseInt(request.getParameter("qtyCheque"));
				// pw.println("check4");
				modeCheque = request.getParameter("modeCheque");
				// pw.println("check6");
				
				// create table chequeBook(accountNumber bigint,chequeQty
				// int,dateIssued date,chequeBookNumber int,chequeStart
				// int,chequeEnd int,mode varchar(20))
				// create table count(cnt varchar(40),value int)
				
				query = "select value from count where cnt='ChequeBook'";
				// pw.println(query);
				rset = ExecuteQuery(query, draftStatusStatement, true);
				
				if(rset.next())
					chequeBookNum = rset.getInt("value");
				else
					;// chequebook entry is not there in database
					
				query = "select value from count where cnt='Cheque'";
				// pw.println(query);
				rset = ExecuteQuery(query, draftStatusStatement, true);
				
				if(rset.next())
					chequeNum = rset.getInt("value");
				else
					;// cheque entry is not there in database
					
				chequeBookNum++;
				StartNum = chequeNum + 1;
				EndNum = chequeNum + quantity;
				
				query = "insert into chequeBook values(" + accountNum + ","
				        + quantity + ",'" + getCurrentDate() + "',"
				        + chequeBookNum + "," + StartNum + "," + EndNum + ",'"
				        + modeCheque + "')";
				// pw.println(query);
				ExecuteQuery(query, draftStatusStatement, false);
				
				query = "update count set value=" + chequeBookNum
				        + " where cnt='ChequeBook'";
				// pw.println(query);
				ExecuteQuery(query, draftStatusStatement, false);
				
				query = "update count set value=" + EndNum
				        + " where cnt='Cheque'";
				// pw.println(query);
				ExecuteQuery(query, draftStatusStatement, false);
				
				responsePacket = "{success:true,data:"+chequeBookNum+"}";
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