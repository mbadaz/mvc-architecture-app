package com.mambure.mvcapp.screens.questiondetail;

import android.view.View;

import com.mambure.mvcapp.questions.QuestionDetail;

public abstract class QuestionDetailMvcView  {

    public abstract View getRootView();

    public abstract void bindQuestionDetail(QuestionDetail questionDetail);

    public abstract void showProgressBar();

    public abstract void hideProgressBar();
}