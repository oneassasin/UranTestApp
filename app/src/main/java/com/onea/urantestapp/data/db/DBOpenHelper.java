package com.onea.urantestapp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.onea.urantestapp.data.db.tables.UranDB;

public final class DBOpenHelper extends SQLiteOpenHelper {

  private static final String LOG_TAG = DBOpenHelper.class.getName();

  public DBOpenHelper(Context context) {
    super(context, UranDB.DB_NAME, null, UranDB.DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    Log.d(LOG_TAG, "Create DB");
    Log.d(LOG_TAG, UranDB.SQL_CREATE_TIMESTAMPS);
    UranDB.onCreate(sqLiteDatabase);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    Log.d(LOG_TAG, "Drop DB");
    UranDB.onDrop(sqLiteDatabase);
    Log.d(LOG_TAG, "Creating DB");
    Log.d(LOG_TAG, UranDB.SQL_CREATE_TIMESTAMPS);
    UranDB.onCreate(sqLiteDatabase);
  }

}
