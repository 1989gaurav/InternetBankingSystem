public class VerifyTransaction extends XMLDbAccess
{
	private string _inputPassword;
	private int _transactionId;
	
	//default constructer
	public VerifyTransaction()
	{
		super();
	}

	//default destructor
	public ~VerifyTransaction()
	{
	}

	public VerifyTransaction( string password)
	{
		_inputPassword = password;
	}
	
	public void setInputPassword( string inputPassword)
	{
		_inputPassword = inputPassword;
	}
	
	public string getInputPassword( )
	{
		return _inputPassword ;
	}
	
	private void generateTransactionId() // generate a transaction id for each transaction
	{
		_tansactionId = generateId();
	}
	
	// first validate transaction password using ajaz script and then if valid then generate a transaction id for that particular transaction
	public int validate()
	{
		//query in the database and compare with the _inputPassword  using azax
		
		//if successfuel then return transaction id else return -1
		if( passwordMatched)
		{
			generateTransactionId();
			return _tansactionId;
		}
		else
		{
			return -1;
		}
	}
}