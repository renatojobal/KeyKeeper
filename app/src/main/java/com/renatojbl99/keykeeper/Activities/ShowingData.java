package com.renatojbl99.keykeeper.Activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.renatojbl99.keykeeper.DataBase.DataHelper;
import com.renatojbl99.keykeeper.DataBase.RecordsList;
import com.renatojbl99.keykeeper.LocalUser;
import com.renatojbl99.keykeeper.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowingData.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowingData#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowingData extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SwipeMenuListView listView;
    ArrayAdapter adapter;


    View vista;
    DataHelper connRecord;


    private OnFragmentInteractionListener mListener;

    public ShowingData() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowingData.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowingData newInstance(String param1, String param2) {
        ShowingData fragment = new ShowingData();
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
        vista = (inflater.inflate(R.layout.fragment_showing_data, container, false));

        listView = (SwipeMenuListView) vista.findViewById(R.id.showingDataLvRecords);

        adapter = new ArrayAdapter<String>(vista.getContext(),android.R.layout.simple_list_item_1, LocalUser.recordsList.listInformation);

        listView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        vista.getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(1);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // delete
                        System.out.println("Ddelte");
                        break;

                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });











        cargarLvRecords();




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


    public void cargarLvRecords(){

        try{
            System.out.println("\n\n\nUnlock = "+ LocalUser.dataUnlock);
            RecordsList recordsList = new RecordsList();
            listView.setAdapter(recordsList.listAll(vista.getContext(), LocalUser.dataUnlock));

        }catch (Exception e){
            System.out.println("Error!: "+e);

        }



    }






    /*
    private void sugarCargarLvRecords(){
        List<Records1> records = Records1.listAll(Records1.class);
        ArrayList<String> lista1 = new ArrayList<String>();


        for(int i =0; i < records.size(); i++){
            Records1 result = records.get(i);

            lista1.add("Website: "+result.getWebsite()+"\n  Email: "+result.getEmail()+"\n  Password: "+result.getPassword());


        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_list_item_1, lista1);

        listView.setAdapter(adapter);


    }
    */
    /*
    public void cargarLvRecords(){

        ArrayList<String> listaInformacion= new ArrayList<String>();
        connRecord = new DataRecordHelper(vista.getContext());
        SQLiteDatabase dbR = connRecord.getReadableDatabase();

        Encriptacion helper = new Encriptacion();

        try{
            Cursor cursor = dbR.rawQuery("SELECT * FROM "+FeedRecordsDB.TABLA_REGISTROS, null);

            while (cursor.moveToNext()){

                if((cursor.getInt(1) == LocalUser.userId) && dataUnlock) {

                    String element = String.format("Website: %s\n  Email: %s\n  Password: %s\n",
                            cursor.getString(2), helper.desencriptar(cursor.getString(3)), helper.desencriptar(cursor.getString(4)));
                    listaInformacion.add(element);
                }else if(cursor.getInt(1) == LocalUser.userId){
                    String element = String.format("Website: %s\n  Email: %s\n  Password: %s\n",
                            cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    listaInformacion.add(element);

                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_list_item_1, listaInformacion);

            listView.setAdapter(adapter);

        }catch (Exception e){
            System.out.println("Error!: "+e);

        }





    }
    */










}
