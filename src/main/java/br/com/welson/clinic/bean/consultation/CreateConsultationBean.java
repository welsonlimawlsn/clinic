package br.com.welson.clinic.bean.consultation;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.AbstractEntity;
import br.com.welson.clinic.persistence.model.Consultation;
import br.com.welson.clinic.persistence.model.Doctor;
import br.com.welson.clinic.utils.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class CreateConsultationBean implements Serializable {

    private Doctor selectedFrom;
    private Doctor selectedTo;

    private List<Doctor> doctorList;

    private Consultation consultation;
    private int durationInMinutes;

    private DAO<Doctor> doctorDAO;
    private DAO<Consultation> consultationDAO;

    @Inject
    public CreateConsultationBean(DAO<Doctor> doctorDAO, DAO<Consultation> consultationDAO) {
        this.doctorDAO = doctorDAO;
        this.consultationDAO = consultationDAO;
    }

    public void init() {
        consultation = new Consultation();
        doctorList = doctorDAO.listAll().stream().filter(Doctor::getEnabled).collect(Collectors.toList());
        consultation.setDoctorList(new ArrayList<>());
    }

    public void addFromTo() {
        consultation.getDoctorList().add(selectedFrom);
        doctorList.remove(selectedFrom);
    }

    public void addToFrom() {
        doctorList.add(selectedTo);
        consultation.getDoctorList().remove(selectedTo);
    }

    @ExceptionHandler
    @Transactional
    public String save() {
        if (consultation.getDoctorList().isEmpty()) {
            throw new RuntimeException("Selecione os medicos aptos a fazer esta consulta!");
        }
        consultation.setDuration(Duration.ZERO.plusMinutes(durationInMinutes));
        consultation.setDoctorList(consultation.getDoctorList().stream().map(AbstractEntity::getId).map(id -> doctorDAO.getById(id)).collect(Collectors.toList()));
        consultationDAO.save(consultation);
        FacesUtil.addInfoMessage("Consulta criada com sucesso!");
        return "list?faces-redirect=true";
    }

    public Doctor getSelectedFrom() {
        return selectedFrom;
    }

    public void setSelectedFrom(Doctor selectedFrom) {
        this.selectedFrom = selectedFrom;
    }

    public Doctor getSelectedTo() {
        return selectedTo;
    }

    public void setSelectedTo(Doctor selectedTo) {
        this.selectedTo = selectedTo;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
