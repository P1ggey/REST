package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //аннотация
@Table(name = "cities")
//моедль данных таблицы Города
public class Cities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_city;

    private String name_city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Peoples> peoples;

    //    конструктор по умолчанию
    public Cities() {

    }
// конструктор
    public Cities(String City) {
        this.name_city = City;
    }
// получение id
    public int getId(){
        return id_city;
    }
// получить название города
    public String getCity(){
        return name_city;
    }
// измениние название города
    public void setCity(String City){
        this.name_city = City;
    }
// изменить страну на другую
    public void setCountry(Country Country){
        this.country = Country;
    }

// вывод
    @Override
    public String toString() {
        return "Id: " + id_city + " City: " + name_city + " Country: " + country.getName();
    }

}
