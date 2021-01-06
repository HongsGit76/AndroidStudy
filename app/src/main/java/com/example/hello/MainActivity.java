package com.example.hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.image2);
        imageView2 = (ImageView)findViewById(R.id.image1);

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