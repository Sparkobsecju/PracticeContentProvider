package com.example.practicecontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String queryUri = Contract.CONTENT_URI.toString();
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.button1).setOnClickListener( v -> getOne());
        findViewById(R.id.button2).setOnClickListener( v -> getAll());
    }

    private void getAll() {
        Cursor cursor = getContentResolver().query(Uri.parse(queryUri),
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                String item = cursor.getString(0);
                Log.v(TAG, item);
            } while (cursor.moveToNext());
        }
    }

    private void getOne() {
        String selectionClause = Contract.INCOME_ID + " = ?";
        String[] selectionArgs = new String[] {"2"};
        Cursor cursor = getContentResolver().query(Uri.parse(queryUri), null,
                selectionClause, selectionArgs, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                String item = cursor.getString(0);
                Log.v(TAG, "select the 3rd item=" + item);
            } while (cursor.moveToNext());
        }
    }
}