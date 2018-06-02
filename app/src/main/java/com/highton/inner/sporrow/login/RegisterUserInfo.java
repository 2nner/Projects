package com.highton.inner.sporrow.login;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.highton.inner.sporrow.R;

import java.util.ArrayList;

public class RegisterUserInfo extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<>();
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_info);
        initView();
    }

    public static int convertDpToPixel(int dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public void initView() {
        ll = findViewById(R.id.ll_selected);

        findViewById(R.id.select1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected1);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected2);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected3);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected4);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected5);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected6);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected7);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected8);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected9);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected10);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.select11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.selected11);
                ll.addView(imageView);
            }
        });

        findViewById(R.id.iv_btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = findViewById(R.id.et_registerNickname);
                if(et.getText().toString().equals(null)) {
                    et.setError("닉네임을 입력해 주세요!");
                } else {
                    doRegister();
                    finish();
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

    public void doRegister() {

    }
}
