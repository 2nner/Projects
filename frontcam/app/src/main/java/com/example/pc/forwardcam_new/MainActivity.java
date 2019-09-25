package com.example.pc.forwardcam_new;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    //private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //backPressCloseHandler = new BackPressCloseHandler(this);
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        initFragment();
        //
    }

    /*
    @Override public void onBackPressed() { //super.onBackPressed();
        backPressCloseHandler.onBackPressed(); }*/

    private void initFragment() {
        android.support.v4.app.Fragment fragment;
        boolean checkLogin;

        if(Constants.IS_LOGGED_IN.equals(true))
            checkLogin = false;
        else
            checkLogin = true;

        if(checkLogin) {
            fragment = new LoginFragment();
        } else {
            finish();
            Intent intent = new Intent(this, SlideActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),Constants.IS_LOGGED_IN,Toast.LENGTH_LONG).show();
            return;
        }

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }

}
