package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyHttp extends AppCompatActivity {
    EditText editText;
    TextView textView;

    String urlStr;

    // 스레드에서 화면 갱신하는 것을 직접 접근 하면 충돌이 일어남
    // editText, textView 둘다 접근했기 때문 -> 핸들러 사용
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_http);

        // textView, editText 찾기
        editText = (EditText)findViewById(R.id.http_insert);
        textView = (TextView)findViewById(R.id.response);

        // request 버튼 찾기
        Button button = (Button)findViewById(R.id.request);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urlStr = editText.getText().toString();

                RequestThread thread = new RequestThread();
                thread.start();
            }
        });

        // 메뉴로 돌아가기
        Button goMenu = (Button)findViewById(R.id.menu3);
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

    class RequestThread extends Thread{
        public void run(){

            try {
                // url 객체 생성
                URL url = new URL(urlStr);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    int resCode = conn.getResponseCode();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;

                    while(true){
                        line = reader.readLine();
                        if (line == null){
                            break;
                        }
                        println(line);
                    }
                    reader.close();
                    conn.disconnect();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    // 텍스트 뷰에 글씨 생성 -> 핸들러를 사용해 메인 스레드에서 동작하도록 함
    public void println(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data);

            }
        });

    }
}