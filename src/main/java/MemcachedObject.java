import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * Created by prabhu-92 on 3/7/18.
 */
public class MemcachedObject {

    private static MemCachedClient instance;

    private MemcachedObject(){

    }

    static{

        String[] servers = {"localhost:11211"};
        SockIOPool pool = SockIOPool.getInstance("Test1");
        pool.setServers( servers );
        pool.setFailover( true );
        pool.setInitConn( 10 );
        pool.setMinConn( 5 );
        pool.setMaxConn( 250 );
        pool.setMaintSleep( 30 );
        pool.setNagle( false );
        pool.setSocketTO( 3000 );
        pool.setAliveCheck( true );
        pool.initialize();

        instance = new MemCachedClient("Test1");
    }

    public static synchronized MemCachedClient getInstance(){

        return instance;
    }
}
