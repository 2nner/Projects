package com.example.pc.forwardcam_new;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getPreferences(0);
        initFragment();
        //
    }

    private void initFragment() {
        android.support.v4.app.Fragment fragment;

        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)) {
            fragment = new LoginFragment();
        } else {
            fragment = new HomeFragment();
        }

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }


}
