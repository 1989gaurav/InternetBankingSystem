public class AddAccount extends DbAccess
{
	import DbAccess;
	
		
	string UserId = GetSessionVariable("UserId");
		
	if(!UserId.equals("false"))
	{
		string AccountNumber = request.getParameter("AccountNumber");
		
		string query = " insert into accountMapping values(UserId,AccountNumber)";
		SetQuery(query);
		Insert();
		
		printwriter pw;
		pw.println("Redirect to myProfile page Show message that account is successfully mapped to this userId");
			
			
	}
}