public class DbAccess extends HandleSession
{
	private string _query;
	private connection conn;
	private statement smt;
		
	//to set value of quuery
	public void SetQuery(string query)
	{
		_query = query;
	}
	
	//to get value of query
	public string GetQuery()
	{
		return _viewQuery;
	}
	
	// default constructor
	public DbAccess()
	{
		conn = getDatabaseConnection(String DatabaseURL); // connection from specific data source
		smt = conn.createStatement();
	}
	
	//destructor
	public ~DbAccess()
	{
	}
	
	//to execute a search query
	public result Search()
	{
		result rst = stmt.executeQuery(_query);
			return rst;
	}
	
	//to execute a update query
	public void Update()
	{
		stmt.UpdateQuery(_query);
	}
	
	//to execute a insert query
	public void Insert()
	{
		stmt.executeQuery(_query);
	}
}
	
	
	
	
	
	
	
	