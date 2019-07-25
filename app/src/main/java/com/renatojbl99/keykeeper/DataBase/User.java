package com.renatojbl99.keykeeper.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.renatojbl99.keykeeper.Encrypted.Encriptacion;

public class User {

    // Atributos
    private long userId;
    private String username;
    private String email;
    private String password;
    private String pin;
    private boolean created = false;
    private boolean saved = false;

    Encriptacion helper = new Encriptacion();

    // Constructor para un usuario sin id (aun no guardado en la base de datos)
    public User(String username, String email, String password, String pin) {

        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setPin(pin);

        this.created = true;
        this.saved = false;


    }

    // Constructor para un usuario con id, ya guardado en la base datos
    public User(long userId, String username, String email, String password, String pin) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.pin = pin;

        this.created = true;
        this.saved = true;

    }

    // Constructor vacio
    public User() {
    }

    // Metodos set
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = helper.encriptar(email);
    }

    public void setPassword(String password) {
        this.password = helper.encriptar(password);
    }

    public void setPin(String pin) {
        this.pin = helper.encriptar(pin);
    }

    // Metodos get
    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPin() {
        return pin;
    }



    // Sobreescribimos el meotodo toSrting
    @Override
    public String toString(){

        return String.format("\n\tUser Id: %d\n\tUsername: %s\n\tEmail: %s\n\tPassword: %s\n\tPin: %s\n", getUserId(), getUsername(), getEmail(), getPassword(), getPin());


    }

    /*
    public void save(Context context){
        if (this.created==true && this.saved==false) {
            // TODO: Ahora intancioamos un objeto
            DataHelper connUser = new DataHelper(context);

            SQLiteDatabase dbW = connUser.getWritableDatabase();

            ContentValues values = new ContentValues();

            // values.put(FeedDB.CAMPO_USER_ID, getUserId());
            values.put(FeedDB.CAMPO_USERNAME, getUsername());
            values.put(FeedDB.CAMPO_EMAIL, getEmail());
            values.put(FeedDB.CAMPO_PASSWORD, getPassword());
            values.put(FeedDB.CAMPO_PIN, getPin());


            Long idResultante = dbW.insert(FeedDB.TABLA_USUARIOS, FeedDB.CAMPO_USER_ID, values);

                Toast.makeText(context, "User id: " + idResultante, Toast.LENGTH_SHORT).show();

            this.setUserId(idResultante);

            checkSave(context);

        } else {
            System.err.println("\t\tError, the user has not been created yet.");
            // TODO: Send message error
            Toast.makeText(context, "Error, the user has not been created yet.", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkSave(Context context){
        DataHelper conn = new DataHelper(context);
        SQLiteDatabase dbR = conn.getReadableDatabase();
        String [] projection = {FeedDB.CAMPO_USER_ID, FeedDB.CAMPO_EMAIL, FeedDB.CAMPO_PASSWORD, FeedDB.CAMPO_PIN};
        String [] argsel = {this.username};



        try {
            Cursor cursor = dbR.query(FeedDB.TABLA_USUARIOS, projection, FeedDB.CAMPO_USERNAME + "=?", argsel, null, null, null);

            this.saved = true;


        } catch (Exception e){
            System.err.println("\t\tUser no save correctly!");
            this.saved = false;
        }

    }*/


    public void save(SQLiteDatabase dbW, Context context){
        if (this.created==true && this.saved==false) {
            // TODO: Ahora intancioamos un objeto


            ContentValues values = new ContentValues();

            // values.put(FeedDB.CAMPO_USER_ID, getUserId());
            values.put(FeedDB.CAMPO_USERNAME, getUsername());
            values.put(FeedDB.CAMPO_EMAIL, getEmail());
            values.put(FeedDB.CAMPO_PASSWORD, getPassword());
            values.put(FeedDB.CAMPO_PIN, getPin());


            Long idResultante = dbW.insert(FeedDB.TABLA_USUARIOS, FeedDB.CAMPO_USER_ID, values);

            Toast.makeText(context, "User id: " + idResultante, Toast.LENGTH_SHORT).show();

            this.setUserId(idResultante);

            this.saved = true;

        } else {
            System.err.println("\t\tError, the user has not been created yet.");
            // TODO: Send message error
            Toast.makeText(context, "Error, the user has not been created yet.", Toast.LENGTH_SHORT).show();
        }
    }
    /*
    public void checkSave(SQLiteDatabase dbW, Context context){

        SQLiteDatabase dbR = conn.getReadableDatabase();
        String [] projection = {FeedDB.CAMPO_USER_ID, FeedDB.CAMPO_EMAIL, FeedDB.CAMPO_PASSWORD, FeedDB.CAMPO_PIN};
        String [] argsel = {this.username};



        try {
            Cursor cursor = dbR.query(FeedDB.TABLA_USUARIOS, projection, FeedDB.CAMPO_USERNAME + "=?", argsel, null, null, null);

            this.saved = true;


        } catch (Exception e){
            System.err.println("\t\tUser no save correctly!");
            this.saved = false;
        }

    }*/


}
