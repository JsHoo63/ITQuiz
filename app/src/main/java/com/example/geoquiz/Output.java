package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Output extends AppCompatActivity {

    private TextView mScore;

    //total score show on the last page and button link to the first page
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        final String getScore=getIntent().getExtras().getString("score");
        mScore=findViewById(R.id.score_text_view);
        mScore.setText(getScore);

        Button mButton = (Button) findViewById(R.id.buttonback);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Output.this, choose_quiz.class);
                startActivity(myIntent);
                finishAffinity();
            }
        });
    }
}

