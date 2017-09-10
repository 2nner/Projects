package com.example.pc.forwardcam_new;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getPreferences(0);
        initFragment();
    }

    private void initFragment() {
        Fragment fragment;

        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)) {
            fragment = new FirstFragment();
            Log.e("asdf", "First");
        } else {

            fragment = new LoginFragment();
            Log.e("asdf", "LOG");
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }
}
