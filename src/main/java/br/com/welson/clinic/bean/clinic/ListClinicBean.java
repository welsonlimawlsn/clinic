package br.com.welson.clinic.bean.clinic;

import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Clinic;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListClinicBean implements Serializable {

    private List<Clinic> clinics;
    @Inject
    private DAO<Clinic> clinicDAO;
    private String searchField;

    public void init() {
        clinics = clinicDAO.listAll();
    }

    @Transactional
    public void search() {
        clinics = clinicDAO.findByHQLQuery(0, "searchClinicByName", "%" + searchField + "%");
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }
}
