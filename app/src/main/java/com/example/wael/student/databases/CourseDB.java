package com.example.wael.student.databases;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Wael on 23/04/2015.
 */
public class CourseDB {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_IDCOURSE = "idCourse";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_URL = "url";
    public static final String KEY_TEACHER = "teacher";
    public static final String KEY_DATEDEPO = "dateDepo";
    public static final String KEY_SUBJECT = "subject";


    private static final String LOG_TAG = "CoursesDb";
        public static final String SQLITE_TABLE = "Course";

        private static final String DATABASE_CREATE =
                "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                        KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                        KEY_IDCOURSE + "," +
                        KEY_NAME + "," +
                        KEY_DESCRIPTION + "," +
                        KEY_URL + "," +
                        KEY_TEACHER + "," +
                        KEY_DATEDEPO + "," +
                        KEY_SUBJECT + "," +
                        " UNIQUE (" + KEY_IDCOURSE +"));";

    public static void onCreate(SQLiteDatabase db) {
        Log.w(LOG_TAG, DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
        onCreate(db);
    }

}

