package com.example.hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView imageView; //테스트 이미지
    ImageView imageView2; //테스트 이미지

    TextView textView; // value 체크용 텍스트

    int index = 0; // 이미지 변환할 때 사용하는 인덱스

    //int value = 0;
    ValueHandler handler = new ValueHandler();

    Handler handler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 이미지 찾아오기
        imageView = (ImageView)findViewById(R.id.image1);
        imageView2 = (ImageView)findViewById(R.id.image2);



        textView = (TextView)findViewById(R.id.value);
        Button button = (Button)findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //BackGroundThread thread = new BackGroundThread();
                //thread.start();

                new Thread(new Runnable() {
                    int value = 0;
                    boolean running = false;

                    @Override
                    public void run() {
                        running = true;
                        while(running) {
                            value += 1;

                            handler2.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("현재 값 : " + value);
                                }
                            });

                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        }
                    }
                }).start();
            }
        });

        Button button2 = (Button)findViewById(R.id.check_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //textView.setText("현재 값 : " + value);
            }
        });

        // menu view로 이동하는 버튼 생성
        Button menu = (Button)findViewById(R.id.intent_page);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_menu = new Intent(getApplicationContext(), IntentPage.class);
                startActivityForResult(intent_menu, 101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101){
            String name = data.getStringExtra("name");
            Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + name, Toast.LENGTH_LONG).show();
        }
    }

    class BackGroundThread extends Thread {
        int value = 0;
        boolean running = false;
        public void run(){
            running = true;
            while(running){
                value += 1;

                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);
                handler.sendMessage(message);

                try {
                    Thread.sleep(1000);
                }catch (Exception e){}
            }
        }
    }

    class ValueHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("현재 값 : " + value);
        }
    }

    public void onButton1Clicked(View v){
        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
    }

    public void onButton2Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }

    public void changeImage(View v){
        System.out.println("clicked");
        index+=1;
        if (index > 1){
            index = 0;
        }

        if (index == 0){
            imageView2.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
        }
        else if(index == 1){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
        }
    }
}