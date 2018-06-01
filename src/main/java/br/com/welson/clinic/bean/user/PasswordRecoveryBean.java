package br.com.welson.clinic.bean.user;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.ejb.PasswordRecoveryEJB;
import br.com.welson.clinic.persistence.model.PasswordRecovery;
import br.com.welson.clinic.utils.FacesUtil;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class PasswordRecoveryBean implements Serializable {

    private String key;
    private PasswordRecovery passwordRecovery;
    private String newPassword;

    @EJB
    private PasswordRecoveryEJB passwordRecoveryEJB;

    @ExceptionHandler
    public void init() {
        passwordRecovery = passwordRecoveryEJB.validateAndReturnPasswordRecovery(key);
        System.out.println(passwordRecovery);
    }

    @ExceptionHandler
    @Transactional
    public String updatePassword() {
        passwordRecoveryEJB.updatePassword(passwordRecovery, newPassword);
        FacesUtil.addInfoMessage("messageUpdatePasswordSuccessfully", true);
        return "login.xhtml?faces-redirect=true";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
