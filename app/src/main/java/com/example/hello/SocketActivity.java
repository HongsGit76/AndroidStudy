package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketActivity extends AppCompatActivity {
    TextView dataView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        // 응답 받은 결과 확인하기
        dataView = (TextView)findViewById(R.id.recieveData);

        // 클라이언트 접속 시작
        Button client = (Button)findViewById(R.id.accessClient);
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });

        // 메뉴로 가는 버튼
        Button goMenu2 = (Button)findViewById(R.id.goMenu2);
        goMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu_intent = new Intent();
                menu_intent.putExtra("go back", "return");

                setResult(Activity.RESULT_OK, menu_intent);

                finish();
            }
        });
    }

    class ClientThread extends Thread{
        public void run(){
            String host = "localhost";
            int port = 5001;
            try {
                Socket socket = new Socket(host, port);
                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject("안녕!");
                outstream.flush();
                Log.d("ClientThread", "서버로 보냄.");

                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                final Object input = instream.readObject();
                Log.d("ClientThread", "받은 데이터 : " + input);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dataView.setText("받은 데이터 : " + input);
                    }
                });


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}