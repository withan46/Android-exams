package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Second extends AppCompatActivity {

    ImageView myImage;
    Button returnButton;
    TextView fname;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        myImage = (ImageView) findViewById(R.id.imageView);
        returnButton = findViewById(R.id.button2);
        fname = findViewById(R.id.textView);

        myImage.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        String imageUri = intent.getStringExtra("image");
        String full_name = intent.getStringExtra("name");
        String type = intent.getStringExtra("type");
        String voice = intent.getStringExtra("voice");

        fname.setText("My Name is "+ full_name +", I am a "+ type+ " and I "+voice);
        Picasso.with(getApplicationContext()).load(Uri.parse(imageUri)).resize(300, 0).into(myImage);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}