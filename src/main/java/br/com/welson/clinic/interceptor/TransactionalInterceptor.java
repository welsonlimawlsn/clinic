package br.com.welson.clinic.interceptor;

import br.com.welson.clinic.annotation.Transactional;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class TransactionalInterceptor {

    @Inject
    private EntityManager entityManager;

    @AroundInvoke
    public Object invoke(InvocationContext invocationContext) {
        EntityTransaction transaction = entityManager.getTransaction();
        Object o = null;
        try {
            transaction.begin();
            o = invocationContext.proceed();
            if (!transaction.getRollbackOnly()) {
                transaction.commit();
            } else {
                transaction.rollback();
                System.err.println("Transactional Failed!");
            }
        } catch (Exception e) {
            transaction.rollback();
            System.err.println("Transactional Failed!");
            e.printStackTrace();
        }
        return o;
    }
}
