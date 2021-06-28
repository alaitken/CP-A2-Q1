import java.util.ArrayList;
import java.util.concurrent.*;
/*
    one lineup,
    if one teller becomes free, next person in line goes to teller,
    if queue is full, customer will leave right away 
*/
public class BankQueue {
    
    public BankQueue(int numOfTellers, int maxQueueSize) {
        // this.numOfTellers = numOfTellers;
        this.maxQueueSize = maxQueueSize;
        sem = new Semaphore(numOfTellers, true);
    }
    
    public void joinQueue(Customer customer) {
        System.out.println("Available permits: " + sem.availablePermits());
        System.out.println("Customer has joined queue, currentQueueSize is: " + currentQueueSize);
        if (maxQueueSize < currentQueueSize + 1) {
            customer.leave();
            System.out.println("Queue is full and customer has left");
        } else {
            currentQueueSize = currentQueueSize + 1;
            System.out.println("Customer added to queue, currentQueueSize is: " + currentQueueSize);
            try {
                //wait for teller to be free
                sem.acquire();
                System.out.println("Acquired lock");
                //no longer apart of line
                currentQueueSize = currentQueueSize - 1;
                System.out.println("Customer needs to be served for " + customer.getRequiredServingTime() + "s");
                TimeUnit.MILLISECONDS.sleep(customer.getRequiredServingTime()*1);
                /*
                    TODO: wait for length of customer.getRequiredServingTime()
                */
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
            //leave after being served
            sem.release();
            customersServed = customersServed + 1;
            System.out.println("Released lock");
        }
        allCustomers.add(customer);
    }

    public int getCustomersLeftTotal() {
        //return customersLeft;
        int customersLeftTotal = 0;
        for (int i = 0; i < allCustomers.size(); i++) {
            if (allCustomers.get(i).getHasLeft()) {
                customersLeftTotal = customersLeftTotal + 1;
            }
        }
        return customersLeftTotal;
    }

    public int getCustomersServedTotal() {
        return customersServed;
        // int customersServedTotal = 0;
        // for (int i = 0; i < allCustomers.size(); i++) {
        //     if (allCustomers.get(i).getWasServed()) {
        //         customersServedTotal = customersServedTotal + 1;
        //     }
        // }
        // return customersServedTotal;
    }

    Semaphore sem;
    // private int numOfTellers;
    private int maxQueueSize;
    private int currentQueueSize;
    private int customersServed = 0;
    private int customersLeft = 0;
    private ArrayList<Customer> allCustomers = new ArrayList<Customer>();
}