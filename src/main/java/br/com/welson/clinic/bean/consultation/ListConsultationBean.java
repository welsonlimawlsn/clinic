package br.com.welson.clinic.bean.consultation;

import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Consultation;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ListConsultationBean implements Serializable {

    private List<Consultation> consultationList;

    @Inject
    private DAO<Consultation> consultationDAO;

    public void init() {
        consultationList = consultationDAO.listAll().stream().filter(Consultation::getEnabled).collect(Collectors.toList());
    }

    public List<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(List<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    public String getTimeFromDuration(Consultation consultation) {
        return consultation.getDuration().toString().substring(2);
    }
}
