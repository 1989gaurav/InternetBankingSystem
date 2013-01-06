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
 * Servlet implementation class for Servlet: A2A
 * 
 */
public class A2A extends Payment implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	private String	  type;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */

	private String	  UserID;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public A2A()
	{
		super();
	}
	
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
	}
	
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
				long source = Long.parseLong(request.getParameter("source"));
				long destination = Long.parseLong(request
				        .getParameter("destination"));
				float amount = Float.parseFloat(request
				        .getParameter("amount_a2a"));
				String transactionPassword = request
				        .getParameter("trans_pass_a2a");
				
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
				ResultSet paymentResult;
				
				// To transfer account to account transfer
				// 1. transaction password should be matched
				// 2. destination account should exist
				// 3. account balance of source should be sufficient
				// 4. debit from source account
				// 5. credit in destination account
				// 6. make entry in transaction table
				
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
					// transaction password is correct
					query = "select balance from accountDetails where accountDetails.accountNumber="
					        + destination;
					paymentResult = ExecuteQuery(query, paymentStatement, true);
					
					if(paymentResult.next())
					{
						// destination account exists
						
						// 40 rupees is transaction charge for each transaction
						float deductableAmount = amount + 40;
						
						transactionID = getCount("transactionID",
						        paymentStatement, pw) + 1;
						// debit from source account id balance is sufficient
						isDebited = DebitFromAccount(paymentStatement, source,
						        deductableAmount, amount
						                + " Rs. Transfered to Account Number "
						                + destination, transactionID);
						
						if(isDebited)
						{
							// balance is sufficient to perform this transaction
							
							// credit in destination account
							CreditFromAccount(
							        paymentStatement,
							        destination,
							        amount,
							        amount
							                + " Rs. Transferred by Account number "
							                + source, transactionID);
							
							// make entry in transaction Table
							makeTransactionEntries(transactionID, date, time,
							        paymentStatement, pw);
							
							// update transactionID count in count table
							updateCountByOne("transactionID", paymentStatement,
							        pw);
							
							// remittance(accountIssueFrom bigint,accountIssueTo
							// bigint,amount float,dateIssue date,number
							// int,personIssueTo varchar(20),status varchar(20)
							// transferred
							query = "insert into remittance values(" + source
							        + "," + destination + "," + amount + ",'"
							        + date + "'," + transactionID + ",'self')";
							ExecuteQuery(query, paymentStatement, false);
							
							responsePacket = "{'success':true,'data':'"
							        + transactionID + "'}";
							
						}
						else
							// Un sufficient transaction
							responsePacket = "{'success':false,'data':'Your account balance is not sufficient to perform this operation'}";
					}
					else
						// destination account does not exist
						responsePacket = "{'success':false,'data':'Destination account does not exist'}";
					
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