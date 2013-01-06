import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: DBConnect
 * 
 */
public class DBConnect extends Common implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	// of the Database
	private Connection	conn;	                  // Refers to the connection
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	private DataSource	dsource;	              // Refers to the DataSource
	// Statement
	public ResultSet	rset;	                  // Refers to the Result Set
	// variable
	public Statement	stmt;	                  // Refers to the SQL Query
	// of the Query
	private String	   URL;
	
	public DBConnect()
	{
		super();
		dsource = null;
		conn = null;
		stmt = null;
		rset = null;
		URL = "java:comp/env/jdbc/MyDataSource";
	}
	
	public Statement CreateQueryStatement(Connection c) // Creates the SQL
	// Statement variable
	// w.r.t to the
	// Connection variable
	{
		try
		{
			stmt = c.createStatement();
		}
		catch (SQLException ex)
		{
			// Error Condition
		}
		return (stmt);
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
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
	@Override
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}
	
	public ResultSet ExecuteQuery(String query, Statement s, boolean SearchType) // Returns
	// the
	// Result
	// of
	// the
	// Query
	// Executed
	{
		// SearchType is a variable which should be set to true if query is of
		// search type
		// and should be set to false if query is of update type i.e. insert,
		// update or delete.
		try
		{
			if(SearchType)
				rset = s.executeQuery(query);
			else
				s.executeUpdate(query);
		}
		catch (SQLException ex)
		{
			// Error Condition
		}
		return (rset);
	}
	
	public String getCurrentDate()
	{
		// calculate current date and current time
		int yyyy, mm, dd;
		Calendar c = Calendar.getInstance();
		yyyy = c.get(Calendar.YEAR);
		mm = c.get(Calendar.MONTH) + 1;
		dd = c.get(Calendar.DAY_OF_MONTH);
		
		String date = "";
		
		if(dd < 10)
			date = date + "0" + dd + ".";
		else
			date = date + dd + ".";
		
		if(mm < 10)
			date = date + "0" + mm + ".";
		else
			date = date + mm + ".";
		
		date = date + yyyy;
		
		return date;
		
	}
	
	public String getCurrentTime()
	{
		int min, hr, sec;
		Calendar c = Calendar.getInstance();
		hr = c.get(Calendar.HOUR_OF_DAY);
		min = c.get(Calendar.MINUTE);
		sec = c.get(Calendar.SECOND);
		
		String time = "";
		
		if(hr < 10)
			time = time + "0" + hr + ":";
		else
			time = time + hr + ":";
		
		if(min < 10)
			time = time + "0" + min + ":";
		else
			time = time + min + ":";
		
		if(sec < 10)
			time = time + "0" + sec;
		else
			time = time + sec;
		
		return time;
	}
	
	public Connection getDBConnection() // Create the connection to DatabasePool
	// & return the connection variable
	{
		try
		{
			InitialContext context = new InitialContext();
			dsource = (DataSource) context.lookup(URL);
			conn = dsource.getConnection();
		}
		catch (Exception ex)
		{
			// Error Condition
		}
		return (conn);
	}
}