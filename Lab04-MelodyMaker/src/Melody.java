import java.util.LinkedList;
import java.util.Queue;

public class Melody {

	private Queue<Note> notes;
	
	Melody(Queue<Note> song){
		this.notes = new LinkedList<>(song);
	}
	
	public double getTotalDuration() { //TODO test this method
		
		double totalDuration = 0;
		
		Queue<Note> localNotes =  clone(this.notes); //TODO LEFT OFF HERE TRYING TO FIND A WAY TO CLONE NOTES
		Queue<Note> repeatedSection = new LinkedList();
		
		boolean onRepeat = false;
		
		while(localNotes.peek() != null) {
			Note note = localNotes.poll();
			
			//////////////////////////////////////////////////////////////////////// TODO fix repeated section logic
			if(note.isRepeat()) {
				
				if(repeatedSection.peek() == null) {repeatedSection.offer(note); onRepeat = true;} //if this is the start of a repeated section
				
				// else run through the repeated section
				else {
					repeatedSection.offer(note);
					
					while(repeatedSection.peek() != null) {
						totalDuration += repeatedSection.poll().getDuration();
					}
					onRepeat = false;
				} 
				
				
			}
			////////////////////////////////////////////////////////////////////////////////////////////
			
			if(onRepeat) repeatedSection.offer(note);
			
			totalDuration += note.getDuration();
			
			
		}
		
		
		return totalDuration;
	}
	
	public String toString() {
		String s = "";
		
		for(Note n: this.notes) {
			s += n.toString() + "\n";
		}
		
		return s;
	}
	
	public void changeTempo(double tempo) { //TODO test this method
		
		Queue<Note> notes = new LinkedList<>();
		
		System.out.println(tempo);
		
		while(this.notes.peek() != null) {
			Note note = this.notes.poll();
			note.setDuration(note.getDuration() / tempo);
			//note.setDuration(1);
			notes.offer(note);
		}
		
		this.notes = notes;
		
	}
	
	public void reverse() {
		//TODO
	}
	
	public void append(Melody other) {
		//TODO
	}
	
	public void play() {
		//TODO
	}
	
	public Queue<Note> getNotes(){
		//TODO
		return this.notes;
	}
	
	
	private Queue<Note> clone(Queue<Note> notes){
		Queue<Note> cloned = new LinkedList();
		
		for(Note n: notes) {
			cloned.offer(n);
		}
		
		return cloned;
		
	}
	
	
	
}
