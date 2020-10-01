public class Command 
{
	private static String[] commands = {
		"go", "quit", "help" , "take"
	};
	
	private String commandWord;
	private String secondWord;
	
	public Command(String first, String second)
	{
		commandWord = first;
		secondWord = second;
	}
	
	/** Returns true if str is a valid command (exists in the array) */
	public static boolean isCommand(String str)
	{
		for(int i=0; i<commands.length; i++){
			if(commands[i].equalsIgnoreCase(str)) return true;
		}
		
		return false; 
	}

	/** Gets all the command words in the array, returns as a String */
	public static String getCommandsString() 
	{
		String commandsString = "";
		for(int i=0; i<commands.length; i++){
			commandsString += commands[i] + " ";
		}

		return commandsString; 
	}

	/** 
	 * An 'unknown' command will have a null commandWord
	 * A 'known' command will have a valid command as a commandWord 
	 */
	public boolean isUnknown() 
	{
		if(isCommand(commandWord)) return false;
		
		return true; 
	}
	
	@Override
	public String toString()
	{
		return commandWord + " " + secondWord;
	}
	
	public String getCommandWord() { return commandWord; }
	public String getSecondWord() { return secondWord; }
}
