package ir.prime.model;


/**
 * Created by alishatergholi on 3/12/18.
 */
public class CommentInfoModel {


    public int starRating;
    public int starNumber;

    public ratingModelInfo rating1;
    public ratingModelInfo rating2;
    public ratingModelInfo rating3;
    public ratingModelInfo rating4;
    public ratingModelInfo rating5;

    public CommentInfoModel(Api.CommentInfoData commentInfo) {
        starRating = commentInfo.getStarsRating();
        starNumber = commentInfo.getStarsNumbers();

        rating1 = new ratingModelInfo("",commentInfo.getStar1Number(),commentInfo.getStar1Percent());
        rating2 = new ratingModelInfo("",commentInfo.getStar2Number(),commentInfo.getStar2Percent());
        rating3 = new ratingModelInfo("",commentInfo.getStar3Number(),commentInfo.getStar3Percent());
        rating4 = new ratingModelInfo("",commentInfo.getStar4Number(),commentInfo.getStar4Percent());
        rating5 = new ratingModelInfo("",commentInfo.getStar5Number(),commentInfo.getStar5Percent());
    }

}
