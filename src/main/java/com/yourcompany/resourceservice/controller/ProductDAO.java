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
public class ProductDAO extends BaseDAO<Product, Long> {

    @Autowired
    public ProductDAO(SessionFactory sessionFactory) {
        super(Product.class);
        super.setSessionFactory(sessionFactory);
    }

    public ResponseStatusDTO save(Product product) {
        ResponseStatusDTO response = new ResponseStatusDTO();
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            response.setResponseStatus("SUCCESS");
        } catch (Exception ex) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            log.error("Error saving product: {}", ex.getMessage(), ex);
            response.setResponseStatus("FAILED");
            response.setErrorMessage(ex.getMessage());
        } finally {
            session.close();
        }
        return response;
    }

    public Product findById(Long id) {  // method name fixed
        Session session = getSessionFactory().openSession();
        Product product = null;
        try {
            product = session.get(Product.class, id);
        } catch (Exception ex) {
            log.error("Error fetching product by id: {}", ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return product;
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {  // method name fixed
        Session session = getSessionFactory().openSession();
        List<Product> products = null;
        try {
            Query query = session.createQuery("FROM Product");
            products = query.list();
        } catch (Exception ex) {
            log.error("Error fetching products: {}", ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return products;
    }

    public ResponseStatusDTO update(Long id, ProductDTO dto) {  // updated signature
        ResponseStatusDTO response = new ResponseStatusDTO();
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.setName(dto.getName());
                product.setPrice(dto.getPrice());
                session.update(product);
                session.getTransaction().commit();
                response.setResponseStatus("SUCCESS");
            } else {
                response.setResponseStatus("FAILED");
                response.setErrorMessage("Product not found");
            }
        } catch (Exception ex) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            log.error("Error updating product: {}", ex.getMessage(), ex);
            response.setResponseStatus("FAILED");
            response.setErrorMessage(ex.getMessage());
        } finally {
            session.close();
        }
        return response;
    }

    public ResponseStatusDTO delete(Long id) {  // method name fixed
        ResponseStatusDTO response = new ResponseStatusDTO();
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
                session.getTransaction().commit();
                response.setResponseStatus("SUCCESS");
            } else {
                response.setResponseStatus("FAILED");
                response.setErrorMessage("Product not found");
            }
        } catch (Exception ex) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            log.error("Error deleting product: {}", ex.getMessage(), ex);
            response.setResponseStatus("FAILED");
            response.setErrorMessage(ex.getMessage());
        } finally {
            session.close();
        }
        return response;
    }
}
