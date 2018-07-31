package br.com.welson.clinic.bean.clinic;

import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Clinic;
import br.com.welson.clinic.utils.FacesUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class ClinicBean implements Serializable {

    private Clinic clinic;

    @Inject
    private DAO<Clinic> clinicDAO;


    @PostConstruct
    public void init() {
        List<Clinic> clinics = clinicDAO.listAll();
        if (clinics.size() == 0) {
            FacesUtil.redirectTo("/restricted/admin/clinic/configure.xhtml");
        } else {
            clinic = clinics.get(0);
        }
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
