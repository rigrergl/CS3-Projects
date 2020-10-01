import java.awt.Color;
import java.awt.Font;

enum GemType {
    GREEN("gem_green.png"), BLUE("gem_blue.png"), ORANGE("gem_orange.png"); //define the different types of Gems, comma delimited
	
	private String fileName;
	
	private GemType(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return this.fileName;
	}
}



public class Gem 
{	
	private final int[] POINT_VALUES = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
	private GemType type;
	private int pointValue;
	
	public Gem() {
		int typeIndex = (int)(Math.random()*(3));
		int pointIndex = (int) (Math.random() * POINT_VALUES.length);
		
		this.pointValue = POINT_VALUES[pointIndex];
		this.type = GemType.values()[typeIndex];
		
	}
	
	public Gem(GemType type, int points) {
		this.type = type;
		this.pointValue = points;
	}
	
	@Override
	public String toString() {
		return this.type.toString() + " " + this.pointValue;
	}
	
	public GemType getType() {
		return this.type;
	}
	
	public int getPoints() {
		return this.pointValue;
	}
	
	public void draw(double x, double y) {
		StdDraw.setFont(new Font("SansSeriff", Font.BOLD, 14));
		StdDraw.picture(x, y, this.type.getFileName());
		
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(x, y, this.pointValue + "");
	}
	
	
	/** Tester main method */
	public static void main(String [] args)
	{
//		final int maxGems = 16;
//		
//		// Create a gem of each type
//		Gem green  = new Gem(GemType.GREEN, 10);
//		Gem blue   = new Gem(GemType.BLUE, 20);
//		Gem orange = new Gem(GemType.ORANGE, 30);
//		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
//		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
//		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
//		green.draw(0.3, 0.7);
//		blue.draw(0.5, 0.7);
//		orange.draw(0.7, 0.7);
//		
//		// A row of random gems
//		for (int i = 0; i < maxGems; i++)
//		{
//			Gem g = new Gem();
//			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
//		}
	}
}
