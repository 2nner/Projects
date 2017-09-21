package com.example.pc.forwardcam_new;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;



public class BluetoothFragment extends Fragment{
    public  BluetoothFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_bluetooth, container, false);

        // String[] values = {"Test1","Test2","Test3","Test4"};
        // ArrayAdapter<String> adapter = new ArrayAdapter (this, R.layout.list_textview, values);
        /*ArrayAdapter<String> adapter;
        ArrayList<String> al = new ArrayList<String>();
        al.add("망고");
        al.add("수박");
        al.add("오렌지");
        al.add("귤");
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),list_textview,al );

        ListView list = (ListView) getActivity().findViewById(R.id.message_listview);
        list.setAdapter(adapter);*/

        return layout;
    }
}