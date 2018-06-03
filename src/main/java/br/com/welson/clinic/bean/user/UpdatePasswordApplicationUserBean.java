package br.com.welson.clinic.bean.user;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.ApplicationUser;
import br.com.welson.clinic.utils.CryptographyUtil;
import br.com.welson.clinic.utils.FacesUtil;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UpdatePasswordApplicationUserBean implements Serializable {
    private String password;
    private String passwordAgain;
    private String oldPassword;
    @Inject
    private DAO<ApplicationUser> applicationUserDAO;
    @Inject
    private LoginBean loginBean;

    @Transactional
    @ExceptionHandler
    public String updatePassword() {
        verifyPasswords();
        loginBean.getApplicationUser().setPassword(encryptPassword(password));
        applicationUserDAO.update(loginBean.getApplicationUser());
        FacesUtil.addInfoMessage("Senha alterada com sucesso!");
        return "index?faces-redirect=true";
    }

    private void verifyPasswords() {
        if(!encryptPassword(oldPassword).equals(loginBean.getApplicationUser().getPassword())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha invalida!", ""));
        }
        if(!password.equals(passwordAgain)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não coincidem!", ""));
        }
    }

    private String encryptPassword(String password) {
        return CryptographyUtil.encodePassword(loginBean.getUsername(), password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
