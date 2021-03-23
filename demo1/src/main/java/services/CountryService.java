package services;

import dao.CountryDao;
import models.Cities;
import models.Country;
import java.util.List;

public class CountryService {
// создание Dao
    private CountryDao countryDao = new CountryDao();
// конструктор по умолчанию
    public CountryService() {

    }
// поиск по id
    public Country findCountry(int id){
        return countryDao.findById(id);
    }
// добавление в бд
    public void saveCountry(Country country){
        countryDao.save(country);
    }
// обновление
    public void updateCountry(Country country){
        countryDao.update(country);
    }
// вывод
    public List<Country> findAllCountry(){
        return countryDao.findAll();
    }
// удалние страны
    public  void deleteCountry(Country country){
        countryDao.delete(country);
    }
// поиск по названию
    public List<Country> findByName(String name){
        return countryDao.findName(name);
    }
// сортировка
    public List<Country> findAllOrder(){
        return countryDao.findAllOrder();
    }
// поиск по шаблону
    public List<Country> findAllPattern(String name){
        return countryDao.findAllPattern(name);
    }

}
