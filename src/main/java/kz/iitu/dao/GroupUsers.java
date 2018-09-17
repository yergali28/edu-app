package kz.iitu.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "group_users")
public class GroupUsers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups group_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;

    @Column(name = "active")
    private int active;

    public GroupUsers() {
    }

    public GroupUsers(Groups group_id, Users user_id, int active) {
        this.group_id = group_id;
        this.user_id = user_id;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Groups getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Groups group_id) {
        this.group_id = group_id;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
