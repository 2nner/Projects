package com.example.pc.forwardcam_new;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;


public class LoginFragment extends Fragment {

    EditText email, password;
    ImageButton loginBtn;
    Button goToRegisterBtn;
    ProgressBar progress;
    SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        pref = getActivity().getPreferences(0);

        email = (EditText) view.findViewById(R.id.main_email);
        password = (EditText) view.findViewById(R.id.main_pw);
        loginBtn = (ImageButton) view.findViewById(R.id.btn_main_login);
        goToRegisterBtn = (Button) view.findViewById(R.id.btn_goToRegister);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        goToRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });

    }

    public void doLogin(){
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();

        if(str_email.isEmpty()) {
            email.setError("이메일을 입력해 주세요!");
        } else if(str_password.isEmpty()) {
            password.setError("비밀번호를 입력해 주세요!");
        } else {
            progress.setVisibility(View.VISIBLE);
            loginProcess(str_email,str_password);
        }
    }

    public void onClickGotoRegister() {
        goToRegister();
    }

    private void loginProcess(String eamil, String pasword) {

    }

    private void goToRegister(){
        Fragment reg = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,reg);
        ft.commit();
    }


}
