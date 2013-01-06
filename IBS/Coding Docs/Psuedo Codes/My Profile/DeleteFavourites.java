public class DeleteFavourite extends Database.DbAccess
{
	
	public DeleteFAvorite()
	{
		super();
	}
	
	string UserId = GetSessionVariable("UserId");
		
	if(!UserId.equals("false"))
	{
		string TaskId = request.getParameter("TaskId");
		
		string query = " delete * from myFavourites where myFavourites.favTaskId=TaskId";
		SetQuery(query);
		Update();	
		
		printwriter pw;
		pw.println("Redirect to myProfile page Show message the task having taskId is deleted from this userId");
			
	}
}