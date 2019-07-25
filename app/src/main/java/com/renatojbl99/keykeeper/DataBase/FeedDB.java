package com.renatojbl99.keykeeper.DataBase;

public class FeedDB {

    public static final String NOMBRE_BASE = "RecordsDataBase.db";


    // TODO: Records
    // Constantes campos de la tabla registros
    public static final String TABLA_REGISTROS ="registros";
    public static final String CAMPO_RECORD_ID="recordid";
    public static final String CAMPO_USER_ID="userid";
    public static final String CAMPO_WEB_SITE="website";
    public static final String CAMPO_EMAIL="email";
    public static final String CAMPO_PASSWORD="pass";




    // Crear tabla registros
    public static final String CREAR_TABLA_REGISTROS = "CREATE TABLE `"+ TABLA_REGISTROS +"` (`" +
            CAMPO_RECORD_ID+"` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `"+
            CAMPO_USER_ID+"` INTEGER NOT NULL, `" +
            CAMPO_WEB_SITE+"` TEXT NOT NULL, `" +
            CAMPO_EMAIL+"` TEXT NOT NULL, `" +
            CAMPO_PASSWORD+"` TEXT NOT NULL" +
            ");";




    // TODO: Users
    // Constantes campos de la tabla usuarios
    public static final String TABLA_USUARIOS="usuarios";
    //public static final String CAMPO_USER_ID="userid";
    public static final String CAMPO_USERNAME = "username";
    //public static final String CAMPO_EMAIL="email";
    //public static final String CAMPO_PASSWORD="pass";
    public static final String CAMPO_PIN = "pin";


    // Crear tabla usuarios
    public static final String CREAR_TABLA_USUARIOS = "CREATE TABLE `"+TABLA_USUARIOS+"` (`" +
            CAMPO_USER_ID+"` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `" +
            CAMPO_USERNAME+"` TEXT NOT NULL UNIQUE, `"+
            CAMPO_EMAIL+"` TEXT NOT NULL, `" +
            CAMPO_PASSWORD+"` TEXT NOT NULL, `" +
            CAMPO_PIN+"` TEXT NOT NULL" +
            ");";

    // Borrar tabla
    public static final String DROP_TABLA = "DROP TABLE IF EXISTS ";

    // Variables de SharedPreferences (Local file)
    public static final String S_FILE_NAME = "LocalUser";
    public static final String S_USERNAME = "Username";
    public static final String S_PASSWORD = "Password";
    public static final String S_REMEMBER = "Remember";
    public static final String S_DEFAULT_STRING = "Doesn't remembered";
    public static final Boolean S_DEFAULT_BOOL = false;



}
