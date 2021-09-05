package com.mambure.mvcapp.screens.common;

import android.app.Activity;

import com.mambure.mvcapp.screens.questiondetail.QuestionDetailActivity;

public class MyNavigator {

    private final Activity mActivity;

    public MyNavigator(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void toQuestionDetailScreen(int questionId) {
        QuestionDetailActivity.launch(mActivity, questionId);
    }
}
