package com.example.practicecontentprovider.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 2;
    private final Context m_context;

    // DDL to insert data into the database
    private static final String INCOME_CREATE_DDL =
            "CREATE TABLE INCOME_MAIN (" +
                    "_ID INTEGER PRIMARY_KEY," +
                    "INCOME_DESCRIPTION TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        m_context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
