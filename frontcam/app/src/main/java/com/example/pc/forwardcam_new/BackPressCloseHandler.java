package com.example.pc.forwardcam_new;

import android.app.Activity;
import android.widget.Toast;

public class BackPressCloseHandler {

    private long backkeypressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context){
        this.activity = context;
    }

    public void onBackPressed(){
        if(System.currentTimeMillis() > backkeypressedTime + 2000){
            backkeypressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if(System.currentTimeMillis() <= backkeypressedTime + 2000){
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide(){
        toast = Toast.makeText(activity,"\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT);
        toast.show();
    }

}
