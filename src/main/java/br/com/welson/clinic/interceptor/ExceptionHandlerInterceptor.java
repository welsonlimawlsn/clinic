package br.com.welson.clinic.interceptor;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.utils.FacesUtil;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@ExceptionHandler
public class ExceptionHandlerInterceptor implements Serializable {

    @AroundInvoke
    public Object invoke(InvocationContext invocationContext) {
        Object o = null;
        try {
            o = invocationContext.proceed();
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtil.addErrorMessage(e.getMessage());
        }
        return o;
    }
}
