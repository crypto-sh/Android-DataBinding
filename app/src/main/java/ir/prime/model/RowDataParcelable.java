package ir.prime.model;



import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import ir.prime.utils.PublicFunction;


/**
 * Created by alishatergholi on 2/24/18.
 */
public class RowDataParcelable extends BaseObservable implements Parcelable {


    @Bindable
    public String mTitle;

    @Bindable
    public Api.RowType mType;

    @Bindable
    public List<ItemsDataParcelable> mRows = new ArrayList<ItemsDataParcelable>();

    final String TAG = RowDataParcelable.class.getSimpleName();

    public RowDataParcelable(Api.ListRow listRow){
        try {
            mType   = listRow.getRowType();
            mTitle  = listRow.getRowTitle();
            for (Api.RowData data : listRow.getRowDataList()){
                mRows.add(new ItemsDataParcelable(data,mType));
            }
        } catch (Exception e) {
            PublicFunction.LogData(false,TAG,e.getMessage());
        }
    }

    public RowDataParcelable(String title, Api.RowType type, ItemsDataParcelable row) {
        try {
            this.mTitle     = title;
            this.mType      = type;
            ArrayList<ItemsDataParcelable> rowData = new ArrayList<>();
            rowData.add(row);
            this.mRows      = rowData;
        } catch (Exception e) {
            PublicFunction.LogData(false,TAG,e.getMessage());
        }
    }

    protected RowDataParcelable(Parcel in) {
        mTitle  = in.readString();
        mType   = Api.RowType.forNumber(in.readInt());
        mRows   = in.createTypedArrayList(ItemsDataParcelable.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeInt(mType.getNumber());
        dest.writeTypedList(mRows);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RowDataParcelable> CREATOR = new Creator<RowDataParcelable>() {
        @Override
        public RowDataParcelable createFromParcel(Parcel in) {
            return new RowDataParcelable(in);
        }

        @Override
        public RowDataParcelable[] newArray(int size) {
            return new RowDataParcelable[size];
        }
    };
}
