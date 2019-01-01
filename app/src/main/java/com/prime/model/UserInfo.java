package com.prime.model;

import android.database.Cursor;
import androidx.databinding.BaseObservable;


/**
 * Created by alishatergholi on 10/16/17.
 */
public class UserInfo extends BaseObservable {

    public static String Key_UserInfo       = "UserInfo";

    public static String Key_Username       = "username";
    public static String Key_nicename       = "nicename";
    public static String Key_mobile         = "mobile";
    public static String Key_sessionid      = "sessionid";
    public static String Key_userID         = "userId";
    public static String Key_SubscriberId   = "subscriberId";

    String username = "";
    String nicename = "";
    String mobile = "";
    String userId = "";
    String sessionId = "";
    String subscriberId = "";

    public UserInfo(){

    }

    public UserInfo(Cursor user) {
        setUsername(user.getString(0));
        setNicename(user.getString(1));
        setMobile(user.getString(2));
        setSessionId(user.getString(3));
        setSubscriberId(user.getString(4));
        setUserId(user.getString(5));
    }

    public UserInfo(String sessionId, String mobile) {
        setUsername(mobile);
        setMobile(mobile);
        setSessionId(sessionId);
    }

    public UserInfo(String mobile, String sessionId, String subscriberId) {
        setUsername(mobile);
        setMobile(mobile);
        setSessionId(sessionId);
        setSubscriberId(subscriberId);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNicename() {
        return nicename;
    }

    public void setNicename(String nicename) {
        this.nicename = nicename;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public static String Query_Create =
            "CREATE TABLE " + Key_UserInfo + " " +
                    "(id INTEGER PRIMARY KEY  AUTOINCREMENT ," +
                    Key_Username + " VARCHAR," +
                    Key_nicename + " VARCHAR," +
                    Key_mobile + " VARCHAR," +
                    Key_sessionid + " VARCHAR," +
                    Key_SubscriberId + " VARCHAR," +
                    Key_userID + " VARCHAR)";

    public static String Query_select =
            "SELECT " +
                    Key_Username + "," +
                    Key_nicename + "," +
                    Key_mobile + "," +
                    Key_sessionid + "," +
                    Key_SubscriberId + "," +
                    Key_userID + " FROM " + Key_UserInfo;

    public static String Query_insert(UserInfo userInfo) {
        return "INSERT INTO " + Key_UserInfo + "(" +

                Key_Username + " , " +

                Key_nicename + " , " +

                Key_mobile + " , " +

                Key_sessionid + " , " +

                Key_SubscriberId + " , " +

                Key_userID + ") VALUES ('" +

                userInfo.getUsername() + "','" +

                userInfo.getNicename() + "','" +

                userInfo.getMobile() + "','" +

                userInfo.getSessionId() + "','" +

                userInfo.getSubscriberId() + "','" +

                userInfo.getUserId() + "')";

    }

    public static String Query_update(UserInfo userInfo) {
        return "UPDATE " + Key_UserInfo + " SET " +
                Key_Username + " = '" + userInfo.getUsername() + "'," +
                Key_nicename + " = '" + userInfo.getNicename() + "'," +
                Key_mobile + " = '" + userInfo.getMobile() + "'," +
                Key_sessionid + " = '" + userInfo.getSessionId() + "'," +
                Key_SubscriberId + " = '" + userInfo.getSubscriberId() + "'," +
                Key_userID + " = '" + userInfo.getUserId() + "'";
    }

    public static String Query_delete = "DELETE FROM " + Key_UserInfo;

    public static String Query_Drop = "DROP TABLE IF EXISTS " + Key_UserInfo;
}
