package com.renatojbl99.keykeeper;



import com.renatojbl99.keykeeper.DataBase.RecordsList;
import com.renatojbl99.keykeeper.DataBase.User;


public class LocalUser {
    // Variables globales
    public static long userId = 0;
    public static User localUser = new User();
    public static boolean dataUnlock = false;
    public static RecordsList recordsList = new RecordsList();



    public static void setLocalUser(User user){
        LocalUser.localUser = user;

        LocalUser.userId =  LocalUser.localUser.getUserId();
        System.out.println(LocalUser.localUser.toString());

    }

    public static void signOut(){

        LocalUser.localUser = null;
        LocalUser.userId = 0;
        LocalUser.dataUnlock = false;



    }



}
