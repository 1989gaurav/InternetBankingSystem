public class SetDefaultAccount extends Database.DbAccess
{
	public SetDefaultAccount()
	{
		super();
	}
	string UserId = GetSessionVariable("UserId");
		
	if(!UserId.equals("false"))
	{
		string AccounNumber = request.getParameter("AccountNumber");
		string query = " update myProfile set myProfile.DefaultAccount=AccountNumber where myProfile.UserId=UserId";
		SetQuery(query);
		Update();
		
		printwriter pw;
		pw.println("Redirect to myProfile page Show message that account is successfully set as default account");
	}
}