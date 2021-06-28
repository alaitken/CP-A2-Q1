import java.util.concurrent.*;
/*
    Like bank queue but semaphore is just 1
*/
public class GroceryQueue {

    public GroceryQueue(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public void joinQueue(Customer customer) {
        try {
            currentQueueSize = currentQueueSize + 1;
            System.out.println("Waiting for permit");
            sem.acquire();
            currentQueueSize = currentQueueSize - 1;
            System.out.println("Acquired permit");
            System.out.println("Customer is being served for " + customer.getRequiredServingTime() + "s");
            TimeUnit.MILLISECONDS.sleep(customer.getRequiredServingTime()*1);
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
        System.out.println("Customer has been served!");
        sem.release();
    }

    public int getCurrentQueueSize() {
        return currentQueueSize;
    }

    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    private Semaphore sem = new Semaphore(1, true);
    private int currentQueueSize;
    private int maxQueueSize;
}
