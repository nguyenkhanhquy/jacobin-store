package com.jacobin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.jacobin.models.Role;

public class RoleDB {

	public static Role selectRoleByID(int roleId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Role r " +
                "WHERE r.roleId = :roleId";
        TypedQuery<Role> q = em.createQuery(qString, Role.class);
        q.setParameter("roleId", roleId);
        try {
        	Role role = q.getSingleResult();
            return role;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
	
	public static List<Role> selectAllRole() {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Role r";
        TypedQuery<Role> q = em.createQuery(qString, Role.class);
        try {
        	List<Role> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
