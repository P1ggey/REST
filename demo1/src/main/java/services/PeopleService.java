package services;

import models.Cities;

import dao.PeopleDao;
import models.Peoples;
import java.util.List;


public class PeopleService {
// создание dao
    private PeopleDao peopleDao = new PeopleDao();
// конструктор
    public PeopleService(){

    }
// поиск по id
    public Peoples findPeopleById(int id){
        return peopleDao.findById(id);
    }
// вывод всех
    public List<Peoples> findAllPeoples(){
        return peopleDao.findAll();
    }
// добавление
    public void savePeople(Peoples people){
        peopleDao.save(people);
    }
// удалние
    public void deletePeople(Peoples people){
        peopleDao.delete(people);
    }
//   обновление
    public void updatePeople(Peoples people){
        peopleDao.update(people);
    }
//   вывод людей по городу
    public List<Peoples> find(String name){
        return peopleDao.find(name);
    }
//    сортировка по id
    public List<Peoples> order(){
        return peopleDao.findAllOrder();
    }
//   поиск по шаблонам
    public List<Peoples> findAllPatterns(String name){
        return peopleDao.findAllPattern(name);
    }

}
