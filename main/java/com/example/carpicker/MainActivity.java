package com.example.carpicker;

import androidx.appcompat.app.*;

import android.content.Intent;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.squareup.picasso.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private CarBrandList cbl;
    private RadioGroup rg;
    ImageButton myImage;

    private final String myIP = "195.251.210.162";
    private String imageUri;
    private String full_name;

    public void PickCarOnClick(View v) {
        Spinner dropDown = (Spinner) findViewById(R.id.cars);
        String brand = String.valueOf(dropDown.getSelectedItem());
        List<String> allModels = cbl.getAllModels(brand);


        rg.removeAllViews();
        myImage.setVisibility(View.GONE);

        for(int i=0;i<allModels.size();i++) {
            RadioButton rb = new RadioButton(this);
            rb.setText(allModels.get(i));
            rb.setId(100+i);
            rg.addView(rb);
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                String url= "http://"+myIP+"/multimediaDBServices/logHistory.php?brand=" + brand + "&model=" + rb.getText().toString() + "&timestamp=" + new Date(System.currentTimeMillis()).toString();
                imageUri = cbl.lookup(brand, rb.getText().toString()).getImage();
                full_name = brand + " " +  rb.getText().toString();
                myImage.setVisibility(View.VISIBLE);
                System.out.println(imageUri);
                Picasso.with(getApplicationContext()).load(Uri.parse(imageUri)).resize(300, 0).into(myImage);

                try {
                    OkHttpHandler okHttpHandler = new OkHttpHandler();
                    okHttpHandler.logHistory(url);
                    Toast.makeText(getApplicationContext(), "Selection Logged", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cbl = new CarBrandList(myIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImage = (ImageButton) findViewById(R.id.imageButton);
        myImage.setVisibility(View.GONE);

        Spinner dropDown = (Spinner) findViewById(R.id.cars);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        cbl.getAllBrands());
        dropDown.setAdapter(arrayAdapter);
        rg = (RadioGroup) findViewById(R.id.rg);

        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(view);
            }
        });
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, CarDetails.class);
        intent.putExtra("IMAGE", imageUri);
        intent.putExtra("FULL_NAME", full_name);
        startActivity(intent);

    }


}