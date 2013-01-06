
import java.io.*;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.*;


/**
 * Servlet implementation class for Servlet: GenerateStatement
 *
 */
 public class GenerateStatement extends GeneratePDF implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GenerateStatement() {
		super();
	}   	
	
	String UserID;
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//JSON String
	//"{'success':true/false, 'size' : <size>, 'data' : [{'id': 1, 'tid':'<transaction id>','type':'credit/debit', 'amount':'<amount>', 'date':'<date>','time':'<time>','balance':'<calculate balance till that date>'},{2nd record and so on}],'path':<path>}"
	public String CreateJsonString( int id, int transactionID, boolean isCredit, float amount, String date, String time, float balance, String details )
	{
		String packet = "";
		if( isCredit)
			packet = packet + "{'id':"+id+",'tid':'"+transactionID+"','type':'Credit','amount':'"+amount+"','date':'"+date+"','time':'"+time+"','balance':'"+balance+"','details':'"+details+"'}";
		else
			packet = packet + "{'id':"+id+",'tid':'"+transactionID+"','type':'Debit','amount':'"+amount+"','date':'"+date+"','time':'"+time+"','balance':'"+balance+"','details':'"+details+"'}";

		return packet;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		try
		{
			//Create a session variable
			HttpSession generateSession = CreateHttpSession(request);
			//HttpSession summarySession = CreateHttpSession(request);
			//Get value of cookie for this session
			UserID = GetFromCookie("loginsession",generateSession);
			//UserID = GetFromCookie("loginsession", summarySession);
			
			if(UserID.equals("F"))
			{
				//User is not currently logged in
				response.sendRedirect("index.jsp");
			}
			else
			{
				//Get parameter from request
				String dateStart = request.getParameter("startdt");
				String dateEnd = request.getParameter("enddt");
				long accountNumber = Long.parseLong(request.getParameter("acStat"));
				
				//local variables
				int count = 1;					//To count the number of transaction according to query
				String responsePacket="";		//response packet to return json string
				int transactionID;
            	String details="";
				float amount=0;
            	String date;
            	String time;
            	float balance=0;
            	String query ="";
            	boolean isCredit=true;
            	boolean isFirst = true;
				
				//query to execute
				/*db2 => select * from (select transaction.date,transaction.time,transaction.transactionID,credit.details as cdetails,debit.details as ddetails credit.accountnumber as cnum,debit.accountnumber as
						 dnum,credit.amount as camount,debit.amount as damount from debit full
						  join transaction on transaction.transactionID=debit.transactionID full join credit
						   on credit.transactionID=transaction.transactionID order by date) where cnum
						 =accountNumber or dnum=accountNumber and date>=dateStart and date<=dateEnd
				*/
            	
            	//generating path by concatenating current dir+contet+userID.pdf
            	
            	//current dir
        		String dir = generateCurrentDirPath();
        		String path= dir+"content\\"+accountNumber+".pdf";		//for window server
        		//String path = dir+"content/"+accountNumber+".pdf";	//for linux server
        		//Initializing a document of given specification
            	pdfDocument = new Document(PageSize.A3, 50, 50, 50, 50);
    			//Initializing pdfWriter to write the document 
            	PdfWriter writer = PdfWriter.getInstance(pdfDocument,new FileOutputStream(path));
    			//open the document to write and document is protected variable of GeneratePDF
            	pdfDocument.open();
            	//Write common header for all files to the pdf
    			generateCommonHeader(dir);
    			//Write Statement specific details
    			generateStatementHeader(accountNumber, dateStart, dateEnd);
    			
    					
		
            	//Get connection from database pool
				Connection statementConnection = getDBConnection();
				//Create a connection statement
				Statement statementStatement = CreateQueryStatement(statementConnection);
				ResultSet statementResult ;
			
				
				query = "select balance from accountDetails where accountDetails.accountNumber="+accountNumber;
				//pw.println(query);
				statementResult = ExecuteQuery(query,statementStatement,true);
				
				
				if(statementResult.next())
				{
					balance = statementResult.getFloat("balance");
				}
				
			
				
				query = "select * from (select transaction.date,transaction.time,transaction.transactionID,credit.details as cdetails,debit.details as ddetails,credit.accountnumber as cnum,debit.accountnumber as dnum,credit.amount as camount,debit.amount as damount from debit full join transaction on transaction.transactionID=debit.transactionID full join credit on credit.transactionID=transaction.transactionID order by date desc) where cnum="+accountNumber+" or dnum="+accountNumber+" and date>='"+dateStart+"' and date<='"+dateEnd+"'";
				//pw.println(query);
				statementResult = ExecuteQuery(query,statementStatement,true);
                
                while(statementResult.next())
                {
                	date = statementResult.getDate("date")+"";
					time = statementResult.getTime("time")+"";
					transactionID = statementResult.getInt("transactionID");
						
                	if(  statementResult.getLong("cnum") == accountNumber)
                	{
						//credit account number is the one which we need so this will be a credit operation
						//so flag isCredit will be true
						isCredit = true;
                		amount = statementResult.getFloat("camount");
                		balance = balance-amount;
                		details = statementResult.getString("cdetails");
        				//pw.println("credit hain bhaiyya");

                	}
					else if( statementResult.getLong("dnum") == accountNumber )
					{
						//debit account number is the one which we need so this will be a debit operation
						//so flag isCredit will be false
						isCredit = false;
						amount = statementResult.getFloat("damount");
						balance = balance+amount;
						details = statementResult.getString("ddetails");
						//pw.println("debit hain bhaiyya");
						
					}
					
                	if(isFirst)
                	{
                		responsePacket = responsePacket + CreateJsonString(count,transactionID,isCredit,amount,date,time,balance,details);
                		isFirst = false;
                		//initialise tabe according to statement specification
            			initializeStatementTable();
            			//make entries in the table
            			generateStatementTable(count, transactionID,amount, date, time,balance,details);
                	}
                	else
                	{
                		responsePacket = responsePacket +","+ CreateJsonString(count,transactionID,isCredit,amount,date,time,balance,details);
                		//make entries in the table
            			generateStatementTable(count, transactionID,amount, date, time,balance,details);
                	
                	}
					count++;
					
                }
                
                if( count==1 )
                {
                    //count==1 means no account found
                    responsePacket = "{'success':false,'size':0}";
                }
                else
                {
                    //count-1 accounts found
                    //adding specification in the end and in starting of responsepacket
                    responsePacket = "{'success':true,'size':"+(count-1)+",'data':["+responsePacket;
                    responsePacket = responsePacket+"],'path':'content/"+UserID+".pdf'}";
                
                }
                
                pdfDocument.add(t);
                pdfDocument.close();
               
                pw.println(responsePacket);
			}
		}
		catch(SQLException ex)
		{
			//Sql exception
			//pw.println("SQL error occurred");
		}
		catch(DocumentException ex)
		{
			//ex.printStackTrace();
			//pw.println("Document exception");
			
		}
		catch(Exception ex)
		{
			//pw.println("Some exception");
			//pw.println(ex.getMessage());
			response.sendRedirect("index.jsp");
		}
	}   	  	    
}