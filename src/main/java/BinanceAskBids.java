/**
 * Created by prabhu-92 on 2/7/18.
 */


import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceEventDepthUpdate;
import com.webcerebrium.binance.datatype.BinanceSymbol;
import com.webcerebrium.binance.websocket.BinanceWebSocketAdapterDepth;
import com.whalin.MemCached.MemCachedClient;
import org.eclipse.jetty.websocket.api.Session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BinanceAskBids implements Runnable {

    public void run() {


        final String coinpair = "ETHBTC";

        try {
            BinanceSymbol symbol = new BinanceSymbol(coinpair);
            Session session = (new BinanceApi()).websocketDepth(symbol, new BinanceWebSocketAdapterDepth() {

                MemCachedClient memCachedInstanceforTuples = MemcachedObject.getInstance();

                ArrayList<List> tupleSetOfPrice_Quantity_bid = new ArrayList<List>();
                ArrayList<List> tupleSetOfPrice_Quantity_ask = new ArrayList<List>();

                @Override
                public void onMessage(BinanceEventDepthUpdate message) {

                    if(memCachedInstanceforTuples.get("ETHBTC_ASK")!=null){
                        tupleSetOfPrice_Quantity_ask = (ArrayList)memCachedInstanceforTuples.get("ETHBTC_ASK");
                    }
                    if(memCachedInstanceforTuples.get("ETHBTC_BID")!=null){
                        tupleSetOfPrice_Quantity_bid = (ArrayList)memCachedInstanceforTuples.get("ETHBTC_BID");
                    }

                    String Key_bid = message.getSymbol()+"_BID";
                    String Key_ask = message.getSymbol()+ "_ASK";
                    //for bids entry
                    for (int i = 0; i < message.getBids().size(); i++) {
                        //;
                        List<BigDecimal>price_quantity = new ArrayList<BigDecimal>();
                        price_quantity.add(message.getBids().get(i).getPrice());
                        price_quantity.add(message.getBids().get(i).getQuantity());
                        this.tupleSetOfPrice_Quantity_bid.add(price_quantity);
                    }

                    for (int i = 0; i < message.getAsks().size(); i++) {
                        //;
                        List<BigDecimal>price_quantity = new ArrayList<BigDecimal>();
                        price_quantity.add(message.getAsks().get(i).getPrice());
                        price_quantity.add(message.getAsks().get(i).getQuantity());
                        this.tupleSetOfPrice_Quantity_ask.add(price_quantity);
                    }

                    memCachedInstanceforTuples.set(Key_bid, tupleSetOfPrice_Quantity_bid);

                    memCachedInstanceforTuples.set(Key_ask, tupleSetOfPrice_Quantity_ask);
                }
            });

            /*try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            session.close();*/

        }catch (BinanceApiException e) {
            System.out.println("ERROR: " + e.getMessage());
        }




    }
}


