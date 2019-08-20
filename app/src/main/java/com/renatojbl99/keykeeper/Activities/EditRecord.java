package com.renatojbl99.keykeeper.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.renatojbl99.keykeeper.DataBase.DataHelper;
import com.renatojbl99.keykeeper.DataBase.Record;
import com.renatojbl99.keykeeper.LocalUser;
import com.renatojbl99.keykeeper.R;



public class EditRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
    }


    // Atributos
    EditText addRecordEtWebSite, addRecordEtEmail, addRecordEtPassword;
    Button addRecordBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);


        addRecordEtWebSite = (EditText) findViewById(R.id.addRecordEtWebSite);
        addRecordEtEmail = (EditText) findViewById(R.id.addRecordEtEmail);
        addRecordEtPassword = (EditText) findViewById(R.id.addRecordEtPassword);
        addRecordBtnAdd = (Button) findViewById(R.id.addRecordBtnAdd);




        // listener del button add
        addRecordBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                // todo Agregar registro


                String webSite = addRecordEtWebSite.getText().toString();
                String email = addRecordEtEmail.getText().toString();
                String password = addRecordEtPassword.getText().toString();



                // Llamamos al metodo
                addRecordToDataBase(webSite, email, password);



                // Go to Showing Data
                Intent intentMain = new Intent(AddRecord.this, MainActivity.class);
                startActivity(intentMain);

                finish();

            }
        });



    }

    // TODO : Este metodo debe ser modificado por uno que edite el record
    private void addRecordToDataBase(String webSite, String email, String password){

        Record record = new Record(LocalUser.localUser.getUserId(), webSite, email, password);
        DataHelper conn = new DataHelper(getApplicationContext());
        SQLiteDatabase dbW = conn.getWritableDatabase();
        record.save(dbW, getApplicationContext());
        System.out.println(record.toString(false));
        System.out.println(record.toString(true));


    }











    /*
    private void addRecordToDataBase(String webSite, String email, String password){

        DataRecordHelper conn = new DataRecordHelper(getApplicationContext());

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FeedRecordsDB.CAMPO_USER_ID, LocalUser.userId);
        //values.put(FeedRecordsDB.CAMPO_RECORD_ID, RecordsDataBase.recordId);
        values.put(FeedRecordsDB.CAMPO_WEB_SITE, webSite);
        values.put(FeedRecordsDB.CAMPO_EMAIL, email);
        values.put(FeedRecordsDB.CAMPO_PASSWORD, password);


        Long idResultante =  db.insert(FeedRecordsDB.TABLA_REGISTROS, FeedRecordsDB.CAMPO_RECORD_ID, values);

        Toast.makeText(getApplicationContext(), "Id registro: "+idResultante, Toast.LENGTH_SHORT).show();

    }*/

    /*
    private void sugarAddRecord(String website, String email, String password){
        int userId = LocalUser.userId;
        Records1 record = new Records1(userId, website, email, password);
        record.save();

    }
    */

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // TODO: Go to Showing Data
        Intent intentMain = new Intent(EditRecord.this, MainActivity.class);
        startActivity(intentMain);

        finish();
    }
}
