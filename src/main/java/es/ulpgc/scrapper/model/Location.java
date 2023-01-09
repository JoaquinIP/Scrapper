package es.ulpgc.scrapper.model;

public class Location {
    private final String hotelName;

    private final String location;

    public String getHotelName() {
        return hotelName;
    }

    public String getLocation() {
        return location;
    }

    public Location(String hotelName, String location) {
        this.hotelName = hotelName;
        this.location = location;
    }
}
