package ir.prime.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import static org.xmlpull.v1.XmlPullParser.TEXT;


/**
 * Created by alishatergholi on 2/24/18.
 */
public class PageListParcelable implements Parcelable {

    public List<RowDataParcelable> mRows = new ArrayList<>();

    final String TAG = PageListParcelable.class.getSimpleName();

    public PageListParcelable() {

    }

    public PageListParcelable(List<Api.ListRow> listRow) {
        try {
            for (Api.ListRow listItem : listRow) {
                Api.RowType mType = listItem.getRowType();
                switch (mType) {
                    case TEXT:
                    case EPISODE:
                    case DIRECTOR:
                    case KEY_VALUE:
                        String title = listItem.getRowTitle();
                        for (Api.RowData rowItem : listItem.getRowDataList()){
                            mRows.add(new RowDataParcelable(title,mType,new ItemsDataParcelable(rowItem,mType)));
                        }
                        break;
                    default:
                        mRows.add(new RowDataParcelable(listItem));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected PageListParcelable(Parcel in) {
        mRows = in.createTypedArrayList(RowDataParcelable.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mRows);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PageListParcelable> CREATOR = new Creator<PageListParcelable>() {
        @Override
        public PageListParcelable createFromParcel(Parcel in) {
            return new PageListParcelable(in);
        }

        @Override
        public PageListParcelable[] newArray(int size) {
            return new PageListParcelable[size];
        }
    };
}
