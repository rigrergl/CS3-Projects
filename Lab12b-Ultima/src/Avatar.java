import java.awt.Font;

/**
 * The class that describe the avatar
 *  
 * @author Keith Vertanen
 * @author Michele Van DYne - added commenting
 *
 */
public class Avatar 
{
	private int x 			= 0;	      // current x-location
	private int y 			= 0;	      // current y-location
	private double torch 	= 4.0;	  	  // how powerful our torch is
	private int hp			= 0;	      // hit points
	private int damage		= 0;	      // damage caused by weapon
	private Stats timer     = null;       // for timing display of hit points
	 
	private static final double TORCH_DELTA = 0.5;
	
	/**
	 * Constructor for the Avatar class
	 *  
	 * @param x		 - current x-location
	 * @param y		 - current y-location
	 * @param hp	 - hit points
	 * @param damage - damage we caused by weapon
	 * @param torch  - how powerful our torch is
	 */
	public Avatar(int x, int y, int hp, int damage, double torch)
	{
		this.x 		= x;
		this.y 		= y;
		this.hp 	= hp;
		this.damage = damage;
		this.torch 	= torch;		
	}

	/**
	 * Set a new location foran avatar
	 * 
	 * @param x - the new x position
	 * @param y - the new y position
	 */
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the x position of the avatar
	 * 
	 * @return the x position
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Get the y position of the avatar
	 * 
	 * @return the y position
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Get the hit points left for the avatar
	 * 
	 * @return the hit points remaining
	 */
	public int getHitPoints()
	{	
		return hp;
	}
	
	/**
	 * Get the current torch radius
	 * 
	 * @return the torch radius
	 */
	public double getTorchRadius()
	{
		return torch;
	}
	
	/**
	 * Make our torch more powerful
	 */
	public void increaseTorch()
	{
		torch += TORCH_DELTA;
	}
	
	/**
	 * Make our torch less powerful
	 */
	public void decreaseTorch()
	{
		torch -= TORCH_DELTA;
		if (torch < 2.0)
			torch = 2.0;
	}

	/**
	 * Draw the avatar
	 */
	public void draw()
	{
		double drawX = (x + 0.5) * Tile.SIZE;
		double drawY = (y + 0.5) * Tile.SIZE;
		StdDraw.picture(drawX, drawY, "avatar.gif");
		if ((timer != null) && (timer.elapsedTime() < World.DISPLAY_DAMAGE_SEC))
		{
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 12));
			//StdDraw.text(drawX, drawY + Tile.SIZE, "" + hp);
			StdDraw.text(drawX, drawY, "" + hp);
		}
	}

	/**
	 * Reduce hit points by amount of damage a monster attack has caused
	 * 
	 * @param points - the number of points of damage a monster attack causes
	 */
	public void incurDamage(int points)
	{
		hp -= points;
		if (timer == null)
		    timer = new Stats();
		timer.reset();
	}
	
	/**
	 * Get the amount of damage we cause when we attack
	 * 
	 * @return - the amount if damage
	 */
	public int getDamage()
	{
		return damage;		
	}
			
	/**
	 * Test main program to make sure avatar methods are working
	 * 
	 * @param args - unused
	 */
	public static void main(String [] args)
	{
		Avatar avatar = new Avatar(5, 5, 20, 4, 4.0);
		System.out.printf("%d %d %.1f\n", avatar.getX(), avatar.getY(), avatar.getTorchRadius());		
		avatar.setLocation(1, 4);
		System.out.printf("%d %d %.1f\n", avatar.getX(), avatar.getY(), avatar.getTorchRadius());
		avatar.increaseTorch();
		System.out.printf("%d %d %.1f\n", avatar.getX(), avatar.getY(), avatar.getTorchRadius());
		for (int i = 0; i < 6; i++)
		{
			avatar.decreaseTorch();
			System.out.printf("%d %d %.1f\n", avatar.getX(), avatar.getY(), avatar.getTorchRadius());
		}	
	}
}
