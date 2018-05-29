package br.com.welson.clinic.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Duration;

@Entity
public class Surgery extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Duration estimatedDuration;
    @ManyToOne
    private Marking marking;

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

    public Marking getMarking() {
        return marking;
    }

    public void setMarking(Marking marking) {
        this.marking = marking;
    }
}
