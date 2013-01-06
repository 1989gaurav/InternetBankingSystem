public class Draft extends Common.DoPayment
{

	string UserId = GetSessionVariable("UserId");
	
	if( UserId.equals("false"))
	{
		string To = request.getParameter("To");
		int amount = request.getParameter("Amount");
		string payableAt = request.getParameter("PayableAt");
		int TransactionId = getTransactionIdFromVerify();
		
		// to get default account from amount should be debited
		query = " select DefaultAccount from myProfile where myProfile.UserId=UserId";
		SetQuery(query);
		result rst = Search();
		rst.next();
		string AccountNumberDebit = rst.getString(1);
		
		string DraftNumber = generateDraftnumber();
		float AmountDebit = 1.02*Amount;
		string commentDebit = "Made a draft DraftNumber of AmountDebit Rs. valued as Amount";
		
		//debit from current account
		setAmount(Amount);
		setTransactionId(TransactionId);
		setAccountNumber( AccountNumberDebit );
		setComment( commentDebit );
		boolean isDebited = debit();
		
		is(isDebited)
		{
			//make entry in transaction table also
			string commentTransaction = "Made a draft DraftNumber of AmountDebit Rs. valued as Amount by AccountNumberDebit";
			string query = "insert into transactionId values(TransactionId,time,date,commentTransaction)";
			SetQuery(query);
			Insert();
			
			query = "insert into draft values(DraftNumber, amount, payableAt, To, issued)";
			SetQuery(query);
			Insert();
		}
		else
		{
			"Show error that amout is not sufficient to process your request"
		}
		
		
	}
}