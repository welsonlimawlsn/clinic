package br.com.welson.clinic.bean.user;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.ejb.ActivateAccountEJB;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Admin;
import br.com.welson.clinic.persistence.model.ApplicationUser;
import br.com.welson.clinic.utils.CryptographyUtil;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class CreateApplicationUserBean implements Serializable {

    private ApplicationUser applicationUser;
    @Inject
    private DAO<ApplicationUser> applicationUserDAO;
    @EJB
    private ActivateAccountEJB activateAccountEJB;

    public void init() {
        applicationUser = new ApplicationUser();
        applicationUser.setAdmin(new Admin());
        applicationUser.setEnabled(false);
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    @Transactional
    @ExceptionHandler
    public void save() {
        applicationUser.setPassword(CryptographyUtil.encodePassword(applicationUser.getUsername(), applicationUser.getPassword()));
        applicationUser = applicationUserDAO.save(applicationUser);
        activateAccountEJB.createActivateAccount(applicationUser);
    }

    private void configInitialAdmin() {
        applicationUser.setUsername("admin");
        applicationUser.setPassword("admin");
        applicationUser.setEmail("admin@gmail.com");
        applicationUser.getAdmin().setName("Welson");
        applicationUser.getAdmin().setCPF("admin");
        applicationUser.getAdmin().setPhone("admin");
    }
}
