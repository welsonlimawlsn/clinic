package br.com.welson.clinic.bean.user;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.ejb.ActivateAccountEJB;
import br.com.welson.clinic.utils.FacesUtil;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ActivateAccountBean implements Serializable {

    private String key;
    @EJB
    private ActivateAccountEJB activateAccountEJB;

    private boolean showButton = false;

    @ExceptionHandler
    @Transactional
    public void activateAccount() {
        activateAccountEJB.activateAccount(activateAccountEJB.verifyAndReturnActivateAccount(key));
        FacesUtil.addInfoMessage("The account has been activated!");
        showButton = true;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isShowButton() {
        return showButton;
    }

    public void setShowButton(boolean showButton) {
        this.showButton = showButton;
    }
}
