package com.example.pc.forwardcam_new;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;


public class UserFragment extends Fragment {

    SharedPreferences pref;
    ImageButton logout;
    Context context;
    ImageButton change_pw; // 태현이가 추가한 버튼(비밀번호 변경)
    TextView tv_fullname, tv_email;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_user_dialog, null);
        builder.setView(view);
        final Button submit = (Button) view.findViewById(R.id.buttonSubmit);
        final EditText pw_01 = (EditText) view.findViewById(R.id.et_pw_01);   //  현재 비밀번호
        final EditText pw_02 = (EditText) view.findViewById(R.id.et_pw_02);   //  변경할 비밀번호

        final AlertDialog dialog = builder.create();
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {                      // OK버튼 눌렀을 때 처리
                String strPW_01 = pw_01.getText().toString();
                String strPW_02 = pw_02.getText().toString();
                Toast.makeText(getActivity().getApplicationContext(), strPW_01+"말로만 변경완료"+strPW_02,Toast.LENGTH_LONG).show();

                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /*
    void show()  // 태현이가 추가한 메소드 (Alertdialog 에 사용됨)
    {
        final EditText edittext = new EditText(this.getActivity());
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(30);
        edittext.setFilters(FilterArray);


        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("비밀번호 변경");
        builder.setMessage("바꿀 비밀번호를 입력해주세요");
        builder.setView(edittext);
        builder.setView(edittext2);
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() { // 확인 버튼 누르면 토스트 처리 해주는 부분, 너가 이 부분 입력받은 비번 실제로 변경되게 해줭
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
    }*/


    private void initViews(View view) {
        pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);

        logout = (ImageButton) view.findViewById(R.id.logout);
        change_pw = (ImageButton) view.findViewById(R.id.btn_user_pw); // 태현이가 추가함 (용도: Alertdialog)
        tv_fullname = (TextView) view.findViewById(R.id.tv_fullname);
        tv_email = (TextView) view.findViewById(R.id.tv_email);

        change_pw.setOnClickListener(new View.OnClickListener() { // 태현이가 추가함 (용도 : Alertdialog)
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

        tv_fullname.setText((String)(pref.getString(Constants.LASTNAME,"")+" "+pref.getString(Constants.FIRSTNAME,"")));
        tv_email.setText(pref.getString(Constants.EMAIL,""));
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

    public void changePassword(String email, String old_password, String new_password) {
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
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.CHANGE_PASSWORD_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();

                if(resp.getResult().equals(Constants.SUCCESS)){}

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constants.TAG,"failed");
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


}