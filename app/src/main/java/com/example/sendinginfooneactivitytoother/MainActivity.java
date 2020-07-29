package com.example.sendinginfooneactivitytoother;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    Button button;
    EditText edtFirstName, edtLastName, edtMobileNo;
    RadioGroup radioGroup;
    final int IMAGE_REQUEST_CODE = 1000;
    String maleInfo = "";

    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtMobileNo = findViewById(R.id.edtMobileNo);

        imageView = findViewById(R.id.imgProfile);

        radioGroup = findViewById(R.id.radioGroup);

        button.setOnClickListener(this);
        imageView.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);

        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.download);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgProfile:
                Intent imgIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imgIntent.setType("image/*");
                startActivityForResult(imgIntent, IMAGE_REQUEST_CODE);
                break;

            case R.id.button:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("FIRST_NAME",edtFirstName.getText().toString());
                intent.putExtra("LAST_NAME", edtLastName.getText().toString());
                intent.putExtra("MOBILE_NO", edtLastName.getText().toString());
                intent.putExtra("INFO",maleInfo);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("PICTURE",byteArray);
                startActivity(intent);
                break;

        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(R.id.radioButtonMale == checkedId){
            maleInfo = "Male";
        }else if(R.id.radioButtonFemale == checkedId)
        {
            maleInfo = "Female";
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case IMAGE_REQUEST_CODE:

                if(resultCode == RESULT_OK) {
                    Uri choosenImge = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), choosenImge);
                        imageView.setImageBitmap(bitmap);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
        }
    }
}