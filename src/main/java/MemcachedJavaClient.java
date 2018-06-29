/**
 * Created by prabhu-92 on 28/6/18.
 */
import java.util.HashMap;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcachedJavaClient {

    /**
     * MemcachedJavaClient program to show the usage of different functions
     * that can be performed on Memcached server with Java Client
     * @param args
     */
    public static void main(String[] args) {
        //initialize the SockIOPool that maintains the Memcached Server Connection Pool
        String[] servers = {"localhost:11211"};
        SockIOPool pool = SockIOPool.getInstance("Test1");
        pool.setServers( servers );
        //pool.setFailover( true );
        //pool.setInitConn( 10 );
        //pool.setMinConn( 5 );
        //pool.setMaxConn( 250 );
        //pool.setMaintSleep( 30 );
        //pool.setNagle( false );
        //pool.setSocketTO( 3000 );
        //pool.setAliveCheck( true );
        pool.initialize();
        //Get the Memcached Client from SockIOPool named Test1
        MemCachedClient mcc = new MemCachedClient("Test1");
        //add some value in cache
        System.out.println("add status:"+mcc.add("1", "Original"));
        //Get value from cache
        System.out.println("Get from Cache:"+mcc.get("1"));

        System.out.println("add status:"+mcc.add("1", "Modified"));
        System.out.println("Get from Cache:"+mcc.get("1"));

        //use set function to add/update value, use replace to update and not add
        System.out.println("set status:"+mcc.set("1","Modified"));
        System.out.println("Get from Cache after set:"+mcc.get("1"));

        //use delete function to delete key from cache
        System.out.println("remove status:"+mcc.delete("1"));
        System.out.println("Get from Cache after delete:"+mcc.get("1"));

        //Use getMulti function to retrieve multiple keys values in one function
        // Its helpful in reducing network calls to 1
        mcc.set("2", "2");
        mcc.set("3", "3");
        mcc.set("4", "4");
        mcc.set("5", "5");
        String [] keys = {"1", "2","3","INVALID","5"};
        HashMap<String,Object> hm = (HashMap<String, Object>) mcc.getMulti(keys);

        for(String key : hm.keySet()){
            System.out.println("KEY:"+key+" VALUE:"+hm.get(key));
        }

        String[] servers1 = {"localhost:11211"};
        SockIOPool pool1 = SockIOPool.getInstance("Test2");
        pool1.setServers( servers1 );
        //pool.setFailover( true );
        //pool.setInitConn( 10 );
        pool1.setMinConn( 5 );
        pool1.setMaxConn( 250 );
        pool1.setMaintSleep( 30 );
        pool1.setNagle( false );
        pool1.setSocketTO( 3000 );
        pool1.setAliveCheck( true );
        pool1.initialize();
        //Get the Memcached Client from SockIOPool named Test1
        MemCachedClient mcc1 = new MemCachedClient("Test2");
        //add some value in cache
        System.out.println("add status:"+mcc1.add("1", "Original"));
        //Get value from cache
        System.out.println("Get from Cache:"+mcc1.get("1"));

    }


}