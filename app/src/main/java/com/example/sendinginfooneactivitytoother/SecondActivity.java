package com.example.sendinginfooneactivitytoother;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.BatchUpdateException;

public class SecondActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
         setTitle("Profile Information");
        textView = findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();
        String fname = bundle.getString("FIRST_NAME");
        String lName = bundle.getString("LAST_NAME");
        int i = bundle.getInt("MOBILE_NO");
        String sex = bundle.getString("INFO");
        textView.setText("First Name: "+fname +"\n"+"Last Name: " +lName +"\n"+"Mobile Number: " +i +
                "\n Sex: "+ sex);

        imageView =  findViewById(R.id.imageView);

        byte[] byteArray = bundle.getByteArray("PICTURE");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView.setImageBitmap(bmp);

    }
}