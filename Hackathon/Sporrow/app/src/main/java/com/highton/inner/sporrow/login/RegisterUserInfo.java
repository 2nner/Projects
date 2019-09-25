package com.highton.inner.sporrow.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.highton.inner.sporrow.R;
import com.highton.inner.sporrow.RequestInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUserInfo extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<>();
    String str_nickname;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_info);
        initView();
    }

    public void initView() {

        findViewById(R.id.select1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("축구");
                ll = findViewById(R.id.ll_selected);
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected1);

                if(list.size() > 1) {
                    ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) ll.getLayoutParams();
                    marginParams.setMarginEnd(50);
                    ll.setLayoutParams(marginParams);
                }
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("야구");
                ll = findViewById(R.id.ll_selected);
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected2);

                if(list.size() > 1) {
                    ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) ll.getLayoutParams();
                    marginParams.setMarginEnd(50);
                    ll.setLayoutParams(marginParams);
                }
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("배드민턴");
                ll = findViewById(R.id.ll_selected);
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected3);

                if(list.size() > 1) {
                    ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) ll.getLayoutParams();
                    marginParams.setMarginEnd(50);
                    ll.setLayoutParams(marginParams);
                }
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("조민규");
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected4);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("백채김치찌개");
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected5);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("누구땜에");
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected6);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("못먹었더라");
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected11);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("족구");
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected8);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("티볼");
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected9);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("치킨을");
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected10);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("달라고");
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected7);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.iv_btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = findViewById(R.id.et_registerNickname);
                if(et.getText().toString().equals("")) {
                    et.setError("닉네임을 입력해 주세요!");
                } else {
                    str_nickname = et.getText().toString();
                    doRegister(str_nickname, list);
                }
            }
        });

        findViewById(R.id.et_registerNickname).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                findViewById(R.id.tv_isAvailableToUseNickname).setVisibility(View.VISIBLE);
            }
        });

    }

    public void doRegister(String str_nickname, ArrayList<String> list) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RequestInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);

        Call<Void> response = request.uploadUserInfo(getIntent().getStringExtra("email"), new UserInfo(str_nickname, list));
        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch(response.code()) {
                    case 201:
                        Toast.makeText(getApplicationContext(), "업로드 완료", Toast.LENGTH_LONG).show();
                        finish();
                        break;

                    case 204:
                        Toast.makeText(getApplicationContext(), "가입되지 않은 이메일입니다!", Toast.LENGTH_LONG).show();
                        break;

                    case 400:
                        Toast.makeText(getApplicationContext(), "관심사 ID 중 존재하지 않는 관심사가 존재합니다!", Toast.LENGTH_LONG).show();
                        break;

                    case 401:
                        Toast.makeText(getApplicationContext(), "이메일 인증이 되지 않았습니다!", Toast.LENGTH_LONG).show();
                        break;

                    case 409:
                        Toast.makeText(getApplicationContext(), "중복된 닉네임입니다!", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("signup", "Fail_Cause : " + t.getCause());
                Log.d("signup", "Faile_Message : " + t.getMessage());
                Toast.makeText(getApplicationContext(), "서버와 연결에 실패하였습니다!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
