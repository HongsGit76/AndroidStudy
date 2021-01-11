package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class intentStudyActivity extends AppCompatActivity {

    EditText phoneNumber; // 전화번호 입력 칸
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_study);

        //전화번호 입력 칸 설정
        phoneNumber = (EditText)findViewById(R.id.Input_call_number);

        // 전화 걸기 버튼
        Button call = (Button)findViewById(R.id.callButton);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receiver = phoneNumber.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + receiver));
                startActivity(callIntent);

                // 문자열로 컴포넌트 액티비티를 지정할 수 있게함
                /*
                Intent intent2 = new Intent();
                ComponentName name = new ComponentName("com.example.hello","com.example.hello.IntentPage");
                intent2.setComponent(name);
                startActivity(intent2);
                */

            }
        });

        // 메뉴로 돌아가기
        Button goMenu = (Button)findViewById(R.id.goMenu);
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
}