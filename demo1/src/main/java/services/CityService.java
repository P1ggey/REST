package services;

import dao.CityDao;
import models.Cities;

import java.util.List;

public class CityService {
// создание Dao
    private CityDao cityDao = new CityDao();

    public CityService(){

    }
// поиск по id
    public Cities findCityById(int id){
        return cityDao.findById(id);
    }
// вывод всех городов
    public List<Cities> findAllCities(){
        return cityDao.findAll();
    }
// добавление в бд
    public void saveCity(Cities city){
        cityDao.save(city);
    }
// удаление
    public void deleteCity(Cities city){
        cityDao.delete(city);
    }
// обновление
    public void updateCity(Cities city){
        cityDao.update(city);
    }
// поиск по имени
    public List<Cities> findCityByName(String name){
        return cityDao.findByName(name);
    }
//сортировка
    public List<Cities> findAllCityOrder(){
        return cityDao.findAllOrder();
    }
//поиск по шаблону
    public List<Cities> findAllPattern(String name){
        return cityDao.findAllPattern(name);
    }

}
