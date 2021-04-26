package com.example.encore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "userhistory.db";
    public static final String USER_TABLE = "user_table";
    public static final String GAMES_PLAYED_TABLE = "games_played_table";
    public static final String USER_COL_1 = "userId";
    public static final String USER_COL_2 = "name";
    public static final String GAMES_COL_1 = "gameId";
    public static final String GAMES_COL_2 = "userId";
    public static final String GAMES_COL_3 = "score";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE +
                " (" + USER_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_COL_2 + " TEXT)");
        db.execSQL("create table " + GAMES_PLAYED_TABLE +
                " (" + GAMES_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GAMES_COL_2 + " INTEGER, " + GAMES_COL_3 + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }
}
