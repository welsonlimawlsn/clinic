package br.com.welson.clinic.bean.user.employee;

import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.Employee;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ListEmployeeBean implements Serializable {

    private String searchField;
    private List<Employee> employeeList;

    @Inject
    private DAO<Employee> employeeDAO;

    public void init() {
        employeeList = employeeDAO.listAll();
    }

    public void search() {
        init();
        String search = searchField.toLowerCase();
        employeeList = employeeList.stream().filter(employee -> employee.getName().toLowerCase().contains(search) || employee.getCPF().contains(search)).collect(Collectors.toList());
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }
}
