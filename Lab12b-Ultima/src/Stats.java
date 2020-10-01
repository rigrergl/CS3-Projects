/*************************************************************************
 * Name        : Keith Vertanen
 * Username    : kvertanen
 * Description : Class that can track running time and also provide
 *               information about the amount of memory used.
 *************************************************************************/

import java.lang.management.*;

public class Stats 
{ 
    private long start;
    
    private static final int BYTES_PER_MEGABYTE = 1024 * 1024;
    private static final int MILLISECONDS_PER_SECOND = 1000;
    
    public Stats() 
    {
        start = System.currentTimeMillis();
    } 

    // Return elapsed time in seconds since the object was created
    public double elapsedTime() 
    {
        long now = System.currentTimeMillis();
        return (now - start) / (double) MILLISECONDS_PER_SECOND;
    }

    public void reset()
    {
        start = System.currentTimeMillis();
    }

    // Return the used heap memory used in MB
    public double heapMemory() 
    {
        return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() / (double) BYTES_PER_MEGABYTE;
    }

    // Return the maximum available memory in MB
    public double maxMemory() 
    {
        return Runtime.getRuntime().maxMemory() / (double) BYTES_PER_MEGABYTE;
    }

    // Return some lines of text with the stats
    public String toString()
    {
        String result = "";
        result += String.format("elapsed time (s)      : %7.4f\n", elapsedTime());
        result += String.format("heap memory used (MB) : %7.4f\n", heapMemory());
        result += String.format("max memory (MB)       : %7.4f", maxMemory());		
        return result;
    }
    
    // Test main method
    public static void main(String [] args)
    {
        final int SIZE = 4096;
        Stats stats = new Stats();
        double [][] matrix = new double[SIZE][SIZE];
        
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                matrix[i][j] = Math.sqrt(i + j);
            }
        }
        System.out.println(stats);
    }
    
} 
