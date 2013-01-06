public class ChangePassword extends Database.DbAccess
{
	public ChangePassword()
	{
		super();
	}
	string UserId = SessionHandler.GetSessionVariable("UserId");
	printwriter pw;	
	if(!UserId.equals("false"))
	{
		string OldPassword = request.getParameter("OldPassword");
		string NewPassword = request.getParameter("NewPassword");
		string ConfirmPassword = request.getParameter("NewPasswordConfirmed");
		
		
		string query = "select password from loginInfo where loginInfo.UserId=UserId";
		SetQuery(query);
		result rst =  Search();
		rst.next();
		
		if(OldPassword.equals(rst.getString(1)))
		{//old password is same so allow to change the password
		
			if(NewPassword.equals(ConfirmPassword))
			{// both enterd passwords are same so change the password
			
				string query = "update loginInfo set loginInfo.password=NewPassword where loginInfo.UserId=UserId";
				SetQuery(query);
				Update();
			}
			else
			{
				pw.println(" Password are not matched correctly please re-enter")
				<redirect to changePassword page to try again>
			}
		}
		else
		{
			pw.println(" Your input password was incorrect please check your password ")
			<redirect to changePassword page to try again>
		}

		
	}
}