public class PhotoMagicDeluxe {
	public static final boolean DEBUG = false;
	public static final int BITS_PER_CHAR = 6;
	static String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
//	public static HashMap<Character, String> alpha = new HashMap<>();
//	
//	static {
//		for(int i = 0; i < base64.length(); i++) {
//			String binary = String.format("%0" + BITS_PER_CHAR + "d", Integer.parseInt(Integer.toBinaryString(i)));
//			alpha.put(base64.charAt(i), binary);
//		}
//	}
	
	public static Picture transform(Picture pic, String password, int tap) {
		String seed = "";
		
		for(int i = 0; i < password.length(); i++) {
			int index64 = base64.indexOf(password.charAt(i)); //password.charAt(i);
			String binary = Integer.toBinaryString(index64);
			binary = pad(binary);
			seed += binary;
		}
		
//		System.out.println(seed);
//		System.out.println(tap);
		LFSR lfsr = new LFSR(seed, tap);
		return PhotoMagic.transform(pic, lfsr);
	}
	
	public static String pad(String s) {
		while(s.length() < 6) {
			s = "0" + s;
		}
		
		return s;
	}
	
//	public static void main(String[] args) {
//		if (DEBUG) testBase64conversion00();
//		if (DEBUG) testBase64conversion01();
//		if (DEBUG) testBase64conversion02();
//		
//		Picture pic = new Picture("mystery.png");
//		pic.show();
//		
//		Picture transformed = transform(pic, "OPENSESAME", 58);
//		transformed.show();
//	}
	
//	public static void testBase64conversion00()
//	{
//		String pw = "A";
//
//		String seed = "";
//		
//		for (int i = 0; i < pw.length(); i++) 
//			seed += alpha.get(pw.charAt(i));
//		
//		System.out.println(seed);
//		
//		//should output:
//		//  000000000000
//		
//		Assert.assertEquals(seed.length(), 6);
//		Assert.assertEquals(seed, "000000");
//	}	
//	public static void testBase64conversion01()
//	{
//		String pw = "AA";
//
//		String seed = "";
//		
//		for (int i = 0; i < pw.length(); i++) 
//			seed += alpha.get(pw.charAt(i));
//		
//		System.out.println(seed);
//		
//		//should output:
//		//  000000000000
//		
//		Assert.assertEquals(seed.length(), 12);
//		Assert.assertEquals(seed, "000000000000");
//	}
//	public static void testBase64conversion02()
//	{
//		String pw = "OPENSESAME"; //used in example
//
//		String seed = "";
//		
//		for (int i = 0; i < pw.length(); i++) 
//			seed += alpha.get(pw.charAt(i));
//		
//		System.out.println(seed);
//		
//		//should output:
//		//  001110001111000100001101010010000100010010000000001100000100
//		
//		Assert.assertEquals(seed.length(), 60);
//		Assert.assertEquals(seed, "001110001111000100001101010010000100010010000000001100000100");
//	}
}
