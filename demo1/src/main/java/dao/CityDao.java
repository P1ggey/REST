package dao;
import models.Cities;
import models.Peoples;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import javax.management.Query;
import java.util.List;


public class CityDao {
// поиск по id
    public Cities findById(int id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Cities city = session.get(Cities.class, id);
        session.close();
        return city;
    }
// добавить
    public void save(Cities city){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(city);
        tx1.commit();
        session.close();
    }
// изменить
    public void update(Cities city){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(city);
        tx1.commit();
        session.close();
    }
// удалить
    public void delete(Cities city){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(city);
        tx1.commit();
        session.close();
    }
// вывод
    public List<Cities> findAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Cities> cities = (List<Cities>) session.createQuery("from Cities").list();
        return cities;
    }
// поиск по имени
    public List<Cities> findByName(String name){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Cities> city =(List<Cities>) session.createQuery("from Cities where name_city = :name").setParameter("name", name).list();
        return city;
    }
//    сортировка
    public List<Cities> findAllOrder(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Cities> cities = (List<Cities>) session.createQuery("from Cities order by name_city").list();
        return cities;
    }
// поиск по шаблонам
    public List<Cities> findAllPattern(String name){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Cities> cities = (List<Cities>) session.createQuery("from Cities where name_city like :name").setParameter("name", "%"+name+"%").list();
        return cities;
    }
}