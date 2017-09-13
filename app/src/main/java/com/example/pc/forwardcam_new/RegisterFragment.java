package com.example.pc.forwardcam_new;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterFragment extends Fragment {

    EditText lastname, firstname, email, password, confirm_password;
    ImageButton reg;
    ProgressBar progress;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        context = getActivity();
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        lastname = (EditText) view.findViewById(R.id.et_lastname);
        firstname = (EditText) view.findViewById(R.id.et_firstname);
        email = (EditText) view.findViewById(R.id.et_email);
        password = (EditText) view.findViewById(R.id.et_pw);
        confirm_password = (EditText) view.findViewById(R.id.et_con_pw);
        reg = (ImageButton) view.findViewById(R.id.btn_register_register);

        progress = (ProgressBar) view.findViewById(R.id.progress);

        lastname.setText(null);
        firstname.setText(null);
        email.setText(null);
        password.setText(null);
        confirm_password.setText(null);



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate() == true) {
                    String str_lastname = lastname.getText().toString();
                    String str_firstname = firstname.getText().toString();
                    String str_email = email.getText().toString();
                    String str_password = password.getText().toString();

                    progress.setVisibility(View.VISIBLE);
                    registerProcess(str_lastname,str_firstname,str_email,str_password);
                    goToLogin();
                } else {
                    Toast.makeText(context, "계정 생성 실패!", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });


    }

    public void registerProcess(String lastname, String firstname, String email, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        ServerRequest request = new ServerRequest();

        User user = new User();

        user.setLastname(lastname);
        user.setFirstname(firstname);
        user.setEmail(email);
        user.setPassword(password);

        request.setOperation(Constants.REGISTER_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Toast.makeText(context, resp.getMessage(), Toast.LENGTH_LONG).show();
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

    public boolean validate() {
        boolean valid = true;

        String str_lastname = lastname.getText().toString();
        String str_firstname = firstname.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();
        String str_confirmPassword = confirm_password.getText().toString();

        if (str_lastname.isEmpty()) {
            lastname.setError("성을 입력해주세요!");
            valid = false;
        } else {
            lastname.setError(null);
        }

        if (str_firstname.isEmpty()) {
            firstname.setError("이름을 입력해주세요!");
            valid = false;
        } else {
            firstname.setError(null);
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(str_email).matches()) {
            email.setError("메일의 형식이 다릅니다!");
            valid = false;
        } else {
            email.setError(null);
        }

        if (str_email.isEmpty()) {
            email.setError("메일을 입력해주세요!");
            valid = false;
        } else {
            email.setError(null);
        }

        if (str_password.isEmpty()) {
            password.setError("비밀번호를 입력해주세요!");
            valid = false;
        } else {
            password.setError(null);
        }

        if (str_confirmPassword.isEmpty() || !(str_confirmPassword.equals(str_password))) {
            confirm_password.setError("비밀번호가 다릅니다!");
            valid = false;
        } else {
            confirm_password.setError(null);
        }

        return valid;
    }

    private void goToLogin(){
        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }
}
