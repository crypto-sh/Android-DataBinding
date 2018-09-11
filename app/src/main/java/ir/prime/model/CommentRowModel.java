package ir.prime.model;

/**
 * Created by alishatergholi on 3/12/18.
 */

public class CommentRowModel {

    public int rating;
    public String mUser;
    public String mTime;
    public String mTitle;
    public String mComment;

    public CommentRowModel(Api.CommentRowData commentRow){

        mTitle      = commentRow.getTitle();
        rating      = commentRow.getStarsRating();
        mUser       = commentRow.getUser();
        mTime       = commentRow.getTime();
        mComment    = commentRow.getComment();
    }
}
