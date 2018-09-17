package kz.iitu.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "group_courses")
public class GroupCourses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups group_id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses course_id;

    @Column(name = "active")
    private int active;

    public GroupCourses() {
    }

    public GroupCourses(Groups group_id, Courses course_id, int active) {
        this.group_id = group_id;
        this.course_id = course_id;
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

    public Courses getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Courses course_id) {
        this.course_id = course_id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
