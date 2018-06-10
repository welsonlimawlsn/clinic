package br.com.welson.clinic.bean.clinic;

import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Clinic;
import br.com.welson.clinic.utils.ContextUtils;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class DetailClinicBean implements Serializable {

    private Clinic clinic;
    private Long id;
    @Inject
    private DAO<Clinic> clinicDAO;

    public void init() {
        clinic = clinicDAO.getById(id);
        if (clinic == null) {
            ContextUtils.redirect("/404.xhtml");
        }
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
