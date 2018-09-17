package kz.iitu.beans;

import kz.iitu.dao.GroupCourses;
import kz.iitu.dao.GroupUsers;
import kz.iitu.dao.Roles;
import kz.iitu.dao.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class UserBean {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void addUser(Users user) {

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(user);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Roles getRoleById(int id) {

        Roles role = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Roles> criteriaQuery = builder.createQuery(Roles.class);
            Root rolesTable = criteriaQuery.from(Roles.class);
            criteriaQuery.select(rolesTable).where(builder.equal(rolesTable.get("id"), id));

            Query query = session.createQuery(criteriaQuery);

            role = (Roles) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return role;
    }

    public Roles getRoleByName(String name) {
        Roles role = null;

        try {

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Roles> criteriaQuery = builder.createQuery(Roles.class);
            Root<Roles> rolesTable = criteriaQuery.from(Roles.class);
            criteriaQuery.select(rolesTable);

            Predicate predicateName = builder.equal(rolesTable.get("name"), name);

            criteriaQuery.where(builder.and(predicateName));
            Query query = session.createQuery(criteriaQuery);
            role = (Roles) query.getSingleResult();

            System.out.println("Role name " + role.getName());
            return role;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return role;
    }

    public Users getUserById(Long id){
        Users user = null;
        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Users> criteriaQuery = builder.createQuery(Users.class);
            Root<Users> usersTable = criteriaQuery.from(Users.class);
            criteriaQuery.select(usersTable);

            Predicate predicateLogin = builder.equal(usersTable.get("id"), id);

            criteriaQuery.where(builder.and(predicateLogin));
            Query query = session.createQuery(criteriaQuery);
            user = (Users)query.getSingleResult();
            return user;

        }catch(Exception e){
            e.printStackTrace();

        }
        return user;

    }


    public Users getUser(String login, String password) {

        Users user = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Users> criteriaQuery = builder.createQuery(Users.class);
            Root<Users> usersTable = criteriaQuery.from(Users.class);
            criteriaQuery.select(usersTable);

            Predicate predicateLogin = builder.equal(usersTable.get("login"), login);
            Predicate predicatePass = builder.equal(usersTable.get("password"), password);

            criteriaQuery.where(builder.and(predicateLogin, predicatePass));
            Query query = session.createQuery(criteriaQuery);
            user = (Users)query.getSingleResult();
            return user;

        }catch(Exception e){
            e.printStackTrace();

        }
        return user;
    }

    public List<Users> getAllUsers(){

        List<Users> users = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Users> criteriaQuery = builder.createQuery(Users.class);
            Root<Users> usersTable = criteriaQuery.from(Users.class);
            criteriaQuery.select(usersTable);

            Query query = session.createQuery(criteriaQuery);

            users = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return users;
    }

    public void updateUser(Users users){

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(users);
            transaction.commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Roles> getRolesList(){

        List<Roles> roles = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Roles> criteriaQuery = builder.createQuery(Roles.class);
            Root<Roles> rolesTable = criteriaQuery.from(Roles.class);
            criteriaQuery.select(rolesTable);

            Query query = session.createQuery(criteriaQuery);

            roles = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return roles;
    }

    public List<GroupUsers> getGroupSomeUsersList(Long currentUserId, Long cab_id, Long group_id, Long role_id){

        List<GroupUsers> groupUsersList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<GroupUsers> criteriaQuery = builder.createQuery(GroupUsers.class);
            Root<GroupUsers> groupUsersTable = criteriaQuery.from(GroupUsers.class);
            criteriaQuery.select(groupUsersTable).where(
                    builder.equal(groupUsersTable.get("active"), 1),
                    builder.notEqual(groupUsersTable.get("user_id").get("id"), currentUserId),
                    builder.equal(groupUsersTable.get("user_id").get("cabinet_id").get("id"), cab_id),
                    builder.equal(groupUsersTable.get("user_id").get("role_id").get("id"), role_id),
                    builder.equal(groupUsersTable.get("group_id").get("cabinet_id").get("id"), cab_id),
                    builder.equal(groupUsersTable.get("group_id").get("id"), group_id)
            );

            Query query = session.createQuery(criteriaQuery);

            groupUsersList = query.getResultList();
            System.out.println(groupUsersList.size());

        }catch (Exception e){
            e.printStackTrace();
        }

        return groupUsersList;
    }

    public void addGroupUsers(GroupUsers groupUsers){

        try{

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(groupUsers);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public GroupUsers getGroupUsersById(Long id) {

        GroupUsers groupUsers = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<GroupUsers> criteriaQuery = builder.createQuery(GroupUsers.class);
            Root guTable = criteriaQuery.from(GroupUsers.class);
            criteriaQuery.select(guTable).where(builder.equal(guTable.get("id"), id), builder.equal(guTable.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            groupUsers = (GroupUsers) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return groupUsers;
    }

    public void updateGroupUsers(GroupUsers groupUsers){

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(groupUsers);
            transaction.commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addGroupCourses(GroupCourses groupCourses){

        try{

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(groupCourses);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
