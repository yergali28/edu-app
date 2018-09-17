package kz.iitu.beans;

import kz.iitu.dao.Lessons;
import kz.iitu.dao.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class NewsBean {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void addNews(News news) {

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(news);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<News> getNewsList(Long cabinet_id){

        List<News> newsList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<News> criteriaQuery = builder.createQuery(News.class);
            Root<News> tasksTable = criteriaQuery.from(News.class);
            criteriaQuery.select(tasksTable).where(
                    builder.equal(tasksTable.get("active"), 1),
                    builder.equal(tasksTable.get("cabinet_id").get("id"), cabinet_id)
            );

            Query query = session.createQuery(criteriaQuery);

            newsList = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return newsList;
    }

    public List<News> getNewsListWithoutCabinetId(){

        List<News> newsList = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<News> criteriaQuery = builder.createQuery(News.class);
            Root<News> tasksTable = criteriaQuery.from(News.class);
            criteriaQuery.select(tasksTable).where(
                    builder.equal(tasksTable.get("active"), 1)
            );

            Query query = session.createQuery(criteriaQuery);

            newsList = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return newsList;
    }

    public void deleteNews(Long id) {
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            News b = getNewsById(id);
            b.setActive(0);
            session.update(b);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public News getNewsById(Long id) {

        News task = null;

        try{

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<News> criteriaQuery = builder.createQuery(News.class);
            Root newsTable = criteriaQuery.from(News.class);
            criteriaQuery.select(newsTable).where(builder.equal(newsTable.get("id"), id), builder.equal(newsTable.get("active"), 1));

            Query query = session.createQuery(criteriaQuery);

            task = (News) query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }

        return task;
    }

    public void editNews(Long news_id, Long cabinet_id, Long admin_id, String title, String content, Date post_date){

        try {

            Session session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<News> criteriaQuery = builder.createQuery(News.class);
            Root<News> newsTable = criteriaQuery.from(News.class);

            CriteriaUpdate update = builder.createCriteriaUpdate(News.class);

            Root news = update.from(News.class);
            update.where(builder.equal(newsTable.get("news").get("id"), news_id));

            update.set(news.get("cabinet_id"), cabinet_id);
            update.set(news.get("admin_id"), admin_id);
            update.set(news.get("title"), title);
            update.set(news.get("content"), content);
            update.set(news.get("post_date"), post_date);

            Query query = session.createQuery(update);
            int rowCount = query.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateNews(News news){

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(news);
            transaction.commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
