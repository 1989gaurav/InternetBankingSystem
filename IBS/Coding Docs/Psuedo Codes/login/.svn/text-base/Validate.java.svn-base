public class Validate extends DatabaseConnection{
	
	boll isCorrect;
	private int count

	Validate()
	{
		isCorrect=false;
	}

	CompareUsernamePassword(String UserName, String Password)
	{
		//DatabaseConnection MyConnection = new DatabaseConnection();
		getDatabaseConnection("url");
		//retrive password corrosponding to userid and compare.
		//increase wrong password count in case of wrong password
		
		if (count == 3)
			BlockUserID(UserName);
		else if (/*userid and password are correct*/)
			return true //login successfull;
	}

	BlockUserID(String UserName)
	{
		//block UserID
	}
}
