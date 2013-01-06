*/
created : Sunday 24 September 2008, 11:43:12  IST

Author: Ajay Kumar Gautam
ajay.kr.gtm@gmail.com
		
*/

/*** Log all the transactions in logs.txt ***/		
public class PayBill
{
	
	private DatabaseConnection MyConnection = new DatabaseConnection();
	private ExecuteQuery MyExecuteQuery = new ExecuteQuery();
	Resultset rset;
	int BillerAccountNum;
	private int BillerID;
	private int Amount;
	
	PayBill()
	{
	}
	
	Payment(BillerID, BillID,Amount)
	{
		BillerID = request.getparameter("BillerID");
		Amount = request.getparameter("BillAmount");
		
		/* we have to add a table that will map billerID to Account number of biller (here billerAccount) */
		
		String query = "select * from billerAccount where billerAccount.billerID="+billerID+";
		MyExecuteQuery.setQuery(query);
		rset = MyExecuteQuery.executeQuery();
		while(rset.next)
		{
			BillerAccountNum = rset.getInteger(billerAccount);	//got the account number of biller
		}
		
		//update billerAccount balance and write a log statement
		
		//update user's account balance by debiting the amount and write a log statement
		
		//retrive the current debit count
		
		//add an entry to debit table  and write a log statement
		
		/*** here debit and credit tables need to be created ***/
	}
	
}