package com.renatojbl99.keykeeper.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

    public DataHelper(Context context) {
        super(context, FeedDB.NOMBRE_BASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(FeedDB.CREAR_TABLA_USUARIOS);
        database.execSQL(FeedDB.CREAR_TABLA_REGISTROS);
        System.out.println("\n\n\nTABLAS CREADAS EXITOSAMENTE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL(FeedDB.DROP_TABLA+FeedDB.TABLA_USUARIOS);
        database.execSQL(FeedDB.DROP_TABLA+FeedDB.TABLA_REGISTROS);
        onCreate(database);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public void deleteAllData(){


    }

}

