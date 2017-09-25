package com.example.pc.forwardcam_new;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


public class UserFragment extends Fragment {

    SharedPreferences pref;
    Button logout;
    Context context;

    public  UserFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_user, container, false);
        context = getActivity();
        initViews(layout);
        return layout;
    }

    private void initViews(View view) {
        pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);

        logout = (Button) view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogout();
            }
        });
    }

    public void doLogout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Constants.IS_LOGGED_IN,false);
        editor.remove(Constants.EMAIL);
        editor.remove(Constants.LASTNAME);
        editor.remove(Constants.FIRSTNAME);
        editor.apply();

        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().finish();
        startActivity(intent);

        Toast.makeText(context,"Logging out",Toast.LENGTH_LONG);
    }
}