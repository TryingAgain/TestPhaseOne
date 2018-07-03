/**
 * Created by prabhu-92 on 3/7/18.
 */
import redis.clients.jedis.Jedis;

public class RedisObject {

    private static Jedis instance;

    private RedisObject(){

    }

    static{

        instance = new Jedis("localhost");
    }

    public static synchronized Jedis getInstance(){

            return instance;
    }
}
