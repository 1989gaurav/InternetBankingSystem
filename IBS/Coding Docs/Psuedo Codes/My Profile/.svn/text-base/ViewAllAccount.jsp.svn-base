<html>
	<core module and quick links which are common to all pages>
	
	<%
	import DbAccess;
	
	HandleSession SessionHandler = new HandleSession();
	
	string UserId = SessionHandler.GetSessionVariable("UserId");
	
	if(!UserId.equals("false"))
	{
			
		ExecuteQuery DbOperation = new ExecuteQuery(databaseURL);
	
		string query = " select * from AccountMapping where AccountMapping.UserId=UserId";
			DbOperation.SetQuery(query);
	
		result rst = DbOperation.GetResult();
	
		while(rst.next())
		{
			%> <make table using all the information collected from executing query>
			<%
		}
	}
	%>

</html>