import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: SaveProfile
 *
 */
 public class SaveProfile extends DBConnect implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
       public SaveProfile() {
        super();
    }
       
       String UserID;
       String type;
       String query;
       
    
    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        try
        {
            //Creating session variable of current session for session validation
            HttpSession currentSession = CreateHttpSession(request);
            UserID = GetFromCookie("loginsession", currentSession);
            
            if( UserID.equals("F"))
            {
                //Currently not logged in so redirect to home page
                response.sendRedirect("index.jsp");
            }
            else
            {
                //If currently logged in
                //then get values from form and make entries in database
                
                //Getting value from the request
                String email = request.getParameter("userMail");
                String phone = request.getParameter("userPhone");
                String theme = "theme"+request.getParameter("theme");
                int securityCode = Integer.parseInt(request.getParameter("securityChoice"));
                
                String security = "";    //convert security code to security level
                
                if(securityCode==1)
                    security = "enabled";
                else
                    security = "disabled";
                
                //Making Database Connection
                Connection saveConnection = getDBConnection();    
                //Creating SQL Statement to execute Query
                Statement saveStatement = CreateQueryStatement(saveConnection);
                //ResultSet to save results of a search query
                ResultSet result = null;
                PrintWriter pw = response.getWriter();
                
                type = GetFromCookie("type", currentSession);
                if(type.equals("Personal"))
                {
                    //if type of account is Personal
                    //update myProfile table
                    query = "update myProfile set myProfile.mail='"+email+"',myProfile.myPhone='"+phone+"' where myProfile.userID="+UserID+"";
                    
                    result = ExecuteQuery(query,saveStatement,false);
                    //Update profileSettings table
                    query = "update profileSettings set profileSettings.securityCode='"+security+"',profileSettings.defaultTheme='"+theme+"' where profileSettings.userID="+UserID+"";
                    
                    result = ExecuteQuery(query,saveStatement,false);
                }
                
                
                String responsePacket = "{success:true}";
                
                //PrintWriter pw = response.getWriter();
                pw.println(responsePacket);
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

