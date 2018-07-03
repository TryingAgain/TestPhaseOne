import com.whalin.MemCached.MemCachedClient;

/**
 * Created by prabhu-92 on 2/7/18.
 */

import redis.clients.jedis.Jedis;

public class BinanceTrades extends Thread  {

    @Override
    public void run() {

//        synchronized(this) {

            MemCachedClient hello = MemcachedObject.getInstance();

            for (int i = 0; i < 100; i++) {
                hello.set("key1", "value_trade");
                System.out.println("trade" + "    " + hello.get("key1"));

            }

            System.out.println(hello);
//        }
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
