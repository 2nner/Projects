package com.example.pc.forwardcam_new;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginFragment extends Fragment {

    EditText email, password;
    ImageButton loginBtn;
    Button goToRegisterBtn;
    ProgressBar progress;
    SharedPreferences pref;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        context = getActivity();
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

    private void loginProcess(String email, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Toast.makeText(context, resp.getMessage(), Toast.LENGTH_LONG).show();

                if(resp.getResult().equals(Constants.SUCCESS)){
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean(Constants.IS_LOGGED_IN,true);
                    editor.putString(Constants.EMAIL,resp.getUser().getEmail());
                    editor.putString(Constants.LASTNAME,resp.getUser().getLastname());
                    editor.putString(Constants.FIRTSNAME,resp.getUser().getFirstname());
                    editor.apply();
                    goToFirst();
                }
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void goToRegister() {
        Fragment fragment = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }

    private void goToFirst() {
        Fragment fragment = new FirstFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }

}
