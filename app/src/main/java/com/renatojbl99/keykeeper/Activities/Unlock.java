package com.renatojbl99.keykeeper.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.renatojbl99.keykeeper.DataBase.DataHelper;
import com.renatojbl99.keykeeper.Encrypted.Encriptacion;
import com.renatojbl99.keykeeper.LocalUser;
import com.renatojbl99.keykeeper.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Unlock.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Unlock#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Unlock extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Aqui creo que se instancia los botones de ambos fragments
    Button unlockBtnAccept;
    EditText unlockEtPin;
    View vista;
    DataHelper connRecord;

    private OnFragmentInteractionListener mListener;

    public Unlock() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Unlock.
     */
    // TODO: Rename and change types and number of parameters
    public static Unlock newInstance(String param1, String param2) {
        Unlock fragment = new Unlock();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = (inflater.inflate(R.layout.fragment_unlock, container, false));
        unlockBtnAccept = (Button) vista.findViewById(R.id.unlockBtnAccept);
        unlockEtPin = (EditText) vista.findViewById(R.id.unlockEtPin);


        connRecord = new DataHelper(vista.getContext());

        /**
         * Creamos el listener del boton
         *
         */

        unlockBtnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Desencriptar los datos
                System.out.println("\t\t\tEl boton es presionado");
                Encriptacion helper = new Encriptacion();

                String targetPin = helper.encriptar(unlockEtPin.getText().toString());
                verifyPin(targetPin);


            }
        });

        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void verifyPin(String targetPin){
        String realPin = String.valueOf(LocalUser.localUser.getPin());
        System.out.println(realPin);
        System.out.println(targetPin);
        if(targetPin.equals(realPin)){
            System.out.println();
            LocalUser.dataUnlock = true;

            // TODO: Go to MainActivity
            Intent intentMain = new Intent(getContext(), MainActivity.class);
            intentMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentMain);

        } else {
            // TODO: Send Pin Error
            Toast.makeText(vista.getContext(),"Wrong Pin", Toast.LENGTH_SHORT).show();

        }

    }

    /*
    private void verifyPin(String targetPin, int userid){
        SQLiteDatabase dbR = connRecord.getReadableDatabase();
        String [] projection = {FeedUsersDB.CAMPO_PIN};
        String suserid = String.format("%d",userid);
        String [] argsel = {suserid};

        try {
            System.out.println("Entra a revisar en la base de datos");
            Cursor cursor = dbR.query(FeedUsersDB.TABLA_USUARIOS, projection, FeedUsersDB.CAMPO_USER_ID + "=?", argsel, null, null, null);

            cursor.moveToFirst();

            String realPin = cursor.getString(0);
            System.out.println("Real pin: "+ realPin);
            if (realPin.equals(targetPin)){
                ShowingData.dataUnlock = true;
                // TODO: Go to MainActivity
                Intent intentMain = new Intent(getContext(), MainActivity.class);
                intentMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentMain);



                Intent intent = new Intent(getContext, MyActivity.class);
                intent.setFlag(Intent.CLEAR_TASK);
                startActivity(intent);

            }else {
                // TODO: Send Pin Error
                Toast toast1 = Toast.makeText(vista.getContext(),"Wrong Pin", Toast.LENGTH_SHORT);
                toast1.show();

            }




        } catch (Exception e){
            System.out.println("Catch!!!!!");

        }

    }*/




}
