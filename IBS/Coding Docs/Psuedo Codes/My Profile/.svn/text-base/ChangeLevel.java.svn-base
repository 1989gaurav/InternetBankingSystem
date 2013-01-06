public class ChangeLevel extends Database.DbAccess
{
	public ChangeLevel()
	{
		super();
	}	
	string UserId = SessionHandler.GetSessionVariable("UserId");
		
	if(!UserId.equals("false"))
	{
		string securityLevel = request.getParameter("level");
		
		//get default account n which these settings should be employed
		string query = "select DefaultAccont from myProfile where myProfile.UserId=UserId";
		SetQuery(query);
		result rst = Search();
		rst.next();
		string DefaultAccount = rst.getString(1);
		
		string query = " update accountSettings set accountSettings.securityCode=securityLevel where accountSettings.AccountNumber=DefaultAccount";
		SetQuery(query);
		Update();
		
		printwriter pw;
		pw.println("Redirect to myProfile page Show message that security level has been chnged for this account");
	}
}