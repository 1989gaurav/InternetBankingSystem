public class interbanking extends Common.DoPayment
{
	string UserId = GetSessionVariable("UserId");
		
	if(!UserId.equals("false"))
	{
		int TransactionId = getTransactionIdFromVerify();
		
		if(TransactionId == -1)
		{
			pw.println("Redirect again to interbanking page and show that wrong transaction password");
		}
		else
		{
			int Amount = integer.ParseInt(request.getParameter("Amount"));
			string AccountNumberCredit = request.getParameter("AccountNumber");
			
			// to get default account from amount should be debited
			query = " select DefaultAccount from myProfile where myProfile.UserId=UserId";
			SetQuery(query);
			result rst =Search();
			rst.next();
			string AccountNumberDebit = rst.getString(1);
			
			string commentDebit = "Transfered to account number "+AccountNumberCredit;
			string commentCredit = "Transfered From account number "+AccountNumberDebit;
			
			//debit
			setAmount(Amount);
			setTransactionId(TransactionId);
			setAccountNumber( AccountNumberDebit );
			setComment( commentDebit );
			boolean isDebited = debit();	
			
			if( isDebited )
			{
				//credit
				setAccountNumber( AccountNumberCredit );
				setComment( commentCredit );
				credit();
			
				//make entry in transaction table also
				string commentTransaction = "Amount Rs. is transferred from your account to AccountNumberCredit";
				string query = "insert into transactionId values(TransactionId,time,date,commentTransaction)"
				SetQuery(query);
				Insert();
	
				pw.println("Ammount transfered successfully");
			}
			else
			{
				"Show error that amout is not sufficient to process your request"
			}
		}
		
		
			
			
	}
}