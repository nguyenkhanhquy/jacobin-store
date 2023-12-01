package com.jacobin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.jacobin.models.Order;
import com.jacobin.models.User;

public class OrderDB {

	public static void insert(Order order) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(order);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(Order order) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(order);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void delete(Order order) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(order));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }
    
    public static Order selectOrderById(int orderId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT o FROM Order o " +
                "WHERE o.orderId = :orderId";
        TypedQuery<Order> q = em.createQuery(qString, Order.class);
        q.setParameter("orderId", orderId);
        try {
            Order order = q.getSingleResult();
            return order;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Order> selectOrderByUser(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT o FROM Order o " +
                "WHERE o.user = :user";
        TypedQuery<Order> q = em.createQuery(qString, Order.class);
        q.setParameter("user", user);
        try {
            List<Order> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static int getTotalOrder() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT COUNT(o) FROM Order o";
        TypedQuery<Long> q = em.createQuery(qString, Long.class);
        try {
            Long count = q.getSingleResult();
            return count.intValue();
        } catch (NoResultException e) {
            return 0;
        } finally {
            em.close();
        }		
    }
    
    public static List<Order> pagingOrder(int index) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT o FROM Order o " +
        		"ORDER BY o.orderId";
        TypedQuery<Order> q = em.createQuery(qString, Order.class);
        q.setFirstResult((index - 1) * 10); // Sử dụng setFirstResult để đặt OFFSET
        q.setMaxResults(10); // Sử dụng setMaxResults để giới hạn số lượng hàng trả về
        try {
        	List<Order> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }	
    }
}
