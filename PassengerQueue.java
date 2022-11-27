import java.util.Queue;
import java.util.LinkedList;

/**
 * Write a description of class PassengerQueue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PassengerQueue
{
    // instance variables - replace the example below with your own
    private String queueName;
    private double arrivalRate;
    private Queue<Passenger> theQueue;
    private int numServed;
    private int totalWait;
    private boolean showAll;

    /**
     * Constructor for objects of class PassengerQueue
     */
    public PassengerQueue(String queueName)
    {
        // initialise instance variables
        this.numServed = 0;
        this.totalWait = 0;
        this.queueName = queueName;
        this.theQueue = new LinkedList<Passenger>();
    }

    public void checkNewArrival(int clock, boolean showAll) {
        if (Math.random() < arrivalRate) {
            theQueue.add(new Passenger(clock));
            if (showAll) {
                System.out.println("Time is "
                    + clock + ": "
                    + queueName
                    + " arrival, new queue size is "
                    + theQueue.size());
            }
        }
    }

    public int getTotalWait()
    {
        return totalWait;
    }

    public int update(int clock, boolean showAll) {
        Passenger nextPassenger = theQueue.remove();
        int timeStamp = nextPassenger.getArrivalTime();
        int wait = clock - timeStamp;
        totalWait += wait;
        numServed++;
        if (showAll) {
            System.out.println("Time is " + clock
                + ": Serving "
                + queueName
                + " with time stamp "
                + timeStamp);
        }
        return clock + nextPassenger.getProcessingTime();
    }

    public int getNumServed()
    {
        return numServed;
    }
    //enter arrivals per hour, divide by 60 in method to get rate of arrivals/minute
    public void setArrivalRate(int arrivalSpeed)
    {
        this.arrivalRate = arrivalSpeed/60;
    }

    public Queue <Passenger> getQueue()
    {
        return theQueue;
    }
}
