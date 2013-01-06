import java.io.IOException;
import java.io.PrintWriter;

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
import java.util.Date;


/**
 * Servlet implementation class for Servlet: MyProfile
 *
 */
 public class MyProfile extends DBConnect implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
  
   private String UserID;    //UserId of user
   private String type;        //type of account
    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public MyProfile() {
        super();
        UserID = "";
       
    }
   
   
    //To create a JSONPacket String having all information about the profile of loogedin user
    public String CreateJSONPacket( Statement s)
    {
        String packet = "" ;
        String query = "";    //To save query string
       
        //default values of parameters
        String name = "";   
        String address = "";
        String mail = "";
        String phone = "";
        String llt = "";
        String lld = "";
        String theme = "";
        String security = "";
        String uname = "";
        
        if( type.equals("Personal"))
        {
            query = "select * from myProfile,ploginInfo,profileSettings where myProfile.userID="+UserID+" and ploginInfo.userID="+UserID+" and profileSettings.userID="+UserID;
        }
        else if(type.equals("Corporate"))
        {
            query = "select * from myProfile,ploginInfo,profileSettings where myProfile.userID="+UserID+" and ploginInfo.userID="+UserID+" and profileSettings.userID="+UserID;
        }
       
        ResultSet queryResult = ExecuteQuery(query,s,true);
       
        try
        {
            if( queryResult.next() )
            {
                // There is an entry of profile setting for corresponding
               
                //ploginInfo(userName varchar(20),password varchar(20),lastloginDate date,lastloginTime time,blockstatus int,userID int,defaultAccount bigint)
                //myProfile(userID int,myName varchar(25),myAddress varchar(50),myPhone varchar(15),mail varchar(30))
                //profileSettings(userID int,securityCode varchar(20),defaultTheme varchar(20))
                //Set the values of all parameter
                name = queryResult.getString("myName");
                address = queryResult.getString("myAddress");
                mail = queryResult.getString("mail");
                phone = queryResult.getString("myPhone");
                uname = queryResult.getString("userName");
                lld = queryResult.getDate("lastloginDate").toString();
                llt = queryResult.getTime("lastloginTime").toString();
                theme = queryResult.getString("defaultTheme");
                security = queryResult.getString("securityCode");
               
                //Create JSON packet String having success: true/false,name: '...' , address: '...', mail: '.....', phone:'....', user: 'UserID', llt: 'last login time(in string form)', lld:'last login date(in string form)', theme:'....', security:'Enabled/Disabled'}
                packet = "{success:true,name:'"+name+"',address:'"+address+"',mail:'"+mail+"',phone:'"+phone+"',user:'"+uname+"',llt:'"+llt+"',lld:'"+lld+"',theme:'"+theme+"',security:'"+security+ "'}";
            }
            else
            {
                // There is no profile settings for corresponding user
                // Either login is first time or may some error
                packet = "{success:false}";
           
            }
        }
        catch( SQLException ex)
        {
            //sql exception occurred
        }
       
        return packet;
    }
       
   
   
    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
       try
       {
	        //HttpSession variable to store the value of the current session to get values of the loginSession and Type
	        HttpSession currentSession = CreateHttpSession(request);
	        //Get the value from cookie and save in UserID
	        UserID = GetFromCookie("loginsession", currentSession);
	       
	        //Creating Writer Object
	        PrintWriter pw = response.getWriter();
	       
	       
	           
	       
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
	            //Create connection from database to load values from database
	            Connection profileConnection = getDBConnection();
	            //Create Statement corresponding to connection to execute query
	            Statement profileStatement = CreateQueryStatement( profileConnection );
	           
	            //A ResponsePacket to store the return values
	            String responsePacket = "";
	           
	            //Storing JSONPacket String in responsePacket having all information about profile
	            responsePacket = CreateJSONPacket( profileStatement);
	           
	            pw.print(responsePacket);
	        }
       }
       catch(Exception ex)
       {
    	   response.sendRedirect("index.jsp");
       }
    }     
   
    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
       
       
       
       
    }                
}