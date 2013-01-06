import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * Servlet implementation class for Servlet: PrintBill
 * 
 */
public class PrintBill extends GeneratePDF implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	private String	  UserID;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public PrintBill()
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
				int transactionID = Integer.parseInt(request
				        .getParameter("biller_history"));
				
				// Get connection from database pool
				Connection paymentConnection = getDBConnection();
				// Create a connection statement
				Statement paymentStatement = CreateQueryStatement(paymentConnection);
				
				// current dir
				String dir = generateCurrentDirPath();
				String path = dir + "content\\Bill" + transactionID + ".pdf"; // for
				// window
				// server
				// String path = dir+"content/Bill"+transactionID+".pdf"; //for
				// linux server
				// Initializing a document of given specification
				Rectangle pageSize = new Rectangle(815, 580);
				pdfDocument = new Document(pageSize);
				// Initializing pdfWriter to write the document
				PdfWriter writer = PdfWriter.getInstance(pdfDocument,
				        new FileOutputStream(path));
				// open the document to write and document is protected variable
				// of GeneratePDF
				pdfDocument.open();
				// Write common header for all files to the pdf
				generateCommonHeader(dir);
				
				generateBillPDF(transactionID, paymentStatement, pw);
				
				pdfDocument.close();
				
				String responsePacket = "{'success':true}";
				pw.println(responsePacket);
			}
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
	@Override
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}
}