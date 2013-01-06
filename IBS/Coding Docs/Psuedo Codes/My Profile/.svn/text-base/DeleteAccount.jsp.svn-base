<html>
	<core module and quick links which are common to all pages>
	
	<%
	import DbAccess;
	
	HandleSession SessionHandler = new HandleSession();
	
	string UserId = SessionHandler.GetSessionVariable("UserId");
		
	if(!UserId.equals("false"))
	{%>
			<form name="DeleteAccount" action="DeleteAccount.java">
				<input type="text" name="AccountNumber">
				
				<input type="Submit" value="Submit">
				<input type="Reset" value="Reset">
					
			</form>
	<%
	}
	else
	{
	 redirect to ==>> "You are not logged in";
	}
	
	%>
	
</html>