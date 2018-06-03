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
public class UpdateApplicationUserBean implements Serializable {

    @Inject
    private DAO<ApplicationUser> applicationUserDAO;
    @Inject
    private LoginBean loginBean;
    private ApplicationUser applicationUser;
    private String password;
    private String username;

    public void init() {
        applicationUser = loginBean.getApplicationUser();
        username = applicationUser.getUsername();
    }

    @Transactional
    @ExceptionHandler
    public String update() {
        verifyPassword();
        preparePasswordWithNewUsername();
        applicationUserDAO.update(applicationUser);
        FacesUtil.addInfoMessage("Usuário alterado com sucesso!");
        return "update?faces-redirect=true";
    }

    private void verifyPassword() {
        if (!CryptographyUtil.encodePassword(username, password).equals(applicationUser.getPassword())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha invalida", ""));
        }
    }

    private void preparePasswordWithNewUsername() {
        applicationUser.setPassword(CryptographyUtil.encodePassword(applicationUser.getUsername(), password));
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
