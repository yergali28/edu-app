package kz.iitu.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "cabinet_id")
    private Cabinets cabinet_id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Roles role_id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "active")
    private int active;

    public Users() {

    }

    public Users(Cabinets cabinet_id, String login, String password, Roles role_id, String name, String surname, int active) {
        this.cabinet_id = cabinet_id;
        this.login = login;
        this.password = password;
        this.role_id = role_id;
        this.name = name;
        this.surname = surname;
        this.active = active;
    }

    public Users(String login, String password, Roles role_id, String name, String surname, int active) {
        this.login = login;
        this.password = password;
        this.role_id = role_id;
        this.name = name;
        this.surname = surname;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cabinets getCabinet() {
        return cabinet_id;
    }

    public void setCabinet(Cabinets cabinet_id) {
        this.cabinet_id = cabinet_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole_id() {
        return role_id;
    }

    public void setRole_id(Roles role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", cabinet=" + cabinet_id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", active=" + active +
                '}';
    }
}
