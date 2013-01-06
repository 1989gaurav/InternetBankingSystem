public class DoPayment extends DbAccess
{
	private int _amount;
	private string _comment;
	private string _accountNumber;
	private int _transactionId;
	.
	//default constructor
	public DoPayment( int amount, string AccountNumber, string Comment, int TransactionId)
	{
		super();
		_amount = amount;
		_accountNumber = accountNumber;
		_comment = comment;
		_transactionId = TransactionId;
	}
	
	//default destructor
	public ~DoPayment()
	{
		super();
	}
	
	//to set amount value
	public void setAmount( int amount )
	{
		_amount = amount;
	}
	
	//to set account number
	public void setAccountNumber( string accountNumber )
	{
		_accountNumber = accountNumber;
	}
	
	//to set a reference comment to tell about details of transaction
	public void setComment( string comment )
	{
		_comment = comment;
	}
	
	public void setTransactionId(int id)
	{
		_transactionId = id;
	}
	
	// just for debugging purpose
	public int getAmount()
	{
		return _amount;
	}
	
	public string getAccountNumber()
	{
		return _accountNumber;
	}
	
	public string getComment()
	{
		return _comment;
	}
		
	//to debit a specific amount from a given account number
	public boolean debit()
	{
		if(AccountBalance < Amount)
		{
			return false
		}
		else
		{
			//inserting an entry in debit tables
			string query = "insert into debit values( _accountNumber, _amount, _comment, _transactionId)";
			SetQuery( query );
			Insert();
		
			//update the account balanace
			query = "update account set account.accountBal=account.accountBal-_amount where account.accountNumber=_accountNumber";
			SetQuery( query );
			Update();
			return true;
		}
	}
	
	//to debit a specific amount from a given account number
	public void credit()
	{
		//insert an entry in credit table
		string query = "insert into credit values( _accountNumber, _amount, _comment, _transactionId)";
		SetQuery( query );
		Insert();
		
		//update the account balance
		query = "update account set account.accountBal=account.accountBal+_amount where account.accountNumber=_accountNumber";
		SetQuery( query );
		Update();
	}
	
	
}