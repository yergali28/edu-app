package kz.iitu.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "courses")
public class Courses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cabinet_id")
    private Cabinets cabinet;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private int active;

    public Courses() {
    }

    public Courses(Cabinets cabinet, String name, String description, int active) {
        this.cabinet = cabinet;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cabinets getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinets cabinet) {
        this.cabinet = cabinet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
