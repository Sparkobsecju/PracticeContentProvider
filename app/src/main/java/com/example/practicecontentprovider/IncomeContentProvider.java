package com.example.practicecontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.practicecontentprovider.util.DBHelper;

public class IncomeContentProvider extends ContentProvider {
    private static final String TAG = IncomeContentProvider.class.getSimpleName();
    private String[] data;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Initialize the UriMatcher with the URIs
    // that this ContentProvider can match with the data it can provide access to
    // in the database table it manages.
    private void initializeUriMatching() {
        // Add a URI to the matcher
        uriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH + "/#", 1);

        // Add a URI to match the content URI for the provider
        uriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH, 0);

        // Add a URI to match the count of items in a table
        uriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH + "/" + Contract.COUNT, 2);
    }

    private DBHelper dbHelper;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        dbHelper = new DBHelper(context); // initialize the DBHelper to initialize the database
//        data = context.getResources().getStringArray(R.array.income_placeholder);
        initializeUriMatching();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int id = -1;
        switch (uriMatcher.match(uri)) {
            case 0:
                id = Contract.ALL_ITEMS;
                if (selection != null) {
                    id = Integer.parseInt(selectionArgs[0]);
                }
                break;
            case 1:
                id = Integer.parseInt(uri.getLastPathSegment());
                break;
            case UriMatcher.NO_MATCH:
                Log.d(TAG, "NO MATCH FOR THIS URI IN SCHEME.");
                id = -1;
                break;
            default:
                Log.d(TAG, "INVALID URI - URI NOT RECOGNIZED.");
                id = -1;
        }
        Log.d(TAG, "query: " + id);
        return populateCursor(id);
    }

    // Populate the cursor with the data from the database
    private Cursor populateCursor(int id) {
//        MatrixCursor cursor = new MatrixCursor(new String[]{Contract.CONTENT_PATH});
//        if (id == Contract.ALL_ITEMS) {
//            for (int i = 0; i < data.length; i++) {
//                String word = data[i];
//                cursor.addRow(new Object[]{word});
//            }
//        } else if (id > 0) {
//            String word = data[id];
//            cursor.addRow(new Object[]{word});
//        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "";
        if (id == Contract.ALL_ITEMS) {
            query = "SELECT * FROM INCOME_MAIN";
        } else if (id >= 0) {
            query = String.format("SELECT * FROM INCOME_MAIN WHERE _ID=%d", id);
        }
        Cursor cursor = db.rawQuery(query, null);
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)) {
            case 0:
                return Contract.MULTIPLE_RECORDS_MIME_TYPE;
            case 1:
                return Contract.SINGLE_RECORD_MIME_TYPE;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
