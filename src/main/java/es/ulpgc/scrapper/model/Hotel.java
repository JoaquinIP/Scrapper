package es.ulpgc.scrapper.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    static ArrayList<String> hotelList = new ArrayList<>();

    public static List<String> getHotelList() {
        return hotelList;
    }

    public void addHotel() {
        hotelList.add("gloria-palace-amadores");
        hotelList.add("lopesan-costa-meloneras-resort-spa-casino");
        hotelList.add("acgrancanaria");
        hotelList.add("riu-gran-canaria-all-inclusive");
        hotelList.add("lopesan-baobab-resort");
        hotelList.add("cordial-muelle-viejo");
        hotelList.add("riu-plaza-espana");
        hotelList.add("hard-rock-tenerife");
        hotelList.add("hotel-artiem-madrid");
        hotelList.add("casa-camper");
    }
}
