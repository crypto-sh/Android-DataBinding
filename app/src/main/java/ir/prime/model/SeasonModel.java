package ir.prime.model;

/**
 * Created by alishatergholi on 3/16/18.
 */

public class SeasonModel {

    public String mAgeRating;
    public String mTitle;
    public String image;
    public String backImage;
    public PageListParcelable listParcelable;

    public SeasonModel(Api.Season season){
        mTitle = season.getTabTitle();
        Api.SeasonInfo seasonInfo = season.getSeasonInfo();
        mAgeRating  = String.valueOf(seasonInfo.getAgeRatingValue());
        backImage   = seasonInfo.getBackImage();
        image       = seasonInfo.getImage();
        listParcelable = new PageListParcelable(seasonInfo.getListRowList());
    }

    public String getAgeRating() {
        return mAgeRating;
    }

    public void setAgeRating(String mAgeRating) {
        this.mAgeRating = mAgeRating;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public PageListParcelable getListParcelable() {
        return listParcelable;
    }

    public void setListParcelable(PageListParcelable listParcelable) {
        this.listParcelable = listParcelable;
    }
}
