package br.com.welson.clinic.persistence.dao;

import br.com.welson.clinic.persistence.model.AbstractEntity;
import br.com.welson.clinic.utils.FileXMLService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DAOImp<T extends AbstractEntity> implements DAO<T> {

    private EntityManager entityManager;
    private Class<T> classType;
    private static final FileXMLService HQL_QUERY;

    static {
        HQL_QUERY = new FileXMLService("hql.xml");
    }

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
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + classType.getSimpleName() + " e WHERE e.id = :id AND e.enabled = true", classType);
        query.setParameter("id", id);
        List<T> resultList = query.getResultList();
        if (resultList.size() == 1)
            return resultList.get(0);
        return null;
    }

    @Override
    public void delete(T entity) {
        entity.setEnabled(false);
        entityManager.merge(entity);
    }

    @Override
    public List<T> listAll() {
        return entityManager.createQuery("SELECT e FROM " + classType.getSimpleName() + " e WHERE e.enabled = true", classType).getResultList();
    }

    @Override
    public List<T> findByHQLQuery(int maxResults, String queryId, Object... values) {
        String hql = HQL_QUERY.findValue(queryId);
        Pattern pattern = Pattern.compile("(:\\w+)");
        Matcher matcher = pattern.matcher(hql);
        List<String> params = new ArrayList<>();
        while (matcher.find()) {
            params.add(matcher.group().replace(":", ""));
        }
        TypedQuery<T> query = entityManager.createQuery(hql, this.classType);
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(params.get(i), values[i]);
        }
        return maxResults == 0 ? query.getResultList() : query.setMaxResults(maxResults).getResultList();
    }
}
