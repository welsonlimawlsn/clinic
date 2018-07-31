package br.com.welson.clinic.bean.user.employee;

import br.com.welson.clinic.annotation.ExceptionHandler;
import br.com.welson.clinic.annotation.Transactional;
import br.com.welson.clinic.ejb.CepEJB;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Address;
import br.com.welson.clinic.persistence.model.ApplicationUser;
import br.com.welson.clinic.persistence.model.CEP;
import br.com.welson.clinic.persistence.model.Employee;
import br.com.welson.clinic.utils.AddressUtils;
import br.com.welson.clinic.utils.FacesUtil;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class EditEmployeeBean implements Serializable {

    private Long employeeId;
    private Employee employee;

    @Inject
    private DAO<Employee> employeeDAO;
    @Inject
    private DAO<ApplicationUser> applicationUserDAO;
    @EJB
    private CepEJB cepEJB;

    public void init() {
        employee = employeeDAO.getById(employeeId);
    }

    @Transactional
    @ExceptionHandler
    public String save() {
        employeeDAO.update(employee);
        FacesUtil.addInfoMessage(employee.getName() + " alterado com sucesso!");
        return "detail?id=" + employeeId + "&faces-redirect=true";
    }

    @Transactional
    @ExceptionHandler
    public String remove() {
        ApplicationUser applicationUser = applicationUserDAO.getById(employee.getApplicationUser().getId());
        applicationUser.setEnabled(false);
        applicationUser.getEmployee().setEnabled(false);
        applicationUser.getActivateAccount().forEach(activateAccount -> activateAccount.setEnabled(false));
        applicationUserDAO.delete(applicationUser);
        FacesUtil.addInfoMessage(employee.getName() + " foi deletado com sucesso!");
        return "list?faces-redirect=true";
    }

    @ExceptionHandler
    public void completeAddress() {
        try {
            CEP address = cepEJB.getAddress(employee.getAddress().getZipCode());
            AddressUtils.convertCepToAddress(address, employee.getAddress());
        } catch (Exception e) {
            employee.setAddress(new Address());
            throw new RuntimeException("CEP invalido!");
        }
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
