public class Main {
    public static void main(String[] args) {
        // if (args.length > 0) {
        //     QueueSimulator simulation = new QueueSimulator(Integer.parseInt(args[0])*60);
        //     simulation.run();
        // } else {
        //     System.out.println("Please provide the number of minutes for the simulation!");
        // }
        QueueSimulator simulation = new QueueSimulator(2*60*60);
        simulation.run();
    } 

}
