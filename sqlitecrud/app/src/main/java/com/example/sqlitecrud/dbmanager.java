package com.example.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class dbmanager extends SQLiteOpenHelper {
    private static final String dbname = "task.db";

    public dbmanager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry = "create table tbl_task ( id integer primary key autoincrement, taskname text, timeRequired text, venue text, date text, time text)";
        sqLiteDatabase.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop Table if exists " + "tbl_task");
        onCreate(sqLiteDatabase);
    }

    public String addrecord(String taskname, String timeRequired, String venue, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("taskname", taskname);
        cv.put("timeRequired", timeRequired);
        cv.put("venue", venue);
        cv.put("date", date);
        cv.put("time", time);
        float res = db.insert("tbl_task", null, cv);

        if (res == -1)
            return "Failed";
        else
            return "Successfully inserted";

    }
    //update Data
    public boolean updateData(String taskname, String timeRequired, String venue, String date, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("taskname", taskname);
        contentValues.put("timeRequired", timeRequired);
        contentValues.put("venue", venue);
        contentValues.put("date", date);
        contentValues.put("time", time);
        float res1 = db.update("tbl_task",contentValues,"time=?",new String[]{time});
        if(res1 !=0){
            return true;
        }
        return false;
    }

    //delete data
    public int deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //int Id = Integer.parseInt(id);
        return db.delete("tbl_task", "id=?", new String[]{id});
    }

    public Cursor readalldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from tbl_task order by id desc";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }
}
