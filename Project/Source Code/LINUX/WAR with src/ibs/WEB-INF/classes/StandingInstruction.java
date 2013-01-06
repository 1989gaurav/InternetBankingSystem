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
 * Servlet implementation class for Servlet: StandingInstruction
 * 
 */
public class StandingInstruction extends DBConnect implements
        javax.servlet.Servlet
{
	static final long	serialVersionUID	= 1L;
	
	private long	  acFrom;
	private long	  acTo;
	private float	  amount;
	private int	      date_instr;	              // Date at which instruction
	// is to be processed
	private String	  time_instr;
	private String	  typeInstruc;
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 * 
	 * "type_inst" - Type of instruction "account_from_inst" - A/c from
	 * "account_to_inst" - A/c to "amount_instr" - Amount "date_instr" - Date at
	 * which instruction is to be processed (it is an integer)
	 * 
	 * "time_instr" - Time (monthly, yearly, quarterly, etc)
	 */
	private String	  userID;
	
	public StandingInstruction()
	{
		super();
		typeInstruc = "";
		acFrom = 0;
		acTo = 0;
		amount = 0;
		date_instr = 0;
		time_instr = "";
		userID = "";
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	// response : JSON String "{success: true/false}"
	
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
	
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String responsePacket = "";
		String query = "";
		ResultSet rset;
		
		PrintWriter pw = response.getWriter();
		
		// HttpSession variable to store the value of the current session to get
		// values of the loginSession and Type
		HttpSession currentSession = CreateHttpSession(request);
		
		// Get the value from cookie and save in UserID
		userID = GetFromCookie("loginsession", currentSession);
		
		// Create connection from database to load values from database
		Connection draftStatusConnection = getDBConnection();
		// Create Statement corresponding to connection to execute query
		Statement draftStatusStatement = CreateQueryStatement(draftStatusConnection);
		
		try
		{
			if(userID.equals("F"))
				response.sendRedirect("index.jsp");
			else
			// the user is logged on with givenID
			{
				typeInstruc = request.getParameter("type_inst");
				
				acFrom = Long.parseLong(request
				        .getParameter("account_from_inst"));
				
				acTo = Long.parseLong(request.getParameter("account_to_inst"));
				
				amount = Float.parseFloat(request.getParameter("amount_instr"));
				
				date_instr = Integer.parseInt(request
				        .getParameter("date_instr"));
				time_instr = request.getParameter("time_instr");
				
				// create table standingInstruction(accountFrom bigint,accountTo
				// bigint,typeSI varchar(20),timeServed varchar(20),dateServed
				// varchar(10),amount float)
				
				query = "insert into standingInstruction values(" + acFrom
				        + "," + acTo + ",'" + typeInstruc + "','" + time_instr
				        + "'," + date_instr + "," + amount + ")";
				// pw.println(query);
				rset = ExecuteQuery(query, draftStatusStatement, false);
				
				responsePacket = "{success:true}";
				// pw.println(responsePacket);
				
			}
		}
		catch (Exception ex)
		{
			response.sendRedirect("index.jsp");
		}
		
		// Creating Writer Object
		
		pw.print(responsePacket);
	}
}