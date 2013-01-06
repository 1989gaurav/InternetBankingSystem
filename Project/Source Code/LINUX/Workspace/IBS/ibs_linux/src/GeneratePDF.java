import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;

/**
 * Servlet implementation class for Servlet: GeneratePDF
 * 
 */
public class GeneratePDF extends Payment implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	protected Document	pdfDocument;	          // To hold the current
	// document variable
	
	protected Table	   t;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GeneratePDF()
	{
		super();
	}
	
	public GeneratePDF(Document document)
	{
		super();
		pdfDocument = document;
		
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
	}
	
	public void generateBillPDF(int transactionID, Statement s, PrintWriter pw)
	{
		String query = "select * from billInfo where billInfo.transactionID="
		        + transactionID;
		// pw.println(query);
		ResultSet result = ExecuteQuery(query, s, true);
		try
		{
			result.next();
			pdfDocument.add(new Paragraph("Bill recipt", FontFactory.getFont(
			        FontFactory.HELVETICA, 25, Font.BOLD, new Color(0, 0, 0))));
		/*	pdfDocument.add(new Paragraph("Bill Issued for Account Number : "
			        + result.getLong("accountNumber") + " ", FontFactory
			        .getFont(FontFactory.HELVETICA, 20, Font.BOLD, new Color(0,
			                0, 0))));
			pdfDocument.add(new Paragraph("Bill Number : "
			        + result.getLong("billNumber") + "         Paid to : "
			        + result.getString("provider"), FontFactory.getFont(
			        FontFactory.HELVETICA, 20, Font.BOLD, new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("Amount to pay : "
			        + result.getFloat("amountPaid")
			        + " Service charges : 15 Rs.      Total amount to paid :"
			        + (result.getFloat("amountPaid") + 15), FontFactory
			        .getFont(FontFactory.HELVETICA, 20, Font.BOLD, new Color(0,
			                0, 0))));
			pdfDocument.add(new Paragraph("Payment Date : "
			        + result.getDate("dateTransaction")
			        + "                    Payment Time  : "
			        + result.getTime("timeTransaction"), FontFactory.getFont(
			        FontFactory.HELVETICA, 20, Font.BOLD, new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("Transaction ID : "
			        + result.getInt("transactionID"), FontFactory.getFont(
			        FontFactory.HELVETICA, 20, Font.BOLD, new Color(0, 0, 0))));
		*/
			t = new Table(2);
			
			t.setBorderColor(new Color(0, 0, 0));
			t.setPadding(5);
			t.setSpacing(2);
			
			Cell c = new Cell("Bill Number :       "+result.getLong("billNumber"));
			t.addCell(c);
			c.setWidth(20);
			c = new Cell("Transaction ID :       "+result.getInt("transactionID"));
			t.addCell(c);
			c = new Cell("Date           :       "+result.getDate("dateTransaction"));
			t.addCell(c);
			c = new Cell("Time            :       "+result.getTime("timeTransaction"));
			t.addCell(c);
			c = new Cell("Pay to                  :                     "+result.getString("provider"));
			c.setColspan(2);
			t.addCell(c);
			c = new Cell("Account Number :                     "+result.getLong("accountNumber"));
			c.setColspan(2);
			t.addCell(c);
			c = new Cell("Amount               :                     "+result.getFloat("amountPaid"));
			c.setColspan(2);
			t.addCell(c);
			c = new Cell("Service charge   :                     "+15);
			c.setColspan(2);
			t.addCell(c);
			c = new Cell("Total                   :                     "+(result.getFloat("amountPaid")+15));
			c.setColspan(2);
			t.addCell(c);
			
			
			pdfDocument.add(t);
			
			
		}
		catch (Exception ex)
		{
			// may be document exception
		}
	}
	
	// Generate common header for all pdfs
	public void generateCommonHeader(String dir)
	{
		try
		{
			
			pdfDocument.add(Image.getInstance(dir + "images/pdfHeader.png"));
			// for linux server dir+"images/pdfHeader.png"
		}
		catch (Exception ex)
		{
			
		}
		
	}
	
	// generate current directory of the servlet
	public String generateCurrentDirPath()
	{
		ServletContext ctx = this.getServletContext();
		String dir = ctx.getRealPath(this.toString());
		char[] dirarr = new char[dir.length()];
		dirarr = dir.toCharArray();
		int k;
		for (k = dir.length() - 1; k >= 0; k--)
			
			 if(dirarr[k]=='/') //for linux server break;
				 break;
			//if(dirarr[k] == '\\') // for window server
				
		
		String path = "";
		for (int o = 0; o <= k; o++)
			path += dirarr[o];
		
		return path;
	}
	
	public void generateDraftPDF(long accountNumber, String payTo,
	        float amount, String payableAt, String mode, String date,
	        String time, int draftNumber,Document document)
	{
		try
		{
			/*pdfDocument.add(new Paragraph("Draft application recipt",
			        FontFactory.getFont(FontFactory.HELVETICA, 25, Font.BOLD,
			                new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("Account Number : " + accountNumber,
			        FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,
			                new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("Application Date : " + date
			        + "                    Application Time  : " + time,
			        FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,
			                new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("In the favour of : " + payTo
			        + "                    Payable at  : " + payableAt,
			        FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,
			                new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("Ammount : " + amount, FontFactory
			        .getFont(FontFactory.HELVETICA, 22, Font.BOLD, new Color(0,
			                0, 0))));
			pdfDocument.add(new Paragraph("Draft Number : " + draftNumber,
			        FontFactory.getFont(FontFactory.HELVETICA, 22, Font.BOLD,
			                new Color(0, 0, 0))));
			                */
			String toPrint = ""+date;
			for(int i=0;i<68;i++)
				toPrint = "          "+toPrint;
			document.add(new Paragraph( toPrint                      ,FontFactory.getFont(FontFactory.HELVETICA, 19, Font.ITALIC,	new Color(0, 0, 0))));
			document.add(new Paragraph("                                                                                                                           "+time,FontFactory.getFont(FontFactory.HELVETICA, 18, Font.ITALIC,	new Color(0, 0, 0))));

			document.add(new Paragraph( "                                 "+ payTo                   /*Payable at  : payableAt"       */  ,FontFactory.getFont(FontFactory.HELVETICA, 18, Font.ITALIC,	new Color(0, 0, 0))));
			document.add(new Paragraph("                                                                                                                                                                                                                                                                            "+amount ,FontFactory.getFont(FontFactory.HELVETICA, 18, Font.ITALIC,	new Color(0, 0, 0))));
			document.add(new Paragraph("                                                                                                                                                             "+accountNumber ,FontFactory.getFont(FontFactory.HELVETICA, 22, Font.ITALIC,	new Color(0, 0, 0))));
			document.add(new Paragraph("                                                                                                                                                    "+payableAt ,FontFactory.getFont(FontFactory.HELVETICA, 22,Font.ITALIC,	new Color(0, 0, 0))));
			document.add(new Paragraph("                                                                                                                                                                                                                                                                                                             "+draftNumber ,FontFactory.getFont(FontFactory.HELVETICA, 22,Font.ITALIC,	new Color(0, 0, 0))));

			
		}
		catch (Exception dex)
		{
			// may be document exception
		}
	}
	
	// Generate header of Statement
	public void generateStatementHeader(long accountNumber, String startDate,
	        String endDate)
	{
		
		// Generate statement specific Details
		try
		{
			pdfDocument.add(new Paragraph("Statement", FontFactory.getFont(
			        FontFactory.HELVETICA, 25, Font.BOLD, new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("Account Number : " + accountNumber
			        + "          From : " + startDate + "     To : " + endDate,
			        FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,
			                new Color(0, 0, 0))));
		}
		catch (Exception dex)
		{
			// may be document exception
		}
	}
	
	public void generateStatementTable(int id, int transactionID, float amount,
	        String date, String time, float balance, String details)
	{
		try
		{
			t.addCell("" + id);
			t.addCell("" + transactionID);
			t.addCell("" + amount);
			t.addCell(date);
			t.addCell(time);
			t.addCell("" + balance);
			t.addCell(details);
			
		}
		catch (Exception ex)
		{
			
		}
	}
	
	public void initializeStatementTable()
	{
		try
		{
			// Create a table of 7 columns
			t = new Table(7);
			// set table border
			t.setBorderColor(new Color(0, 0, 0));
			// set padding
			t.setPadding(5);
			// set spacing
			t.setSpacing(2);
			// set border width
			t.setBorderWidth(2);
			t.setCellsFitPage(true);
			t.setUseVariableBorders(true);
			
			// adding cell to table
			t.addCell("Sr.");
			t.addCell("Transaction ID");
			t.addCell("Amount");
			t.addCell("Date");
			t.addCell("Time");
			t.addCell("balance");
			t.addCell("details");
			
		}
		catch (Exception ex)
		{
			// exception occurred
		}
	}
}