package ir.prime.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;

import com.google.protobuf.Descriptors;

import ir.prime.utils.PublicFunction;

/**
 * Created by alishatergholi on 3/8/18.
 */
public class AgeRate implements Parcelable{

    public int mNumber          = 0;
    public String mDescription  = "";

    final String TAG = AgeRate.class.getSimpleName();

    public AgeRate(Api.AgeRating rate){
        try {
            this.mNumber        = rate.getNumber();
//            Descriptors.EnumDescriptor des = rate.getDescriptorForType();
//            if (des != null){
//                this.mDescription   = des.getFullName();
//            }
        } catch (Exception e) {
            PublicFunction.LogData(false, TAG,e.getMessage());
        }
    }


    protected AgeRate(Parcel in) {
        mNumber         = in.readInt();
        mDescription    = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mNumber);
        dest.writeString(mDescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AgeRate> CREATOR = new Creator<AgeRate>() {
        @Override
        public AgeRate createFromParcel(Parcel in) {
            return new AgeRate(in);
        }

        @Override
        public AgeRate[] newArray(int size) {
            return new AgeRate[size];
        }
    };
}
