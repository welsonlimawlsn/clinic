package br.com.welson.clinic.persistence.model;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;

@Entity
public class Consultation extends AbstractEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Duration duration;
    @Column(nullable = false, precision = 2)
    private Double price;
    @JoinTable(joinColumns = @JoinColumn(name = "consultation_id"), inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> doctorList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
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
