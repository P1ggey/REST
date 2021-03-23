package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // аннотация
@Table(name = "peoples")
public class Peoples {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_people;

    private String name_people;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_city")
    private Cities city;
// конструктор по умолчанию
    public Peoples(){

    }
// конструктор
    public Peoples(String name_people){
        this.name_people = name_people;
    }
// получить id человека
    public int getId(){
        return id_people;
    }
// получить имя человека
    public String getName(){
        return name_people;
    }
// изменить имя человека
    public void setName(String name){
        this.name_people = name;
    }
// изменить город на другой
    public void setCity(Cities city){
        this.city = city;
    }
//    вывод
    public String toString(){
        return "Id: "+ id_people +" Name: "+name_people+" City: " + city.getCity();
    }
}
