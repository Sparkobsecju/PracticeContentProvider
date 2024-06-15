package com.example.practicecontentprovider.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 2;
    private final Context m_context;

    // DDL to insert data into the database
    private static final String INCOME_CREATE_DDL =
            "CREATE TABLE INCOME_MAIN (" +
                    "_ID INTEGER PRIMARY_KEY," +
                    "INCOME_DESCRIPTION TEXT);";
    // DDL to delete data from the database
    private static final String INCOME_DELETE_DDL =
            "DROP TABLE IF EXISTS INCOME_MAIN;";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        m_context = context;
    }

    // create some demo data
    private void addSomeDemo(SQLiteDatabase db) {
        db.execSQL("INSERT INTO INCOME_MAIN (INCOME_DESCRIPTION) VALUES ('January_10000')");
        db.execSQL("INSERT INTO INCOME_MAIN (INCOME_DESCRIPTION) VALUES ('February_10000')");
        db.execSQL("INSERT INTO INCOME_MAIN (INCOME_DESCRIPTION) VLAUES ('March_10000')");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(m_context, "DB creates!!", Toast.LENGTH_LONG).show();
        db.execSQL(INCOME_CREATE_DDL);
        addSomeDemo(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(m_context, "DB upgrades!!", Toast.LENGTH_LONG).show();
        db.execSQL(INCOME_DELETE_DDL);
        db.execSQL(INCOME_CREATE_DDL);
        addSomeDemo(db);
    }


}




















