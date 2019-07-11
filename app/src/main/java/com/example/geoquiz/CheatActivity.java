package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CheatActivity extends Activity {

    private boolean mAnswerIsTrue;
    private boolean mAnswerIsShown;

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.example.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_IS_SHOWN =
            "com.example.geoquiz.answer_shown";

    private static final String TAG = "CheatActivity";
    private static final String ANSWER_SHOWN = "answerShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerIsShown = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_SHOWN, false);
        Log.d(TAG, "mAnswerIsTrue: " + mAnswerIsTrue + " mAnswerIsShown: " + mAnswerIsShown);

        if (savedInstanceState != null) {
            mAnswerIsShown = savedInstanceState.getBoolean(ANSWER_SHOWN);
            Log.d(TAG, "Instance is saved successfully, mAnswerIsShown: " + mAnswerIsShown);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Log.d(TAG, "CheatActivity onCreate() called.");

        mAnswerTextView = findViewById(R.id.answerTextView);
        if (mAnswerIsShown) {
            setAnswerTextView();
        }

        mShowAnswerButton = findViewById(R.id.showAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "mShowAnswerButton onClick() called.");
                setAnswerTextView();
                mAnswerIsShown = true;
                Log.d(TAG, "answer is shown");
                setAnswerShownResult(mAnswerIsShown);
            }
        });
        setAnswerShownResult(mAnswerIsShown);
    }

    private void setAnswerTextView() {
        if (mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerShown);
        Log.d(TAG, "setAnswerShownResult answerIsShown: " + mAnswerIsShown);
        setResult(RESULT_OK, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(ANSWER_SHOWN, mAnswerIsShown);
        Log.d(TAG, "onSaveInstanceState, saved mAnswerIsShown: " + mAnswerIsShown);
    }
}
