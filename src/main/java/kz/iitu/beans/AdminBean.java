package kz.iitu.beans;

import kz.iitu.dao.Courses;
import kz.iitu.dao.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

public class AdminBean {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void addCourse(Courses cabinet) {

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(cabinet);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Users> getAdminsList(){

        List<Users> adminsList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Users> criteriaQuery = builder.createQuery(Users.class);
            Root<Users> adminsTable = criteriaQuery.from(Users.class);
            criteriaQuery.select(adminsTable).where(builder.equal(adminsTable.get("role_id").get("id"), 1), builder.equal(adminsTable.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            adminsList = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return adminsList;
    }

    public List<Users> getSomeUsersList(Long currentUserId, Long role, Long cab_id){

        List<Users> studentsList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            System.out.println("Cabinet id: " + cab_id);

            CriteriaQuery<Users> criteriaQuery = builder.createQuery(Users.class);
            Root<Users> adminsTable = criteriaQuery.from(Users.class);
            criteriaQuery.select(adminsTable).where(
                    builder.equal(adminsTable.get("role_id").get("id"), role),
                    builder.equal(adminsTable.get("active"), 1),
                    builder.notEqual(adminsTable.get("id"), currentUserId),
                    builder.equal(adminsTable.get("cabinet_id").get("id"), cab_id)
            );

            Query query = session.createQuery(criteriaQuery);

            studentsList = query.getResultList();
            System.out.println(studentsList.size());

        }catch (Exception e){
            e.printStackTrace();
        }

        return studentsList;
    }

    public void addUser(Users admin) {

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(admin);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
