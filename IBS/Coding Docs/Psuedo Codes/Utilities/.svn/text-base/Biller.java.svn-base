public class Biller
{
	String BillerName;
	String BillerState;
	String BillerCity;
	private int BillerAccountNumber;
	private int BillerID;
	
	private DatabaseConnection MyConnection = new DatabaseConnection();
	private ExecuteQuery MyExecuteQuery = new ExecuteQuery();
	resultset rset;
	
	Biller()
	{
	}
	
	setBillerName(String Name)
	{
		this.BillerName=Name;
	}
	
	setBillerState(String State)
	{
		this.BillerState=State;
	}
	
	setBillerCity(String City )
	{
		this.BillerCity=City;
	}
	
	setBillerID(int BillerIDNum)
	{
		this.BillerID = BillerIDNum;
	}
	
	public DisplayBiller()
	{
		String query = "select * from provider";
		MyExecuteQuery.setQuery();
		MyExecuteQuery.ExecuteQuery(URL);	
		rset = MyExecuteQuery.Search();
		
		while(rset)
		{
			//Display biller info to select from
		}
	}
	
	AddBiller(String UserID){
		
		/* A table (here biller) need to be created in which there will be a list of all billers corrosponding to all users */
		
		String query = "insert into biller where biller.user="+userID+" and biller.billerID="+BillerID+";
		MyExecuteQuery.setQuery(query);
		MyExecuteQuery.inset();
	}
	
	DeleteBiller(BillerID)
	{
		String query = "delete from biller where biller.user="+userID+" and biller.billerID="+BillerID+";";
		MyExecuteQuery.setQuery(query);
		MyExecuteQuery.Delete();
	}
}