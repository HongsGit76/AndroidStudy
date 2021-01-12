package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class VolleyActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        textView = (TextView)findViewById(R.id.textView);

        Button button = (Button)findViewById(R.id.requestVolley);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });

        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        // 메뉴로 돌아가기
        Button goMenu = (Button)findViewById(R.id.menu4);
        goMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu_intent = new Intent();
                menu_intent.putExtra("go back", "return");

                setResult(Activity.RESULT_OK, menu_intent);

                finish();
            }
        });
    }

    // 응답이 왔을 때 보내주는 방식 -> 비동기 방식
    public void sendRequest(){
        String url = "https://www.google.co.kr";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        //volley 내부적으로 캐싱을 해줌 -> 매번 받은 결과를 받기
        request.setShouldCache(false);

        AppHelper.requestQueue.add(request);
        println("요청 보냄."); // 응답이 왔을 때 보내주는 방식 -> 비동기 방식 (먼저 뜰 것임)
    }

    public void println(String data){
        textView.append(data + "\n");
    }
}