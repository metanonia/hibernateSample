package com.metanonia.hibernateSample.run;

import com.metanonia.hibernateSample.model.Binance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class runSample {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Binance org = new Binance();
            Binance find = em.find(Binance.class, org.getYmdhm());
            if(find == null) em.persist(org);
            else {
                find.setCurHash(org.getCurHash());
            }
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
        finally {
            em.close();
        }
        emf.close();
    }
}
