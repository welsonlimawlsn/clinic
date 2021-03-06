package br.com.welson.clinic.persistence.dao;

import br.com.welson.clinic.persistence.model.AbstractEntity;

import java.io.Serializable;
import java.util.List;

public interface DAO<T extends AbstractEntity> extends Serializable {

    T save(T entity);

    T update(T entity);

    T getById(Long id);

    void delete(T entity);

    List<T> listAll();

    List<T> findByHQLQuery(int maxResults, String queryId, Object... values);
}
