package com.renatojbl99.keykeeper.Activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.renatojbl99.keykeeper.DataBase.DataHelper;
import com.renatojbl99.keykeeper.DataBase.FeedDB;
import com.renatojbl99.keykeeper.DataBase.User;
import com.renatojbl99.keykeeper.Encrypted.Encriptacion;
import com.renatojbl99.keykeeper.LocalUser;
import com.renatojbl99.keykeeper.R;


public class LoginActivity extends AppCompatActivity {

    // Atributos
    TextView loginTvTitle, loginTvRegister;
    EditText loginEtUsername, loginEtPassword;
    Button loginBtnSignIn;
    CheckBox loginCbRemember;

    // Variables locales
    public String username;
    public String password;


    DataHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginTvTitle = (TextView) findViewById(R.id.loginTvTitle);
        loginTvRegister = (TextView) findViewById(R.id.loginTvRegister);
        loginEtUsername = (EditText) findViewById(R.id.loginEtUsername);
        loginEtPassword = (EditText) findViewById(R.id.loginEtPassword);
        loginBtnSignIn = (Button) findViewById(R.id.loginBtnSignIn);
        loginCbRemember = (CheckBox) findViewById(R.id.loginCbRemember);



        conn = new DataHelper(getApplicationContext());
        SQLiteDatabase db = conn.getWritableDatabase();



        uploadFileSPreferences();



        if(previousRemembered() == true && verifySavedCredentials()){
            setLocalUser(username);

            // TODO: Go to MainActivity
            Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intentMain);
            finish();

        }





        // Listener del boton Sign In
        loginBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 Revisamos si los datos ingresados estan en la listaUsuarios, aqui obviamente no habra nada porque
                 cada vez que volvemos a abrir la app, se borraran los datos. Provisional antes de poner la base de datos
                 */
                Encriptacion helper = new Encriptacion();


                username = loginEtUsername.getText().toString();
                password = loginEtPassword.getText().toString();

                password = helper.encriptar(password);

                int result = verifyUsername(username, password);

                if(result == 1){ // TODO: Si estan correctas las credenciales

                    setLocalUser(username);
                    if(loginCbRemember.isChecked()){
                        saveUserCredentials();
                    } else {
                        deleteSaveCredentials();
                    }

                    // TODO: Go to MainActivity
                    Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentMain);
                    finish();

                }else if(result == 0) { // TODO: Si esta correcto el usuario pero no la contrasena
                    // TODO: Send Message Error
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT);
                    toast1.show();

                } else if (result == 3){ // TODO: Caracteres vacios
                    // TODO: Send Message Error
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Wrong Password or Username", Toast.LENGTH_SHORT);
                    toast1.show();

                } else if (result == -2){ // TODO: Codigo secreto para borrar toda la base de datos
                    deleteSaveCredentials();
                    deleteDataBase();
                    // TODO: Send Message +
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Data Base Deleted Successful!", Toast.LENGTH_SHORT);
                    toast1.show();



                } else{ // TODO: Si el usuario name no existe
                    // TODO: Send Message Error
                    Toast toast1 = Toast.makeText(getApplicationContext(),"Doesn't exist this username", Toast.LENGTH_SHORT);
                    toast1.show();
                }


            }
        });

        // TODO: Listener del text Register
        loginTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:  Se debe ir a la pantalla del formulario
                Intent intentRegistry = new Intent(LoginActivity.this, UserRegistry.class);
                startActivity(intentRegistry);

            }
        });




    }

    private int verifyUsername(String targetUsername, String targetPassword) {
        SQLiteDatabase dbR = conn.getReadableDatabase();
        String [] projection = {FeedDB.CAMPO_PASSWORD};
        String [] argsel = {targetUsername};


        if (targetUsername.equals("") || targetPassword.equals("")){ // TODO: Caracteres vacios
            return 3;
        } else if (targetUsername.equals("Master") || targetPassword.equals("FRARYRFSYSNSDR")) { // TODO: Codigo secreto para borrar toda la base de datos
            return -2;
        }


        try {
            Cursor cursor = dbR.query(FeedDB.TABLA_USUARIOS, projection, FeedDB.CAMPO_USERNAME + "=?", argsel, null, null, null);

            cursor.moveToFirst();


            String realPassword = cursor.getString(0);

            if(realPassword.equals(targetPassword)){ // TODO: Si las credenciales estan correctas
                return 1;

            }else { // TODO: Si la contrasena esta incorrecta

                return 0;
            }


        } catch (Exception e){ // TODO: Si el usaurio no existe
            System.err.println("This user doesn't exist!");
            return -1;

        }




    }


    // TODO: Metodo para borrar la base de datos
    private void deleteDataBase(){

    }


    private void setLocalUser(String targetUsername){
        SQLiteDatabase dbR = conn.getReadableDatabase();
        String [] projection = {FeedDB.CAMPO_USER_ID, FeedDB.CAMPO_EMAIL, FeedDB.CAMPO_PASSWORD, FeedDB.CAMPO_PIN};
        String [] argsel = {targetUsername};



        try {
            Cursor cursor = dbR.query(FeedDB.TABLA_USUARIOS, projection, FeedDB.CAMPO_USERNAME + "=?", argsel, null, null, null);
            cursor.moveToFirst();

            User user = new User(cursor.getLong(0), targetUsername, cursor.getString(1), cursor.getString(2), cursor.getString(3));

            LocalUser.setLocalUser(user);



        } catch (Exception e){
            System.out.println("Catch!!!!!");

        }
    }

    private void uploadFileSPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(FeedDB.S_FILE_NAME, Context.MODE_PRIVATE);
    }

    private void saveUserCredentials(){
        SharedPreferences sharedPreferences = getSharedPreferences(FeedDB.S_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(FeedDB.S_USERNAME, LocalUser.localUser.getUsername());
        editor.putString(FeedDB.S_PASSWORD, LocalUser.localUser.getPassword());
        editor.putBoolean(FeedDB.S_REMEMBER, true);
        System.out.println("\t\tCREDENTIALS SAVED CORRECTLY");
        presentCredentials();

        editor.commit();

    }

    private boolean verifySavedCredentials(){
        SharedPreferences sharedPreferences = getSharedPreferences(FeedDB.S_FILE_NAME, Context.MODE_PRIVATE);
        String rememberedUsername = sharedPreferences.getString(FeedDB.S_USERNAME, FeedDB.S_DEFAULT_STRING);
        String rememberedPassword = sharedPreferences.getString(FeedDB.S_PASSWORD, FeedDB.S_DEFAULT_STRING);

        int result = verifyUsername(rememberedUsername, rememberedPassword);
        if (result == 1){
            System.out.println("\t\tCREDENTIALS VERIFIED");
            presentCredentials();
            return true;
        } else {
            return false;
        }
    }

    public void deleteSaveCredentials(){
        SharedPreferences sharedPreferences = getSharedPreferences(FeedDB.S_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(FeedDB.S_USERNAME, FeedDB.S_DEFAULT_STRING);
        editor.putString(FeedDB.S_PASSWORD, FeedDB.S_DEFAULT_STRING);
        editor.putBoolean(FeedDB.S_REMEMBER, FeedDB.S_DEFAULT_BOOL);

        System.out.println("\t\tDELETED CREDENTIALS!");
        presentCredentials();
        editor.commit();


    }

    public boolean previousRemembered(){
        SharedPreferences sharedPreferences = getSharedPreferences(FeedDB.S_FILE_NAME, Context.MODE_PRIVATE);

        boolean rememberedUsername = sharedPreferences.getBoolean(FeedDB.S_REMEMBER, FeedDB.S_DEFAULT_BOOL);

        return rememberedUsername;
    }

    public void presentCredentials(){
        SharedPreferences sharedPreferences = getSharedPreferences(FeedDB.S_FILE_NAME, Context.MODE_PRIVATE);
        String rememberedUsername = sharedPreferences.getString(FeedDB.S_USERNAME, FeedDB.S_DEFAULT_STRING);
        String rememberedPassword = sharedPreferences.getString(FeedDB.S_PASSWORD, FeedDB.S_DEFAULT_STRING);

        System.out.println("\tUSER: "+rememberedUsername);
        System.out.println("\tPASS: "+rememberedPassword);

    }




}
