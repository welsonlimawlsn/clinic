package br.com.welson.clinic.bean.user.doctor;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.ApplicationUser;
import br.com.welson.clinic.persistence.model.Doctor;
import br.com.welson.clinic.utils.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class EditDoctorBean implements Serializable {

    private Long doctorId;
    private Doctor doctor;

    @Inject
    private DAO<Doctor> doctorDAO;
    @Inject
    private DAO<ApplicationUser> applicationUserDAO;

    public void init() {
        doctor = doctorDAO.getById(doctorId);
    }

    @Transactional
    @ExceptionHandler
    public String save() {
        doctorDAO.update(doctor);
        FacesUtil.addInfoMessage(doctor.getName() + " atualizado com sucesso!");
        return "detail.xhtml?id=" + doctorId + "&faces-redirect=true";
    }

    @Transactional
    @ExceptionHandler
    public String remove() {
        ApplicationUser applicationUser = applicationUserDAO.getById(doctor.getApplicationUser().getId());
        applicationUser.setEnabled(false);
        applicationUser.getDoctor().setEnabled(false);
        applicationUser.getActivateAccount().forEach(activateAccount -> activateAccount.setEnabled(false));
        applicationUserDAO.update(applicationUser);
        FacesUtil.addInfoMessage(applicationUser.getDoctor().getName() + " foi deletado com sucesso!");
        return "list?faces-redirect=true";
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
