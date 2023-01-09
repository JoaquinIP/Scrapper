package es.ulpgc.scrapper.controller;

import es.ulpgc.scrapper.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scrapper {

    static ArrayList<Location> locationList = new ArrayList<>();
    static ArrayList<Rating> ratingsList = new ArrayList<>();
    static ArrayList<Service> servicesList = new ArrayList<>();
    static ArrayList<Review> reviewList = new ArrayList<>();

    public static List<Location> getLocationlist() {
        return locationList;
    }
    public static List<Rating> getRatinglist() {
        return ratingsList;
    }
    public static List<Service> getServiceslist() {
        return servicesList;
    }
    public static List<Review> getReviewlist() {
        return reviewList;
    }


    public void scrapping() throws IOException {
        // La URL de la página del hotel en booking.com

        List<String> hotels = getHotelList();
        for (int j = 0; j < hotels.size(); j++) {

            String url = "https://www.booking.com/hotel/es/" + hotels.get(j) + ".es.html";

            String url2 = "https://www.booking.com/reviews/es/hotel/" + hotels.get(j) + ".es.html";

            Document doc2 = Jsoup.connect(url2).get();

            /*String hotel_name = hotels.get(j).replaceAll("-", " ");
            char[] caracteres = hotel_name.toCharArray();
            caracteres[0] = Character.toUpperCase(caracteres[0]);
            for (int i = 0; i < hotel_name.length() - 1; i++) {
                // Es 'palabra'
                if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',') {
                    // Reemplazamos
                    caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
                }
            }
            String hotel_name_mayus = new String(caracteres);*/

            // Descarga el contenido HTML de la página
            Document doc = Jsoup.connect(url).get();


            // Busca la ubicación del hotel
            Element location = doc.select("span.hp_address_subtitle").first();
            System.out.println("Ubicación: " + location.text());
            Location location1 = new Location(hotels.get(j), location.text());
            locationList.add(location1);

            // Busca los ratings del hotel
            Element rating = doc.select("div.d46673fe81").first();
            System.out.println("Rating: " + rating.text());
            Rating rating1 = new Rating(hotels.get(j), rating.text());
            ratingsList.add(rating1);

        /*Elements popservices = doc.select("div.important_facility");
        for (Element popservice : popservices) {
            System.out.println("Servicios mas populares: " + popservice.text());
        }*/

            // Busca los servicios del hotel
            Elements services = doc.select("div.hotel-facilities-group");
            System.out.println("Servicios: \n");
            for (Element service : services) {
                String titulo = service.select("div.bui-title__text").text();
                String contenido1 = service.select("ul.bui-list").text();
                String contenido2 = service.select("div.hotel-facilities-group__policy").text();
                String contenido = contenido1 + contenido2;
                System.out.println(titulo + ": " + contenido1 + contenido2);
                Service service1 = new Service(hotels.get(j), titulo, contenido);
                servicesList.add(service1);
            }


            ArrayList<String> titlesList = new ArrayList<>();
            ArrayList<String> positiveList = new ArrayList<>();
            ArrayList<String> negativeList = new ArrayList<>();
            ArrayList<String> marksList = new ArrayList<>();
            ArrayList<String> reviewerNameList = new ArrayList<>();

            // Busca los comentarios del hotel
            Elements comment = doc2.select("div.review_item_review_content");
            for (Element comments : comment) {
                Elements positive = comments.select("p.review_pos");
                String positivo = positive.select("span").attr("itemprop", "reviewBody").text();
                Elements negative = comments.select("p.review_neg");
                String negativo = negative.select("span").attr("itemprop", "reviewBody").text();
                System.out.println(negativo);
                negativeList.add(negativo);
                System.out.println(positivo);
                positiveList.add(positivo);
            }

            Elements title = doc2.select("div.review_item_header_content");
            for (Element titles : title) {
                String header = titles.select("span").attr("itemprop", "name").text();
                System.out.println(header);
                titlesList.add(header);
            }

            Elements mark = doc2.select("div.review_item_header_score_container");
            for (Element marks : mark) {
                String puntuation = "";
                String note = marks.select("span").attr("class", "content").text();
                for (int i = 0; i < note.length(); i++) {
                    char c = note.charAt(i);
                    if (c == ' ') {
                        break;
                    } else {
                        puntuation += c;
                    }
                }
                System.out.println(puntuation);
                marksList.add(puntuation);
            }


            Elements reviewer_names = doc2.select("p.reviewer_name");
            for (Element reviewer_name : reviewer_names) {
                System.out.println(reviewer_name.text());
                reviewerNameList.add(reviewer_name.text());
            }

            for (int i = 0; i < positiveList.size(); i++) {
                String positive = positiveList.get(i);
                String puntuacion = marksList.get(i);
                String negative = negativeList.get(i);
                String titulo = titlesList.get(i);
                String reviewerName = reviewerNameList.get(i);
                Review review = new Review(hotels.get(j), reviewerName, titulo, puntuacion, positive, negative);
                reviewList.add(review);
            }
        }
    }

    private static List<String> getHotelList() {
        return Hotel.getHotelList();
    }

}
