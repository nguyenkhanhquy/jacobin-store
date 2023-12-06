package com.jacobin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.jacobin.models.User;

public class UserDB {

	public static void insert(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(user));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }
    
    public static User selectUserById(int userId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u " +
                "WHERE u.userId = :userId";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("userId", userId);
        try {
            User user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static User selectUserByUserName(String userName) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u " +
                "WHERE u.userName = :userName";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("userName", userName);
        try {
            User user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static User selectUserByEmail(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u " +
                "WHERE u.email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        try {
            User user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static boolean checkEmailExists(String email) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u " +
                "WHERE u.email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        try {
        	q.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }
    
    public static boolean checkPhoneExists(String phone) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u " +
                "WHERE u.phone = :phone";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("phone", phone);
        try {
        	q.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }
    
    public static boolean checkUserNameExists(String userName) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u " +
                "WHERE u.userName = :userName";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("userName", userName);
        try {
        	q.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }
    
//    public static boolean checkExists(String check) {
//    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        String qString = "SELECT u FROM User u " +
//                "WHERE u.email = :check OR u.phone = :check OR u.userName = :check";
//        TypedQuery<User> q = em.createQuery(qString, User.class);
//        q.setParameter("check", check);
//        try {
//        	q.getSingleResult();
//            return true;
//        } catch (NoResultException e) {
//            return false;
//        } finally {
//            em.close();
//        }
//    }
    
    public static int getTotalUser() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT COUNT(u) FROM User u";
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
    
    public static List<User> pagingUser(int index) {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u " +
        		"ORDER BY u.userId";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setFirstResult((index - 1) * 4); // Sử dụng setFirstResult để đặt OFFSET
        q.setMaxResults(4); // Sử dụng setMaxResults để giới hạn số lượng hàng trả về
        try {
        	List<User> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }	
    }
}
