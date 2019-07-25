package com.renatojbl99.keykeeper.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.renatojbl99.keykeeper.DataBase.DataHelper;
import com.renatojbl99.keykeeper.DataBase.FeedDB;
import com.renatojbl99.keykeeper.DataBase.User;
import com.renatojbl99.keykeeper.Encrypted.Encriptacion;
import com.renatojbl99.keykeeper.R;


public class UserRegistry extends AppCompatActivity {

    // Atributos
    EditText userRegistryEtUsername, userRegistryEtEmail, userRegistryEtPassword, userRegistryEtRepeatedPassword, userRegistryEtPin, userRegistryEtRepeatedPin;
    Button userRegistryBtnSend;
    DataHelper connUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registry);
        connUser = new DataHelper(getApplicationContext());
        userRegistryEtUsername = (EditText) findViewById(R.id.userRegistryEtUsername);
        userRegistryEtEmail = (EditText) findViewById(R.id.userRegistryEtEmail);
        userRegistryEtPassword = (EditText) findViewById(R.id.userRegistryEtPassword);
        userRegistryEtRepeatedPassword = (EditText) findViewById(R.id.userRegistryEtRepeatedPassword);
        userRegistryEtPin = (EditText) findViewById(R.id.userRegistryEtPin);
        userRegistryEtRepeatedPin = (EditText) findViewById(R.id.userRegistryEtRepeatedPin);
        userRegistryBtnSend = (Button) findViewById(R.id.userRegistryBtnSend);

        // Bulding Button Listenner
        userRegistryBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Encriptacion helper = new Encriptacion();

                String username = userRegistryEtUsername.getText().toString();
                String email = userRegistryEtEmail.getText().toString();
                String password = userRegistryEtPassword.getText().toString();
                String repeatedPassword = userRegistryEtRepeatedPassword.getText().toString();
                String pin = userRegistryEtPin.getText().toString();
                String repeatedPin = userRegistryEtRepeatedPin.getText().toString();



                // TODO: Using the DataBase UserDataBase

                boolean verified = verifyCredentials(password, repeatedPassword, pin, repeatedPin);



                if (isNewUsername(username)==false) { // TODO: User repetido
                    // Send UsernameRepeated Message Error
                    Toast toast1 = Toast.makeText(getApplicationContext(), "This Username already exist!", Toast.LENGTH_LONG);
                    toast1.show();

                } else if (password.equals(repeatedPassword) && pin.equals(repeatedPin) && isNewUsername(username) && verified) { // TODO: todo esta correcto
                    addUserToDataBase(username, email, password, pin);

                } else {
                    System.err.println("\tError!");
                }

            }
        });



    }


    private void addUserToDataBase(String username, String email, String password, String pin){

        User user = new User(username, email, password, pin);
        SQLiteDatabase dbW = connUser.getWritableDatabase();
        user.save(dbW, getApplicationContext());

        finish();


    }

    private boolean isNewUsername(String username){
        try {
            SQLiteDatabase dbR = connUser.getReadableDatabase();
            String [] projection = {FeedDB.CAMPO_USER_ID, FeedDB.CAMPO_USERNAME};
            String [] argsel = {username};

            Cursor cursor = dbR.query(FeedDB.TABLA_USUARIOS, projection, FeedDB.CAMPO_USERNAME + "=?", argsel, null, null, null);
            cursor.moveToFirst();
            String user = cursor.getString(1);
            System.out.println("\tUser ya registrado");
            return false;
        }catch (Exception e){
            System.out.println("\tEs un nuevo usuario");
            return true;
        }

    }


    private boolean verifyCredentials(String password, String repeatedPassword, String pin, String repeatedPin){
        if (password.equals(repeatedPassword) && !pin.equals(repeatedPin)) { //todo Si no coincide los pins
            // Send Message Error
            Toast toast1 = Toast.makeText(getApplicationContext(), "The Pins doesn't match", Toast.LENGTH_SHORT);
            toast1.show();
            return false;
        } else if (!password.equals(repeatedPassword) && pin.equals(repeatedPin)) { //todo Si no coincide las passwords
            // Send Message Error
            Toast toast1 = Toast.makeText(getApplicationContext(), "The Passwords doesn't match", Toast.LENGTH_SHORT);
            toast1.show();
            return false;
        } else {
            System.out.println("\tCredenciales Correctas");
            return true;
        }

    }



}
