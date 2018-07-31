package br.com.welson.clinic.bean.clinic;

import br.com.welson.clinic.annotation.ExceptionHandler;
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

    @Inject
    private ClinicBean clinicBean;

    private Clinic clinic;

    @ExceptionHandler
    public void init() {
        clinic = clinicBean.getClinic();
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
