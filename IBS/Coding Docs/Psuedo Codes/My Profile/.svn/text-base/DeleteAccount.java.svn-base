public class DeleteAccount extends Database.DbAccess
{
	public DeleteAccount()
	{
		super();
	}	
	string UserId = SessionHandler.GetSessionVariable("UserId");
		
	if(!UserId.equals("false"))
	{
		string AccountNumber = request.getParameter("AccountName");
		
		string query = " delete * from accountMapping where accountMapping.AccountMunber=AccountNumber";
		SetQuery(query);
		Update();	
		
		printwriter pw;
		pw.println("Redirect to myProfile page Show message that account is successfully deleted from this userId");
			
	}
}