import com.whalin.MemCached.MemCachedClient;

/**
 * Created by prabhu-92 on 2/7/18.
 */


public class BinanceTrades extends Thread  {

    @Override
    public void run() {


            MemCachedClient hello = MemcachedObject.getInstance();

            for (int i = 0; i < 100; i++) {
                hello.set("key1", "value_trade");
                System.out.println("trade" + "    " + hello.get("key1"));

            }

            System.out.println(hello);

    }
}


