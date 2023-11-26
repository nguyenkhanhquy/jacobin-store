package com.jacobin.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.jacobin.models.OrderTrack;

public class OrderTrackDB {

	public static void insert(OrderTrack orderTrack) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(orderTrack);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(OrderTrack orderTrack) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(orderTrack);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(OrderTrack orderTrack) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(orderTrack));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }

    public static OrderTrack selectOrderTrackById(OrderTrack orderTrackId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT o FROM OrderTrack o " +
                "WHERE o.orderTrackId = :orderTrackId";
        TypedQuery<OrderTrack> q = em.createQuery(qString, OrderTrack.class);
        q.setParameter("orderTrackId", orderTrackId);
        try {
        	OrderTrack orderTrack = q.getSingleResult();
            return orderTrack;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
