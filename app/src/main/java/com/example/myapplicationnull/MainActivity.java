package com.example.myapplicationnull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
    private static final String KEY_INDEX="index";
    private static final String TAG="MainActivity";
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
        Log.d(TAG,"onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            mCountIndex=savedInstanceState.getInt(KEY_INDEX);
        }

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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCountIndex);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
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
