<html>
	<core module and quick links which are common to all pages>
	
	<%
	import DbAccess;
	
	HandleSession SessionHandler = new HandleSession();
	
	string UserId = SessionHandler.GetSessionVariable("UserId");
	if(!UserId.equals("false"))
	{
		DnAccess DbOperation = new DbAccess();
		
		//get default account which was selected previously on that account
		string query = "select DefaultAccont from myProfile where myProfile.UserId=UserId";
		DbOperation.SetQuery(query);
		result rst = DbOperation.Search();
		rst.next();
		string DefaultAccount = rst.getString(1);
		
		// get Security code which was previously selected on default account
		query = " select securityCode from accountSettings where accountSettings.AccountNumber=DefaultAccount";
		DbOperation.SetQuery(query);
		rst  = DbOperation.Search();
		rst.next();
		string prevSettings = rst.getString(1);
	%>	
		<form name="ChangeLevel" action="ChangeLevel.java">
		// previously selected setting will be automatically checked which can be altered
		<input type="radio" name="level" value="Normal" <%if(prevSettings.equal("Normal")) checked %> > Normal
		<input type="radio" name="level" value="High" <%if(prevSettings.equal("High")) checked %> > High
		<input type="radio" name="level" value="Easy" <%if(prevSettings.equal("Easy")) checked %> > Easy
		<input type="radio" name="level" value="VeryHigh" <%if(prevSettings.equal("VeryHigh")) checked %> > VeryHigh
		
		<input type="Submit" value="Submit">
		</form>
	<%
	}
	else
	{
	 redirect to ==>> "You are not logged in";
	}
	%>
</html>