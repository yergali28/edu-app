package kz.iitu.beans;

import kz.iitu.dao.Cabinets;
import kz.iitu.dao.SuperAdmins;
import kz.iitu.dao.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class SuperAdminBean {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public SuperAdmins getSuperAdmin(String login, String password) {

        SuperAdmins s_admin = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SuperAdmins> criteriaQuery = builder.createQuery(SuperAdmins.class);
            Root<SuperAdmins> sadminsTable = criteriaQuery.from(SuperAdmins.class);
            criteriaQuery.select(sadminsTable);

            Predicate predicateLogin = builder.equal(sadminsTable.get("login"), login);
            Predicate predicatePass = builder.equal(sadminsTable.get("password"), password);

            criteriaQuery.where(builder.and(predicateLogin, predicatePass));
            Query query = session.createQuery(criteriaQuery);
            s_admin = (SuperAdmins) query.getSingleResult();
            return s_admin;

        }catch(Exception e){
            e.printStackTrace();

        }
        return s_admin;
    }

    public List<SuperAdmins> getSuperAdminsList(){

        List<SuperAdmins> super_admins = null;
        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<SuperAdmins> criteriaQuery = builder.createQuery(SuperAdmins.class);
            Root<SuperAdmins> super_adminsTable = criteriaQuery.from(SuperAdmins.class);
            criteriaQuery.select(super_adminsTable).where(
                    builder.equal(super_adminsTable.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            super_admins = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return super_admins;
    }

    public void addSuperAdmin(SuperAdmins s_admin){

        try{

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(s_admin);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public SuperAdmins getSuperAdminById(Long id){
        SuperAdmins super_admin = null;
        try{
            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SuperAdmins> criteriaQuery = builder.createQuery(SuperAdmins.class);
            Root<SuperAdmins> superAdminsRoot = criteriaQuery.from(SuperAdmins.class);
            criteriaQuery.select(superAdminsRoot);

            Predicate predicateLogin = builder.equal(superAdminsRoot.get("id"), id);

            criteriaQuery.where(builder.and(predicateLogin));
            Query query = session.createQuery(criteriaQuery);
            super_admin = (SuperAdmins) query.getSingleResult();

            return super_admin;

        }catch(Exception e){
            e.printStackTrace();

        }
        return super_admin;

    }

    public void updateSuperAdmin(SuperAdmins super_admins){

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(super_admins);
            transaction.commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addAdmin(Users admin) {

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
