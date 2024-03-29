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
    
    public static boolean checkNameExists(String name) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Category c " +
                "WHERE c.name = :name";
        TypedQuery<Category> q = em.createQuery(qString, Category.class);
        q.setParameter("name", name);
        try {
        	q.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }

    public static Category selectCategoryById(int categoryId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Category c " +
                "WHERE c.categoryId = :categoryId";
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
    
    public static Category selectCategoryByName(String name) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Category c " +
                "WHERE c.name = :name";
        TypedQuery<Category> q = em.createQuery(qString, Category.class);
        q.setParameter("name", name);
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
        	List<Category> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static int getTotalCategory() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT COUNT(c) FROM Category c";
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
    
    public static List<Category> pagingCategory(int index) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Category c " +
        		"ORDER BY c.categoryId";
        TypedQuery<Category> q = em.createQuery(qString, Category.class);
        q.setFirstResult((index - 1) * 10); // Sử dụng setFirstResult để đặt OFFSET
        q.setMaxResults(10); // Sử dụng setMaxResults để giới hạn số lượng hàng trả về
        try {
        	List<Category> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }	
    }
}
