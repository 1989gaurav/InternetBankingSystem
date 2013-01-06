import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: Common
 * 
 */
public class Common extends javax.servlet.http.HttpServlet implements
        javax.servlet.Servlet
{
	static final long	serialVersionUID	= 1L;
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Common()
	{
		super();
	}
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */

	public HttpSession AddToCookie(String param, String value, HttpSession s)
	{
		s.putValue(param, value);
		return s;
	}
	
	public HttpSession CreateHttpSession(HttpServletRequest r) // Create
	// Session
	// Variable to
	// store cookie
	{
		HttpSession session = null;
		session = r.getSession(true);
		return session;
	}
	
	
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
	}
	
	public String GetFromCookie(String param, HttpSession s)
	{
		return (s.getValue(param).toString());
	}
}