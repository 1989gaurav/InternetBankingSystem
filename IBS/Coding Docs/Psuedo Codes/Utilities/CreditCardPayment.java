/****************************************************
* created : Sunday 24 September 2008, 07:39:43  IST *
*						    *
* Author: Ajay Kumar Gautam			    *
* ajay.kr.gtm@gmail.com				    *
*						    *
****************************************************/

public class CreditCardPayment{
	private int CardNumber;
	private String CardProvider;
	private int Amount;
	
	CreditCardPayment()
	{
	}
	
	Payment()
	{
		CardNumber = request.getparameter("CardNumber");
		CardProvider = request.getparameter("CardProvider");
		Amount = request.getparameter("Amount");
		
		//I dont have any idea about Credit Card Bill Payment (could not find any info on net)
		
		//here payment procedure.
	}
	
}