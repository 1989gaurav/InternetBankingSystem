<html>
	<core module and quick links which are common to all pages>
	
	<%
	import DbAccess;
	
	HandleSession SessionHandler = new HandleSession();
	
	string UserId = SessionHandler.GetSessionVariable("UserId");
		
	if(!UserId.equals("false"))
	{
		ExecuteQuery DbOperation = new ExecuteQuery(databaseURL);
	
		string query = " select * from accountMapping where accountMapping.UserId=UserId";
		DbOperation.SetQuery(query);
		result rst = DbOperation.Search();
		
		query = " select DefaultAccount from myProfile where myProfile.UserId=UserId";
		DbOperation.SetQuery(query);
		result rstDefault = DbOperation.Search();
		rstDefault.next();
	%>
		<form name="SetDefaultAccount action="SetDefaultAccount.java"">
			
		// show all accounts which are mapped on this user id with a radio button and selected radio button will be the default account
	<%	while(rst.next)
		{
			// it is default account which was previously selected
			if((rst.getString(2)).equals(rstDefault.getString(1)))
			{
	%>			   <input type="radio" name="AccountNumber" value="<% rst.getString(2)%> checked"> <% rst.getString(2)%> <br>
	<%		}
			else
			{
	%>			   <input type="radio" name="AccountNumber" value="<% rst.getString(2)%> "> <% rst.getString(2)%> <br>
	<%		}
			
		}
	%>	
			<input type="Submit" value="Update">
		
		</form>
	<%}%>
</html>