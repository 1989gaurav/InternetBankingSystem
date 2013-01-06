import javax.servlet.ServletContext;

public class HandleSession
{
	private ServletContext ctx;
	
	public HandleSession()
	{
		ctx = this.getServletContext();
	}
	
	public void SetSessionVariable(string name, value)
	{
		ctx.SetAttribute(name, value);
	}
	
	public string GetSessionVariable(string name)
	{
		string sessionValue = ctx.getAttribute(name).toString();
		
		if(sessionValue.equals("false"))
		{
			<redirect to login page and show an error that you was not loggedin prevously >
		}
		
		return sessionValue;
	}
	
}