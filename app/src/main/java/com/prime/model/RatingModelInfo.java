package com.prime.model;


/**
 * Created by alishatergholi on 3/16/18.
 */
public class RatingModelInfo {

    public String  title   = "";
    public Integer rating  = 0;
    public String  percent = "";

    public RatingModelInfo(String title, Integer rating, Integer percent) {
        this.title      = title;
        this.rating     = rating;
        this.percent    = String.valueOf(percent);
    }
}
