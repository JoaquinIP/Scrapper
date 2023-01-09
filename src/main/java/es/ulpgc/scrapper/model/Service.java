package es.ulpgc.scrapper.model;

public class Service {


    private final String hotelName;
    private final String titleService;
    private final String services;

    public String getHotelName() {
        return hotelName;
    }

    public String getTitleService() {
        return titleService;
    }

    public String getServices() {
        return services;
    }

    public Service(String hotelName, String titleService, String services) {
        this.hotelName = hotelName;
        this.titleService = titleService;
        this.services = services;
    }
}
