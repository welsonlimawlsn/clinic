package br.com.welson.clinic.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Exam extends AbstractEntity{

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
