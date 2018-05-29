package br.com.welson.clinic.jsf;

import br.com.welson.clinic.ejb.interfaces.ApplicationUserBeanRemote;
import br.com.welson.clinic.persistence.model.Address;
import br.com.welson.clinic.persistence.model.ApplicationUser;
import br.com.welson.clinic.persistence.model.Patient;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class CreateApplicationUserBean implements Serializable {

    @EJB
    private ApplicationUserBeanRemote applicationUserBean;

    public void create() {
        Address address = new Address();
        address.setAddress("Rua A");
        address.setCity("Brasilia");
        address.setDistrict("Ceilandia");
        address.setNumber("158");
        address.setState("Distrito Federal");
        address.setZipCode("72275100");
        Patient patient = new Patient();
        patient.setName("Welson");
        patient.setAddress(address);
        patient.setCPF("123456789");
        patient.setPhone("123456789");
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setPatient(patient);
        applicationUser.setUsername("welson");
        applicationUser.setPassword("welson");
        applicationUser.setEmail("welsonlimawlsn@gmail.com");
        ApplicationUser applicationUser1 = applicationUserBean.createApplicationUser(applicationUser);
        System.out.println(applicationUser1);
    }
}
