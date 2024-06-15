package com.example.practicecontentprovider;

import android.net.Uri;

public final class Contract {
    private Contract() {
    }

    public static final String AUTHORITY = "com.example.practicecontentprovider.provider";
    public static final String CONTENT_PATH = "income";

    // A content:// style URL to the authority for the income table
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH);

    static final int ALL_ITEMS = -2; // Query code for all items in the database table
    static final String INCOME_ID = "id"; // The unique ID column for a single income item

    static final String SINGLE_RECORD_MIME_TYPE =
            "vnd.android.cursor.item/com.example.practicecontentprovider.words";
    static final String MULTIPLE_RECORDS_MIME_TYPE =
            "vnd.android.cursor.dir/com.example.practicecontentprovider.words";


}
