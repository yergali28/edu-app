package kz.iitu.beans;

import kz.iitu.dao.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CourseBean {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Cabinets getCabinetByName(String name) {

        Cabinets cabinets = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Cabinets> criteriaQuery = builder.createQuery(Cabinets.class);
            Root cabinetsTable = criteriaQuery.from(Cabinets.class);
            criteriaQuery.select(cabinetsTable).where(builder.equal(cabinetsTable.get("name"), name), builder.equal(cabinetsTable.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            cabinets = (Cabinets) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return cabinets;
    }

    public Courses getCourseByName(String name) {

        Courses course = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Courses> criteriaQuery = builder.createQuery(Courses.class);
            Root coursesTable = criteriaQuery.from(Courses.class);
            criteriaQuery.select(coursesTable).where(builder.equal(coursesTable.get("name"), name), builder.equal(coursesTable.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            course = (Courses) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return course;
    }

    public List<Courses> getCoursesList(Long cabinet_id){

        List<Courses> courses = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Courses> criteriaQuery = builder.createQuery(Courses.class);
            Root<Courses> coursesTable = criteriaQuery.from(Courses.class);
            criteriaQuery.select(coursesTable).where(
                    builder.equal(coursesTable.get("active"), 1),
                    builder.equal(coursesTable.get("cabinet").get("id"), cabinet_id)
            );

            Query query = session.createQuery(criteriaQuery);

            courses = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return courses;
    }

    public List<Cabinets> getCabinetsList(){

        List<Cabinets> cabinets = null;
        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Cabinets> criteriaQuery = builder.createQuery(Cabinets.class);
            Root<Cabinets> cabinetsRoot = criteriaQuery.from(Cabinets.class);
            criteriaQuery.select(cabinetsRoot).where(builder.equal(cabinetsRoot.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            cabinets = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return cabinets;
    }

    public List<Lessons> getLessonsList(Long cabinet_id){

        List<Lessons> lessonsList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Lessons> criteriaQuery = builder.createQuery(Lessons.class);
            Root<Lessons> lessonsTable = criteriaQuery.from(Lessons.class);

            criteriaQuery.select(lessonsTable).where(
                    builder.equal(lessonsTable.get("active"), 1)
            );

            Query query = session.createQuery(criteriaQuery);

            lessonsList = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return lessonsList;
    }

    public List<Lessons> getLessonsListByCourseId(Long course_id){

        List<Lessons> lessonsList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Lessons> criteriaQuery = builder.createQuery(Lessons.class);
            Root<Lessons> lessonsTable = criteriaQuery.from(Lessons.class);

            criteriaQuery.select(lessonsTable).where(
                    builder.equal(lessonsTable.get("active"), 1),
                    builder.equal(lessonsTable.get("course"), course_id)
            );

            Query query = session.createQuery(criteriaQuery);

            lessonsList = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return lessonsList;
    }

    public List<Lessons> getCourseLessonsList(Long cabinet_id, Long course_id){

        List<Lessons> lessonsList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Lessons> criteriaQuery = builder.createQuery(Lessons.class);
            Root<Lessons> lessonsTable = criteriaQuery.from(Lessons.class);

            criteriaQuery.select(lessonsTable).where(
                    builder.equal(lessonsTable.get("course").get("id"), course_id),
                    builder.equal(lessonsTable.get("active"), 1)
            );

            Query query = session.createQuery(criteriaQuery);

            lessonsList = query.getResultList();
            System.out.println("getCourseLessonsList " + lessonsList.size());

        }catch (Exception e){
            e.printStackTrace();
        }

        return lessonsList;
    }

    public List<LessonAttachements> getLessonAttList(Long lesson_id){

        List<LessonAttachements> lesson_atts = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<LessonAttachements> criteriaQuery = builder.createQuery(LessonAttachements.class);
            Root<LessonAttachements> lessonAttTable = criteriaQuery.from(LessonAttachements.class);

            criteriaQuery.select(lessonAttTable).where(
                    builder.equal(lessonAttTable.get("lesson_id").get("id"), lesson_id),
                    builder.equal(lessonAttTable.get("active"), 1)
            );

            Query query = session.createQuery(criteriaQuery);

            lesson_atts = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return lesson_atts;
    }

    public Courses getCourseById(Long id) {

        Courses course = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Courses> criteriaQuery = builder.createQuery(Courses.class);
            Root coursesTable = criteriaQuery.from(Courses.class);
            criteriaQuery.select(coursesTable).where(builder.equal(coursesTable.get("id"), id), builder.equal(coursesTable.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            course = (Courses) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return course;
    }

    public Lessons getLessonById(Long id) {

        Lessons lessons = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Lessons> criteriaQuery = builder.createQuery(Lessons.class);
            Root lessonsTable = criteriaQuery.from(Lessons.class);
            criteriaQuery.select(lessonsTable).where(builder.equal(lessonsTable.get("id"), id), builder.equal(lessonsTable.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            lessons = (Lessons) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return lessons;
    }

    public Cabinets getCabinetById(Long id) {

        Cabinets cabinets = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Cabinets> criteriaQuery = builder.createQuery(Cabinets.class);
            Root cabinetsTable = criteriaQuery.from(Cabinets.class);
            criteriaQuery.select(cabinetsTable).where(builder.equal(cabinetsTable.get("id"), id), builder.equal(cabinetsTable.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            cabinets = (Cabinets) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return cabinets;
    }

    public LessonAttachements getLAById(Long id) {

        LessonAttachements las = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<LessonAttachements> criteriaQuery = builder.createQuery(LessonAttachements.class);
            Root lasTable = criteriaQuery.from(LessonAttachements.class);
            criteriaQuery.select(lasTable).where(builder.equal(lasTable.get("id"), id), builder.equal(lasTable.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            las = (LessonAttachements) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return las;
    }

    public void addCourse(Courses course){

        try{

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(course);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void addCabinet(Cabinets cabinets){

        try{

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(cabinets);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void addLessons(Lessons lessons){

        try{

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(lessons);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void addLesson_attachments(LessonAttachements las){

        try{

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(las);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void updateCourse(Courses course){

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(course);
            transaction.commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateCabinets(Cabinets cab){

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(cab);
            transaction.commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateLessons(Lessons lesson){

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(lesson);
            transaction.commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateLesson_attachment(LessonAttachements las) {

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(las);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMark(Marks mark){

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(mark);
            transaction.commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addMarks(Marks marks){

        try{

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(marks);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Marks getMarkByLessonIdAndUserId(Long id, Long user_id) {

        Marks mark = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Marks> criteriaQuery = builder.createQuery(Marks.class);
            Root groupsTable = criteriaQuery.from(Marks.class);
            criteriaQuery.select(groupsTable).where(
                    builder.equal(groupsTable.get("lesson_id").get("id"), id),
                    builder.equal(groupsTable.get("user_id").get("id"), user_id)
            );

            Query query = session.createQuery(criteriaQuery);

            mark = (Marks) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return mark;
    }

    public Marks getMarkByLessonId(Long id) {

        Marks mark = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Marks> criteriaQuery = builder.createQuery(Marks.class);
            Root groupsTable = criteriaQuery.from(Marks.class);
            criteriaQuery.select(groupsTable).where(
                    builder.equal(groupsTable.get("lesson_id"), id));

            Query query = session.createQuery(criteriaQuery);

            mark = (Marks) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return mark;
    }

    public List<Marks> getMarksList(Long lesson_id){

        List<Marks> marksList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Marks> criteriaQuery = builder.createQuery(Marks.class);
            Root<Marks> marksTable = criteriaQuery.from(Marks.class);

            criteriaQuery.select(marksTable).where(
                    builder.equal(marksTable.get("active"), 1),
                    builder.equal(marksTable.get("lesson_id").get("id"), lesson_id)
            );

            Query query = session.createQuery(criteriaQuery);

            marksList = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return marksList;
    }

    // Group
    public Groups getGroupById(Long id) {

        Groups group = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Groups> criteriaQuery = builder.createQuery(Groups.class);
            Root groupsTable = criteriaQuery.from(Groups.class);
            criteriaQuery.select(groupsTable).where(builder.equal(groupsTable.get("id"), id), builder.equal(groupsTable.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            group = (Groups) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return group;
    }

    public void updateGroup(Groups group){

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(group);
            transaction.commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Groups> getgroupsList(Long cabinet_id){

        List<Groups> groupsList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Groups> criteriaQuery = builder.createQuery(Groups.class);
            Root<Groups> groupsTable = criteriaQuery.from(Groups.class);

            criteriaQuery.select(groupsTable).where(
                    builder.equal(groupsTable.get("active"), 1),
                    builder.equal(groupsTable.get("cabinet_id").get("id"), cabinet_id)
            );

            Query query = session.createQuery(criteriaQuery);

            groupsList = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return groupsList;
    }

    public void addGroup(Groups group){

        try{

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(group);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // Groups end

    public List<GroupCourses> getGroupCoursesListByCourseId(Long course_id){

        List<GroupCourses> groupCoursesList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<GroupCourses> criteriaQuery = builder.createQuery(GroupCourses.class);
            Root<GroupCourses> groupCoursesTable = criteriaQuery.from(GroupCourses.class);
            criteriaQuery.select(groupCoursesTable).where(
                    builder.equal(groupCoursesTable.get("active"), 1),
                    builder.equal(groupCoursesTable.get("course_id").get("id"), course_id)
            );

            Query query = session.createQuery(criteriaQuery);

            groupCoursesList = query.getResultList();
            System.out.println(groupCoursesList.size());

        }catch (Exception e){
            e.printStackTrace();
        }

        return groupCoursesList;
    }

    public List<GroupCourses> getGroupSomeCoursesList(Long group_id){

        List<GroupCourses> groupCoursesList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<GroupCourses> criteriaQuery = builder.createQuery(GroupCourses.class);
            Root<GroupCourses> groupCoursesTable = criteriaQuery.from(GroupCourses.class);
            criteriaQuery.select(groupCoursesTable).where(
                    builder.equal(groupCoursesTable.get("active"), 1),
                    builder.equal(groupCoursesTable.get("group_id").get("id"), group_id)
            );

            Query query = session.createQuery(criteriaQuery);

            groupCoursesList = query.getResultList();
            System.out.println(groupCoursesList.size());

        }catch (Exception e){
            e.printStackTrace();
        }

        return groupCoursesList;
    }

    public List<Groups> getGroupListOfUserById(Long user_id){

        List<GroupUsers> groupCoursesList = null;
        List<Groups> groupsList = new ArrayList<>();

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<GroupUsers> criteriaQuery = builder.createQuery(GroupUsers.class);
            Root<GroupUsers> groupCoursesTable = criteriaQuery.from(GroupUsers.class);
            criteriaQuery.select(groupCoursesTable).where(
                    builder.equal(groupCoursesTable.get("active"), 1),
                    builder.equal(groupCoursesTable.get("user_id").get("id"), user_id)
            );

            Query query = session.createQuery(criteriaQuery);

            groupCoursesList = query.getResultList();
            for(int i=0;i<groupCoursesList.size();i++) {
                groupsList.add(groupCoursesList.get(i).getGroup_id());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return groupsList;
    }

    public List<GroupCourses> getGroupCoursesListOfUserById(Long user_id){

        List<GroupCourses> groupCoursesList = new ArrayList<>();
        List<Long> groupIdList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<GroupUsers> criteriaQuery = builder.createQuery(GroupUsers.class);
            Root<GroupUsers> groupCoursesTable = criteriaQuery.from(GroupUsers.class);
            criteriaQuery.select(groupCoursesTable.get("group_id").get("id")).where(
                    builder.equal(groupCoursesTable.get("active"), 1),
                    builder.equal(groupCoursesTable.get("user_id").get("id"), user_id)
            );

            Query query = session.createQuery(criteriaQuery);

            groupIdList = query.getResultList();

            for(Long l: groupIdList) {
                try {

                    CriteriaQuery<GroupCourses> criteria = builder.createQuery(GroupCourses.class);
                    Root<GroupCourses> groupCoursesRoot = criteria.from(GroupCourses.class);
                    criteria.select(groupCoursesRoot).where(
                            builder.equal(groupCoursesRoot.get("active"), 1),
                            builder.equal(groupCoursesRoot.get("group_id").get("id"), l)
                    );

                    Query q = session.createQuery(criteria);

                    List<GroupCourses> list = q.getResultList();

                    groupCoursesList = Stream.concat(groupCoursesList.stream(), list.stream()).collect(Collectors.toList());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return groupCoursesList;
    }

    public List<GroupUsers> getGroupUsersListByGroupId(){

        List<GroupUsers> studentsList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<GroupUsers> criteriaQuery = builder.createQuery(GroupUsers.class);
            Root<GroupUsers> groupCoursesTable = criteriaQuery.from(GroupUsers.class);
            criteriaQuery.select(groupCoursesTable).where(
                    builder.equal(groupCoursesTable.get("active"), 1)
            );

            Query query = session.createQuery(criteriaQuery);

            studentsList = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return studentsList;
    }

    // Attendance
    public void addAttendance(Attendances attendance) {

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(attendance);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Attendances> getAttendancesList(Long lesson_id){

        List<Attendances> attendancesList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Attendances> criteriaQuery = builder.createQuery(Attendances.class);
            Root<Attendances> marksTable = criteriaQuery.from(Attendances.class);

            criteriaQuery.select(marksTable).where(
                    builder.equal(marksTable.get("active"), 1),
                    builder.equal(marksTable.get("lesson_id").get("id"), lesson_id)
            );

            Query query = session.createQuery(criteriaQuery);

            attendancesList = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return attendancesList;
    }

    public Attendances getAttendanceByLessonId(Long id) {

        Attendances mark = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Attendances> criteriaQuery = builder.createQuery(Attendances.class);
            Root groupsTable = criteriaQuery.from(Attendances.class);
            criteriaQuery.select(groupsTable).where(
                    builder.equal(groupsTable.get("lesson_id"), id));

            Query query = session.createQuery(criteriaQuery);

            mark = (Attendances) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return mark;
    }

    public double getMarkAvgByUserIdAndCourseId(Long user_id, Long course_id) {

        double markAvg = 0;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Marks> criteriaQuery = builder.createQuery(Marks.class);
            Root groupsTable = criteriaQuery.from(Marks.class);
            criteriaQuery.select(groupsTable);

            Query query =
                    session.createQuery("select avg(m.mark_value) from Marks m where m.user_id.id = :uId and m.lesson_id.course.id = :cId");
            query.setParameter("uId", user_id);
            query.setParameter("cId", course_id);

            markAvg = (double) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return markAvg;
    }

    public double getAttendanceAvgByUserIdAndCourseId(Long user_id, Long course_id) {

        double attendannceAvg = 0;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Attendances> criteriaQuery = builder.createQuery(Attendances.class);
            Root groupsTable = criteriaQuery.from(Attendances.class);
            criteriaQuery.select(groupsTable);

            Query query = session.createQuery("select avg(m.attendance_value) from Attendances m where m.user_id.id = :uId and m.lesson_id.course.id = :cId");
            query.setParameter("uId", user_id);
            query.setParameter("cId", course_id);

            attendannceAvg = (double) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return (attendannceAvg * 100);
    }
}
