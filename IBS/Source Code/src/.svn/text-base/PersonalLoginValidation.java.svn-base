

import java.io.*;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.*;

/**
 * Servlet implementation class for Servlet: PersonalLoginValidation
 *
 */
 public class PersonalLoginValidation extends DBConnect implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
   private String UserID;	//contains the UserID 
   private String Password;   //contains the password
   private boolean isSession;	// The Session variable
   private int count;
   private int choice;			// Type of Customer : Personal, Corporate or Administrator
   
	public PersonalLoginValidation() {
		super();
		UserID = "";
		Password = "";
		isSession = false;
		count = 0;
		choice = 0;
	}   	
	
	public void UpdateBlockCount(Statement s)
	{
		String query = "";
		count++;
		//Incrementing the count by 1
		if(choice == 1)
			query="update ploginInfo set ploginInfo.blockstatus="+count+" where ploginInfo.userName='"+UserID+"'";
		else if(choice == 2)
			query="update cloginInfo set cloginInfo.blockstatus="+count+" where cloginInfo.userName='"+UserID+"'";
		ExecuteQuery(query,s,false);
	}
	
	public int GetBlockCount(Statement s)
	{
		count = 0;
		String query = "";
		if(choice == 1)
			query = "select * from ploginInfo where ploginInfo.userName='"+UserID+"'";
		else if(choice == 2)
			query = "select * from cloginInfo where cloginInfo.userName='"+UserID+"'";
		ResultSet blockCount = ExecuteQuery(query,s,true);
		try
		{
			if(blockCount.next())
			{
				//Invalid Password
				//Retrieving the block count
				count = blockCount.getInt("blockstatus");				
			}
		}
		catch(SQLException ex)
		{
			//Error Condition
		}
		return count;
	}
	
	public String CreateJSONPacket(boolean validateStatus, Statement s, boolean isAlreadyBlocked)
	{
		String packet = "";
		if(validateStatus)
			packet = "{success:true,data:'"+UserID+"'}";
		else
		{
			packet = "{success:false,data:"+GetBlockCount(s)+"}";
			if(!isAlreadyBlocked)
				UpdateBlockCount(s);
		}
		return packet;
	}
	
	public void ResetBlockCount(Statement s) // It resets the block count on correct login before count < 3
	{
		String query = "";
		if(choice == 1)
			query = "update ploginInfo set ploginInfo.blockstatus=0 where ploginInfo.userName='"+UserID+"'";
		else if(choice == 2)
			query = "update cloginInfo set cloginInfo.blockstatus=0 where cloginInfo.userName='"+UserID+"'";
		ExecuteQuery(query,s,false);
	}
	
	
	
	/*boolean CompareUsernamePassword(String UserName, String Password)	// Checks whether Username & Password are valid or not
	{
		
	}*/
	
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
		
		//Retrieving Username & Password entered in the form
		UserID = request.getParameter("puser");
		Password = request.getParameter("ppass");
		choice = Integer.parseInt(request.getParameter("choice"));
		//Making Database Connection
		Connection loginConnection = getDBConnection();	
		//Creating SQL Statement to execute Query
		Statement loginStatement = CreateQueryStatement(loginConnection);
		//Building Query
		String query="";
		if(choice == 1)
			query="select * from ploginInfo where ploginInfo.userName='"+UserID+"' and ploginInfo.password='"+Password+"'";
		else if(choice == 2)
			query="select * from cloginInfo where cloginInfo.userName='"+UserID+"' and cloginInfo.password='"+Password+"'";
		//Execute Query
		ResultSet validate = ExecuteQuery(query,loginStatement,true);
		//Taking corresponding operation
		String responsePacket = "";
		//Creating HttpSession Variable for Session Validation
		HttpSession loginSession = CreateHttpSession(request);
		
		try
		{
			if(validate.next())
			{
				//Valid User
				int userNum = validate.getInt("userID");
				int blockValue = GetBlockCount(loginStatement);
				if(blockValue < 3)
				{
					responsePacket = CreateJSONPacket(true,loginStatement,false); // creating JSON Packet to send back data
					ResetBlockCount(loginStatement); // Resetting the number of invalid attempts
					loginSession = AddToCookie("loginsession",""+userNum,loginSession);  // Applying Session Settings
					loginSession = AddToCookie("type","Personal",loginSession);  // Saving to session the type of customer
				}
				else
				{
					responsePacket = CreateJSONPacket(false,loginStatement,true); // creating corresponding JSON Packet
					loginSession = AddToCookie("loginsession","F",loginSession); // Applying Session Settings
				}
			}
			else
			{
				//Invalid User
				responsePacket = CreateJSONPacket(false,loginStatement,false); // creating corresponding JSON Packet
				loginSession = AddToCookie("loginsession","F",loginSession); // Applying Session Settings
			}
		}
		catch(SQLException ex)
		{
			//Error Condition
		}
		
		//Creating Writer Object
		PrintWriter pw = response.getWriter();
		pw.print(responsePacket);
	}   	  	    
}