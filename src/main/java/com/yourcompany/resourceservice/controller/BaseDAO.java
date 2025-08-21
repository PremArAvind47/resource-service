package com.yourcompany.resourceservice.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDAO<T, ID> {

    private final Class<T> entityClass;

    private SessionFactory sessionFactory;

    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }
}
