import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Melody {

	Queue<Note> notes;
	
	public Melody(Queue<Note> song){
		this.notes = song;
	}
	
	
	public double getTotalDuration() {
		Queue<Note> repeatedSection = new LinkedList<>();
		boolean onRepeat = false;
		
		double totalDuration = 0;
		
		for(Note note: this.notes) {
			
			if(note.isRepeat()) {
				if(repeatedSection.peek() == null) {onRepeat = true;} //if this is the start of a repeated section
				else { //if this is the end of a repeated section
					repeatedSection.offer(note);
					while(repeatedSection.peek() != null) {
						totalDuration += repeatedSection.poll().getDuration();
					}
					
					onRepeat = false;
				} 
			}
			
			if(onRepeat) {repeatedSection.offer(note);}
			totalDuration += note.getDuration();
		}
		
		return totalDuration;
	
	}
	
	@Override
	public String toString() { //TODO TEST THIS METHOD
		String s = ""; //return value
		
		for(Note n: this.notes) {
			s += n.toString() + "\n";
		}
		
		return s;
		
	}
	
	public void changeTempo(double tempo) {
		Queue<Note> newNotes = new LinkedList<>();
		
		while(this.notes.peek() != null) {
			Note newNote = this.notes.poll();
			newNote.setDuration(newNote.getDuration() / tempo);
			newNotes.offer(newNote);
		}
		
		this.notes = newNotes;
		
	}
	
	public void reverse() {
		Stack<Note> reverse = new Stack<>();
		while(this.notes.peek() != null) {
			reverse.push(this.notes.poll());
		}
		
		while(!reverse.empty()) {
			this.notes.offer(reverse.pop());
		}
	}
	
	public void append(Melody other) { //TODO TEST THIS METHOD
		Queue<Note> otherNotes = cloneQueue(other.getNotes());
		
		while(otherNotes.peek() != null) {
			this.notes.offer(otherNotes.poll());
		}
	}
	
	public void play() {
		Queue<Note> notes = cloneQueue(this.notes);
		Queue<Note> repeatedSection = new LinkedList<>();
		
		boolean onRepeat = false;
		
		while(notes.peek() != null) {
			Note note = notes.poll();
			
			if(note.isRepeat()) {
				
				//if this is the start of a repeated section
				if(repeatedSection.peek() == null) {onRepeat = true;} 
				
				//else if it's the end of a repeated section
				else {
					repeatedSection.offer(note);
					while(repeatedSection.peek() != null){
						repeatedSection.poll().play();
					}
					onRepeat = false;
				}
				
			}
			
			if(onRepeat) {repeatedSection.offer(note);}
			
			note.play();
		}
	}
	
	public Queue<Note> getNotes(){
		return this.notes;
	}
	
	
	/**
	 * 
	 * @return a cloned object of Queue<Note> supplied
	 * 
	 * useful for iterating through the Queue with poll without messing up the original Queue
	 */
	private Queue<Note> cloneQueue(Queue<Note> notes) {
		Queue<Note> clonedQueue = new LinkedList<>();
		for(Note n: notes) {
			clonedQueue.offer(n);
		}
		
		return clonedQueue;
	}
	
	
}
