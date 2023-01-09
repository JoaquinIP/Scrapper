package es.ulpgc.scrapper.controller;


import static spark.Spark.*;
import com.google.gson.Gson;
import es.ulpgc.scrapper.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class ApiRestController {
    private final Gson gson = new Gson();

    public void controller() {
        port(4567);

        get("/hotels", (req, res) -> {
            res.type("application/json");
            List<String> hotels = Hotel.getHotelList();
            return gson.toJson(hotels);
        });

        get("/hotels/:name", (req, res) -> {
            String name = req.params(":name");
            res.type("application/json");
            List<Location> filteredLocations = getLocationByHotelName(name);
            return gson.toJson(filteredLocations);
        });

        get("/hotels/:name/ratings", (req, res) -> {
            String name = req.params(":name");
            res.type("application/json");
            List<Rating> filteredRatings = getRatingByHotelName(name);
            return gson.toJson(filteredRatings);
        });

        get("/hotels/:name/services", (req, res) -> {
            String name = req.params(":name");
            res.type("application/json");
            List<Service> filteredServices = getServiceByHotelName(name);
            return gson.toJson(filteredServices);
        });

        get("/hotels/:name/comments", (req, res) -> {
            String name = req.params(":name");
            res.type("application/json");
            List<Review> filteredReviews = getReviewByHotelName(name);
            return gson.toJson(filteredReviews);
        });
    }

    private static List<Location> getLocationByHotelName(String name) {
        return Scrapper.getLocationlist().stream()
                .filter(hotel -> hotel.getHotelName().equals(name))
                .collect(Collectors.toList());
    }

    private static List<Rating> getRatingByHotelName(String name) {
        return Scrapper.getRatinglist().stream()
                .filter(hotel -> hotel.gethotelName().equals(name))
                .collect(Collectors.toList());
    }

    private static List<Service> getServiceByHotelName(String name) {
        return Scrapper.getServiceslist().stream()
                .filter(hotel -> hotel.getHotelName().equals(name))
                .collect(Collectors.toList());
    }

    private static List<Review> getReviewByHotelName(String name) {
        return Scrapper.getReviewlist().stream()
                .filter(hotel -> hotel.getHotelName().equals(name))
                .collect(Collectors.toList());
    }
}