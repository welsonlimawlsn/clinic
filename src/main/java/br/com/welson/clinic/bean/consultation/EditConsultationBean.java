package br.com.welson.clinic.bean.consultation;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Consultation;
import br.com.welson.clinic.persistence.model.Doctor;
import br.com.welson.clinic.utils.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.Duration;
import java.util.List;

@Named
@ViewScoped
public class EditConsultationBean implements Serializable {

    private Long consultationId;
    private Consultation consultation;
    private List<Doctor> doctorList;
    private long durationInMinutes;

    private Doctor selectedFrom;
    private Doctor selectedTo;

    private DAO<Consultation> consultationDAO;
    private DAO<Doctor> doctorDAO;

    @Inject
    public EditConsultationBean(DAO<Consultation> consultationDAO, DAO<Doctor> doctorDAO) {
        this.consultationDAO = consultationDAO;
        this.doctorDAO = doctorDAO;
    }

    public void init() {
        consultation = consultationDAO.getById(consultationId);
        doctorList = doctorDAO.listAll();
        consultation.getDoctorList().forEach(doctor -> doctorList.remove(doctor));
        durationInMinutes = consultation.getDuration().toMinutes();
    }

    public void addFromTo() {
        consultation.getDoctorList().add(selectedFrom);
        doctorList.remove(selectedFrom);
    }

    public void addToFrom() {
        doctorList.add(selectedTo);
        consultation.getDoctorList().remove(selectedTo);
    }

    @Transactional
    @ExceptionHandler
    public String save() {
        consultation.setDuration(Duration.ZERO.plusMinutes(durationInMinutes));
        consultationDAO.update(consultation);
        FacesUtil.addInfoMessage(consultation.getName() + " foi alterado com sucesso!");
        return "detail?consultationId=" + consultationId + "&faces-redirect=true";
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(long durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Doctor getSelectedFrom() {
        return selectedFrom;
    }

    public void setSelectedFrom(Doctor selectedFrom) {
        this.selectedFrom = selectedFrom;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public Doctor getSelectedTo() {
        return selectedTo;
    }

    public void setSelectedTo(Doctor selectedTo) {
        this.selectedTo = selectedTo;
    }
}
