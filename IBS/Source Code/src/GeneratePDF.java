

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.io.FileOutputStream;

//import com.lowagie.text.Cell;
//import com.lowagie.text.Chapter;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Table;
import com.lowagie.text.Paragraph;

/**
 * Servlet implementation class for Servlet: GeneratePDF
 *
 */
 public class GeneratePDF extends Payment implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GeneratePDF() {
		super();
	} 
	
	public GeneratePDF( Document document) 
	{
		super();
		pdfDocument = document;
		
	}
	
	protected Document pdfDocument;			//To hold the current document variable
	protected Table t;
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//Generate common header for all pdfs
	public void generateCommonHeader(String dir)
	{
		try
		{
			
			pdfDocument.add(Image.getInstance(dir+"images\\pdfHeader.png"));
			//for linux server dir+"images/pdfHeader.png"
		}
		catch(Exception ex)
		{
		
		}
		
	}
	
	//generate current directory of the servlet
	public String generateCurrentDirPath()
	{
		ServletContext ctx=this.getServletContext();
		String dir=ctx.getRealPath(this.toString());
		char[] dirarr = new char[dir.length()];
		dirarr=dir.toCharArray();
		int k;
		for(k=dir.length()-1;k>=0;k--)
		{
			/*if(dirarr[k]=='/')	//for linux server
				break;*/
			if(dirarr[k]=='\\')		//for window server
				break;
		}
		
		String path = "";
		for(int o=0;o<=k;o++)
			path+=dirarr[o];
		
		
		return path;
	}
	
	//Generate header of Statement 
	public void generateStatementHeader( long accountNumber, String startDate, String endDate)
	{
		
		//Generate statement specific Details
		try
		{
			pdfDocument.add(new Paragraph("Statement",FontFactory.getFont(FontFactory.HELVETICA, 25, Font.BOLD,	new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("Account Number : "+accountNumber+"          From : "+startDate+"     To : "+endDate,FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,	new Color(0, 0, 0))));
		}
		catch(Exception dex)
		{
			//may be document exception
		}
	}
	
	public void initializeStatementTable()
	{
		try
		{
			//Create a table of 7 columns
			t = new Table(7);
			//set table border
			t.setBorderColor(new Color(0, 0, 0));
			//set padding
			t.setPadding(5);
			//set spacing
			t.setSpacing(2);
			//set border width
			t.setBorderWidth(2);
			t.setCellsFitPage(true);
			t.setUseVariableBorders(true);
			
			//adding cell to table
			t.addCell("Sr.");
			t.addCell("Transaction ID");
			t.addCell("Amount");
			t.addCell("Date");
			t.addCell("Time");
			t.addCell("balance");
			t.addCell("details");
			
		}
		catch(Exception ex)
		{
			//exception occurred
		}
	}
	public void generateStatementTable(int id, int transactionID, float amount, String date, String time, float balance, String details)
	{
		try
		{
			t.addCell(""+id);
			t.addCell(""+transactionID);
			t.addCell(""+amount);
			t.addCell(date);
			t.addCell(time);
			t.addCell(""+balance);
			t.addCell(details);
			
		}
		catch(Exception ex)
		{
		
		}
	}
	
	public void generateDraftPDF(long accountNumber, String payTo,float amount,String payableAt,String mode,String date,String time,int draftNumber)
	{
		try
		{
			pdfDocument.add(new Paragraph("Draft application recipt",FontFactory.getFont(FontFactory.HELVETICA, 25, Font.BOLD,	new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("Account Number : "+accountNumber         ,FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,	new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("Application Date : "+date+"                    Application Time  : "+time         ,FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,	new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("In the favour of : "+payTo+"                    Payable at  : "+payableAt         ,FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,	new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("Ammount : "+amount                    ,FontFactory.getFont(FontFactory.HELVETICA, 22, Font.BOLD,	new Color(0, 0, 0))));
			pdfDocument.add(new Paragraph("Draft Number : "+draftNumber                    ,FontFactory.getFont(FontFactory.HELVETICA, 22, Font.BOLD,	new Color(0, 0, 0))));


		}
		catch(Exception dex)
		{
			//may be document exception
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}