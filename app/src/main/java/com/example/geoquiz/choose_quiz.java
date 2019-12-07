package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class choose_quiz extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_choose);

        Button mButton2= (Button) findViewById(R.id.cppquizbutton);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent2 = new Intent(choose_quiz.this, CppActivity.class);
                startActivity(myIntent2);
            }
        });

        Button mButton = (Button) findViewById(R.id.javaquizbutton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(choose_quiz.this, JavaActivity.class);
                startActivity(myIntent);
            }
        });

        Button mButton1= (Button) findViewById(R.id.normalquizbutton);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent1 = new Intent(choose_quiz.this, NormalActivity.class);
                startActivity(myIntent1);
            }
        });

        Button mButton3= (Button) findViewById(R.id.backbutton);
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent3 = new Intent(choose_quiz.this, AddNameActivity.class);
                startActivity(myIntent3);
            }
        });


    }
}
