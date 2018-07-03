/**
 * Created by prabhu-92 on 29/6/18.
 */
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceEventDepthUpdate;
import com.webcerebrium.binance.datatype.BinanceSymbol;
import com.webcerebrium.binance.datatype.BinanceEventAggTrade;
import com.webcerebrium.binance.websocket.BinanceWebSocketAdapterDepth;
import com.webcerebrium.binance.websocket.BinanceWebSocketAdapterAggTrades;
import org.eclipse.jetty.websocket.api.Session;
import redis.clients.jedis.Jedis;

public class BotLogic {

    protected void firstMethod(){

        /*try {
            BinanceApi api = new BinanceApi();
            System.out.println("ETH-BTC PRICE=" + api.pricesMap().get("ETHBTC"));


            System.out.println((new BinanceApi()).time().get("serverTime").getAsString());
        } catch (BinanceApiException e) {
            System.out.println( "ERROR: " + e.getMessage());
        }*/

        Jedis jedis = new Jedis("localhost");

        try {

            BinanceSymbol symbol = new BinanceSymbol("ETHBTC");
            Session session = (new BinanceApi()).websocketDepth(symbol, new BinanceWebSocketAdapterDepth() {
                @Override
                public void onMessage(BinanceEventDepthUpdate message) {
                    //System.out.println(message.getClass().getName());
                    String symb = message.getSymbol().toString();
                    for (int i = 0; i < message.getBids().size(); i++) {

                        // System.out.println(message.getBids().get(i).getPrice()+ "   " + message.getBids().get(i).getQuantity());
                    }


                    for (int i = 0; i < message.getAsks().size(); i++) {
                        //System.out.println(message.getAsks().get(i).getPrice()+ "    "+ message.getAsks().get(i).getQuantity());
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


            try {
                BinanceSymbol symbol = new BinanceSymbol("ETHBTC");

                Session session = (new BinanceApi()).websocketTrades(symbol, new BinanceWebSocketAdapterAggTrades() {
                    @Override
                    public void onMessage(BinanceEventAggTrade message) {
                        System.out.println(message.getPrice()+"     "+ message.getQuantity());
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
}
