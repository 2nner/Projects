package com.dev.inner.owntrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ThemeActivity extends AppCompatActivity {

    ImageView iv_theme_thumbnail;
    TextView tv_theme_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        iv_theme_thumbnail = findViewById(R.id.iv_theme_thumbnail);
        tv_theme_title = findViewById(R.id.tv_theme_title);

        Intent intent = getIntent();
        String tag = intent.getStringExtra("tag");

        if (tag.equals("#여름")) {
            Glide.with(getApplicationContext()).asBitmap().load(R.drawable.beach).into(iv_theme_thumbnail);
            tv_theme_title.setText(tag);
        } else if (tag.equals("#캠핑")) {
            Glide.with(getApplicationContext()).asBitmap().load(R.drawable.camping).into(iv_theme_thumbnail);
            tv_theme_title.setText(tag);
        } else if (tag.equals("#맛집")) {
            Glide.with(getApplicationContext()).asBitmap().load(R.drawable.matzip).into(iv_theme_thumbnail);
            tv_theme_title.setText(tag);
        } else if (tag.equals("#호캉스")) {
            Glide.with(getApplicationContext()).asBitmap().load(R.drawable.hotel).into(iv_theme_thumbnail);
            tv_theme_title.setText(tag);
        }
    }
}
