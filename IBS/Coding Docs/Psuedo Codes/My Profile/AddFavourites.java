public class AddFavourites extends Database.DbAccess
{
	
	public AddFavourites()
	{
		super();
	}
	
	string UserId = GetSessionVariable("UserId");
		
	if(!UserId.equals("false"))
	{
		string Favourite = request.getParameter("FavouriteTaskId");
		string MyUsage = request.getParameter("MyUsage");
		
		string query = " insert into myFavourites values(UserId,Favourite,MyUsage)";
		SetQuery(query);
		Insert();
		
		printwriter pw;
		pw.println("Redirect to myProfile page Show message that your favourite task has been added to this account");
			
			
	}
}