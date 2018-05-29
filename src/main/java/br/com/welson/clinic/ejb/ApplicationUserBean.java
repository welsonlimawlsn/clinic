package br.com.welson.clinic.ejb;

import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.ejb.interfaces.ApplicationUserBeanRemote;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.ApplicationUser;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless(name = "ApplicationUserEJB")
public class ApplicationUserBean implements ApplicationUserBeanRemote {

    @Inject
    private DAO<ApplicationUser> applicationUserDAO;

    public ApplicationUserBean() {
    }

    @Override
    @Transactional
    public ApplicationUser createApplicationUser(ApplicationUser applicationUser) {
        return applicationUserDAO.save(applicationUser);
    }
}
