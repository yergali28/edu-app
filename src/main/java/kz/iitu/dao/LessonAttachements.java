package kz.iitu.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "lesson_attachements")
public class LessonAttachements implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lessons lesson_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "mime", columnDefinition = "varchar(255)")
    private String mime;

    @Column(name = "size", columnDefinition = "bigint(20)")
    private BigInteger size;

    @Column(name = "attachment", columnDefinition = "longblob")
    private Blob attachment;

    @Column(name = "download_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date download_date;

    @Column(name = "active")
    private int active;

    public LessonAttachements() {
    }

    public LessonAttachements(Lessons lesson_id, Users user_id, String name, String mime, BigInteger size, Blob attachment, Date download_date, int active) {
        this.lesson_id = lesson_id;
        this.user_id = user_id;
        this.name = name;
        this.mime = mime;
        this.size = size;
        this.attachment = attachment;
        this.download_date = download_date;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lessons getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Lessons lesson_id) {
        this.lesson_id = lesson_id;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    public Blob getAttachment() {
        return attachment;
    }

    public void setAttachment(Blob attachment) {
        this.attachment = attachment;
    }

    public Date getDownload_date() {
        return download_date;
    }

    public void setDownload_date(Date download_date) {
        this.download_date = download_date;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
