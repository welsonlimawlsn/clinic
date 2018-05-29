package br.com.welson.clinic.persistence.dao;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

@Dependent
public class DAOFactory {

    @Inject
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Dependent
    @Produces
    public <T> DAO<T> getDAO(InjectionPoint injectionPoint) {
        ParameterizedType parameterizedType = (ParameterizedType) injectionPoint.getType();
        Class classType = (Class) parameterizedType.getActualTypeArguments()[0];
        return new DAOImp<>(entityManager, classType);
    }
}
