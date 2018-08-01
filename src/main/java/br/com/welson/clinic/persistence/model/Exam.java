package br.com.welson.clinic.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Duration;

@Entity
public class Exam extends AbstractEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Duration duration;
    @Column(nullable = false, precision = 2)
    private Double price;

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
}
