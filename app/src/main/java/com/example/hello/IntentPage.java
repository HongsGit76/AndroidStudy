package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntentPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_page);

        Button intentStudy = (Button)findViewById(R.id.intentStudy);
        intentStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent studyIntent = new Intent(getApplicationContext(), intentStudy.class);
                startActivityForResult(studyIntent, 101);
            }
        });

        // 메인메뉴로 돌아가는 버튼 생성
        Button goHome = (Button)findViewById(R.id.go_to_home);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike");

                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }
}