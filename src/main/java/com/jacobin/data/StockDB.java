package com.jacobin.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.jacobin.models.Stock;

public class StockDB {

	public static void delete(Stock Stock) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(Stock));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }
	
	public static Stock selectStock(int stockId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT s FROM Stock s " +
                "WHERE s.stockId = :stockId";
        TypedQuery<Stock> q = em.createQuery(qString, Stock.class);
        q.setParameter("stockId", stockId);
        try {
        	Stock stock = q.getSingleResult();
            return stock;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
