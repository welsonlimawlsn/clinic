package br.com.welson.clinic.persistence.dao;

import javax.persistence.EntityManager;
import java.util.List;

public class DAOImp<T> implements DAO<T> {

    private EntityManager entityManager;
    private Class<T> classType;

    public DAOImp(EntityManager entityManager, Class<T> classType) {
        this.entityManager = entityManager;
        this.classType = classType;
    }

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public T getById(Long id) {
        return entityManager.find(classType, id);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<T> listAll() {
        return entityManager.createQuery("SELECT e FROM " + classType.getSimpleName() + " e", classType).getResultList();
    }
}
