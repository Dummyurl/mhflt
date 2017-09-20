package com.changju.fanlitou.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chengww on 2017/6/11.
 * 本地搜索历史保存
 */

public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {

    private static Integer version = 1;

    public RecordSQLiteOpenHelper(Context context,String name) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //name 记录名 count 记录数
        db.execSQL("create table records(id integer primary key autoincrement,name varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
