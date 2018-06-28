/**
 * Created by prabhu-92 on 28/6/18.
 */
import redis.clients.jedis.Jedis;
import java.util.List;

public class RedRead {

    public static void main(String[] args) {
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Server is running: "+jedis.ping());

        jedis.set("tutorial-name", "Redis tutorial");
        jedis.get("tutorialname");

        //store data in redis list
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // Get the stored data and print it
        List<String> list = jedis.lrange("tutorial-list", 0 ,5);

        for(int i = 0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }

        Jedis jedis1 = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");

        //store data in redis list
        jedis1.lpush("tutorial-list", "Redis1");
        jedis1.lpush("tutorial-list", "Mongodb1");
        jedis1.lpush("tutorial-list", "Mysql1");
        // Get the stored data and print it
        List<String> list1 = jedis1.lrange("tutorial-list", 0 ,5);

        for(int i = 0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list1.get(i));
        }


    }
}
