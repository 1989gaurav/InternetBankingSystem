//The name of this class has been kept as PersonalLoginValidation, CorporateLoginValidation & AdminLoginValidation
public class Login extends Validate{
	
	private bollean isSession;
	private String UserID;
	private String Password;

	Login()
	{

	}

	AcceptLogin()
	{
		UserID = request.getparameter("UserName");
		Password = request.getparameter("Password");
		
		//Validate ValidateLogin = new Validate();
		isSession = CompareUsernamePassword(UserID, Password);
	}

}
