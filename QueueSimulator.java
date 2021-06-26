import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/*
    Accepts events from customers,
    contains a clock ticking everying second,
    processes any events that occurred on clock tick
*/

public class QueueSimulator {
    
    public QueueSimulator(int maxSimulationTime) {
        this.maxSimulationTime = maxSimulationTime;
    }

    public void run() {

        Timer timer = new Timer();
        Random arrivalTime = new Random();
        Random servingTime = new Random();

        int numOfTellers = 3;
        int maxQueueSize = 5;
        BankQueue bankQueue = new BankQueue(numOfTellers, maxQueueSize);

        int numOfQueues = 3;
        maxQueueSize = 5;
        GroceryQueues groceryQueues = new GroceryQueues(numOfQueues, maxQueueSize);

        nextCustomerArrivalTime = arrivalTime.nextInt(61) + 20;

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //if simulation has time remaining
                if (currSimulationTime < maxSimulationTime) {
                    //if time for customer to arrive
                    if (currSimulationTime == nextCustomerArrivalTime) {

                        int requiredServingTime = servingTime.nextInt(301) + 60;
                        Customer customer = new Customer(currSimulationTime, requiredServingTime);
                        
                        bankQueue.joinQueue(customer);
                        groceryQueues.joinQueue(customer);

                        nextCustomerArrivalTime = currSimulationTime + arrivalTime.nextInt(61) + 20;
                        totalNumOfCustomers = totalNumOfCustomers + 1;
                    }
                    //second has passed
                    currSimulationTime = currSimulationTime + 1;
                    System.out.println(currSimulationTime);
                } else {
                    System.out.println("Total customers that arrived is " + totalNumOfCustomers + "\n");
                    System.out.println("For BankQueue, total customers that left without being served is " + bankQueue.getCustomersLeftTotal());
                    System.out.println("For BankQueue, total customers that were served is " + bankQueue.getCustomersServedTotal());
                    System.out.println("For BankQueue, the average amount of time to serve each customer is " + bankQueue.getCustomersServedTotal()/maxSimulationTime + "\n");
                    System.out.println("For GroceryQueue, total customers that left without being served is " + bankQueue.getCustomersLeftTotal());
                    System.out.println("For GroceryQueue, total customers that were served is " + bankQueue.getCustomersServedTotal());
                    System.out.println("For GroceryQueue, the average amount of time to serve each customer is " + bankQueue.getCustomersServedTotal()/maxSimulationTime + "\n");
                    System.exit(0);
                }
            }
        } , 0, 1*1000);
    }

    private int maxSimulationTime;
    private int currSimulationTime = 0;
    private int nextCustomerArrivalTime;
    private int totalNumOfCustomers = 0;
}
