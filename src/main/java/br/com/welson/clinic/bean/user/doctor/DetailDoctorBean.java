package br.com.welson.clinic.bean.user.doctor;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.ApplicationUser;
import br.com.welson.clinic.persistence.model.Doctor;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class DetailDoctorBean implements Serializable {

    private ApplicationUser applicationUser;
    private Long doctorId;

    @Inject
    private DAO<Doctor> doctorDAO;

    @Transactional
    @ExceptionHandler
    public void init() {
        Doctor doctor = doctorDAO.getById(doctorId);
        applicationUser = doctor.getApplicationUser();
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
