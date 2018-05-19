package com.example.michael.test2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Library extends AppCompatActivity {
    private ImageView test;
    private Button goBackButton;
    private String filename = "myfile";
    private String fileContents = "Hello world!";
    private Button saveSomeDataButton;
    private TextView testText;
    private String getText;
    private Button showText;
    private BufferedReader in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        goBackButton = findViewById(R.id.go_back_button);
        saveSomeDataButton = findViewById(R.id.save_button);
        testText = findViewById(R.id.saved_data_text);
        showText = findViewById(R.id.show_text);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Library.this, MainActivity.class));
            }
        });

        saveSomeDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDataTest();
            }
        });

        showText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new InputStreamReader(openFileInput(filename)));
                    getText = in.readLine();
                    testText.setText(getText);
                } catch (IOException e) {
                    testText.setText("Error");
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // ignore
                        }
                    }
                }
            }
        });
    }

    private void saveDataTest(){
        try {
            FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            testText.setText("error");
        }
    }
}
