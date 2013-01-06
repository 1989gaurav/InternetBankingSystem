public class AddLoanAccount extends Database.DbAccess
{
	public AddLoanAccount()
	{
		super();
	}
	
	string UserId = SessionHandler.GetSessionVariable("UserId");
		
	if(!UserId.equals("false"))
	{
		string AccountNumber = request.getParameter("LoanAccountNumber");
		
		string query = " insert into loanMapping values(UserId,LoanAccountNumber)";
		SetQuery(query);
		Insert();
		
		printwriter pw;
		
		pw.println("Redirect to myProfile page Show message that loan account is successfully mapped to this userId");
			
			
	}

}