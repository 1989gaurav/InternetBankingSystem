

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
 * Servlet implementation class for Servlet: ChangeLimit
 *
 */
 public class ChangeLimit extends DBConnect implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
   
    private String userID;
    private int newLimit;
    private long accountNum;
	public ChangeLimit() {
		super();
		userID="";
		newLimit=0;
		accountNum = 0;
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//Response : JSON String : "{success:true/false}"
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String responsePacket = "{success:false}";
        String query="";
        ResultSet rset;
        PrintWriter pw = response.getWriter();
        //HttpSession variable to store the value of the current session to get values of the loginSession and Type
		HttpSession currentSession = CreateHttpSession(request);
		
		//Get the value from cookie and save in UserID
        userID = GetFromCookie("loginsession", currentSession);
        
        //Create connection from database to load values from database
        Connection cLimitConnection = getDBConnection();
        //Create Statement corresponding to connection to execute query
        Statement cLimitStatement = CreateQueryStatement( cLimitConnection );
		
        try
        {
        	if(userID.equals("F"))
	        {
	            //if UserID is F then User is not currently logged in
	            //redirect to the home page of the IBS
        		
	            response.sendRedirect("index.jsp");
	        }
        	
        	else	// the user is logged on with givenID
        	{
        		accountNum= Long.parseLong((request.getParameter("acnum_limit")));
        		newLimit = Integer.parseInt(request.getParameter("limit"));
        		
            	
            		// the accountnum and userID are mapped
            	{
            		query = "update accountDetails set draftLimit="+newLimit+" where accountNumber="+accountNum;
					
            		ExecuteQuery(query,cLimitStatement,false);
            		
            		responsePacket = "{success:true}";
            	}
            	
            		
        	}
        	   	  	
        }
        catch(Exception ex)
        {
			pw.print(ex.getMessage());
        	response.sendRedirect("index.jsp");
        }
        
        //Creating Writer Object
        
        pw.print(responsePacket);
}
 }