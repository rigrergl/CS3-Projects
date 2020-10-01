/*************************************************************************
 * Name        :  
 * Username    : 
 * Description :  
 *************************************************************************/
import java.util.*;
import java.io.*;

/**
 * Class to set up the world for game play in Ultima
 * 
 * @author Keith Vertanen
 * @author Michele Van Dyne - eliminated use of StdIn, added commenting
 *
 */
public class World
{
	private Tile [][] tiles 	= null;		    // Stores all the tiles in a 2D array
	private int width 			= 0;		    // Stores the width, first dimension in array
	private int height			= 0;		    // Stores the height, second dimension in array
	private Avatar avatar      	= null;		    // Where the player is

	private ArrayList<Monster> monsters = new ArrayList<Monster>();		// Holds the monster objects
	
	public final static double DISPLAY_DAMAGE_SEC = 3.0;	// How long hit points are display after damage		
	
	/**
	 * Constructor for the world class
	 * 
	 * @param filename - the String name of a file that will hold the configuration parameters for the world
	 */
	public World(String filename)
	{
		try
		{
			Scanner scan = new Scanner(new File(filename));
			
			// First two lines specify the size of the world
			width 	= scan.nextInt();
			height 	= scan.nextInt();

			// Initial location of the Avatar
			avatar = new Avatar(scan.nextInt(),			// x-position 
								scan.nextInt(), 		// y-position
								scan.nextInt(),			// hit points
								scan.nextInt(),			// damage
								scan.nextDouble());		// torch radius

			tiles 	= new Tile[width][height];
			
			for (int i = 0; i < height; i++)
			{
				for (int j = 0; j < width; j++)
				{					
					String code = scan.next();
					tiles[j][height - i - 1] = new Tile(code);
				}
			}
			
			// Read in the monsters
			while (scan.hasNext())
			{
				Monster monster = new Monster(this,					// reference to the World object
											  scan.next(), 			// type of monster code
											  scan.nextInt(),		// x-location
											  scan.nextInt(),		// y-location
											  scan.nextInt(),		// hit points
											  scan.nextInt(),		// damage points
											  scan.nextInt());		// sleep ms
				monsters.add(monster);
			}
			
			scan.close();
		}
		catch (FileNotFoundException e)
		{
		    
			System.out.println("Failed to load file: " + filename);
		}
		
		// Set up the drawing canvas
		StdDraw.setCanvasSize(width * Tile.SIZE, height * Tile.SIZE);
		StdDraw.setXscale(0.0, width * Tile.SIZE);
		StdDraw.setYscale(0.0, height * Tile.SIZE);

		// Initial lighting
		light(avatar.getX(), avatar.getY(), avatar.getTorchRadius());

		// Fire up the monster threads
		for (Monster monster : monsters)
		{
			Thread thread = new Thread(monster);
			thread.start();
		}
	}

	/**
	 * Figure out if the game should end
	 * 
	 * @return true if avatar still alive, false otherwise
	 */
	public boolean avatarAlive()
	{
		return (avatar.getHitPoints() > 0);
	}

	/**
	 * Monster attempting to move to (x, y)
	 *      Damage is how much damage this monster will cause if they hit Avatar.
	 * 
	 * @param x - the new x location of a monster
	 * @param y - the new y location of a monster
	 * @param monster - the monster to be moved
	 */
	public synchronized void monsterMove(int x, int y, Monster monster)
	{
		// Can't attempt to move off board
		if ((x < 0) || (y < 0) || (x >= width) || ( y >= height))
			return;
		
		// Dead monsters tell no tales
		if (monster.getHitPoints() <= 0)
		    return;
		
		// See if we can't actually move there
		if (!tiles[x][y].isPassable())
			return;
		
		// Check if avatar is in this location
		if ((avatar.getX() == x) && (avatar.getY() == y))
		{
			avatar.incurDamage(monster.getDamage());
			return;
		}
		
		// Check no other monsters are in this spot
		for (Monster m : monsters)
		{
			if ((m != monster) && (m.getX() == x) && (m.getY() == y))
				return;
		}
		
		// Make sure monsters get hurt by lava
		int damage = tiles[x][y].getDamage();
		if (damage > 0)
			monster.incurDamage(damage);
		
		monster.setLocation(x, y);
	}

	// 
	/**
	 * Attempt to move the Avatar to the new (x, y) location.
	 * 
	 * @param x - the new x location
	 * @param y - the new y location
	 */
	public synchronized void avatarMove(int x, int y)
	{
		// Can't attempt to move off board
		if ((x < 0) || (y < 0) || (x >= width) || ( y >= height))
			return;
		
		// See if we can't actually move there
		if (!tiles[x][y].isPassable())
			return;

		// Check to see if there is a monster there
		for (Monster monster : monsters)
		{
			if ((monster.getX() == x) && (monster.getY() == y))
			{
				monster.incurDamage(avatar.getDamage());
				return;
			}
		}		
		int damage = tiles[x][y].getDamage();
		if (damage > 0)
			avatar.incurDamage(damage);
		avatar.setLocation(x, y);
	}

	// 
	/**
	 * Handle keyboard input
	 * 
	 * @param ch - the character input from the keyboard
	 */
	public void handleKey(char ch)
	{
		int deltaX = 0;
		int deltaY = 0;
		switch (ch)
		{
			case 'w':	
				deltaY = +1;
				break;
			case 's':		
				deltaY = -1;
				break;
			case 'a':		
				deltaX = -1;
				break;
			case 'd':		
				deltaX = +1;
				break;
			case '+':
				avatar.increaseTorch();
				break;
			case '-':				
				avatar.decreaseTorch();
				break;			
		}
		
		if ((deltaX != 0) || (deltaY != 0))
		{
			int x = avatar.getX() + deltaX;
			int y = avatar.getY() + deltaY;
			avatarMove(x, y);
		}
		setLit(false);
		light(avatar.getX(), avatar.getY(), avatar.getTorchRadius());

	}	
		
	/**
	 * Draw all the lit tiles
	 */
	public synchronized void draw()
	{
		// First update the lighting of the world
		//int x = avatar.getX();
		//int y = avatar.getY();		

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				tiles[x][y].draw(x, y);
			}
		}
		
		for (int i = monsters.size() - 1; i >=0; i--)
		{
			Monster monster = monsters.get(i);
			int x = monster.getX();
			int y = monster.getY();
			
			// Check if the monster has been killed
			if (monster.getHitPoints() <= 0)
			{
				monsters.remove(i);
			}
			else
			{
				if (tiles[x][y].getLit())
					monster.draw();
			}
		}		
		avatar.draw();
	}

	/**
	 * Return the number of alive monsters
	 * 
	 * @return
	 */
	public int getNumMonsters()
	{
		return monsters.size();
	}
	
	/**
	 * Light the current position and all tiles to a surrounding radius
	 * 
	 * @param x - the current x position
	 * @param y - the current y position
	 * @param r - the radius of the avatar's torch
	 * 
	 * @return the number of tiles that were lit
	 */
	public int light(int x, int y, double r)
	{
		setSeen(false);
		int result = light(x, y, x, y, r);
		
		return result;
	}
	
	/**
	 * Recursively light from (x, y) limiting to radius r
	 * 
	 * @param x - the initial x position
	 * @param y - the initial y position
	 * @param currentX - the current x position
	 * @param currentY - the current y position
	 * @param r - the radius of the avatar's torch
	 * 
	 * @return the number of tiles lit
	 */
	private int light(int x, int y, int currentX, int currentY, double r)
	{
		if(currentX < 0 || currentX >= this.width || currentY < 0 || currentY >= this.height)
			return 0;
		
		if(tiles[currentX][currentY].getLit())
			return 0;
		
		if(tiles[currentX][currentY].isOpaque()) {
			tiles[currentX][currentY].setLit(true);
			tiles[currentX][currentY].setSeen(true);
			return 1;
		}
		
		double distance = Math.sqrt(  Math.pow(currentX - x, 2) + Math.pow(currentY - y, 2) );
		
		if(distance > r)
			return 0;
		
		//Done with all base cases
		
		tiles[currentX][currentY].setLit(true);
		tiles[currentX][currentY].setSeen(true);
		return 1 + light(x,y, currentX + 1, currentY, r) + light(x,y, currentX - 1, currentY, r) + light(x,y, currentX, currentY + 1, r) + light(x,y, currentX + 1, currentY - 1, r);
		
		
//		int result = 0;

		// ***** <YOUR CODE GOES HERE> *****
//		return result;
	}
		
	/**
	 * Set all tiles to either on or off
	 * 
	 * @param value true (for lit), false otherwise
	 */
	private void setLit(boolean value)
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				tiles[x][y].setLit(value);
			}
		}				
	}	
	
	/**
	 * Set all the seen values used in the DFS search
	 * 
	 * @param value true (if seen), false otherwise
	 */
	private void setSeen(boolean value)
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				tiles[x][y].setSeen(value);
			}
		}				
	}	

//	/**
//	 * Test main method to make sure world methods are working correctly
//	 * 
//	 * @param args - the name of the file holding world configuration data
//	 */
//	public static void main(String [] args)
//	{		
//		World world0 = new World(args[0]);
//		world0.draw();		
//	}
	
}
