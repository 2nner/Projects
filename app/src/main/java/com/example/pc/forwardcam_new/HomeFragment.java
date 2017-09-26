package com.example.pc.forwardcam_new;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {

    private Context context;
    Canvas canvas;
    TextView totalSensoredValue, totalAvoidedValue;
    SharedPreferences pref;
    int int_totalSensoredValue, int_totalAvoidedValue;

    public HomeFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        context = getActivity();

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false);

        initViews(layout);
        layout.addView(new CircleGraph(context));
        return layout;
    }

    private void initViews(View view) {
        pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        totalSensoredValue = (TextView) view.findViewById(R.id.tv_totalSensoredValue);
        totalAvoidedValue = (TextView) view.findViewById(R.id.tv_totalAvoidedValue);

        setTodayTotalValue(pref.getString(Constants.EMAIL,""));
        setTodayAvoidedValue(pref.getString(Constants.EMAIL,""));

    }

    private void setTodayTotalValue (String email) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.TODAY_TOTAL_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                int_totalSensoredValue = resp.getValue();

                if (resp.getResult().equals(Constants.SUCCESS)) {

                    totalSensoredValue.setText(String.valueOf(int_totalSensoredValue));

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constants.TAG, "failed",t);
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setTodayAvoidedValue (String email) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.TODAY_AVOIDED_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                int_totalAvoidedValue = resp.getValue();


                if (resp.getResult().equals(Constants.SUCCESS)) {
                    totalAvoidedValue.setText(String.valueOf(int_totalAvoidedValue));
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constants.TAG, "failed",t);
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private class CircleGraph extends View {

        Paint p = new Paint();

        public CircleGraph(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {

            final float ZERO = -90f; //drawAcr를 이용하면 오른쪽이 0도가 된다. 일반적으로 가장 위를 0으로 보기 때문에 - 90도를 해준다.
            final float DOTONE = 3.6f;
            if(int_totalSensoredValue == 0) {
                int_totalSensoredValue = 1;
            }
            float value = (float) (int_totalAvoidedValue / int_totalSensoredValue)*100;
            float degree = value * DOTONE;

            p.setAntiAlias(true);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(5);
            p.setAlpha(0x00);

            RectF rectF = new RectF(0, 0, 200, 200);

            if (degree < 25) {
                p.setColor(Color.RED);
            } else if (degree < 50) {
                p.setColor(Color.YELLOW);
            } else if (degree < 75) {
                p.setColor(Color.BLUE);
            } else if (degree <= 100) {
                p.setColor(Color.GREEN);
            } //점수에 맞춰 점수 원의 색을 변경한다.

            canvas.drawArc(rectF, ZERO, degree, false, p);
        }
    }
}