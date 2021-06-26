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
            GroceryQueue queue = new GroceryQueue();
            queues.add(queue);
        }
    }

    public void joinQueue(Customer customer) {

    }

    public int getCustomersLeftTotal() {
        return 0;
    }

    public int getCustomersServedTotal() {
        return 0;
    }

    private ArrayList<GroceryQueue> queues = new ArrayList<GroceryQueue>();
    private int maxQueueSize;
}
