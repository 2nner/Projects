package com.example.pc.forwardcam_new;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class BluetoothFragment extends Fragment{

    Button btn_goTobluetooth;
    public  BluetoothFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_bluetooth, container, false);
        View view = inflater.inflate(R.layout.fragment_bluetooth,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn_goTobluetooth = (Button) view.findViewById(R.id.btn_goTobluetooth);
        btn_goTobluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BluetoothFragment.this.getActivity(), BluetoothActivity.class);
                startActivity(intent);
            }
        });
    }
}