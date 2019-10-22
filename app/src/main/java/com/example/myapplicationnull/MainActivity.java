package com.example.myapplicationnull;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mSureToAnswer;
    private ImageView mNextImage;
    private ImageView mPreImage;
    private TextView mQuestionTextView;
    private static final String KEY_INDEX="index";
    private static final String TAG="MainActivity";
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.Q1,true),
            new Question(R.string.Q2,true),
            new Question(R.string.Q3,false),
            new Question(R.string.Q4,true),
    };
    private int mCountIndex=0;
    private static final int REQUEST_CODE_CHEAT=0;
    private boolean[] mIsCheater=new boolean[mQuestionBank.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,TAG+"   onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            mCountIndex=savedInstanceState.getInt(KEY_INDEX);
        }

        mQuestionTextView=findViewById(R.id.question_text_view);

        mTrueButton=(Button) findViewById(R.id.button_true);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
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

        mNextImage=findViewById(R.id.ImageView_right);
        mNextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountIndex=(mCountIndex+1)%mQuestionBank.length;
                mIsCheater[mCountIndex]=false;
                updateQuestion();
            }
        });

        mPreImage=findViewById(R.id.imageView_left);
        mPreImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountIndex=(mCountIndex-1+mQuestionBank.length)%mQuestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();

        mSureToAnswer=findViewById(R.id.sureToAnswer);
        mSureToAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=mQuestionBank[mCountIndex].getTextResId();
                boolean answerIsTrue=mQuestionBank[mCountIndex].isActionTrue();
                Intent intent=CheckActivity.newIntent(MainActivity.this,answerIsTrue,id);
                startActivityForResult(intent,REQUEST_CODE_CHEAT);

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode!= Activity.RESULT_OK){
            //Log.d(TAG," /////////////////////////----------------11111");
            return;
        }
        if(requestCode==REQUEST_CODE_CHEAT){
            //Log.d(TAG," /////////////////////////----------------22222");
            if(data==null){
            //   Log.d(TAG," /////////////////////////----------------33333");
                return;
            }
            mIsCheater[mCountIndex]=CheckActivity.wasAnswerShown(data);
            //Log.d(TAG,"SET mIsCheater /////////////////////////"+mIsCheater);
        }
        //Log.d(TAG," /////////////////////////----------------44444");

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"  onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCountIndex);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,TAG+"  onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,TAG+"  onResume() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,TAG+"  onPause() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,TAG+"  onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,TAG+"  onDestroy() called");
    }




    private void updateQuestion(){
        int question=mQuestionBank[mCountIndex].getTextResId();
        mQuestionTextView.setText(question);
    }


    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mQuestionBank[mCountIndex].isActionTrue();
        int messageResId=0;
        if(mIsCheater[mCountIndex]){
            messageResId=R.string.judgment_toast;
        }else {
            if(userPressedTrue==answerIsTrue){
                messageResId=R.string.true_toast;
            }else {
                messageResId=R.string.false_toast;
            }
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();

    }

    public  Question[] questions(){
        return mQuestionBank;
    }

}
