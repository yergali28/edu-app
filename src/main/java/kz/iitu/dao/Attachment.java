package kz.iitu.dao;

import java.io.Serializable;
import java.util.List;

public class Attachment implements Serializable {

    private Lessons lesson;
    private List<LessonAttachements> attachements;

    public Attachment() {
    }

    public Attachment(Lessons lesson, List<LessonAttachements> attachements) {
        this.lesson = lesson;
        this.attachements = attachements;
    }

    public Lessons getLesson() {
        return lesson;
    }

    public void setLesson(Lessons lesson) {
        this.lesson = lesson;
    }

    public List<LessonAttachements> getAttachements() {
        return attachements;
    }

    public void setAttachements(List<LessonAttachements> attachements) {
        this.attachements = attachements;
    }
}
