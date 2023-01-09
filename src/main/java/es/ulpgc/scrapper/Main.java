package es.ulpgc.scrapper;

import es.ulpgc.scrapper.controller.ApiRestController;
import es.ulpgc.scrapper.controller.Scrapper;
import es.ulpgc.scrapper.model.Hotel;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new Hotel().addHotel();
        new Scrapper().scrapping();
        new ApiRestController().controller();
    }
}
