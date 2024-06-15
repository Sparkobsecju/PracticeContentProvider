package com.example.practicecontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IncomeContentProvider extends ContentProvider {
    private static final String TAG = IncomeContentProvider.class.getSimpleName();
    private String[] data;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private void initializeUriMatching() {
        uriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH + "/#", 1);
        uriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH, 0);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        data = context.getResources().getStringArray(R.array.income_placeholder);
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

    private Cursor populateCursor(int id) {
        MatrixCursor cursor = new MatrixCursor(new String[] {Contract.CONTENT_PATH});
        if (id == Contract.ALL_ITEMS) {
            for (int i = 0; i < data.length; i++) {
                String word = data[i];
                cursor.addRow(new Object[]{word});
            }
        } else if (id > 0) {
            String word = data[id];
            cursor.addRow(new Object[]{word});
        }
        return cursor;
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
