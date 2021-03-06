package br.com.welson.clinic.persistence.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Doctor extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String CRM;
    @Column(nullable = false, unique = true)
    private String CPF;
    @Column(nullable = false)
    private String phone;
    @OneToOne(mappedBy = "doctor", cascade = {CascadeType.MERGE})
    private ApplicationUser applicationUser;
    @ManyToMany(mappedBy = "doctorList")
    private List<Consultation> consultationList;
    @ManyToMany(mappedBy = "doctorList")
    private List<Surgery> surgeryList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public List<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(List<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    public List<Surgery> getSurgeryList() {
        return surgeryList;
    }

    public void setSurgeryList(List<Surgery> surgeryList) {
        this.surgeryList = surgeryList;
    }
}
