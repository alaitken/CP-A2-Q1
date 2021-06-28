import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.Timer;
import java.util.TimerTask;
/*
    has multiple lineups (groceryQueue),
    customer joins shortest queue (random on tie),
    if customer arrives and no queue is available, customer will wait 10s before leaving
*/
public class GroceryQueues {
    
    public GroceryQueues(int numOfQueues, int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
        for (int i = 0; i < numOfQueues; i++) {
            GroceryQueue queue = new GroceryQueue(maxQueueSize);
            queues.add(queue);
        }
        sem = new Semaphore(numOfQueues*(maxQueueSize+1), true);
    }

    public void joinQueue(Customer customer) {
        try {

            System.out.println("Waiting for permit up to 10s for queue space");
            System.out.println("Remaining spots in the store is " + sem.availablePermits());
            boolean hasAcquiredPermit = sem.tryAcquire(10*1, TimeUnit.MILLISECONDS);
            if (hasAcquiredPermit) {
                System.out.println("Acquired permit");

                int minQueueSize = queues.get(0).getCurrentQueueSize();
                int minQueueIndex = 0;
                System.out.println("Starting min queue size is " + queues.get(0).getCurrentQueueSize());
                for (int i = 1; i < queues.size(); i++) {
                    if (queues.get(i).getCurrentQueueSize() < minQueueSize) {
                        System.out.println("New min queue size is " + queues.get(i).getCurrentQueueSize());
                        minQueueSize = queues.get(i).getCurrentQueueSize();
                        minQueueIndex = i;
                    }
                }
                int index = minQueueIndex;
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    queues.get(index).joinQueue(customer);
                    System.out.println("Customer was served and left the store!");
                    sem.release();
                    customersServed = customersServed + 1;
                });
            } else {
                System.out.println("Could not acquire permit and customer left!");
                customersLeft = customersLeft + 1;
            }

        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
        allCustomers.add(customer);
    }

    public int getCustomersLeftTotal() {
        return customersLeft;
    }

    public int getCustomersServedTotal() {
        return customersServed;
    }

    private ArrayList<GroceryQueue> queues = new ArrayList<GroceryQueue>();
    private ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private int maxQueueSize;
    private int customersServed = 0;
    private int customersLeft = 0;
    private Semaphore sem;
}
