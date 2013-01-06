

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Calendar;
/**
 * Servlet implementation class for Servlet: DBConnect
 *
 */
 public class DBConnect extends Common implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
   private DataSource dsource;	// Refers to the DataSource of the Database
   private Connection conn;		// Refers to the connection variable
   public Statement stmt;		// Refers to the SQL Query Statement
   public ResultSet rset;		// Refers to the Result Set of the Query 
   private String URL;
	public DBConnect() {
		super();
		dsource = null;
		conn = null;
		stmt = null;
		rset = null;
		URL = "java:comp/env/jdbc/MyDataSource";
	}   	
	
	public Connection getDBConnection()	//Create the connection to DatabasePool & return the connection variable
	{
		try
		{
			InitialContext context = new InitialContext ();
			dsource = (DataSource) context.lookup(URL);
			conn = dsource.getConnection();
		}
		catch(Exception ex)
		{
			//Error Condition
		}
		return(conn);
	}
	
	public Statement CreateQueryStatement(Connection c)	// Creates the SQL Statement variable w.r.t to the Connection variable 
	{
		try
		{
			stmt = c.createStatement();
		}
		catch(SQLException ex)
		{
			//Error Condition
		}
		return(stmt);
	}
	public ResultSet ExecuteQuery(String query,Statement s,boolean SearchType)	// Returns the Result of the Query Executed
	{
		// SearchType is a variable which should be set to true if query is of search type
		// and should be set to false if query is of update type i.e. insert, update or delete.
		try
		{
			if(SearchType)
				rset = s.executeQuery(query);
			else
				s.executeUpdate(query);
		}
		catch(SQLException ex)
		{
			//Error Condition
		}
		return(rset);
	}
	
	public String getCurrentDate()
	{
		//calculate current date and current time
		int yyyy,mm,dd;
		Calendar c = Calendar.getInstance();
		yyyy = c.get(Calendar.YEAR);
		mm = c.get(Calendar.MONTH) +1;
		dd = c.get(Calendar.DAY_OF_MONTH);
		String date = dd+"."+mm+"."+yyyy ;
		
		return date;
		
	}
	
	public String getCurrentTime()
	{
		int min,hr,sec;
		Calendar c = Calendar.getInstance();
		hr = c.get(Calendar.HOUR_OF_DAY);
		min = c.get(Calendar.MINUTE);
		sec = c.get(Calendar.SECOND);
		String time = hr+":"+min+":"+sec;
		
		return time;
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