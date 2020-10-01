import java.util.HashSet;

public class GuitarHero {

	
	static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
	static GuitarString[] strings = new GuitarString[keyboard.length()];
	
	public static void main(String[] args) {
		initialize();
		
		//listen to pressed keys
		while(true) {
			if(StdDraw.hasNextKeyTyped()) {
			
				char keyChar = StdDraw.nextKeyTyped();
				int keyIndex = keyboard.indexOf(keyChar);
				
				if(keyIndex != -1) //&& !StdDraw.isKeyPressed(keyChar))
					strings[keyIndex].pluck();
				
			}
			
			double sample = 0;
			for(GuitarString s: strings) {
				sample += s.sample();
			}
			
			StdAudio.play(sample);
			
			for(GuitarString s: strings) {
				s.tic();
			}
		}
	}
	
	/**
	 * initializes the GuitarString[] strings
	 */
	public static void initialize() {
		
		for(int i = 0; i < strings.length; i++) {
			int frequency = (int) (440 * Math.pow(1.05956, i-24));
//			System.out.println(keyboard.charAt(i) + " results in " + frequency + " Hz");
			strings[i] = new GuitarString(frequency);
		}
	}
	
}

//440 × 1.05956(i - 24)