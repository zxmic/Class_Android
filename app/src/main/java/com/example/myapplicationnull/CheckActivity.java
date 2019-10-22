package com.example.myapplicationnull;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CheckActivity extends AppCompatActivity {

    private static final String TAG="CheckActivity";
    private boolean mAnswerIsTrue;
    private int mId;
    private static final String EXTRA_ANSWER_IS_TRUE="com.bignerdranch.android.MyApplicationnull.ansder_is_true";
    private static final String EXTRA_ANSWER_IS_SHOWN="com.bignerdranch.android.MyApplicationnull.ansder_shown";
    private static final String EXTRA_QUESTION="com.bignerdranch.android.MyApplicationnull.extra_question";
    private TextView mAnswerTextView;
    private TextView mQuestionAsk;
    private Button mShowAnswerButton;
    private ImageButton mImageButton_left;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mId=getIntent().getIntExtra(EXTRA_QUESTION,8848);

        mAnswerTextView=findViewById(R.id.answer_text_view);
        mQuestionAsk=findViewById(R.id.queston_ask);

        mShowAnswerButton=findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mId!=8848){
                    mQuestionAsk.setText(mId);
                    if(mAnswerIsTrue){
                        mAnswerTextView.setText(R.string.Button_true);
                    }else {
                        mAnswerTextView.setText(R.string.Button_false);
                    }
                }else {
                    mQuestionAsk.setText(R.string.none_question);
                }
                setAnswerShownResult(true);
                //Log.d(TAG,"----------------setAnswerShownResult(true);---------------------");

            }
        });

        mImageButton_left=findViewById(R.id.imageButton_left);
        mImageButton_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue,int index){

        Intent intent = new Intent(packageContext,CheckActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        intent.putExtra(EXTRA_QUESTION,index);
        return intent;
    }

    public void setAnswerShownResult(boolean isAnswerShown){
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
        //Log.d(TAG,"++++++++++++++++++IN++++setAnswerShownResult(boolean isAnswerShown)+++++++++");
    }

    public static boolean wasAnswerShown(Intent result){
        //Log.d(TAG,"++++++++++++++++++wasAnswerShown(Intent result)+++++++++");

        return result.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN,false);
    }



    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,TAG+"   onStart() called");
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
}
