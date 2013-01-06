import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Servlet implementation class for Servlet: PresentmentPay
 * 
 */
public class PresentmentPay extends GeneratePDF implements
        javax.servlet.Servlet
{
	static final long	serialVersionUID	= 1L;
	
	private String	  type;
	
	private String	  UserID;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public PresentmentPay()
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
				// pw.println("logged in");
				// Get parameter from request
				String nick = request.getParameter("biller_nick_present");
				long billNumber = Long.parseLong(request
				        .getParameter("bill_num_present"));
				float amount = Float.parseFloat(request
				        .getParameter("amount_present"));
				long accountNumber = Long.parseLong(request
				        .getParameter("ac_num_present"));
				String transactionPassword = request
				        .getParameter("pass_present");
				
				// local variable
				int transactionID;
				String query = "";
				String responsePacket = "";
				boolean isValid;
				boolean isDebited;
				String date = getCurrentDate(); // get current date from
				// function defined in DBConnect
				String time = getCurrentTime(); // get current time from
				// function defined in DBConnect
				
				// pw.println(nick);
				// pw.println(billNumber);
				// pw.println(amount);
				// pw.println(accountNumber);
				// pw.println(transactionPassword);
				
				// Get connection from database pool
				Connection paymentConnection = getDBConnection();
				// Create a connection statement
				Statement paymentStatement = CreateQueryStatement(paymentConnection);
				ResultSet paymentResult;
				
				// To pay presentment bill
				// 1.varify transaction password
				// 2.Debit account balance by amount+charges if sufficient
				// 3.update the table bill info
				// 4. update table transaction
				// 5.update count values accordingly
				
				if(type.equals("Personal"))
					isValid = VarifyTransactionPassword(paymentStatement,
					        transactionPassword, UserID, true, pw);
				else
					isValid = VarifyTransactionPassword(paymentStatement,
					        transactionPassword, UserID, false, pw);
				
				if(!isValid)
					// bad password
					// pw.println("password not varified");
					responsePacket = "{'success':false,'data':'Bad Password, Please check your password and re-enter'}";
				else
				{ // transaction password is correct
					// calculate transaction ID if successful
					transactionID = getCount("transactionID", paymentStatement,
					        pw) + 1;
					
					// add service charges of transaction
					float deductableAmount = amount + 15;
					
					isDebited = DebitFromAccount(paymentStatement,
					        accountNumber, deductableAmount,
					        "Debited for payment of bill Number " + billNumber
					                + " of " + amount + " rupees for " + nick,
					        transactionID);
					
					if(isDebited)
					{
						// update transactionID count in count table
						updateCountByOne("transactionID", paymentStatement, pw);
						
						// make entry in transaction Table
						makeTransactionEntries(transactionID, date, time,
						        paymentStatement, pw);
						
						// update table billInfo where provider is above the
						// input nick and billNumber which was entered
						query = "select provider from billerMapping where billerMapping.userID="
						        + UserID
						        + " and billerMapping.nick='"
						        + nick
						        + "'";
						paymentResult = ExecuteQuery(query, paymentStatement,
						        true);
						paymentResult.next();
						
						String provider = paymentResult.getString("provider");
						
						// update bill info
						query = "update billInfo set billInfo.transactionID="
						        + transactionID + ",billInfo.dateTransaction='"
						        + date + "',billInfo.timeTransaction='" + time
						        + "' where billInfo.provider='" + provider
						        + "' and billInfo.billNumber=" + billNumber;
						// pw.println(query);
						ExecuteQuery(query, paymentStatement, false);
						
						// print the pdf recipt
						
						// current dir
						String dir = generateCurrentDirPath();
						//String path = dir + "content\\bill" + accountNumber
						//        + ".pdf"; // for window server
						 String path =
						 dir+"content/bill" + accountNumber+".pdf"; //for linux
						// server
						// Initializing a document of given specification
						Rectangle pageSize = new Rectangle(815, 580);
						pdfDocument = new Document(pageSize);
						// Initializing pdfWriter to write the document
						PdfWriter writer = PdfWriter.getInstance(pdfDocument,
						        new FileOutputStream(path));
						// open the document to write and document is protected
						// variable of GeneratePDF
						pdfDocument.open();
						// Write common header for all files to the pdf
						generateCommonHeader(dir);
						
						generateBillPDF(transactionID, paymentStatement, pw);
						
						pdfDocument.close();
						
						responsePacket = "{'success':true,'data':'" + path
						        + "'}";
						
					}
					else
						// balance is not sufficient in your account
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