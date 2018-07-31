package br.com.welson.clinic.bean.user.doctor;

import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Doctor;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@ViewScoped
@Named
public class ListDoctorBean implements Serializable {

    private List<Doctor> doctorList;
    private String searchField;
    @Inject
    private DAO<Doctor> doctorDAO;


    public void init() {
        doctorList = doctorDAO.listAll();
    }

    public void search() {
        String search = searchField.toLowerCase();
        doctorList = doctorDAO.listAll();
        doctorList = doctorList.stream().filter(doctor ->
                doctor.getName().toLowerCase().contains(search) ||
                        doctor.getCPF().toLowerCase().contains(search) ||
                        doctor.getCRM().toLowerCase().contains(search))
                .collect(Collectors.toList());
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }
}
