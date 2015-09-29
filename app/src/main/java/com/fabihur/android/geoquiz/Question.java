package com.fabihur.android.geoquiz;

/**
 * Created by fabi on 23/09/15.
 */
public class Question {

    private int questionText;

    private Boolean questionAnswer;

    public Question(int questionResId, Boolean questionAnswer) {
        this.questionText = questionResId;
        this.questionAnswer = questionAnswer;
    }

    public int getQuestionText() {
        return questionText;
    }

    public void setQuestionText(int questionText) {
        this.questionText = questionText;
    }

    public Boolean getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(Boolean questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
