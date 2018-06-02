package br.com.welson.clinic.bean.user;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.ejb.ActivateAccountEJB;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.ApplicationUser;
import br.com.welson.clinic.utils.CryptographyUtil;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private ApplicationUser applicationUser;
    @Inject
    private DAO<ApplicationUser> applicationUserDAO;
    @EJB
    private ActivateAccountEJB activateAccountEJB;

    private String username;
    private String password;

    @Transactional
    @ExceptionHandler
    public String login() {
        List<ApplicationUser> searchApplicationUserByUsernameAndPassword =
                applicationUserDAO.findByHQLQuery(0, "searchApplicationUserByUsernameAndPassword", username,
                        CryptographyUtil.encodePassword(username, password));
        if (searchApplicationUserByUsernameAndPassword.size() == 1) {
            activateAccountEJB.verifyIfApplicationUserIsActivated(searchApplicationUserByUsernameAndPassword.get(0));
            applicationUser = searchApplicationUserByUsernameAndPassword.get(0);
            if (applicationUser.getAdmin() != null) {
                return "/restricted/admin/index?faces-redirect=true";
            }
            if (applicationUser.getPatient() != null) {
                return "/restricted/patient/index?faces-redirect=true";
            }
            if (applicationUser.getEmployee() != null) {
                return "/restricted/employee/index?faces-redirect=true";
            }
            if (applicationUser.getDoctor() != null) {
                return "/restricted/doctor/index?faces-redirect=true";
            }
        }
        throw new RuntimeException("Username or password is invalid");
    }

    public String logout() {
        applicationUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
