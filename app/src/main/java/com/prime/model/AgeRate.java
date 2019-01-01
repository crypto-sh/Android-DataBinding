package com.prime.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.prime.utils.PublicFunction;

/**
 * Created by alishatergholi on 3/8/18.
 */
public class AgeRate implements Parcelable {

    public int mNumber = 0;
    public String mDescription = "";

    public AgeRate() {
//            this.mNumber        = rate.getNumber();
//            Descriptors.EnumDescriptor des = rate.getDescriptorForType();
//            if (des != null){
//                this.mDescription   = des.getFullName();
//            }
    }


    protected AgeRate(Parcel in) {
        mNumber = in.readInt();
        mDescription = in.readString();
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
