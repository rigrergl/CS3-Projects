/**
 * Class representing a tile in the Ultima game
 * 
 * @author Keith Vertanen
 * @author Michele Van Dyne - added commenting
 *
 */
public class Tile 
{
	public enum TileType {INVALID, FLOOR, LAVA, WATER, FOREST, GRASS, MOUNTAIN, WALL}
	
	private TileType 	type 	= TileType.INVALID;		// Type of tile this is
	private boolean 	lit 	= false;				// Is the tile currently illuminated?		
	private boolean		seen	= false;				// Has the tile already been lit?
	
	public static final int SIZE = 32;					// Size of a tile in pixels
	
	// Convert a character from the file into our enumerated type
	/**
	 * Constructor for the tile class
	 * 
	 * @param code - letter code that determines the type of tile
	 */
	public Tile(String code)
	{
		if      (code.equals("B")) 	type = TileType.FLOOR;
		else if (code.equals("L")) 	type = TileType.LAVA;
		else if (code.equals("W")) 	type = TileType.WATER;
		else if (code.equals("F")) 	type = TileType.FOREST;
		else if (code.equals("G")) 	type = TileType.GRASS;
		else if (code.equals("M")) 	type = TileType.MOUNTAIN;
		else if (code.equals("S")) 	type = TileType.WALL;
	}

	/**
	 * Get whether this tile has been lit before or not
	 * 
	 * @return true if it has been visited by the lighting method, false otherwise
	 */
	public boolean getSeen()
	{
		return seen;
	}
	
	/**
	 * Set tile to lit or not
	 * 
	 * @param value true or false
	 */
	public void setSeen(boolean value)
	{
		seen = value;
	}
	
	/**
	 * Get whether this tile is lit or not
	 * 
	 * @return true if lit, false otherwise
	 */
	public boolean getLit()
	{
		return lit;
	}
	
	/**
	 * Set whether the tile is lit or not
	 * 
	 * @param value true or false
	 */
	public void setLit(boolean value)
	{
		lit = value;
	}
	
	/**
	 * Returns true if the tile itself could be a source of light, false otherwise
	 * 
	 * @return true or false
	 */
	public boolean isLightSource()
	{
		return (type == TileType.LAVA);			
	}
	
	/**
	 * Does light pass through this tile
	 * 
	 * @return true if opaque, false otherwise
	 */
	public boolean isOpaque()
	{
		switch (type)
		{
			case FLOOR:
			case LAVA:
			case WATER:
			case GRASS:
			case INVALID:				
				return false;
		    default:	break;	
		}		
		return true;
	}
	
	/**
	 * Get the amount of damage caused by this tile
	 * 
	 * @return the damage caused
	 */
	public int getDamage()
	{
		if (type == TileType.LAVA)
			return 1;
		return 0;
	}
	
	/**
	 * Can the hero walk through this tile
	 * 
	 * @return true or false
	 */
	public boolean isPassable()
	{
		switch (type)
		{
			//case LAVA:
			case WATER:
			case MOUNTAIN:
			case WALL:				
				return false;	
		    default:	break;	
		}		
		return true;		
	}

	/**
	 * Draw the tile at the given location
	 * 
	 * @param x the x position of the tile
	 * @param y the y position of the tile
	 */
	public void draw(int x, int y)
	{
		double drawX = (x + .5) * SIZE;
		double drawY = (y + .5) * SIZE;
		
		if (lit)
		{
			switch (type)
			{
				case FLOOR:		StdDraw.picture(drawX, drawY, "brickfloor.gif", 32, 32); 	break;
				case LAVA:		StdDraw.picture(drawX, drawY, "lava.gif", 32, 32); 			break;
				case WATER:		StdDraw.picture(drawX, drawY, "water.gif", 32, 32);     	break;
				case GRASS:		StdDraw.picture(drawX, drawY, "grasslands.gif", 32, 32); 	break;
				case FOREST:	StdDraw.picture(drawX, drawY, "forest.gif", 32, 32);    	break;
				case MOUNTAIN:	StdDraw.picture(drawX, drawY, "mountains.gif", 32, 32); 	break;
				case WALL:		StdDraw.picture(drawX, drawY, "stonewall.gif", 32, 32); 	break;
			    default:                                            				break;										
			}			
		}
		else
		{
			StdDraw.picture(drawX, drawY, "blank.gif", 32, 32); 	
		}
	}
	
	// Test method
	/**
	 * Test main method to ensure tile methods are correct
	 * 
	 * @param args - unused
	 */
	public static void main(String [] args)
	{
		final int SIZE 		= 32;
		final int WIDTH 	= 7;
		final int HEIGHT 	= 2;
		
		StdDraw.setCanvasSize(WIDTH * SIZE, HEIGHT * SIZE);
		StdDraw.setXscale(0.0, WIDTH * SIZE);
		StdDraw.setYscale(0.0, HEIGHT * SIZE);

		String [] codes = {"B", "L", "W", "F", "G", "M", "S"};
		for (int i = 0; i < WIDTH; i++)
		{
			for (int j = 0; j < HEIGHT; j++)
			{
				Tile tile = new Tile(codes[i]);
				if ((i + j) % 2 == 0)
					tile.setLit(true);
				System.out.printf("%d %d : lit %s\topaque %s\tpassable %s\n", 
						           i, j, tile.getLit(), tile.isOpaque(), tile.isPassable()); 
				tile.draw(i, j);
			}
		}		
	}	
}
