package com.jacobin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.jacobin.models.LineItem;
import com.jacobin.models.Product;

public class LineItemDB {

	public static void insert(LineItem lineItem) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(lineItem);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(LineItem lineItem) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(lineItem);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(LineItem lineItem) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(lineItem));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }

    public static LineItem selectLineItemById(int lineItemId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT l FROM LineItem l " +
                "WHERE l.lineItemId = :lineItemId";
        TypedQuery<LineItem> q = em.createQuery(qString, LineItem.class);
        q.setParameter("lineItemId", lineItemId);
        try {
        	LineItem lineItem = q.getSingleResult();
            return lineItem;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<LineItem> selectLineItemByProduct(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT l FROM LineItem l " +
        		"WHERE l.product = :product";
        TypedQuery<LineItem> l = em.createQuery(qString, LineItem.class);
        l.setParameter("product", product);
        try {
            List<LineItem> list = l.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
