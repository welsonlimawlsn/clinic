package br.com.welson.clinic.converters;

import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Doctor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DoctorConverter implements Converter {

    @Inject
    private DAO<Doctor> doctorDAO;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s.matches("\\d")) {
            return doctorDAO.getById(Long.parseLong(s));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Doctor doctor = (Doctor) o;
        if (doctor != null && doctor.getId() != null) {
            return doctor.getId().toString();
        }
        return null;
    }
}
