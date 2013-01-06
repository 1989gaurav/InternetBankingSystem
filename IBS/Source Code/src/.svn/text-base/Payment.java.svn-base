

import java.io.*;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.*;

/**
 * Servlet implementation class for Servlet: Payment
 *
 */
 public class Payment extends DBConnect implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Payment() {
		super();
	}  
	
	private String query="";
	//Statement variable to store statement solely for payment
	//in derived class it can be set and there it may be used as a normal statement
	
	
	//credit a particular amount from accountNumber
	public boolean DebitFromAccount(Statement paymentStatement, long accountNumber, float amount, String details, int transactionId )
	{
		//check for balance of account number
		query = "select balance from accountDetails where accountDetails.accountNumber="+accountNumber+" and accountDetails.balance>="+amount;
		ResultSet creditResult = ExecuteQuery(query, paymentStatement, true);
		
		try
		{
			if( creditResult.next())
			{
				//balance of account number is greater than amount to be credited
				
				//make entry in credit table
				query = "insert into debit values("+accountNumber+","+amount+","+transactionId+",'"+details+"')";
				ExecuteQuery(query, paymentStatement, false);
				
				//update balance in accountDetails table
				query = "update accountDetails set accountDetails.balance=accountDetails.balance-"+amount+" where accountDetails.accountNumber="+accountNumber;
				ExecuteQuery(query, paymentStatement, false);

				return true;
			}
			else
			{
				//balance is less than amount or account does not exist
				return false;
			}
		}
		catch( SQLException ex)
		{
			//sql exception occured
			return false;
		}
		
	}
	
	//debit a particular amount to accountNumber
	public boolean CreditFromAccount(Statement paymentStatement, long accountNumber, float amount, String details, int transactionId  )
	{
		try
		{
			//make entry in debit table
			query = "insert into credit values("+accountNumber+","+amount+","+transactionId+",'"+details+"')";
			ExecuteQuery(query, paymentStatement, false);
		
			//	update balance in accountDetails table
			query = "update accountDetails set accountDetails.balance=accountDetails.balance+"+amount+" where accountDetails.accountNumber="+accountNumber;
			ExecuteQuery(query, paymentStatement, false);

			return true;
		}
		catch( Exception ex)
		{
			//exception occurred
			return false;
		}
	}
	
	//Get count of particular field
	public int getCount( String field, Statement paymentStatement,PrintWriter pw)
	{
		int value;
		
		query = "select value from count where count.cnt='"+field+"'";
		ResultSet result = ExecuteQuery(query, paymentStatement, true);
		
		try
		{
			if(result.next())
			{	
				return result.getInt("value");
			
			}
			else
			{
				return 0;
			}
		}
		catch( SQLException ex)
		{
			//sql exceeption occurred
			return 0;
		}
	}
	
	public void updateCountByOne( String field, Statement paymentStatement,PrintWriter pw)
	{
		query = "update count set count.value=count.value+1 where count.cnt='"+field+"'"; 
		ExecuteQuery(query, paymentStatement, false);
	}
	
	
	public boolean makeTransactionEntries(int transactionID, String date, String time, Statement paymentStatement,PrintWriter pw)
	{
		query = "insert into transaction values("+transactionID+",'"+time+"','"+date+"')";
		ExecuteQuery(query, paymentStatement, false);
		return true;
	}
	
	public boolean VarifyTransactionPassword(Statement paymentStatement, String enterdPassword, String UserID, boolean isPersonal,PrintWriter pw)
	{
		//varify transaction password
		
		//if user is personal or corporate user
		if(isPersonal)
			query = "select * from ploginInfo where ploginInfo.userID="+UserID+" and ploginInfo.transactionPass='"+enterdPassword+"'";
		else
			query = "select * from cloginInfo where cloginInfo.userID="+UserID+" and cloginInfo.transactionPass='"+enterdPassword+"'";
		
		//pw.println("varify Transaction : "+query);
		ResultSet verifyResult = ExecuteQuery(query, paymentStatement, true);
		
		try
		{
			if(verifyResult.next())
			{
				//valid transaction password is matched
				return true;
			}
			else
			{
				//transaction password didn't match so wrong password return 0
				return false;
			}
		}
		catch(SQLException ex)
		{
			//exception occurred
			return false;
		}
	}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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