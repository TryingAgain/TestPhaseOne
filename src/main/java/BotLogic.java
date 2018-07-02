/**
 * Created by prabhu-92 on 29/6/18.
 */
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceEventDepthUpdate;
import com.webcerebrium.binance.datatype.BinanceSymbol;
import com.webcerebrium.binance.websocket.BinanceWebSocketAdapterDepth;
import org.eclipse.jetty.websocket.api.Session;

public class BotLogic {

    protected void firstMethod(){

        /*try {
            BinanceApi api = new BinanceApi();
            System.out.println("ETH-BTC PRICE=" + api.pricesMap().get("ETHBTC"));


            System.out.println((new BinanceApi()).time().get("serverTime").getAsString());
        } catch (BinanceApiException e) {
            System.out.println( "ERROR: " + e.getMessage());
        }*/

        try {

            BinanceSymbol symbol = new BinanceSymbol("ETHBTC");
            Session session = (new BinanceApi()).websocketDepth(symbol, new BinanceWebSocketAdapterDepth() {
                @Override
                public void onMessage(BinanceEventDepthUpdate message) {
                    //System.out.println(message.getClass().getName());
                    for (int i = 0; i < message.getBids().size(); i++) {
                        System.out.println(message.getBids().get(i).getType());
                    }
                    for (int i = 0; i < message.getAsks().size(); i++) {
                        System.out.println(message.getAsks().get(i));
                    }
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            session.close();
            }catch (BinanceApiException e) {
            System.out.println("ERROR: " + e.getMessage());
            }
    }


    public void secondMethod(){

        System.out.println("Starting Main Thread...");
        MyRunnableThread mrt = new MyRunnableThread();
        Thread t = new Thread(mrt);
        t.start();
        while(MyRunnableThread.myCount <= 10){
            try{
                System.out.println("Main Thread: "+(++MyRunnableThread.myCount));
                Thread.sleep(100);
            } catch (InterruptedException iex){
                System.out.println("Exception in main thread: "+iex.getMessage());
            }
        }
    }

    public void thirdMethod(){

        BinanceTrades worker1 = new BinanceTrades();
        worker1.start();

        BinanceTrades worker2 = new BinanceTrades();
        worker2.start();
    }
}
