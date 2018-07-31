package br.com.welson.clinic.bean.user.employee;

import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Employee;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class DetailEmployeeBean implements Serializable {

    private Long employeeId;
    private Employee employee;

    @Inject
    private DAO<Employee> employeeDAO;

    public void init() {
        employee = employeeDAO.getById(employeeId);
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
