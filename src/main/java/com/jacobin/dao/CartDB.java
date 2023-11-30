package com.jacobin.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.jacobin.models.Cart;
import com.jacobin.models.LineItem;
import com.jacobin.models.User;

public class CartDB {

	public static void insert(Cart cart) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(cart);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(Cart cart) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(cart);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void delete(Cart cart) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(cart));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }
    
    public static Cart selectCartById(int cartId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Cart c " +
                "WHERE c.cartId = :cartId";
        TypedQuery<Cart> q = em.createQuery(qString, Cart.class);
        q.setParameter("cartId", cartId);
        try {
            Cart cart = q.getSingleResult();
            return cart;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static Cart selectCartByUser(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Cart c " +
                "WHERE c.user = :user";
        TypedQuery<Cart> q = em.createQuery(qString, Cart.class);
        q.setParameter("user", user);
        try {
            Cart cart = q.getSingleResult();
            return cart;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static Cart selectCartByLineItem(LineItem item) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Cart c " +
                "WHERE c.items = :item";
        TypedQuery<Cart> q = em.createQuery(qString, Cart.class);
        q.setParameter("item", item);
        try {
            Cart cart = q.getSingleResult();
            return cart;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
