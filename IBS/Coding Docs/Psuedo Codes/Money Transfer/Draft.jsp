<html>
	< core module for looks >
	< money transfer between two accounts of same bank>
	
	<script src="EnterTransactionPassword.js"> </script>
	
	<form name="InterBanking" action="Draft.java">
		
		In Favour Of : <input type="text" name="To"><br>
		Ammount To Transfer : <input type="text" name="Amount"><br>
		Payable at : <input type="text" name="PayableAt"><br>
		Want Home delievery of draft :           Yes<input type="radio" name="HomeDelievery" value="Yes">		
												 No <input type="radio" name="HomeDelievery" value="No" checked>		
		<input type="Reset" value="Reset" >
		<input type="Submit" value="submit" onclick=EnterPassword() >
		
	</form>
</html>