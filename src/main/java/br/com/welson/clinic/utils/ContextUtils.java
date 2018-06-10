package br.com.welson.clinic.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ContextUtils {

    public static void redirect(String path) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServletContext().getContextPath() + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
