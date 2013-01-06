<html>
	<core module and quick links which are common to all pages>
	
	<%
	import DbAccess;
	
	HandleSession SessionHandler = new HandleSession();
	
	string UserId = SessionHandler.GetSessionVariable("UserId");
		
	if(!UserId.equals("false"))
	{
		ExecuteQuery DbOperation = new ExecuteQuery(databaseURL);
	
		string query = " select * from myProfile where myProfile.UserId=UserId";
		DbOperation.SetQuery(query);
		result rst = DbOperation.Search();
		rst.next();
	%>
	
		//Complete form having all fields of the profile with default option saved priviosly in the database
		<form name="EditProfile" action="EditProfile.java" method=Get>
	
			<input type="text" name=FirstName value=<% rst.FirstName%> size=50>
			<input type="text" name=LastName value=<% rst.LastName%> size=50>
		
			<input type="Submit" value="Update">
			<input typr="Reset" value="Reset">
		</form>
	<%}
	%>
</html>