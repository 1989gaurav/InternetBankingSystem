

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: ChangePassword
 *
 */
 public class ChangePassword extends DBConnect implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
   	private String UserID;
   	private String type;
   	private String currentPass;
   	private String newPass ;
   	private String cPass_trans ;
   	private String nPass_trans ; 
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ChangePassword() {
		super();
		UserID="";
		type = "";
		currentPass="";
		newPass = "";
		cPass_trans="";
		nPass_trans="";
	}   	
	
	public String CreateJsonPacket(Statement s,PrintWriter pw1)
	{
		
		String query="",packet="";
		if(type.equals("Personal"))
		{
			query = "select * from ploginInfo where ploginInfo.userID="+UserID;
		}
		else if(type.equals("Corporate"))
		{
			query = "select * from cloginInfo where cloginInfo.userID="+UserID;
		}
				
		
		ResultSet queryResult = ExecuteQuery(query,s,true);
		
		try
		{
			if(queryResult.next())
			{
				if(currentPass==""&&newPass=="")	// profile password field is empty => transaction pass case 
				{
					if(queryResult.getString("transactionPass").equals(cPass_trans))
					{
						query = "update ploginInfo set ploginInfo.transactionPass = '"+nPass_trans+"' where ploginInfo.userID="+UserID;
						ExecuteQuery(query,s,false);	// see do we need to store the value its returning?
						packet ="{success:true}";
					}
					/*else if(cPass_trans==queryResult.getString("transactionPass")&&type.equals("Corporate"))
					{
						query = "update cloginInfo set cloginInfo.transactionPass = '"+nPass_trans+"' where cloginInfo.userID="+UserID;
					}*/
					else	// wrong current transaction pass
					{
						packet="{success:false}";
					}
					
				}
									
				else	// case of profile's pass 
				{
					
					if(queryResult.getString("password").equals(currentPass))
					{
						query = "update ploginInfo set ploginInfo.password = '"+newPass+"' where ploginInfo.userID="+UserID;
						ExecuteQuery(query,s,false);	// see do we need to store the value its returning?
						packet ="{success:true}";
					}
					/*else if(currentPass==queryResult.getString("password")&& type.equals("Corporate"))
					{
						query = "update cloginInfo set cloginInfo.password = '"+newPass+"' where cloginInfo.userID="+UserID;
					}*/
					else	// wrong current profile pass
					{
						packet="{success:false}";
					}
					
				}
					
				
			}
			else
				packet="{success:false}";
		}
		catch(SQLException ex)
		{
			//exception condition
		}
		return packet;
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
		
		//A ResponsePacket to store the return values
		
        String responsePacket = "";
        PrintWriter pw = response.getWriter();
		
        //HttpSession variable to store the value of the current session to get values of the loginSession and Type
		HttpSession currentSession = CreateHttpSession(request);
				
		//Get the value from cookie and save in UserID
        UserID = GetFromCookie("loginsession", currentSession);
        
        //Create connection from database to load values from database
        Connection cpConnection = getDBConnection();
        //Create Statement corresponding to connection to execute query
        Statement cpStatement = CreateQueryStatement( cpConnection );
		
		try
		{
			currentPass = request.getParameter("cpass");	// profile current password
			newPass = request.getParameter("npass");		// profile's new password
			cPass_trans = request.getParameter("cpass_t");	// transaction current pass
			nPass_trans = request.getParameter("npass_t");	// transaction new pass
			
      
	        if(UserID.equals("F"))
	        {
	            //if UserID is F then User is not currently logged in
	            //redirect to the home page of the IBS
	            response.sendRedirect("index.jsp");
	        }
	        
	        else
	        {
	            //pw.println("Logged in");
	            //if UserID is not equal to F then User is logged in with given ID
	           
	            type = GetFromCookie("type", currentSession);
	            
	            //Storing JSONPacket String in responsePacket having all information about profile
	            responsePacket = CreateJsonPacket(cpStatement,pw);
	           
	           
	        }
		}
		catch(Exception ex)
		{
			if(currentPass!="" && newPass!="")			// case of profile's password
			{
				
				if(UserID.equals("F"))	
		        {
		            //if UserID is F then User is not currently logged in
		            //redirect to the home page of the IBS
		            response.sendRedirect("index.jsp");
		        }
		        
		        else
		        {
		            //pw.println("Logged in");
		            //if UserID is not equal to F then User is logged in with given ID
		           
		            type = GetFromCookie("type", currentSession);
		           
		            //Storing JSONPacket String in responsePacket having all information about profile
		            responsePacket = CreateJsonPacket(cpStatement,pw);
		        }
			}
			else		// neither of cases in profile or transaction pass 
				responsePacket = "{success:false}";
					
		}
		
		//Creating Writer Object
        //PrintWriter pw = response.getWriter();
        pw.print(responsePacket);
	}   	  	    
}