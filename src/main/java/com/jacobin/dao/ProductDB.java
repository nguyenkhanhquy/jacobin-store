package com.jacobin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.jacobin.models.Product;

public class ProductDB {

	public static void insert(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(product);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(product);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void delete(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(product));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }
    
    public static Product selectProductById(int productId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p " +
                "WHERE p.productId = :productId";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        q.setParameter("productId", productId);
        try {
            Product product = q.getSingleResult();
            return product;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Product> selectProductByName(String productNameSearch) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p " +
                "WHERE p.name LIKE :pName";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        q.setParameter("pName", "%" + productNameSearch + "%");
        try {
        	List<Product> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Product> selectAllProduct() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        try {
            List<Product> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static List<Product> selectProductByIdDesc() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p " +
        		"ORDER BY p.productId DESC";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        try {
            List<Product> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Product> select20FirstProduct() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p";
        TypedQuery<Product> q = em.createQuery(qString, Product.class)
        		.setMaxResults(20);
        try {
            List<Product> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Product> selectNext10Product(int amount) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p";
        TypedQuery<Product> q = em.createQuery(qString, Product.class)
        		.setFirstResult(amount)
        		.setMaxResults(10);
        try {
            List<Product> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Product> select10FirstProductByCategoryId(int categoryId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p " +
                "WHERE p.category.categoryId = :categoryId";
        TypedQuery<Product> q = em.createQuery(qString, Product.class)
        		.setMaxResults(10);
        q.setParameter("categoryId", categoryId);
        try {
            List<Product> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Product> selectNext5ProductByCategoryId(int categoryId, int amount) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p " +
                "WHERE p.category.categoryId = :categoryId";
        TypedQuery<Product> q = em.createQuery(qString, Product.class)
        		.setFirstResult(amount)
        		.setMaxResults(5);
        q.setParameter("categoryId", categoryId);
        try {
            List<Product> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static int getTotalProduct() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT COUNT(p) FROM Product p";
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
    
    public static List<Product> pagingProduct(int index) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p " +
        		"ORDER BY p.productId";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        q.setFirstResult((index - 1) * 4); // Sử dụng setFirstResult để đặt OFFSET
        q.setMaxResults(4); // Sử dụng setMaxResults để giới hạn số lượng hàng trả về
        try {
        	List<Product> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }	
    }
}
