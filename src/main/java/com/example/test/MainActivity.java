package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String imageUrl;
    private String name;

    private String type;
    private String voice;
    private AnimalList animalList;

    private final String myIp = "195.251.211.64";
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        animalList = new AnimalList(myIp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        List<String> allAnimalNames = animalList.getAllAnimalName();
        System.out.println(allAnimalNames);
        for (int i = 0; i<allAnimalNames.size();i++){
            RadioButton rb = new RadioButton(this);
            rb.setText(allAnimalNames.get(i));
            rb.setId(100+i);
            rg.addView(rb);
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                imageUrl = animalList.getAnimalImage(rb.getText().toString());
                name = rb.getText().toString();
                type = (String) animalList.getAnimalType(rb.getText().toString());
                voice = (String) animalList.getAnimalVoice(rb.getText().toString());
            }
        });


    }

    public void nextActivityOnClick(View view) {
        Intent intent = new Intent(this, Second.class);
        intent.putExtra("image", imageUrl);
        intent.putExtra("name", name);
        intent.putExtra("type", type);
        intent.putExtra("voice", voice);
        startActivity(intent);
    }




}