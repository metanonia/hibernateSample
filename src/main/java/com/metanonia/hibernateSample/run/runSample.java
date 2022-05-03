package com.metanonia.hibernateSample.run;

import com.metanonia.hibernateSample.model.Candle;
import com.metanonia.hibernateSample.model.CandleId;
import com.metanonia.hibernateSample.service.RestService;
import org.json.JSONArray;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class runSample {
    public static void main(String[] args) {
        String symbol = "BTCUSDT";
        String ret = RestService.Rest(null, null, "GET", "/api/v3/klines?symbol="+symbol+"&interval=1m", null);
        JSONArray jsonArray = new JSONArray(ret);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        for(int i=0; i<jsonArray.length(); i++) {
            JSONArray item = jsonArray.getJSONArray(i);

            long openTime = item.getLong(0);
            BigDecimal openPrice = item.getBigDecimal(1);
            BigDecimal highPrice = item.getBigDecimal(2);
            BigDecimal lowPrice = item.getBigDecimal(3);
            BigDecimal closePrice = item.getBigDecimal(4);
            BigDecimal vol = item.getBigDecimal(5);
            long closeTime = item.getLong(6);
            BigDecimal volAsset = item.getBigDecimal(7);
            long numTrade = item.getLong(8);
            BigDecimal takeBaseAsset = item.getBigDecimal(9);
            BigDecimal takeQuoteAsset = item.getBigDecimal(10);
            BigDecimal ignore = item.getBigDecimal(11);
            Date dd = new Date(openTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
            String str = sdf.format(dd);
            Timestamp openTimestamp = Timestamp.valueOf(str);

            CandleId candleId = new CandleId(symbol, openTimestamp);
            Candle candle = new Candle();
            candle.setCandleId(candleId);
            candle.setOpen(openPrice);
            candle.setHigh(highPrice);
            candle.setLow(lowPrice);
            candle.setClose(closePrice);
            candle.setVolume(vol);

            tx.begin();
            try {
                Candle find = em.find(Candle.class, candle.getCandleId());
                if (find == null) em.persist(candle);
                else {
                    find.setOpen(candle.getOpen());
                    find.setHigh(candle.getHigh());
                    find.setLow(candle.getLow());
                    find.setClose(candle.getClose());
                    find.setVolume(candle.getVolume());
                    em.flush();
                }
                tx.commit();
            }
            catch (Exception e) {
                tx.rollback();
            }
        }
        em.close();
        emf.close();
    }
}
