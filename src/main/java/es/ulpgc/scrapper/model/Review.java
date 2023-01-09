package es.ulpgc.scrapper.model;

public class Review {

    private final String hotelName;
    private final String reviewerName;
    private final String title;
    private final String mark;
    private final String positive;
    private final String negative;

    public String getHotelName() {
        return hotelName;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getTitle() {
        return title;
    }

    public String getMark() {
        return mark;
    }

    public String getPositive() {
        return positive;
    }

    public String getNegative() {
        return negative;
    }

    public Review(String hotelName, String reviewerName, String title, String mark, String positive, String negative) {
        this.hotelName = hotelName;
        this.reviewerName = reviewerName;
        this.title = title;
        this.mark = mark;
        this.positive = positive;
        this.negative = negative;
    }

}