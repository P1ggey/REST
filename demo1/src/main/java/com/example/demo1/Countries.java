package com.example.demo1;

import javafx.geometry.Pos;
import models.Cities;
import models.Country;
import services.CityService;
import services.CountryService;

import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/countries")
public class Countries {

    public static int count;

    @GET
    @Produces("text/html")
    @Path("/show")
//    выводит все страны, хранящиеся в базе данных
    public String showCountry() {
        CountryService countryService = new CountryService();
        List<Country> countries = countryService.findAllCountry();
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Country</title>\n" +
                "</head>\n" + "<body>";
        for (Country s : countries) {
            html += "<div>" + s.toString() + "</div>";
        }
        return html + "</body>";
    }

    @POST
    @Produces("text/plain")
    @Path("/add")
//    добавлет страну в базу данных
    public String addCountry(@FormParam("name_country") String name_country) {
        try {
            CountryService countryService = new CountryService();
            Country country = new Country(name_country);
            countryService.saveCountry(country);
            return "successful add";
        } catch (Exception e) {
            return "Exception";
        }

    }

    @POST
    @Produces("text/plain")
    @Path("/update")
//  Обновляет какую либо страну в базе данных, по id
    public String uppCountry(@FormParam("name_country") int id, @FormParam("name_country_up") String name_country_up) {
        try {
            CountryService countryService = new CountryService();
            Country country = countryService.findCountry(id);
            country.setName(name_country_up);
            countryService.updateCountry(country);
            return "successful upp";
        } catch (Exception e) {
            return "Exception";
        }
    }

    @POST
    @Produces("text/plain")
    @Path("/delete")
//  Удаляем какую либо страну в базе данных, по id
    public String delCountry(@FormParam("name_country") int id) {
        try {
            CountryService countryService = new CountryService();
            Country country = countryService.findCountry(id);
            countryService.deleteCountry(country);
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
        CountryService countryService = new CountryService();
        List<Country> countries = countryService.findAllCountry();
        String string = "";
        this.count = count;
        int size = countries.size();
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
//    выводит количесво элементов базы данных стран, которое ранее было указано в форме
    public String pag(@PathParam("c") int c, String args[]) {

        CountryService countryService = new CountryService();
        List<Country> countries = countryService.findAllCountry();
        int size = countries.size();
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Country</title>\n" +
                "</head>\n" + "<body>";
        if (c == 1) {
            for (int i = 0; i < count; i++) {
                if (i >= size) {
                    break;
                }
                html += "<div>" + countries.get(i).toString() + "</div>";
            }
        } else {
            for (int i = ((c - 1) * count); i < (c * count); i++) {
                if (i >= size) {
                    break;
                } else {
                    html += "<div>" + countries.get(i).toString() + "</div>";
                }
            }
        }
        return html + "</body>";
    }

    @POST
    @Produces("text/html")
    @Path("/findByName")
//    ищет страну по имени
    public String findByName(@FormParam("name") String name){
        CountryService countryService = new CountryService();
        List<Country> countries = countryService.findByName(name);
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Country</title>\n" +
                "</head>\n" + "<body>";
        for (Country s : countries) {
            html += "<div>" + s.toString() + "</div>";
        }
        return html + "</body>";
    }
    @GET
    @Produces("text/html")
    @Path("/order")
//    сортировка по имени
    public String OrderBy(){
        CountryService countryService = new CountryService();
        List<Country> countries = countryService.findAllOrder();
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Country</title>\n" +
                "</head>\n" + "<body>";
        for (Country s : countries) {
            html += "<div>" + s.toString() + "</div>";
        }
        return html + "</body>";
    }
    @POST
    @Produces("text/html")
    @Path("/search")
    public String search(@FormParam("name") String name){
        CountryService countryService = new CountryService();
        List<Country> countries = countryService.findAllPattern(name);
        String html = "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Country</title>\n" +
                "</head>\n" + "<body>";
        for (Country s : countries) {
            html += "<div>" + s.toString() + "</div>";
        }
        return html + "</body>";
    }
}