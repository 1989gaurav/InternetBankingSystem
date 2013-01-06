public class Search{
	String SearchText;

	Search()
	{
	}

	SearchText = request.getparameter("Search");

	Switch(/*Search type. Ex- by transaction, by date etc*/)
	{
		case A:
		String query ="";
		rset = stmt.executeQuery(query);
		break;

		case B:

		case C:
	}

	while (rset.next)
	{
		DisplayResult();
	}
	
	SearchATM()
	{
		//similar as above
	}
}
