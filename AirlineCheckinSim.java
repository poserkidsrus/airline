
/**
 * Write a description of class AirlineCheckSim here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JOptionPane;
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
    private double freqRate;
    private double regRate;

    public AirlineCheckinSim()
    {
        this.frequentFlyerQueue = new PassengerQueue("Frequent Flyer");
        this.regularPassengerQueue = new PassengerQueue("Regular Passenger");
    }

    public void enterData()
    {
        int i = -1;
        while(i<0)
        {
            String input1 = JOptionPane.showInputDialog("Please set the rate of frequent flyer arrivals per hour: ");
            int freqRate1 = Integer.parseInt(input1);
            Double freqRate = Double.valueOf(freqRate1);
            String input2 = JOptionPane.showInputDialog("Please set the rate of regular passenger arrivals per hour: ");
            int regRate1 = Integer.parseInt(input2);
            regRate = Double.valueOf(regRate1);
            String input3 = JOptionPane.showInputDialog("Enter the max number of frequent flyers served between regular passengers: ");
            frequentFlyerMax = Integer.parseInt(input3);
            String input4 = JOptionPane.showInputDialog("What is the max processing time allowed in minutes?");
            maxProcessingTime = Integer.parseInt(input4);
            String input5 = JOptionPane.showInputDialog("What is the total simulation time in minutes?");
            totalTime = Integer.parseInt(input5);
            

            i++;
        }
        int input6 = JOptionPane.showConfirmDialog(null,"Would you like to show a trace of this simulation?");
            if(input6 == 0)
            {
                showAll = true;
            }
        frequentFlyerQueue.setArrivalRate(freqRate);
        regularPassengerQueue.setArrivalRate(regRate);
        Passenger.setMaxProcessingTime(maxProcessingTime*60);
        
        
    }

    public void runSimulation() {
        for (clock = 0; clock < totalTime*60; clock++) {
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
            ((double) regularPassengerQueue.getTotalWait()
                / (double) regularPassengerQueue.getNumServed())*60;
        System.out.println(" with an average waiting time of "
            + averageWaitingTime+ " seconds.");
        System.out.println("The number of frequent flyers served was "
            + frequentFlyerQueue.getNumServed());
        double averageWaitingTime2 =
            ((double) frequentFlyerQueue.getTotalWait()
                / (double) frequentFlyerQueue.getNumServed())*60;
        System.out.println(" with an average waiting time of "
            + averageWaitingTime2+ " seconds.");
        System.out.println("Passengers in frequent flyer queue: "
            + frequentFlyerQueue.getQueue().size());
        System.out.println("Passengers in regular passenger queue: "
            + regularPassengerQueue.getQueue().size());
    }

}
