package com.renatojbl99.keykeeper.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.renatojbl99.keykeeper.LocalUser;

import java.util.ArrayList;

public class RecordsList {

    public ArrayList<String> listInformation = new ArrayList<>();

    public ArrayAdapter<String> listAll(Context context, boolean dataUnlock){

        listInformation.clear();



        DataHelper connRecord = new DataHelper(context);
        SQLiteDatabase dbR = connRecord.getReadableDatabase();


        try{

            Cursor cursor = dbR.rawQuery("SELECT * FROM "+ FeedDB.TABLA_REGISTROS, null);

            while (cursor.moveToNext()){

                if((cursor.getInt(1) == LocalUser.userId)) {
                    Record record = new Record(cursor.getLong(0), cursor.getLong(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    listInformation.add(record.toString(dataUnlock));
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, listInformation);

            return adapter;

        }catch (Exception e){
            System.out.println("Error!: "+e);
            ArrayAdapter<String> adapter = null;
            return adapter;

        }

    }




}
