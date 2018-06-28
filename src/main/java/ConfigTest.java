/**
 * Created by prabhu-92 on 25/6/18.
 */
import java.util.*;
import java.util.Properties;
import com.whalin.MemCached.MemCachedClient;
import java.net.InetSocketAddress;



public class ConfigTest {

    Properties configFil;

    public static void main (String [] s) {

        ConfigTest cfg = new ConfigTest();
        cfg.ConfigT();
        System.out.println(cfg.getProperty("Test"));

        //new InetSocketAddress().checkPort(11211);
        //MemCachedClient mcc = new MemCachedClient(new InetSocketAddress("127.0.0.1:11211"));


 //       MemCachedClient mcc = new MemCachedClient("Test1");




    }

    public void ConfigT()
    {
        this.configFil = new Properties();
        try {

            this.configFil.load(this.getClass().getClassLoader().getResourceAsStream("Configuration.properties"));
        }

        catch(Exception e)
        {

            e.printStackTrace();
        }


        //String value = this.configFil.getProperty(key);
        //return value;

    }

    public String getProperty(String key){

        String value = this.configFil.getProperty(key);
        return value;
    }
}
