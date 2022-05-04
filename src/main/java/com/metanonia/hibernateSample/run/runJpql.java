
package com.metanonia.hibernateSample.run;

import com.metanonia.hibernateSample.model.Candle;
import com.metanonia.hibernateSample.model.CandleId;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public class runJpql {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

/*
        TypedQuery<Candle> query = em.createQuery("SELECT m FROM Candle m", Candle.class);
        List<Candle> list = query.getResultList();
        for(Candle candle: list) {
            System.out.println(candle.toString());
        }
        */
        Query single = em.createNativeQuery("SELECT m.* FROM candle m WHERE symbol=? ");
        CandleId candleId
                = new CandleId("BTCUSDT", Timestamp.valueOf("2022-05-04 10:31:00.0"));
        single.setParameter(1, "BTCUSDT");
        //single.setParameter(2, Timestamp.valueOf("2022-05-04 10:31:00.0"));
        List<Object[]> list = single.getResultList();
        for(Object[] obj:list) {
            System.out.println(obj[0]);
        }
    }
}
