package br.com.welson.clinic.bean.user;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.ejb.PasswordRecoveryEJB;
import br.com.welson.clinic.utils.FacesUtil;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class ForgotPasswordBean implements Serializable {

    private String email;

    @EJB
    private PasswordRecoveryEJB passwordRecoveryEJB;

    @Transactional
    @ExceptionHandler
    public void sendMail() {
        passwordRecoveryEJB.sendMailRecoveryPassword(email);
        FacesUtil.addInfoMessage("messageSendEmailRecovery", true);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
