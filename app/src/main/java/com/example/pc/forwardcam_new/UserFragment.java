package com.example.pc.forwardcam_new;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.R.id.edit;
import static android.content.Context.MODE_PRIVATE;


public class UserFragment extends Fragment {

    SharedPreferences pref;
    ImageButton logout;
    Context context;
    ImageButton change_pw;
    //Activity root = getActivity();

    public  UserFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_user, container, false);
        context = getActivity();
        initViews(layout);
        return layout;
    }

    void show()
    {
        final EditText edittext = new EditText(this.getActivity());
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(30);
        edittext.setFilters(FilterArray);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("비밀번호 변경");
        builder.setMessage("바꿀 비밀번호를 입력해주세요");
        builder.setView(edittext);
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"말로만 변경되었습니다" ,Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }


    private void initViews(View view) {
        pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);

        logout = (ImageButton) view.findViewById(R.id.logout);
        change_pw = (ImageButton) view.findViewById(R.id.btn_user_pw);


        change_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

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