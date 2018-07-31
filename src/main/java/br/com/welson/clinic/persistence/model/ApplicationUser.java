package br.com.welson.clinic.persistence.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ApplicationUser extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Doctor doctor;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Employee employee;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Patient patient;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Admin admin;
    @OneToMany(mappedBy = "applicationUser", cascade = {CascadeType.MERGE})
    private List<ActivateAccount> activateAccount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<ActivateAccount> getActivateAccount() {
        return activateAccount;
    }

    public void setActivateAccount(List<ActivateAccount> activateAccount) {
        this.activateAccount = activateAccount;
    }
}
