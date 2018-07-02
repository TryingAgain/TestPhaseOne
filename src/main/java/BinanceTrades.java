/**
 * Created by prabhu-92 on 2/7/18.
 */
public class BinanceTrades extends Thread  {

    @Override
    public void run() {


    }
}

/*public class Worker extends Thread {

    @Override
    public void run() {

        // Loop for ten iterations.

        for(int i=0; i<10; i++) {
            System.out.println(i + " looping ...");

            // Sleep for a while
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Interrupted exception will occur if
                // the Worker object's interrupt() method
                // is called. interrupt() is inherited
                // from the Thread class.
                break;
            }
        }
    }

}*/
