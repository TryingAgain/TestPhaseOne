/**
 * Created by prabhu-92 on 2/7/18.
 */

import com.whalin.MemCached.MemCachedClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class BinanceAskBids implements Runnable {

    public void run() {

//          synchronized (this){

            MemCachedClient hello1 = MemcachedObject.getInstance();

            List<Double> test = new ArrayList<Double>();

            test.add(23.322);
            test.add(3342.33);

            hello1.set("key", test);
            //System.out.println("redis" + "    " + hello1.get("key"));

            List val = Arrays.asList(hello1.get("key"));

            for(int i = 0; i<val.size(); i++) {
                System.out.println("Stored string in memcache:: "+val.get(i));
            }

            

            //System.out.println(hello1);
//        }


    }



}


