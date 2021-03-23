package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity //аннотация
@Table (name = "countries")

//моедль данных таблицы Страны
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_country;

    private String name_country;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cities> cities;
//    конструктор по умолчанию
    public Country() {

    }
// конструктор
    public Country(String Name) {
        this.name_country = Name;
        cities = new ArrayList<>();
    }

    //добавление города
    public void addCity(Cities city){
        city.setCountry(this);
        cities.add(city);
    }
//    удаление города
    public void removeCity(Cities city) {
        cities.remove(city);
    }

//    получение id страны
    public int getId(){

        return id_country;
    }
// получение имени страны
    public String getName(){

        return name_country;
    }
// изменение название страны
    public void setName(String Name){

        this.name_country = Name;
    }
// получение списка городов
    public List<Cities> getCities(){

        return cities;
    }
// приводим к строке для удобства вывода
    public String toString(){
        return "Id: " + id_country + " Name: " + name_country;
    }
}
