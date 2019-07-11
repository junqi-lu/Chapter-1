package com.example.geoquiz;

public class TrueFalse extends Object {
    private int mQuestion;

    private boolean mTureQuestion;

    public TrueFalse(int question, boolean trueQuestion) {
        mQuestion = question;

        mTureQuestion = trueQuestion;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public boolean isTureQuestion() {
        return mTureQuestion;
    }

    public void setTureQuestion(boolean tureQuestion) {
        mTureQuestion = tureQuestion;
    }
}
