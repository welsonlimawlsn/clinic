package br.com.welson.clinic.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
        getExternalContext().getFlash().setKeepMessages(true);
        getExternalContext().getFlash().setRedirect(true);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static String getBundleValue(String key) {
        return FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "m").getString(key);
    }

    public static String getURL() {
        HttpServletRequest request = getRequest();
        int serverPort = request.getServerPort();
        return request.getScheme() + "//" + request.getServerName() + (serverPort != 80 ? ":" + serverPort : "") + request.getContextPath();

    }

    private static HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public static void redirectTo(String to) {
        try {
            getExternalContext().redirect(getRequest().getContextPath() + to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }
}
