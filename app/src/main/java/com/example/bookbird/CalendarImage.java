package com.example.bookbird;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CalendarImage extends AppCompatActivity {

    Button page1, page2;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_image);

        page1 = (Button)findViewById(R.id.img1);
        page2 = (Button)findViewById(R.id.img2);
        image = (ImageView)findViewById(R.id.image);

        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.calendar_1);
            }
        });

        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.calendar_2);
            }
        });
    }
}
