package br.com.welson.clinic.persistence.model;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;

@Entity
public class Surgery extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Duration estimatedDuration;
    @Column(nullable = false)
    private Double price;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "surgery_id"), inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> doctorList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Duration estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
}
