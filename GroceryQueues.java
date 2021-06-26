import java.util.ArrayList;

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
    }

    public void joinQueue(Customer customer) {
        int minQueueSize = queues.get(0).getCurrentQueueSize();
        int minQueueIndex = 0;
        for (int i = 1; i < queues.size(); i++) {
            if (queues.get(i).getCurrentQueueSize() < minQueueSize) {
                minQueueSize = queues.get(i).getCurrentQueueSize();
                minQueueIndex = i;
            }
        }
        if (maxQueueSize < minQueueSize + 1) {
            /*
                TODO: Customer waits 10s before leaving
            */
        } else {
            queues.get(minQueueIndex).joinQueue(customer);
        }
        allCustomers.add(customer);
    }

    public int getCustomersLeftTotal() {
        return 0;
    }

    public int getCustomersServedTotal() {
        return 0;
    }

    private ArrayList<GroceryQueue> queues = new ArrayList<GroceryQueue>();
    private ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private int maxQueueSize;
}
