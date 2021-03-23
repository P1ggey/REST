package dao;

import models.Country;
import models.Cities;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.management.Query;
import java.util.List;
import java.util.Queue;

public class CountryDao {
// поиск страны по id
    public Country findById(int id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Country country = session.get(Country.class, id);
        session.close();
        return country;
    }
// сохранение страны в таблице
    public void save(Country country){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(country);
        tx1.commit();
        session.close();
    }
// обновление страны
    public void update(Country country){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(country);
        tx1.commit();
        session.close();
    }
// удаление страны
    public void delete(Country country){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(country);
        tx1.commit();
        session.close();
    }
// поиск по id города
    public Cities findCityById(int id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Cities city = session.get(Cities.class, id);
        session.close();
        return city;
    }
// вывод всех записей в таблице
    public List<Country> findAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Country> countries = (List<Country>) session.createQuery("from Country").list();
        session.close();
        return countries;
    }
//   поиск по имени
    public List<Country> findName(String name){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Country> countries = (List<Country>) session.createQuery("from Country where name_country = :name").setParameter("name", name).list();
        session.close();
        return countries;
    }
//   сортировка
    public List<Country> findAllOrder(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Country> countries = (List<Country>) session.createQuery("from Country order by name_country").list();
        session.close();
        return countries;
    }
//    поиск по шаблону
    public List<Country> findAllPattern(String name){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Country> countries = (List<Country>) session.createQuery("from Country where name_country like :name").setParameter("name", "%"+name+"%").list();
        return countries;
    }

}
