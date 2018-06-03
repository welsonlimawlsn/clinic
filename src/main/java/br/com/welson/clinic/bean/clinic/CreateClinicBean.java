package br.com.welson.clinic.bean.clinic;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.ejb.CepEJB;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Address;
import br.com.welson.clinic.persistence.model.CEP;
import br.com.welson.clinic.persistence.model.Clinic;
import br.com.welson.clinic.utils.FacesUtil;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class CreateClinicBean implements Serializable {

    @Inject
    private DAO<Clinic> clinicDAO;
    private Clinic clinic;
    @EJB
    private CepEJB cepEJB;

    public void init() {
        clinic = new Clinic();
        clinic.setAddress(new Address());
    }

    @Transactional
    @ExceptionHandler
    public String save() {
        clinicDAO.save(clinic);
        FacesUtil.addInfoMessage("Clinica criada com sucesso!");
        init();
        return "save?faces-redirect=true";
    }

    @ExceptionHandler
    public void completeAddress() {
        try {
            CEP address = cepEJB.getAddress(clinic.getAddress().getZipCode());
            clinic.getAddress().setState(address.getUf());
            clinic.getAddress().setDistrict(address.getBairro());
            clinic.getAddress().setCity(address.getLocalidade());
            clinic.getAddress().setAddress(address.getLogradouro());
            clinic.getAddress().setComplement(address.getComplemento());
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
