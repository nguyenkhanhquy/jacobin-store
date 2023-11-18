package com.jacobin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.jacobin.models.Category;

public class CategoryDB {

	public static void insert(Category category) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(category);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(Category category) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(category);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Category category) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(category));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }

    public static Category selectCategoryById(Category categoryId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Category c " +
                "WHERE c.category = :category";
        TypedQuery<Category> q = em.createQuery(qString, Category.class);
        q.setParameter("categoryId", categoryId);
        try {
        	Category category = q.getSingleResult();
            return category;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Category> selectAllCategory() {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Category c";
        TypedQuery<Category> q = em.createQuery(qString, Category.class);
        try {
        	List<Category> category = q.getResultList();
            return category;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
