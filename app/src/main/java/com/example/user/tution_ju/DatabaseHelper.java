package com.example.user.tution_ju;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tuition.db";
    public static final String TABLE_NAME = "tuition_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "LOC";
    //public static final String COL4 = "GENDER";
    //public static final String COL4 = "SUBJECT";
    public DatabaseHelper(Context context)

        {
            super(context, DATABASE_NAME, null, 1);
        }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,LOC)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(new StringBuilder().append("DROP IF TABLE EXISTS ").append(TABLE_NAME).toString());
        //db.execSQL(new StringBuilder().append("DROP IF COLUMN EXISTS").append(COL3).toString());
        onCreate(db);
    }

    public boolean addData(String name,String loc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,loc);
        //contentValues.put(COL4,gender);
        //contentValues.put(COL4,subject);
        long result  = db.insert(TABLE_NAME, null, contentValues);
        //long result = db.insertWithOnConflict(TABLE_NAME,null,contentValues,SQLiteDatabase.CONFLICT_IGNORE);

       /* Cursor cursor = db.rawQuery(String.format("SELECT * FROM%sWHERE%s=?", TABLE_NAME, DatabaseHelper.COL3), new String[]{TVSHOW});
            if(cursor.getCount()<1)
            {
                //cursor.moveToNext();
                result  = db.insert(TABLE_NAME, null, contentValues);


            }*/








        if(result == -1){
            return false;
        }else{
            return true;
        }
    }



    public Cursor showData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }



   /* public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "TVSHOW = ?", new String[] {id});
    }*/
}
