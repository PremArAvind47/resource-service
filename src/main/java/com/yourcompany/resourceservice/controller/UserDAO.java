package com.yourcompany.resourceservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Slf4j
public class UserDAO extends BaseDAO<User, Long> {

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        super(User.class);
        super.setSessionFactory(sessionFactory);
    }

    public User findById(Long id) {
        Session s = getSessionFactory().openSession();
        try { return s.get(User.class, id); }
        finally { s.close(); }
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        Session s = getSessionFactory().openSession();
        try {
            Query q = s.createQuery("FROM User");
            return q.list();
        } finally { s.close(); }
    }

    public ResponseStatusDTO updateUser(Long id, String username, String email) {
        ResponseStatusDTO res = new ResponseStatusDTO();
        Session s = getSessionFactory().openSession();
        try {
            s.beginTransaction();
            User u = s.get(User.class, id);
            if (u == null) {
                res.setResponseStatus("FAILED");
                res.setErrorMessage("User not found");
                return res;
            }
            u.setUsername(username);
            u.setEmail(email);
            s.update(u);
            s.getTransaction().commit();
            res.setResponseStatus("SUCCESS");
            res.setMessage("User updated");
        } catch (Exception e) {
            if (s.getTransaction()!=null) s.getTransaction().rollback();
            log.error("updateUser error", e);
            res.setResponseStatus("FAILED");
            res.setErrorMessage(e.getMessage());
        } finally { s.close(); }
        return res;
    }

    public ResponseStatusDTO deleteUser(Long id) {
        ResponseStatusDTO res = new ResponseStatusDTO();
        Session s = getSessionFactory().openSession();
        try {
            s.beginTransaction();
            User u = s.get(User.class, id);
            if (u == null) {
                res.setResponseStatus("FAILED");
                res.setErrorMessage("User not found");
            } else {
                s.delete(u);
                s.getTransaction().commit();
                res.setResponseStatus("SUCCESS");
                res.setMessage("User deleted");
            }
        } catch (Exception e) {
            if (s.getTransaction()!=null) s.getTransaction().rollback();
            log.error("deleteUser error", e);
            res.setResponseStatus("FAILED");
            res.setErrorMessage(e.getMessage());
        } finally { s.close(); }
        return res;
    }
}
