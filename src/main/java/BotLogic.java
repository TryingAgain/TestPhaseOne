import com.whalin.MemCached.MemCachedClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prabhu-92 on 29/6/18.
 */


public class BotLogic {

    protected void firstMethod(){

        new BotLogic().secondMethod();
        new BotLogic().thirdMethod();
    }


    public void secondMethod(){

        BinanceAskBids mrt = new BinanceAskBids();
        Thread t = new Thread(mrt);
        t.start();

    }

    public void thirdMethod(){

        //BinanceTrades worker1 = new BinanceTrades();
        //worker1.start();

        MemCachedClient memCachedTest = MemcachedObject.getInstance();

        ArrayList<List<BigDecimal>> val = (ArrayList)memCachedTest.get("ETHBTC_BID");

        for(int i = 0; i<val.size(); i++) {

            for(int j= 0; j<val.get(i).size();j++){

                System.out.println(val.get(i).get(j));
            }

        }
        
    }
}
