package com.example.pc.forwardcam_new;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {

    private Context context;
    TextView totalSensoredValue, totalAvoidedValue;
    SharedPreferences pref;
    int int_totalSensoredValue=0, int_totalAvoidedValue=0;
    float percentageValue;

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
        return layout;
    }

    private void initViews(View view) {
        pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        totalSensoredValue = (TextView) view.findViewById(R.id.tv_totalSensoredValue);
        totalAvoidedValue = (TextView) view.findViewById(R.id.tv_totalAvoidedValue);

        setTodayTotalValue(pref.getString(Constants.EMAIL, ""));

    }

    private void setTodayTotalValue (String email) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        User user = new User();
        user.setEmail(email);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.TODAY_TOTAL_OPERATION);
        request.setUser(user);

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<ServerResponse> response = requestInterface.operation(request);


        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();

                if (resp.getResult().equals(Constants.SUCCESS)) {

                    int_totalSensoredValue = resp.getValue();
                    totalSensoredValue.setText(String.valueOf(int_totalSensoredValue));
                }
                setTodayAvoidedValue(pref.getString(Constants.EMAIL, ""));
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

                if (resp.getResult().equals(Constants.SUCCESS)) {

                    int_totalAvoidedValue = resp.getValue();
                    totalAvoidedValue.setText(String.valueOf(int_totalAvoidedValue));

                }

                DecoView decoView = (DecoView) getView().findViewById(R.id.circleGraph);
                TextView percentView = (TextView) getView().findViewById(R.id.tv_percentage);

                if(int_totalSensoredValue == 0) {
                    percentageValue = 0;
                } else {
                    percentageValue = ((float)int_totalAvoidedValue / int_totalSensoredValue) * 100;
                }

                String percentageValue_format = String.format("%.2f", percentageValue);
                percentView.setText(percentageValue_format+"%");

                Log.d(Constants.TAG,"TotalSensored : "+ int_totalSensoredValue);
                Log.d(Constants.TAG,"TotalAvoided : " + int_totalAvoidedValue);
                Log.d(Constants.TAG,"percent : "+percentageValue);

                decoView.addSeries(new SeriesItem.Builder(Color.argb(255, 226, 226, 226))
                        .setRange(0, 100, 100)
                        .build());

                SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                        .setRange(0, 100, percentageValue)
                        .build();

                int series1Index = decoView.addSeries(seriesItem1);

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constants.TAG, "failed",t);
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


}