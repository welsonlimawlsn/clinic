package br.com.welson.clinic.bean.consultation;

import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Consultation;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class DetailConsultationBean implements Serializable {

    private Long consultationId;
    private Consultation consultation;

    @Inject
    private DAO<Consultation> consultationDAO;

    public void init() {
        consultation = consultationDAO.getById(consultationId);
    }

    public String getTimeFromDuration() {
        return consultation.getDuration().toString().substring(2);
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
}
