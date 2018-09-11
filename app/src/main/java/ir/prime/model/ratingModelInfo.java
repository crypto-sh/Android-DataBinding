package ir.prime.model;


/**
 * Created by alishatergholi on 3/16/18.
 */
public class ratingModelInfo {

    public String  title   = "";
    public Integer rating  = 0;
    public String  percent = "";

    public ratingModelInfo(String title, Integer rating, Integer percent) {
        this.title      = title;
        this.rating     = rating;
        this.percent    = String.valueOf(percent);
    }
}
