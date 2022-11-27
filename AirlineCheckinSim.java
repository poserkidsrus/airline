
/**
 * Write a description of class AirlineCheckSim here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Queue;
import java.util.Scanner;
public class AirlineCheckinSim
{
    /** Queue of frequent flyers. */
    private PassengerQueue frequentFlyerQueue;
    /** Queue of regular passengers. */
    private PassengerQueue regularPassengerQueue;
    /** Maximum number of frequent flyers to be served
    before a regular passenger gets served. */
    private int frequentFlyerMax;
    /** Maximum time to service a passenger. */
    private int maxProcessingTime;
    /** Total simulated time. */
    private int totalTime;
    /** If set true, print additional output. */
    private boolean showAll;
    /** Simulated clock. */
    private int clock = 0;
    /** Time that the agent will be done with the current passenger.*/
    private int timeDone;
    /** Number of frequent flyers served since the
    last regular passenger was served. */
    private int frequentFlyersSinceRP;
    private char z;

    public AirlineCheckinSim()
    {
        this.frequentFlyerQueue = new PassengerQueue("Frequent Flyer");
        this.regularPassengerQueue = new PassengerQueue("Regular Passenger");
    }
    
    public void enterData()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please set the rate of frequent flyer arrivals per hour: ");
        int freqRate = scanner.nextInt();
        System.out.println("Please set the rate of regular passenger arrivals per hour: ");
        int regRate = scanner.nextInt();
        frequentFlyerQueue.setArrivalRate(freqRate);
        regularPassengerQueue.setArrivalRate(regRate);
        System.out.println("Enter the max number of frequent flyers served between reuglar passengers: ");
        this.frequentFlyerMax= scanner.nextInt();
        
        System.out.println("What is the max processing time allowed in minutes?");
        this.maxProcessingTime = scanner.nextInt();
        System.out.println("What is the total simulation time in minutes?");
        this.totalTime = scanner.nextInt();
        System.out.println("Would you like to show a trace of this simulation? Enter 'y'.");
        this.z = scanner.next().charAt(0);
        if(Character.toLowerCase(z) == 'y')
        {
            showAll = true;
            if(showAll)
            {
                System.out.println("showall is now true");
            }
            else
            {
                System.out.println("Something is wrong.");
            }
        }
        
    }

    public void runSimulation() {
        for (clock = 0; clock < totalTime; clock++) {
            frequentFlyerQueue.checkNewArrival(clock, showAll);
            regularPassengerQueue.checkNewArrival(clock, showAll);
            if (clock >= timeDone) {
                startServe();
            }
        }
    }

    public void startServe() {
        if (!frequentFlyerQueue.getQueue().isEmpty()&& ((frequentFlyersSinceRP <= frequentFlyerMax)|| regularPassengerQueue.getQueue().isEmpty())) {
            // Serve the next frequent flyer.
            frequentFlyersSinceRP++;
            timeDone = frequentFlyerQueue.update(clock, showAll);
        } else if (!regularPassengerQueue.getQueue().isEmpty()) {
            // Serve the next regular passenger.
            frequentFlyersSinceRP = 0;
            timeDone = regularPassengerQueue.update(clock, showAll);
        } else if (showAll) {
            System.out.println("Time is " + clock
                + " server is idle");
        }
    }

    public void showStats() {
        System.out.println
        ("\nThe number of regular passengers served was "
            + regularPassengerQueue.getNumServed());
        double averageWaitingTime =
            (double) regularPassengerQueue.getTotalWait()
            / (double) regularPassengerQueue.getNumServed();
        System.out.println(" with an average waiting time of "
            + averageWaitingTime);
        System.out.println("The number of frequent flyers served was "
            + frequentFlyerQueue.getNumServed());
        double averageWaitingTime2 =
            (double) frequentFlyerQueue.getTotalWait()
            / (double) frequentFlyerQueue.getNumServed();
        System.out.println(" with an average waiting time of "
            + averageWaitingTime2);
        System.out.println("Passengers in frequent flyer queue: "
            + frequentFlyerQueue.getQueue().size());
        System.out.println("Passengers in regular passenger queue: "
            + regularPassengerQueue.getQueue().size());
    }

    
}
