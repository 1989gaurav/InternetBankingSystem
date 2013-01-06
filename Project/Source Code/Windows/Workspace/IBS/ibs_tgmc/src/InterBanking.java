import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: InterBanking
 * 
 */
//
public class InterBanking extends Payment implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */

	/**
	 * Type of user. Its value will be either "Personal" or "Corporate".
	 */
	private String	  type;
	
	/**
	 * UserName of the user using this service.
	 */
	private String	  UserID;
	
	/**
	 * Default constructor calling the constructor of parent class.
	 */
	public InterBanking()
	{
		super();
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
		PrintWriter pw = response.getWriter();
		
		try
		{
			// Create a session variable
			HttpSession draftSession = CreateHttpSession(request);
			// Get value of cookie for this session
			UserID = GetFromCookie("loginsession", draftSession);
			type = GetFromCookie("type", draftSession);
			
			// pw.println("starting ");
			if(UserID.equals("F"))
				// User is not currently logged in
				response.sendRedirect("index.jsp");
			else
			{
				// Get parameter from request
				long source = Long.parseLong(request
				        .getParameter("src_bank_ac"));
				long destination = Long.parseLong(request
				        .getParameter("dest_bank_ac"));
				String bank = request.getParameter("dest_bank");
				float amount = Float.parseFloat(request
				        .getParameter("inter_amount"));
				String transactionPassword = request
				        .getParameter("trans_pass_inter");
				
				// local variables;
				int transactionID;
				String date = getCurrentDate(); // get current date from
				// function defined in DBConnect
				String time = getCurrentTime(); // get current time from
				// function defined in DBConnect
				boolean isValid;
				boolean isDebited;
				String responsePacket = "";
				String query;
				
				// Get connection from database pool
				Connection draftConnection = getDBConnection();
				// Create a connection statement
				Statement paymentStatement = CreateQueryStatement(draftConnection);
				if(type.equals("Personal"))
					isValid = VarifyTransactionPassword(paymentStatement,
					        transactionPassword, UserID, true, pw);
				else
					isValid = VarifyTransactionPassword(paymentStatement,
					        transactionPassword, UserID, false, pw);
				
				if(!isValid)
					// bad password
					responsePacket = "{'success':false,'data':'Bad Transaction Password, Please check your password and re-enter'}";
				else
				{
					// destination bank should exist and account should exist it
					// is an assumption
					
					// 50 rupees is transaction charge for each inter-banking
					// transaction
					float deductableAmount = amount + 50;
					
					transactionID = getCount("transactionID", paymentStatement,
					        pw) + 1;
					// debit from source account id balance is sufficient
					isDebited = DebitFromAccount(paymentStatement, source,
					        deductableAmount, amount
					                + " Rs. Transfered to Bank " + bank
					                + " in Account Number " + destination,
					        transactionID);
					
					if(isDebited)
					{
						// balance is sufficient to perform this transaction
						
						// make entry in transaction Table
						makeTransactionEntries(transactionID, date, time,
						        paymentStatement, pw);
						
						// remittance(accountIssueFrom bigint,accountIssueTo
						// bigint,amount float,dateIssue date,number
						// int,personIssueTo varchar(20),status varchar(20)
						// transferred
						query = "insert into remittance values(" + source + ","
						        + destination + "," + amount + ",'" + date
						        + "'," + (transactionID) + ",'" + bank + "')";
						ExecuteQuery(query, paymentStatement, false);
						
						// update transactionID count in count table
						updateCountByOne("transactionID", paymentStatement, pw);
						
						responsePacket = "{'success':true,'data':'"
						        + transactionID + "'}";
						
					}
					else
						// Un sufficient transaction
						responsePacket = "{'success':false,'data':'Your account balance is not sufficient to perform this operation'}";
					
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
}