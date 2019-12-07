package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NormalActivity extends AppCompatActivity {

    private static final String TAG = "NormalActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    public int score;
    private TextView mScore;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_one,true),
            new Question(R.string.question_two,true),
            new Question(R.string.question_three,false),
            new Question(R.string.question_four,true),
            new Question(R.string.question_five,true),
            new Question(R.string.question_six,true),
            new Question(R.string.question_seven,true),
            new Question(R.string.question_eight,false),
            new Question(R.string.question_nine,false),
            new Question(R.string.question_ten,false),
    };

    private int mCurrentIndex=0;
    private boolean mIsCheater;

    //update question from 1 to 10
    private void updateQuestion() {
        int question=mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    //check the answer is it correct for the current question
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId=0;

        if(mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
                score = score+10;
                mScore.setText(Integer.toString(score));


            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                    .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScore=findViewById(R.id.scoretesting);
        mScore.setText(Integer.toString(score));

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        //check answer when the button true is click for the question
        mTrueButton=(Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                mCurrentIndex=(mCurrentIndex+1) ;
                mIsCheater=false;
                if(mCurrentIndex ==  mQuestionBank.length){
                    resultPage();
                }
                else {
                    updateQuestion();
                }
            }
        });

        //check answer when the button wrong is click for the question
        mFalseButton=(Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                mCurrentIndex=(mCurrentIndex+1) ;
                mIsCheater=false;
                if(mCurrentIndex ==  mQuestionBank.length){
                    resultPage();
                }
                else {
                    updateQuestion();
                }
            }
        });

        //when cheat button is click, intent to cheat activity to get answer
        mCheatButton=(Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(NormalActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        if (savedInstanceState!=null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }


        mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionBank[0].getTextResId();

        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) {
            return;
        }
        if(requestCode == REQUEST_CODE_CHEAT) {
            if(data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    //pass the total score to the end of the page and intent to output activity
    public void resultPage() {
        Intent intent = new Intent(NormalActivity.this, Output.class);
        intent.putExtra("score",mScore.getText().toString());
        startActivity(intent);

    }

}
