package com.example.myapplicationnull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.Q1,true),
            new Question(R.string.Q2,true),
            new Question(R.string.Q3,true),
            new Question(R.string.Q4,true),
    };
    private int mCountIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView=findViewById(R.id.question_text_view);

        mTrueButton=(Button) findViewById(R.id.button_true);mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               checkAnswer(true);
            }
        });

        mFalseButton=(Button) findViewById(R.id.button_false);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton=findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountIndex=(mCountIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });

        mPreButton=findViewById(R.id.pre_button);
        mPreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountIndex=(mCountIndex-1+mQuestionBank.length)%mQuestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void updateQuestion(){
        int question=mQuestionBank[mCountIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mQuestionBank[mCountIndex].isActionTrue();

        int messageResId=0;
        if(userPressedTrue==answerIsTrue){
            messageResId=R.string.true_toast;
        }else {
            messageResId=R.string.false_toast;
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_LONG).show();

    }

}
