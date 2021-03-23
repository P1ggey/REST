package dao;

import models.Cities;
import models.Peoples;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;


public class PeopleDao {
//   поиск по id
    public Peoples findById(int id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Peoples people = session.get(Peoples.class, id);
        session.close();
        return people;
    }
// добавление
    public void save(Peoples people){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(people);
        tx1.commit();
        session.close();
    }
// обновление
    public void update(Peoples people){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(people);
        tx1.commit();
        session.close();
    }
// удаление
    public void delete(Peoples people){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(people);
        tx1.commit();
        session.close();
    }
// вывод
    public List<Peoples> findAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Peoples> peoples = (List<Peoples>) session.createQuery("from Peoples").list();
        return peoples;
    }
// сортировка
    public List<Peoples> findAllOrder(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Peoples> peoples = (List<Peoples>) session.createQuery("from Peoples order by id_people").list();
        return peoples;
    }

//    поиск по шаблону в имени человека
    public List<Peoples> findAllPattern(String name){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Peoples> peoples = (List<Peoples>) session.createQuery("from Peoples where name_people like :name").setParameter("name", "%"+name+"%").list();
        return peoples;
    }
// вывод всех людей в каком-то городе
    public List<Peoples> find(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Peoples> list = (List<Peoples>) session.createQuery("from Peoples where city.name_city = :name").setParameter("name", name).list();
        return list;
    }
}
