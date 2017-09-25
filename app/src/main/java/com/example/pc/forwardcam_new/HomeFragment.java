package com.example.pc.forwardcam_new;

import android.content.Context;
import android.content.SharedPreferences;
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
    int int_totalSensoredValue, int_totalAvoidedValue;

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
        pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        totalSensoredValue = (TextView) view.findViewById(R.id.tv_totalSensoredValue);
        totalAvoidedValue = (TextView) view.findViewById(R.id.tv_totalAvoidedValue);

        setTodayTotalValue(pref.getString(Constants.EMAIL,""));
        setTodayAvoidedValue(pref.getString(Constants.EMAIL,""));
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
}