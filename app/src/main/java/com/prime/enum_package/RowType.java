package com.prime.enum_package;

public enum RowType {
    /**
     * <code>UNDEFINED_ROWTYPE = 0;</code>
     */
    UNDEFINED_ROWTYPE(0),
    /**
     * <code>BANNER = 1;</code>
     */
    BANNER(1),
    /**
     * <code>SERIAL = 2;</code>
     */
    SERIAL(2),
    /**
     * <code>MOVIE = 3;</code>
     */
    MOVIE(3),
    /**
     * <code>EPISODE = 4;</code>
     */
    EPISODE(4),
    /**
     * <code>ALSO_WATCHED = 5;</code>
     */
    ALSO_WATCHED(5),
    /**
     * <code>CAST = 6;</code>
     */
    CAST(6),
    /**
     * <code>DIRECTOR = 7;</code>
     */
    DIRECTOR(7),
    /**
     * <code>KEY_VALUE = 8;</code>
     */
    KEY_VALUE(8),
    /**
     * <code>TEXT = 9;</code>
     */
    TEXT(9),
    /**
     * <code>COMMENT_INFO = 10;</code>
     */
    COMMENT_INFO(10),
    /**
     * <code>COMMENT_ROW = 11;</code>
     */
    COMMENT_ROW(11),
    UNRECOGNIZED(-1),;

    private int value;

    RowType(int value) {
        this.value = value;
    }

    public int getCode() {
        return value;
    }

    public static RowType Parse(int value) {
        if (value == 0) {
            return UNRECOGNIZED;
        }
        RowType[] arr$ = values();
        for (RowType val : arr$) {
            if (val.value == value) {
                return val;
            }
        }
        return UNRECOGNIZED;
    }

}
