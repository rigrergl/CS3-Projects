import java.util.ArrayList;
import java.util.HashMap;

public class Room 
{
	private String description;
	private HashMap<String,Room> exits;
	private ArrayList<Item> items;
	
	public Room(String desc, ArrayList<Item> itemList)
	{
		description = desc;
		exits = new HashMap<>();
		items = itemList;
	}
	
	/**
	 * Sets a new exit for this room
	 * 
	 * The Room parameter should be passed in as null if there is no exit in that direction
	 */
	public void setExit(String direction, Room neighbor)
	{
		exits.put(direction, neighbor);
		
	}
	
	
	/** Return a String showing all the possible exits a Room has */
	public String getExitString()
	{
        String  result=  "      Exits  >>>  ";          
        for  (String  exit  : exits.keySet()) {//a  for-each  loop,  looping  through  a  Set  of  String  keys  
        	result+=  "  "  +  exit;}    
        return  result;  
        }
	
	
	public String getDescription() { 
		String itemList = "";
		for(int i = 0; i<items.size(); i++){
			itemList += "/ " + items.get(i).getDescription() + " / ";
		}
		return description + "\nItems: " + itemList;
		}
	public Room getExit(String direction){ return exits.get(direction); }
}
