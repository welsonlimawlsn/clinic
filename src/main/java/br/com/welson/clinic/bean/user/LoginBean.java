package br.com.welson.clinic.bean.user;

import br.com.welson.clinic.persistence.model.ApplicationUser;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private ApplicationUser applicationUser;

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
