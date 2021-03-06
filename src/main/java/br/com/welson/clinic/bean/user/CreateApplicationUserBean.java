package br.com.welson.clinic.bean.user;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.ejb.ActivateAccountEJB;
import br.com.welson.clinic.ejb.CepEJB;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.*;
import br.com.welson.clinic.utils.AddressUtils;
import br.com.welson.clinic.utils.CryptographyUtil;
import br.com.welson.clinic.utils.FacesUtil;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
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
    private String confirmationPassword;
    @EJB
    private CepEJB cepEJB;

    public void initAdmin() {
        applicationUser = new ApplicationUser();
        applicationUser.setAdmin(new Admin());
    }

    public void initDoctor() {
        applicationUser = new ApplicationUser();
        applicationUser.setDoctor(new Doctor());
    }

    public void initEmployee() {
        applicationUser = new ApplicationUser();
        applicationUser.setEmployee(new Employee());
        applicationUser.getEmployee().setAddress(new Address());
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    @Transactional
    @ExceptionHandler
    public String save() {
        verifyPassword();
        encryptPassword();
        applicationUser = applicationUserDAO.save(applicationUser);
        activateAccountEJB.createActivateAccount(applicationUser);
        FacesUtil.addInfoMessage("Funcionario adicionado com sucesso!");
        return "list?faces-redirect=true";
    }

    @ExceptionHandler
    public void completeAddress() {
        try {
            CEP address = cepEJB.getAddress(applicationUser.getEmployee().getAddress().getZipCode());
            AddressUtils.convertCepToAddress(address, applicationUser.getEmployee().getAddress());
        } catch (Exception e) {
            applicationUser.getEmployee().setAddress(new Address());
            throw new RuntimeException("CEP invalido!");
        }
    }

    private void verifyPassword() {
        if (!confirmationPassword.equals(applicationUser.getPassword())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não se coincidem", ""));
        }
    }

    private void encryptPassword() {
        applicationUser.setPassword(CryptographyUtil.encodePassword(applicationUser.getUsername(), applicationUser.getPassword()));
    }
}
