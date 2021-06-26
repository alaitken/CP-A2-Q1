
/*
    Like bank queue but semaphore is just 1
*/
public class GroceryQueue {

    public GroceryQueue(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public void joinQueue(Customer customer) {

    }

    public int getCurrentQueueSize() {
        return currentQueueSize;
    }

    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    private int currentQueueSize;
    private int maxQueueSize;
}
