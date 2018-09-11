package ir.prime.model;


import android.os.Parcel;
import android.os.Parcelable;



import ir.prime.utils.PublicFunction;


/**
 * Created by alishatergholi on 3/8/18.
 */
public class DetailsMovieModel implements Parcelable{

    public String mImage;
    public String mTitle;
    public String mBackgroundImage;
    public AgeRate ageRate;
    public PageListParcelable detailsList;

    final String TAG = DetailsMovieModel.class.getSimpleName();

    public DetailsMovieModel(Api.MovieInfo video){
        try {
            ageRate          = new AgeRate(video.getAgeRating());
            mImage           = video.getImage();
            mTitle           = video.getTitle();
            mBackgroundImage = video.getBackImage();
            detailsList      = new PageListParcelable(video.getListRowList());
        } catch (Exception e) {
            PublicFunction.LogData(false, TAG,e.getMessage());
        }
    }


    protected DetailsMovieModel(Parcel in) {
        mImage              = in.readString();
        mTitle              = in.readString();
        mBackgroundImage    = in.readString();
        ageRate             = in.readParcelable(AgeRate.class.getClassLoader());
        detailsList         = in.readParcelable(PageListParcelable.class.getClassLoader());

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mImage);
        dest.writeString(mTitle);
        dest.writeString(mBackgroundImage);
        dest.writeParcelable(ageRate, flags);
        dest.writeParcelable(detailsList, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DetailsMovieModel> CREATOR = new Creator<DetailsMovieModel>() {
        @Override
        public DetailsMovieModel createFromParcel(Parcel in) {
            return new DetailsMovieModel(in);
        }

        @Override
        public DetailsMovieModel[] newArray(int size) {
            return new DetailsMovieModel[size];
        }
    };
}
