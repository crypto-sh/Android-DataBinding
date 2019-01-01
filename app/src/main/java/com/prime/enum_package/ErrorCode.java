package com.prime.enum_package;


/**
 * Created by alishatergholi on 2/17/18.
 */
public enum ErrorCode {

    ERROR_SESSION_EXPIRE(403),
    ERROR_INTERNET_CONNECTION(1000),
    ERROR_SERVER_CONNECTTON(1001),
    ERROR_TIMEOUT_CONNECTION(1002),
    ERROR_NOT_VALID_ACTIVE(1003),
    ERROR_EXCEPTION_FILENOTFOUND(1004),
    ERROR_EXCEPTION_IO(1005),
    ERROR_PERMISSION_DENIED(1009),
    ERROR_NOT_VALID_NOT_ACTIVE(1017),
    ERROR_SERVER_DATA_ERROR(1020),
    ERROR_EMPTY_LIST(1021),
    ERROR_EXCEPTION(9999);

    private final int value;

    private ErrorCode(int value) {
        this.value = value;
    }

    public int getCode() {
        return this.value;
    }

    public static ErrorCode Parse(int value) {
        if(value == 1) {
            return ERROR_EXCEPTION;
        } else {
            ErrorCode[] arr$ = values();
            ErrorCode[] var2 = arr$;
            int var3 = arr$.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                ErrorCode val = var2[var4];
                if(val.value == value) {
                    return val;
                }
            }
            return ERROR_EXCEPTION;
        }
    }
}
