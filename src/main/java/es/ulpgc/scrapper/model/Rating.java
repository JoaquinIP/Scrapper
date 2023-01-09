package es.ulpgc.scrapper.model;

public class Rating {
    private final String hotelName;

    private final String ratings;

    public String gethotelName() {
        return hotelName;
    }

    public String getRatings() {
        return ratings;
    }

    public Rating(String hotelName, String ratings) {
        this.hotelName = hotelName;
        this.ratings = ratings;
    }
}