<html>
	<core module and quick links which are common to all pages>
	
	<%
	import DbAccess;
	
	HandleSession SessionHandler = new HandleSession();
	
	string UserId = SessionHandler.GetSessionVariable("UserId");
	if(!UserId.equals("false"))
	{
	%>
		<form name="ChangePassword" action="ChangePassword.java">
		Old Password : <input type="password" name="OldPassword"><br>
		New Password : <input type="password" name="NewPassword"><br>
		ConfirmPassword : <input type="password" name="NewPasswordConfirmed"><br>
		
		<input type="Submit" value="Change Password">
		</form>
	<%
	}
	else
	{
	 redirect to ==>> "You are not logged in";
	}
	%>
	
</html>