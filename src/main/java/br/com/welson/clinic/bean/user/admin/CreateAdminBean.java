package br.com.welson.clinic.bean.user.admin;

import br.com.welson.clinic.persistence.model.Admin;
import br.com.welson.clinic.persistence.model.ApplicationUser;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class CreateAdminBean implements Serializable {

    private ApplicationUser applicationUser;

    public void init() {
        applicationUser = new ApplicationUser();
        applicationUser.setAdmin(new Admin());
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
