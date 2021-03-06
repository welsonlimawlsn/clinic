package br.com.welson.clinic.persistence.model;

import javax.persistence.*;

@Entity
public class Employee extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String CPF;
    @Column(nullable = false)
    private String phone;
    @Embedded
    private Address address;
    @OneToOne(mappedBy = "employee", cascade = {CascadeType.MERGE})
    private ApplicationUser applicationUser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
