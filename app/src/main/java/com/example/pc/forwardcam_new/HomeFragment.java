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

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {

    private Context context;
    TextView totalSensoredValue, totalAvoidedValue;
    SharedPreferences pref;
    String str_totalSensoredValue, str_totalAvoidedValue;

    public HomeFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false);
        initViews(layout);
        context = getActivity();
        return layout;
    }

    private void initViews(View view) {
        pref = getActivity().getPreferences(0);

        totalSensoredValue = (TextView) view.findViewById(R.id.tv_totalSensoredValue);
        totalAvoidedValue = (TextView) view.findViewById(R.id.tv_totalAvoidedValue);

        setTodayTotalValue(pref.getString(Constants.EMAIL,""));
    }

    private void setTodayTotalValue (String email) {
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
        request.setOperation(Constants.TODAY_TOTAL_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                str_totalSensoredValue = resp.getValue();

                if (resp.getResult().equals(Constants.SUCCESS)) {
                    totalSensoredValue.setText(str_totalSensoredValue);
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constants.TAG, "failed");
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    protected void onDraw(Canvas canvas) {
        final float ZERO = -90f; //drawAcr를 이용하면 오른쪽이 0도가 된다. 일반적으로 가장 위를 0으로 보기 때문에 - 90도를 해준다.
        final float DOTONE = 3.6f; //이 소스에서는 5점이 만점이기 때문에 360/5를 해서 1점당 72도를 준다.
        float score = Float.parseFloat(str_totalAvoidedValue); //점수. 하드코딩으로 넣었지만 나중에 변경 예정

        float degree = score * DOTONE;

        Paint p = new Paint(); //페인트 객체 p 생성
        p.setAntiAlias(true); //윤곽에 안티알리아싱을 처리해서 부드럽게 할건지 설정
        p.setStyle(Paint.Style.STROKE); //원의 윤곽선만 그리는 페인트 스타일
        p.setStrokeWidth(5); //윤곽선의 두께
        p.setAlpha(0x00); //배경 원의 투명도. 이 부분을 00으로 투명하게 처리하지 않으면 배경 원과 점수의 원이 다 보인다.

        RectF rectF = new RectF(100, 100, 400, 400); //사각형 객체 rectF를 생성하며 점수 원의 크기를 사각형으로 보고 (좌, 상, 우, 하) 좌표 설정. 좌상이 기준이 된다.

        if (degree < 2) {
            p.setColor(Color.RED);
        } else if (degree < 3) {
            p.setColor(Color.YELLOW);
        } else if (degree < 4) {
            p.setColor(Color.BLUE);
        } else if (degree <= 5) {
            p.setColor(Color.GREEN);
        } //점수에 맞춰 점수 원의 색을 변경한다.

        canvas.drawArc(rectF, ZERO, degree, false, p);  //점수 원(호)를 그리는 메소드. (정사각형 객체, 시작각도, 끝각도, 시작각도와 끝 각도에서의 중앙으로 선을 그을것이냐, 사용할 페인트 객체). 각도는 시계방향으로 증가한다.
    }
}