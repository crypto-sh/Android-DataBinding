package ir.prime.utils;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import ir.prime.model.UserInfo;

public class DBController extends SQLiteOpenHelper {

    private static int databaseVersion = 1;

    private static String databaseName = "prime.db";

    private static final String TAG = DBController.class.getSimpleName();

    public DBController(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            database.execSQL(UserInfo.Query_Create);

            PublicFunction.LogData(true,TAG,database.toString());
        } catch (SQLException ex) {
            PublicFunction.LogData(false,TAG,ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        try {
            database.execSQL(UserInfo.Query_Drop);
            onCreate(database);
        } catch (Exception ex) {
            PublicFunction.LogData(false, TAG, "onUpgrade", ex.getMessage());
        }
    }

    //region userInfo
    public void insertUserInfo(UserInfo userInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(UserInfo.Query_delete);
            db.execSQL(UserInfo.Query_insert(userInfo));
        } catch (Exception ex) {
            PublicFunction.LogData(false, TAG, ex.getMessage());
        }
    }

    public UserInfo getUserInfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery(UserInfo.Query_select, null);
            if (cursor.moveToFirst()) {
                do {
                    return new UserInfo(cursor);

                } while (cursor.moveToNext());
            }
            cursor.close();
            return null;
        } catch (Exception ex) {
            return null;
        } finally {
            db.close();
        }
    }

    public void updateUserInfo(UserInfo userInfo) {
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            database.execSQL(UserInfo.Query_update(userInfo));
        } catch (Exception ex) {
            PublicFunction.LogData(false, TAG, ex.getMessage());
        }
    }

    //endregion

    public void clearDataBase() {
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            database.execSQL(UserInfo.Query_delete);
        } catch (Exception ex) {
            PublicFunction.LogData(false, TAG, ex.getMessage());
        }
    }
}
