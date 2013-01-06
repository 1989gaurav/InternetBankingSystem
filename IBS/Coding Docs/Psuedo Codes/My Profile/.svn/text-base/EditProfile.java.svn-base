public class EditProfile extends Database.DbAccess
{
	
	public EditProfile()
	{
		super();
	}
	string UserId = SessionHandler.GetSessionVariable("UserId");
	
	if(!UserId.equals("false"))
	{
		//get all values from the HTML form which was field previously
		string firstName = request.getparameter("FirstName");
		string lastName = request.getParameter("LastName");
		
		string query = " update myProfile set myProfile.FirstName="+firstName+" myProfile.LastName="+lastName+" where myProfile.UserId=UserId";
		SetQuery(query);
		Update();
	
		printWriter pw;
		pw.println(" Show my profile page with some succesfule completed message ");
	}
	else
	{
		Redirect to login page with an error that you was not logged in
	}
	
}