import java.util.ArrayList;

public class Player {
private Room currentRoom;
private ArrayList<Item> inventory;

public Player(Room room){
	currentRoom = room;
}

public Room getCurrentRoom(){
	return currentRoom;
}
public void setCurrentRoom(Room room){
	currentRoom = room;
}
}
