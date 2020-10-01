import java.awt.Color;

public class PhotoMagic {
	public static Picture transform(Picture pic, LFSR lfsr) {
		Picture transformedPic = new Picture(pic.width(), pic.height());
		
		for(int col = 0; col < pic.width(); col++) {
			for(int row = 0; row < pic.height(); row++) {
				Color color = pic.get(col, row);
				
				int red = color.getRed() ^ lfsr.generate(8);
				int green = color.getGreen() ^ lfsr.generate(8);
				int blue = color.getBlue() ^ lfsr.generate(8);
				
				transformedPic.set(col, row, new Color(red, green, blue));
			}
		}
		
		return transformedPic;
	}
	
	
//	public static void main(String[] args) {
//		Picture pic = new Picture("catEncryptedJ.png");
//		pic.show();
//		
////		try {
////			Thread.sleep(1000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		Picture transformed = transform(pic, new LFSR("001110001111000100001101010010000100010010000000001100000100", 58));
////		Picture transformed = transform(pic, new LFSR("01101000010100010000", 16));
//		transformed.show();
//	}
}
