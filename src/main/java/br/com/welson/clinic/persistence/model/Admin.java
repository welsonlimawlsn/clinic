package br.com.welson.clinic.persistence.model;

import javax.persistence.Entity;

@Entity
public class Admin extends AbstractEntity {

    private String name;
    private String CPF;
    private String phone;

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
}
