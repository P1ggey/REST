package com.example.demo1;

import models.Cities;
import models.Country;
import models.Peoples;
import services.CityService;
import services.CountryService;
import services.PeopleService;

import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.List;

@Path("/cities")
public class City {

    public static int count;

//    вывод всей таблицы Города
    @GET
    @Produces("text/html")
    @Path("/show")
    public String showCity() {
        CityService cityService = new CityService();
        List<Cities> cities = cityService.findAllCities();
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Cities</title>\n" +
                "</head>\n" + "<body>";
        for (Cities s : cities) {
            html += "<div>" + s.toString() + "</div>";
        }
        return html + "</body>";
    }

//    добавление города в таблицу
    @POST
    @Produces("text/plain")
    @Path("/add")
    public String addCity(@FormParam("name_city") String name_city, @FormParam("id_country") int id_country)
    {
        try {
            CountryService countryService = new CountryService();
            CityService cityService = new CityService();
            Cities city = new Cities(name_city);
            Country country = countryService.findCountry(id_country);
            city.setCountry(country);
            cityService.saveCity(city);
            return "successful add";
        } catch (Exception e) {
            return "Exception";
        }

    }

    @POST
    @Produces("text/plain")
    @Path("/update")
//  Обновляет город в базе данных, по id
    public String uppCity(@FormParam("id_city") int id_city,
                          @FormParam("name_city_up") String name_city_up,
                          @FormParam("id_country_up") int id_country_up) {
        try{
            CityService cityService = new CityService();
            Cities city = cityService.findCityById(id_city);
            if (name_city_up != ""){
                city.setCity(name_city_up);
            }
            if (id_country_up != 0){
                CountryService countryService = new CountryService();
                Country country = countryService.findCountry(id_country_up);
                city.setCountry(country);
            }
            cityService.updateCity(city);

            return "successful upp";
        }
        catch (Exception e){
            return "Exception";
        }
    }

//    удаление города из таблицы
    @POST
    @Produces("text/plain")
    @Path("/del")
    public String delCity(@FormParam("id_city") int id_city) {
        try {
            CityService cityService = new CityService();
            Cities city = cityService.findCityById(id_city);
            cityService.deleteCity(city);
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
        CityService cityService = new CityService();
        List<Cities> cities = cityService.findAllCities();
        String string = "";
        this.count = count;
        int size = cities.size();
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
//    выводит количесво элементов базы данных города, которое ранее было указано в форме
    public String pag(@PathParam("c") int c, String args[]) {

        CityService cityService = new CityService();
        List<Cities> cities = cityService.findAllCities();
        int size = cities.size();
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Cities</title>\n" +
                "</head>\n" + "<body>";
        if (c == 1) {
            for (int i = 0; i < count; i++) {
                if (i >= size) {
                    break;
                }
                html += "<div>" + cities.get(i).toString() + "</div>";
            }
        } else {
            for (int i = ((c - 1) * count); i < (c * count); i++) {
                if (i >= size) {
                    break;
                } else {
                    html += "<div>" + cities.get(i).toString() + "</div>";
                }
            }
        }
        return html + "</body>";

    }

    @POST
    @Produces("text/html")
    @Path("/findByName")
//    поиск по имени
    public String SelectByName(@FormParam("city_name") String city_name){
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Cities</title>\n" +
                "</head>\n" + "<body>";
        try {
            CityService cityService = new CityService();
            List<Cities> city = cityService.findCityByName(city_name);
            html += city.get(0).toString();
            return html + "</body>";
        }
        catch (Exception e){
            return html + "Exception </body>";
        }
    }

    @GET
    @Produces("text/html")
    @Path("/order")
//    группировка
    public String OrderBy(){
        CityService cityService = new CityService();
        List<Cities> cities = cityService.findAllCityOrder();
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Cities</title>\n" +
                "</head>\n" + "<body>";
        for (Cities s : cities) {
            html += "<div>" + s.toString() + "</div>";
        }
        return html + "</body>";
    }

    @POST
    @Produces("text/html")
    @Path("/search")
//    поиск по шаблону
    public String Search(@FormParam("name") String name){
        CityService cityService = new CityService();
        List<Cities> cities = cityService.findAllPattern(name);
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Cities</title>\n" +
                "</head>\n" + "<body>";
        for (Cities s : cities) {
            html += "<div>" + s.toString() + "</div>";
        }
        return html + "</body>";
    }

}
