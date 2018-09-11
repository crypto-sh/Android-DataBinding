package ir.prime.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alishatergholi on 3/8/18.
 */
public class PersonModel implements Parcelable{

    public String mId;
    public String mTitle;
    public String mImagePath;
    public String mDescription;

    public PersonModel(Api.Person person){
        mId                 = person.getId();
        mTitle              = person.getTitle();
        mImagePath          = person.getImage();
        mDescription        = person.getDescription();
    }

    protected PersonModel(Parcel in) {
        mId = in.readString();
        mTitle = in.readString();
        mImagePath = in.readString();
        mDescription = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mTitle);
        dest.writeString(mImagePath);
        dest.writeString(mDescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PersonModel> CREATOR = new Creator<PersonModel>() {
        @Override
        public PersonModel createFromParcel(Parcel in) {
            return new PersonModel(in);
        }

        @Override
        public PersonModel[] newArray(int size) {
            return new PersonModel[size];
        }
    };
}
