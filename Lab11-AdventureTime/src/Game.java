import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
	private Player p1;
	private Scanner console;
	private HashMap<String,Room> rooms;

	public Game() throws FileNotFoundException {
		console = new Scanner(System.in);
		rooms = new HashMap<>();
		createRooms();
	}

	/** Creates Room objects and sets exits to create the game world 
	 *  Creates a HashMap<RoomName, Room Object> that is created from rooms.txt
	 * @throws FileNotFoundException 
	 * */
	private void createRooms() throws FileNotFoundException {
//		// create Rooms
//		Room rotunda = new Room("A large, echoing rotunda");
//		Room principalsOffice = new Room("The principal's office");
//		Room entryWay = new Room("School entryway");
//		Room outside = new Room("Outside the school");
//
//		// set exits for Rooms
//		outside.setExit("north",entryWay);
//		
//		entryWay.setExit("north",rotunda);
//		entryWay.setExit("south",outside);
//		entryWay.setExit("east",principalsOffice);
//		
//		principalsOffice.setExit("west",entryWay);
//		
//		rotunda.setExit("south",entryWay);
//
//		// initialize game's starting room
//		currentRoom = outside;
		
		
		//*****New Version
		
		
		Scanner roomLoader = new Scanner(new File("rooms.txt"));
		roomLoader.nextLine();
		
		
		//create all the Rooms
		while(roomLoader.hasNextLine()){
			String roomName = roomLoader.nextLine(); 
			if(roomName.equals("//"))break;
			String description = roomLoader.nextLine();
			ArrayList<Item> items = new ArrayList<Item>();
			int itemQuantity = roomLoader.nextInt(); roomLoader.nextLine();
			for(int i = 0; i<itemQuantity; i++){
				Item item = new Item(roomLoader.nextLine());
				items.add(item);
			}
			rooms.put(roomName, new Room(description, items));
			roomLoader.nextLine();
		}
		roomLoader.nextLine(); //skips a comment inside rooms.txt
		
//		for(String roomName: rooms.keySet()){
//			System.out.print(roomName + ": ");
//			System.out.println(rooms.get(roomName).getDescription());
//		}
		
//		System.out.println();
		
		//set the exits for the Rooms
		
		while(roomLoader.hasNextLine()){
			String nextLine = roomLoader.nextLine();
			if(nextLine.equals("//"))break;
			Scanner exitLoader = new Scanner(nextLine);
			String room = exitLoader.next();
			while(exitLoader.hasNext()){
				String next = exitLoader.next();
				rooms.get(room).setExit(next, rooms.get(exitLoader.next()));
			}
			exitLoader.close();
		}
		
		roomLoader.nextLine(); //skips the comments inside rooms.txt
		p1 = new Player(rooms.get(roomLoader.nextLine()));
		roomLoader.close();
	}

	private void printWelcome() {
		System.out.println("Welcome to my text-based adventure!");
		System.out.println("At the moment, it's a very boring adventure");
		System.out.println("Type 'help' if you need help\n");
		System.out.println("You are here >>> " + p1.getCurrentRoom().getDescription());
	}

	private void printHelp() {
		System.out.println();
		System.out.println("You are alone, you are lost.");
		System.out.println("You wander around an emtpy school.");
		System.out.println("Your commands are >>> " + Command.getCommandsString());
		System.out.println();
	}

	/** Takes a Command object and attempts to process it as a game command */
	private boolean processCommand(Command command) {
		boolean finished = false;
		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}
		String commandWord = command.getCommandWord();
		if (commandWord.equalsIgnoreCase("help"))
			printHelp();
		if (commandWord.equalsIgnoreCase("go"))
			goRoom(command);
		if (commandWord.equalsIgnoreCase("take"))
			takeItem(command);
		if (commandWord.equalsIgnoreCase("quit"))
			finished = quit(command);

		return finished;
	}

	/** Attempts to move the player to another Room based on their Command */
	private void goRoom(Command command) {
		System.out.println();
		if (command.getSecondWord() == null) {
			System.out.println("Go where?");
			return;
		}
		
		String direction = command.getSecondWord();

		Room nextRoom = null;
		nextRoom = p1.getCurrentRoom().getExit(direction);

		if (nextRoom != null) {
			p1.setCurrentRoom(nextRoom);
			System.out.println("You are here >>>" + p1.getCurrentRoom().getDescription());
		} else
			System.out.println("There is no door!");

	}
	
	private void takeItem(Command command){
		if (command.getSecondWord() == null) {
			System.out.println("Get what?");
			return;
		}
		
		String itemName = command.getSecondWord(); 
	}

	/** Returns true if the user is wanting to quit */
	private boolean quit(Command command) {
		if (command.getSecondWord() != null) {
			System.out.println("Quit what?");
			return false;
		} else{
			System.out.println("Thanks for playing, goodbye");
			return true;
		}

			
	}

	/** This method starts the game, continuing until the user wants to quit */
	public void play() {
		printWelcome();
		boolean finished =  false;
		while(!finished){
			System.out.println(p1.getCurrentRoom().getExitString());
			System.out.println(">");
			String line = console.nextLine();
			
			Scanner tokenizer = new Scanner(line);
			String command = null;
			String second = null;
			if(tokenizer.hasNext()) command = tokenizer.next();
			if(tokenizer.hasNext()) second = tokenizer.next();
			tokenizer.close();
			
			Command currentCommand = new Command(command, second);
			finished = processCommand(currentCommand);
			
		}
		console.close();
	}
}
