package kz.iitu.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "groups")
public class Groups implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cabinet_id")
    private Cabinets cabinet_id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created_date;

    @Column(name = "active")
    private int active;

    public Groups() {
    }

    public Groups(Cabinets cabinet_id, String name, Date created_date, int active) {
        this.cabinet_id = cabinet_id;
        this.name = name;
        this.created_date = created_date;
        this.active = active;
    }

    public String changeFormat() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(created_date);
        return today;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cabinets getCabinet_id() {
        return cabinet_id;
    }

    public void setCabinet_id(Cabinets cabinet_id) {
        this.cabinet_id = cabinet_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
