
/*
    Time to serve customer: 60-300s (uniform distribution)
    Customer arrives every: 20-60s (uniform distribution)
*/
public class Customer {

    public Customer(int arrivalTime, int servingTime) {
        this.arrivalTime = arrivalTime;
        this.servingTime = servingTime;
    }

    public void leave() {
        hasLeft = true;
    }

    public void serve() {
        wasServed = true;
    }

    public int getRequiredServingTime() {
        return servingTime;
    }

    public boolean getWasServed() {
        return wasServed;
    }

    public boolean getHasLeft() {
        return hasLeft;
    }

    private int arrivalTime;
    private int servingTime;
    private boolean hasLeft = false;
    private boolean wasServed = false;
}
