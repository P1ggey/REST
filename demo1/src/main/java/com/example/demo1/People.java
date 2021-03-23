package com.example.demo1;

import models.Cities;
import models.Peoples;
import services.CityService;
import services.PeopleService;

import javax.ws.rs.*;
import java.util.List;

@Path("/peoples")
public class People {
    public static int count;

    //    вывод всей таблицы Люди
    @GET
    @Produces("text/html")
    @Path("/show")
    public String showCity() {
        PeopleService peopleService = new PeopleService();
        List<Peoples> peoples = peopleService.findAllPeoples();
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Peoples</title>\n" +
                "</head>\n" + "<body>";
        for (Peoples s : peoples) {
            html += "<div>" + s.toString() + "</div>";
        }
        return html + "</body>";
    }

    //    добавление человека в таблицу
    @POST
    @Produces("text/plain")
    @Path("/add")
    public String addPeople(@FormParam("name_people") String name_people, @FormParam("id_city") int id_city) {
        try {
            PeopleService peopleService = new PeopleService();
            CityService cityService = new CityService();
            Peoples people = new Peoples(name_people);
            Cities city = cityService.findCityById(id_city);
            people.setCity(city);
            peopleService.savePeople(people);
            return "successful add";
        } catch (Exception e) {
            return "Exception";
        }

    }

    @POST
    @Produces("text/plain")
    @Path("/update")
//  Обновляет человека в базе данных, по id
    public String uppPeople(@FormParam("id_people") int id_people,
                          @FormParam("name_people_up") String name_people_up,
                          @FormParam("id_city_up") int id_city_up)
    {
        try{
            PeopleService peopleService = new PeopleService();
            Peoples people = peopleService.findPeopleById(id_people);
            if (name_people_up != ""){
                people.setName(name_people_up);
            }
            if (id_city_up != 0){
                    CityService cityService = new CityService();
                    Cities city = cityService.findCityById(id_city_up);
                    people.setCity(city);

            }
            peopleService.updatePeople(people);

            return "successful upp";
        }
        catch (Exception e){
            return "Exception";
        }
    }

    //    удаление человека из таблицы
    @POST
    @Produces("text/plain")
    @Path("/del")
    public String delPeople(@FormParam("id_people") int id_people) {
        try {
            PeopleService peopleService = new PeopleService();
            Peoples people = peopleService.findPeopleById(id_people);
            peopleService.deletePeople(people);
            return "successful del";
        } catch (Exception e) {
            return "Exception";
        }
    }

    @POST
    @Produces("text/html")
    @Path("/h")
//    выводит количество страниц пагинации
    public String g(@FormParam("count") int count) {
        PeopleService peopleService = new PeopleService();
        List<Peoples> peoples = peopleService.findAllPeoples();
        String string = "";
        this.count = count;
        int size = peoples.size();
        int pages = (size / count);
        if (size % count > 0) {
            pages++;
        }
        if (count > size) {
            string += "<p><a href = \"h/" + 1 + "\">" + 1 + "</a></p>";
        } else {
            for (int i = 1; i <= pages; i++) {
                string += "<p><a href = \"h/" + i + "\">" + i + "</a></p>";
            }

        }
        return string;
    }

    @GET
    @Produces("text/html")
    @Path("/h/{c}")
//    выводит количесво элементов базы данных людей, которое ранее было указано в форме
    public String pag(@PathParam("c") int c, String args[]) {

        PeopleService peopleService = new PeopleService();
        List<Peoples> peoples = peopleService.findAllPeoples();
        int size = peoples.size();
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Peoples</title>\n" +
                "</head>\n" + "<body>";
        if (c == 1) {
            for (int i = 0; i < count; i++) {
                if (i >= size) {
                    break;
                }
                html += "<div>" + peoples.get(i).toString() + "</div>";
            }
        } else {
            for (int i = ((c - 1) * count); i < (c * count); i++) {
                if (i >= size) {
                    break;
                } else {
                    html += "<div>" + peoples.get(i).toString() + "</div>";
                }
            }
        }
        return html + "</body>";

    }

    @POST
    @Produces("text/html")
    @Path("/find")
//    ищет всех людей в городе
    public String find(@FormParam("city_name") String name){
        try{ PeopleService peopleService = new PeopleService();
            List<Peoples> peoples = peopleService.find(name);
            String html = "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Peoples</title>\n" +
                    "</head>\n" + "<body>";
            for (Peoples s : peoples) {
                html += "<div>" + s.toString() + "</div>";
            }
            return html + "</body>";
        } catch (Exception e)
        {
            return "Exception";
        }

    }

    @GET
    @Produces("text/html")
    @Path("/order")
//    сортировка по id
    public String order(){
        PeopleService peopleService = new PeopleService();
        List<Peoples> peoples = peopleService.order();
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Peoples</title>\n" +
                "</head>\n" + "<body>";
        for (Peoples s : peoples) {
            html += "<div>" + s.toString() + "</div>";
        }
        return html + "</body>";
    }

    @POST
    @Produces("text/html")
    @Path("/pattern")
//    ищет человека по шаблону
    public String findAllPatterns(@FormParam("name") String name){
        PeopleService peopleService = new PeopleService();
        List<Peoples> peoples = peopleService.findAllPatterns(name);
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Peoples</title>\n" +
                "</head>\n" + "<body>";
        for (Peoples s : peoples) {
            html += "<div>" + s.toString() + "</div>";
        }
        return html + "</body>";

    }


}
