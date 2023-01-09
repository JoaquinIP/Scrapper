package es.ulpgc.scrapper.controller;


import static spark.Spark.*;
import com.google.gson.Gson;
import es.ulpgc.scrapper.model.Location;
import es.ulpgc.scrapper.model.Rating;
import es.ulpgc.scrapper.model.Review;
import es.ulpgc.scrapper.model.Service;

import java.util.List;
import java.util.stream.Collectors;

public class ApiRestController {
    private final Gson gson = new Gson();

    public void controller() {
        port(4567);

        get("/hotels/:name", (req, res) -> {
            // Devuelve la lista de comentarios del hotel con el ID proporcionado
            String name = req.params(":name");
            res.type("application/json");
            List<Location> filteredLocations = getLocationByHotelName(name).stream()
                    .filter(hotel -> hotel.getHotelName().equals(name))
                    .collect(Collectors.toList());
            return gson.toJson(filteredLocations);
        });

        get("/hotels/:name/ratings", (req, res) -> {
            // Devuelve la lista de comentarios del hotel con el ID proporcionado
            String name = req.params(":name");
            res.type("application/json");
            List<Rating> filteredRatings = getRatingByHotelName(name).stream()
                    .filter(hotel -> hotel.gethotelName().equals(name))
                    .collect(Collectors.toList());
            return gson.toJson(filteredRatings);
        });

        get("/hotels/:name/services", (req, res) -> {
            // Devuelve la lista de comentarios del hotel con el ID proporcionado
            String name = req.params(":name");
            res.type("application/json");
            List<Service> filteredServices = getServiceByHotelName(name).stream()
                    .filter(hotel -> hotel.getHotelName().equals(name))
                    .collect(Collectors.toList());
            return gson.toJson(filteredServices);
        });

        get("/hotels/:name/comments", (req, res) -> {
            // Devuelve la lista de comentarios del hotel con el ID proporcionado
            String name = req.params(":name");
            res.type("application/json");
            List<Review> filteredReviews = getReviewByHotelName(name).stream()
                    .filter(hotel -> hotel.getHotelName().equals(name))
                    .collect(Collectors.toList());
            return gson.toJson(filteredReviews);
        });
    }

    private static List<Location> getLocationByHotelName(String name) {
        return Scrapper.getLocationlist();
    }

    private static List<Rating> getRatingByHotelName(String name) {
        return Scrapper.getRatinglist();
    }

    private static List<Service> getServiceByHotelName(String name) {
        return Scrapper.getServiceslist();
    }

    private static List<Review> getReviewByHotelName(String name) {
        return Scrapper.getReviewlist();
    }
}