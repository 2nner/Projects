package com.example.pc.forwardcam_new;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginFragment extends android.support.v4.app.Fragment {

    EditText email, password;
    ImageButton loginBtn;
    ImageButton goToRegisterBtn;
    //Button btn_test; // 태현이가 추가한거 (임시)버튼, 너가 나중에 한 줄위에 있는 로그인버튼으로 뷰페이저 연결하셈,난 DB가 없어서 임시방편으로 만들어놓은거
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
        //btn_test = (Button) view.findViewById(R.id.btn_test);      // 태현이가 추가한거 (임시로)
        goToRegisterBtn = (ImageButton) view.findViewById(R.id.btn_goToRegister);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        /*btn_test.setOnClickListener(new View.OnClickListener() {  // 태현이가 추가한거 (임시로)
            @Override
            public void onClick(View v) {                      // 태현이가 추가한거 (임시로)
                Intent intent = new Intent(LoginFragment.this.getActivity(), SlideActivity.class);
                startActivity(intent);
            }
        });*/

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

        if (isInternetAvailable()) {
            if(str_email.isEmpty()) {
                email.setError("이메일을 입력해 주세요!");
            } else if(str_password.isEmpty()) {
                password.setError("비밀번호를 입력해 주세요!");
            } else {
                progress.setVisibility(View.VISIBLE);
                loginProcess(str_email,str_password);
            }
        }
        else {
            Toast.makeText(context,"인터넷 연결을 확인해 주세요!",Toast.LENGTH_LONG).show();
        }
    }

    private void loginProcess(String email, String password) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
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
                    goToHome();
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
        android.support.v4.app.Fragment fragment = new RegisterFragment();
        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void goToHome() {
        android.support.v4.app.Fragment fragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private Boolean isInternetAvailable() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable();
        boolean isMobileConnected = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
        boolean isWifiAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable();
        boolean isWifiConnected = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();

        if((isMobileAvailable && isMobileConnected) || (isWifiAvailable && isWifiConnected))
            return true;
        else
            return false;

    }
}
