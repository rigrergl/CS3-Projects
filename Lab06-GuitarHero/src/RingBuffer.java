import java.io.IOException;

public class RingBuffer 
{
    private double[] data;          // items in the buffer
    private int      first;         // index for the next dequeue or peek
    private int      last;          // index for the next enqueue
    private int      size;          // number of items in the buffer

    /** create an empty buffer, with given max capacity */
    public RingBuffer(int capacity) {
    	data = new double[capacity];
    	first = 0;
    	last = 0;	
    	size = 0;
    }

    /** return number of items currently in the buffer */
    public int size() {
        return this.size;
    }

    /** is the buffer empty (size equals zero)? */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /** is the buffer full (size equals array capacity)? */
    public boolean isFull() {
        return (this.size == data.length);
    }

    /** add item x to the end 
     * @throws IOException */
    public void enqueue(double x) {
    	data[last] = x;
    	size++;
    	last = (last + 1) % data.length;
    	
    }

    /** delete and return item from the front 
     * @throws IOException */
    public double dequeue() throws RuntimeException {
    	  if(isEmpty()) {
          	throw new RuntimeException("Tried to dequeue when Ringbuffer is empty");
          }
          
    	  double value = data[first];
    	  
          size--;
          first = (first +1) % data.length;
          return value;
    }

    /** return (but do not delete) item from the front */
    public double peek() throws RuntimeException {
    	if(this.isEmpty()) {
    		throw new RuntimeException("Tried to peek an empty buffer");
    	}
        return this.data[first];
    }

    /** a simple test of the constructor and methods in RingBuffer */
    public static void main(String[] args) {
        int N = 100;
        RingBuffer buffer = new RingBuffer(N);
        
        for (int i = 1; i <= N; i++) {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        System.out.println(buffer.peek());
        
        /*
         * Your program should produce the following output:
         * 
         *  Size after wrap-around is 100
			5050.0
         */
    }
}
