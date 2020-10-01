
public class Tester {
	public static void main(String[] args) {
		Room testRoom = new Room("A room");
		testRoom.setExit("north", new Room("a dark alley"));
		System.out.println(testRoom.getExitString());

		System.out.println(Command.isCommand("quit"));
		System.out.println(Command.getCommandsString());
		
		Command c = new Command("quit","");
		System.out.println(c.isUnknown());
	}
}
