package com.example.w6_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private ImageView imageView;
    private Button button;
    private String currImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String restoredText = prefs.getString("editText", null);
        String restoredAnimalName = prefs.getString("animalName",null);
        if (restoredText != null){
            editText.setText(restoredText);
        }
        if (restoredAnimalName != null){
            if (restoredAnimalName == "dog") {
                imageView.setImageResource(R.drawable.dog);
            }
            if (restoredAnimalName == "lion"){
                imageView.setImageResource(R.drawable.lion);
            }
            else {
                imageView.setImageResource(R.drawable.wolf);
            }
        }
        else {
            imageView.setImageResource(R.drawable.dog);
            currImage = "dog";
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int randomNum = rand.nextInt(3);
                if (randomNum == 0) {
                    imageView.setImageResource(R.drawable.dog);
                    currImage = "dog";
                }
                if (randomNum == 1) {
                    imageView.setImageResource(R.drawable.lion);
                    currImage = "lion";
                }
                if (randomNum == 2) {
                    imageView.setImageResource(R.drawable.wolf);
                    currImage = "wolf";
                }
            }
        });

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        EditText et = findViewById(R.id.editText);
        String ss = et.getText().toString();
        String imageId = currImage;
        editor.putString("editText",ss);
        editor.putString("animalName", imageId);
        editor.commit();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}