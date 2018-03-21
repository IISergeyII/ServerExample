package com.s.samsungitschool.serverexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView answerTV;
    EditText number1ED, number2ED;
    Button countBT;

    String a, b, answerHTTP;
    final String server = "http://demo.harix.org";
    Gson gson = new GsonBuilder().create();

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(server)
            .build();

    Request request = retrofit.create(Request.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerTV = (TextView) findViewById(R.id.answer_tv);
        number1ED =  (EditText) findViewById(R.id.number_1_et);
        number2ED =  (EditText) findViewById(R.id.number_2_et);
        countBT = (Button) findViewById(R.id.count_bt);

        countBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = number1ED.getText().toString();
                b = number2ED.getText().toString();

                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("a", a);
                postDataParams.put("b", b);

                Call<Object> call = request.performPostCall(postDataParams);
                call.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        HashMap<String, Double> map = gson.fromJson(response.body().toString(), HashMap.class);
                        answerHTTP = Double.toString(map.get("c"));
                        answerTV.setText(answerHTTP);
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        answerTV.setText("Request error");
                    }
                });
            }
        });
    }
}
