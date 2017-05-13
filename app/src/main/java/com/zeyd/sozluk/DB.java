package com.zeyd.sozluk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zeyd on 23.5.2016.
 */
public class DB extends SQLiteOpenHelper {
    final  static String DB_NAME="sozluk.db";
    public DB(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table sozluk(id INTEGER primary key,tr Text,ing TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists sozluk");
        onCreate(db);
    }
}
