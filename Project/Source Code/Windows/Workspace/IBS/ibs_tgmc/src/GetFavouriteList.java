import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: GetFavouriteList
 * 
 */
public class GetFavouriteList extends DBConnect implements
        javax.servlet.Servlet
{
	static final long	serialVersionUID	= 1L;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */

	private String	  query;
	private String	  ResponcePacket;
	private String	  Result	         = "[";
	// Statement
	public ResultSet	rset;	                  // Refers to the Result Set
	public Statement	stmt;	                  // Refers to the SQL Query
	// of the Query
	private int	      Userid;
	private String	  Username;
	
	public GetFavouriteList()
	{
		super();
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
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
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		try
		{
			HttpSession s = CreateHttpSession(request);
			// Retrieving UserID if authentic user
			String user = GetFromCookie("loginsession", s);
			if(user.equals("F"))
				// Unauthenticated User
				response.sendRedirect("index.jsp");
			else
			{
				// Authenticated User
				
				// String htmlHead = "<html> <head> <meta
				// http-equiv=\"Content-Type\" content=\"text/html;
				// charset=iso-8859-1\" /> <title id='title'>Internet Banking
				// System - Homepage</title> <link rel=\"stylesheet\"
				// type=\"text/css\" href=\"css/ext-all.css\" /> <script
				// type=\"text/javascript\" src=\"js/ext-base.js\"></script>
				// <script type=\"text/javascript\"
				// src=\"js/ext-all.js\"></script> <script
				// type=\"text/javascript\" src=\"js/workspace.js\"></script>
				// <link rel=\"stylesheet\" type=\"text/css\"
				// href=\"css/menus.css\" /> <link href=\"css/style.css\"
				// rel=\"stylesheet\" type=\"text/css\"> </head>";
				// htmlHead += "<body> <img alt=\"Internet Banking System Logo\"
				// src=\"images/header.png\" style=\"height: 95px; width:
				// 956px\" /> <div style=\"width: 956px; background-color:
				// #DDDDDD;\" id=\"quickBar\"> </div> <div style=\"height:
				// 500px; width: 950px\" id=\"container\"> </div> <div
				// id=\"west\"> </div> <div id=\"quickArea\" class=\"x-hidden
				// x-window x-window-plain x-resizable-pinned\"
				// style=\"position: absolute; z-index: 9003; visibility:
				// visible; left: 225px; top: 220px; width: 500px; display:
				// block;\"></div> <div id=\"quickArea1\" class=\"x-hidden
				// x-window x-window-plain x-resizable-pinned\"
				// style=\"position: absolute; z-index: 9003; visibility:
				// visible; left: 225px; top: 220px; width: 500px; display:
				// block;\"></div>
				// &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
				// color=\"black\" face=Arial size=2><u><b>copyright © 2008-09
				// Teenovators, All rights reserved</b></u></font>
				// </body></html>";
				
				Userid = Integer.parseInt(GetFromCookie("loginsession", s));
				
				query = "select * from ploginInfo where ploginInfo.userID="
				        + Userid;
				rset = ExecuteQuery(query, stmt, true);
				rset.next();
				{
					Username = rset.getString(1);
				}
				
				query = "select * from myFavorites where myFavorites.userID="
				        + Userid;
				rset = ExecuteQuery(query, stmt, true);
				
				/*
				 * now i need to make a packet in the form of
				 * [['Fav1'],['fav2'],....] the statement outside the loop
				 * creates[['fav1], the statement inside the loop creates
				 * ['fav2],['fav3'],... and last statement outside loop add the
				 * trailing bracket
				 */

				rset.next();
				Result = Result + "['" + rset.getString(3) + "']";
				while (rset.next())
					Result = Result + ",['" + rset.getString(3) + "']";
				Result = Result + "]";
				
				ResponcePacket = "{success:true,Data:'" + Result + "'}";
				PrintWriter pw = response.getWriter();
				pw.print(ResponcePacket);
			}
		}
		catch (Exception ex)
		{
			response.sendRedirect("index.jsp");
		}
	}
}