package osdi;


import java.time.Duration;
import java.time.Instant;

import osdi.exchange.ExchangeFeed;
import osdi.exchange.NumberRange;
import osdi.tracker.*;

public class App 
{
    private static final long startValue = 1000L;
    private static final long thousand = 1000L;
    private static final long million = thousand * thousand;
    private static final long endValue = 200L * million;
    
    public static void main( String[] args )
    {
    	 // Summary:
    	 // In our main, we are feeding numbers ranging from 1 to 200 million through an exchange system.
         // This system consists of two threads. The first thread finds what market these numbers should go to.
    	 // The second thread determines if it is a prime market. In other words if we have found a prime number
    	 // within our system it will be queued to the Loyola Prime Market Index or (LPX).
    	
         // The numbers are treated like a key value pair. The key representing a ticket number and the value is the 
    	 // information of the order being placed.
    	
    	 // The numbers are read in like a queue that is FIFO. The processed ordered are feed to a dequeue 
    	 // that are eventually redirected to a market. 
    	
    	 // This is a baseline approach to find a particular difference in what is being feed through the 
    	 // Exchange Feed
    	
    	 // We will eventually remove the numbers that are feed here and replace them with strings
    	 // Those strings will contain an OrderID, OrderType, OrderSize, PriceBought, PriceSold, etc. 
    	
    	 // The value we will focus on first is the OrderID's e.g. LPX (Loyola Prime Index), or AAPL, MSFT, etc.
    	 // This will determine which market the string info will go to. 
    	
    	 // In that Market the string will be parsed into separate variables. From there we will determine if the 
    	 // OrderType is a Buy/Sell, QrderSize (quantity bought/sold), PriceBought $50.25, PriceSold $52.00, etc.
    	
    	 // Then we need to do the accounting portion of the project
    	
    	 System.out.println("Starting the Exchange Feed... 3 seconds");
    	 try {
			Thread.sleep(3000);
		 } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
    	 NumberRange range = new NumberRange(startValue, endValue);
         Instant start = Instant.now();
         long numberOfPrimesInRange = new ExchangeFeed().iterateThroughOrders(range);
         reportNumberOfPrimes(numberOfPrimesInRange);
         Instant end = Instant.now();
         System.out.println("Duration: " + Duration.between(start, end) + " to complete...");    
    }

  
    private static void reportNumberOfPrimes(long count) {
        System.out.println("There are " + count + " primes in the range");
        System.out.flush();
    }
}