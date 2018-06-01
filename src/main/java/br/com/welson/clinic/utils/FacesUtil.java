package br.com.welson.clinic.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

    public static void addErrorMessage(String keyOrMessage, boolean isKey) {
        addMessage(FacesMessage.SEVERITY_ERROR, isKey ? getBundleValue(keyOrMessage) : keyOrMessage);
    }

    public static void addInfoMessage(String keyOrMessage, boolean isKey) {
        addMessage(FacesMessage.SEVERITY_INFO, isKey ? getBundleValue(keyOrMessage) : keyOrMessage);
    }

    public static void addWarningMessage(String keyOrMessage, boolean isKey) {
        addMessage(FacesMessage.SEVERITY_WARN, isKey ? getBundleValue(keyOrMessage) : keyOrMessage);
    }

    public static void addErrorMessage(String message) {
        addMessage(FacesMessage.SEVERITY_ERROR, message);
    }

    public static void addInfoMessage(String message) {
        addMessage(FacesMessage.SEVERITY_INFO, message);
    }

    public static void addWarningMessage(String message) {
        addMessage(FacesMessage.SEVERITY_WARN, message);
    }

    private static void addMessage(FacesMessage.Severity severity, String message) {
        FacesMessage facesMessage = new FacesMessage(severity, message, "");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setRedirect(true);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static String getBundleValue(String key) {
        return FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "m").getString(key);
    }
}
