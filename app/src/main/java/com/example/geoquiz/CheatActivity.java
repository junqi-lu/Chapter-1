package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

    private boolean mAnswerIsTrue;
    private boolean mAnswerIsShown;

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.example.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN =
            "com.example.geoquiz.answer_shown";

    private static final String TAG = "CheatActivity";
    private static final String ANSWER_SHOWN = "answerShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Answer will not be shown until the user
        // presses the button
        mAnswerIsShown = false;

        if (savedInstanceState != null) {
            mAnswerIsShown = savedInstanceState.getBoolean(ANSWER_SHOWN);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = findViewById(R.id.answerTextView);
        if (mAnswerIsShown) {
            setAnswerTextView();
        }

        mShowAnswerButton = findViewById(R.id.showAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerTextView();
                mAnswerIsShown = true;
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
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "CheatActivity onCreate() called.");
        savedInstanceState.putBoolean(ANSWER_SHOWN, mAnswerIsShown);
    }
}
