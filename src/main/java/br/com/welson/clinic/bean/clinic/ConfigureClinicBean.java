package br.com.welson.clinic.bean.clinic;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.ejb.CepEJB;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Address;
import br.com.welson.clinic.persistence.model.CEP;
import br.com.welson.clinic.persistence.model.Clinic;
import br.com.welson.clinic.utils.AddressUtils;
import br.com.welson.clinic.utils.FacesUtil;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ConfigureClinicBean implements Serializable {

    @Inject
    private DAO<Clinic> clinicDAO;
    @Inject
    private ClinicBean clinicBean;
    private Clinic clinic;
    @EJB
    private CepEJB cepEJB;

    public void init() {
        if(clinicBean.getClinic() != null) {
            FacesUtil.redirectTo("/restricted/admin/index.xhtml");
        }
        clinic = new Clinic();
        clinic.setAddress(new Address());
    }

    @Transactional
    @ExceptionHandler
    public String save() {
        clinicDAO.save(clinic);
        FacesUtil.addInfoMessage("Clinica configurada com sucesso!");
        clinicBean.init();
        return "/restricted/admin/index?faces-redirect=true";
    }

    @ExceptionHandler
    public void completeAddress() {
        try {
            CEP address = cepEJB.getAddress(clinic.getAddress().getZipCode());
            AddressUtils.connectAddressToClinic(address, clinic);
        } catch (Exception e) {
            clinic.setAddress(new Address());
            throw new RuntimeException("CEP invalido!");
        }
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
