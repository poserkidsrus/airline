
/**
 * Write a description of class Passenger here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
public class Passenger
{
    // instance variables - replace the example below with your own
    private int passengerId;
    /** The time needed to process this passenger. */
    private int processingTime;
    /** The time this passenger arrives. */
    private int arrivalTime;
    /** The maximum time to process a passenger. */
    private static int maxProcessingTime;
    /** The sequence number for passengers. */
    private static int idNum = 0;
    /**
     * Constructor for objects of class Passenger
     */
    public Passenger(int arrivingTime)
    {
        // initialise instance variables
        this.arrivalTime = arrivingTime;
        Random rand = new Random();
        processingTime = 1 + rand.nextInt(maxProcessingTime);
        passengerId = idNum++;
    }

    public int getArrivalTime()
    {
        // put your code here
        return arrivalTime;
    }

    public int getId() {
        return passengerId;
    }

    public int getProcessingTime()
    {
        return processingTime;
    }

    public void setMaxProcessingTime(int maxProcessTime)
    {
        maxProcessingTime = maxProcessTime;
    }
}
