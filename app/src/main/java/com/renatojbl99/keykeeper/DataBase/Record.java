package com.renatojbl99.keykeeper.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.renatojbl99.keykeeper.Encrypted.Encriptacion;

public class Record {
    // Atributos
    private long userId;
    private long registroId;
    private String webSite;
    private String email;
    private String password;

    private boolean created = false;
    private boolean saved = false;


    // Creamos objeto para encriptar
    Encriptacion helper = new Encriptacion();

    // Constructor
    public Record(long userId, String webSite, String email, String password) {
        this.setUserId(userId);

        this.setWebSite(webSite);
        this.setEmail(email);
        this.setPassword(password);

        this.created = true;

    }

    // Constructor para record ya guardado en la base de datos


    public Record(long registroId, long userId, String webSite, String email, String password) {
        this.registroId = registroId;
        this.userId = userId;
        this.webSite = webSite;
        this.email = email;
        this.password = password;

        this.created = true;
        this.saved = true;
    }

    // Metodos set
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setRegistroId(long registroId) {
        this.registroId = registroId;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public void setEmail(String email) {
        this.email = helper.encriptar(email);
    }

    public void setPassword(String password) {
        this.password = helper.encriptar(password);
    }


    // Metodos get
    public long getUserId() {
        return userId;
    }

    public long getRegistroId() {
        return registroId;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public String toString(boolean unlocked){
        String string="";
        if(unlocked){
            string = String.format(" %s\n    '%s'\n    '%s'\n", getWebSite(), helper.desencriptar(getEmail()), helper.desencriptar(getPassword()));
        } else {
            string = String.format(" %s\n    '%s'\n    '%s'\n", getWebSite(), getEmail(), getPassword());
        }
        return string;
    }


    public void save(SQLiteDatabase dbW, Context context){
        if (this.created == true && this.saved == false) {
            // TODO: Ahora intancioamos un objeto


            ContentValues values = new ContentValues();

            values.put(FeedDB.CAMPO_USER_ID, getUserId());
            //values.put(FeedRecordsDB.CAMPO_RECORD_ID, RecordsDataBase.recordId); Este valor es autoincrementable
            values.put(FeedDB.CAMPO_WEB_SITE, webSite);
            values.put(FeedDB.CAMPO_EMAIL, email);
            values.put(FeedDB.CAMPO_PASSWORD, password);


            Long idResultante = dbW.insert(FeedDB.TABLA_REGISTROS, FeedDB.CAMPO_RECORD_ID, values);

            Toast.makeText(context, "Id registro: " + idResultante, Toast.LENGTH_SHORT).show();

            this.setRegistroId(idResultante);
            this.saved = true;
        } else {
            System.err.println("Error, the record has not been created yet.");
            // TODO: Send message error
            Toast.makeText(context, "Error, the record has not been created yet.", Toast.LENGTH_SHORT).show();
        }
    }



}
