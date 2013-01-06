import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: Home
 * 
 */
public class Home extends DBConnect implements javax.servlet.Servlet{
	static final long	serialVersionUID	= 1L;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Home()
	{
		super();
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		// Creating Session Variable to check for authentic user
		try
		{
			HttpSession checkLoginStatus = CreateHttpSession(request);
			// Retrieving UserID if authentic user
			String user = GetFromCookie("loginsession", checkLoginStatus);
			if(user.equals("F"))
				// Unauthenticated User
				response.sendRedirect("index.jsp");
			else
			{
				// Authenticated User
				String htmlHead = "<html>    <head>        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />        <title id='title'>Internet Banking System - Homepage</title>         <link rel=\"stylesheet\" type=\"text/css\" href=\"css/ext-all.css\" />		<script type=\"text/javascript\" src=\"js/ext-base.js\"></script>        <script type=\"text/javascript\" src=\"js/ext-all.js\"></script>		<script type=\"text/javascript\" src=\"js/workspace.js\"></script>		<link rel=\"stylesheet\" type=\"text/css\" href=\"css/menus.css\" />		<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\">    </head>";
				htmlHead += "<body>		<img alt=\"Internet Banking System Logo\" src=\"images/header.png\" style=\"height: 95px; width: 956px\" />		<div style=\"width: 956px; background-color: #DDDDDD;\" id=\"quickBar\">		</div>		<div style=\"height: 500px; width: 950px\" id=\"container\">		</div>		<div id=\"west\">		</div>	<div id=\"grid\">		</div> <div id=\"gridStatement\">		</div> <div id=\"draftGrid\">		</div> <div id=\"transaction\">		</div> 	<div id=\"quickArea\" class=\"x-hidden x-window x-window-plain x-resizable-pinned\" style=\"position: absolute; z-index: 9003; visibility: visible; left: 225px; top: 220px; width: 500px; display: block;\"></div>		<div id=\"quickArea1\" class=\"x-hidden x-window x-window-plain x-resizable-pinned\" style=\"position: absolute; z-index: 9003; visibility: visible; left: 225px; top: 220px; width: 500px; display: block;\"></div>  <div id=\"quickArea2\" class=\"x-hidden x-window x-window-plain x-resizable-pinned\" style=\"position: absolute; z-index: 9003; visibility: visible; left: 225px; top: 220px; width: 500px; display: block;\"></div><div id=\"quickArea3\" class=\"x-hidden x-window x-window-plain x-resizable-pinned\" style=\"position: absolute; z-index: 9003; visibility: visible; left: 225px; top: 220px; width: 500px; display: block;\"></div>  <div id=\"quickArea4\" class=\"x-hidden x-window x-window-plain x-resizable-pinned\" style=\"position: absolute; z-index: 9003; visibility: visible; left: 225px; top: 220px; width: 500px; display: block;\"></div>	<div id=\"quickArea5\" class=\"x-hidden x-window x-window-plain x-resizable-pinned\" style=\"position: absolute; z-index: 9003; visibility: visible; left: 225px; top: 220px; width: 500px; display: block;\"></div>   <div id=\"quickArea6\" class=\"x-hidden x-window x-window-plain x-resizable-pinned\" style=\"position: absolute; z-index: 9003; visibility: visible; left: 225px; top: 220px; width: 500px; display: block;\"></div>  <div id=\"quickArea7\" class=\"x-hidden x-window x-window-plain x-resizable-pinned\" style=\"position: absolute; z-index: 9003; visibility: visible; left: 225px; top: 220px; width: 500px; display: block;\"></div>  <div id=\"quickArea8\" class=\"x-hidden x-window x-window-plain x-resizable-pinned\" style=\"position: absolute; z-index: 9003; visibility: visible; left: 225px; top: 220px; width: 500px; display: block;\"></div><div id=\"quickArea9\" class=\"x-hidden x-window x-window-plain x-resizable-pinned\" style=\"position: absolute; z-index: 9003; visibility: visible; left: 225px; top: 220px; width: 500px; display: block;\"></div><div id=\"quickArea10\" class=\"x-hidden x-window x-window-plain x-resizable-pinned\" style=\"position: absolute; z-index: 9003; visibility: visible; left: 225px; top: 220px; width: 500px; display: block;\"></div>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color=\"black\" face=Arial size=2><u><b>copyright © 2008-09 Teenovators, All rights reserved</b></u></font>	</body></html>";
				PrintWriter pw = response.getWriter();
				pw.println(htmlHead);
			}
		}
		catch (Exception ex)
		{
			response.sendRedirect("index.jsp");
		}
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
	}
}