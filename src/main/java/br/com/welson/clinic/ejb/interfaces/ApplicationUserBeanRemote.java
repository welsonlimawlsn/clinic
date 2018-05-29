package br.com.welson.clinic.ejb.interfaces;

import br.com.welson.clinic.persistence.model.ApplicationUser;

import javax.ejb.Remote;
import java.io.Serializable;

@Remote
public interface ApplicationUserBeanRemote extends Serializable {

    ApplicationUser createApplicationUser(ApplicationUser applicationUser);
}
