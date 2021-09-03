package com.mambure.mvcapp.screens.questionslist;

import android.view.View;

import com.mambure.mvcapp.common.MyObservable;
import com.mambure.mvcapp.questions.Question;

import java.util.List;

public abstract class QuestionsListMvcView extends MyObservable<QuestionsListMvcView.QuestionsListViewListener> {

    public abstract View getRootView();

    public abstract void bindQuestions(List<Question> questions);

    public abstract void showProgressBar();

    public abstract void hideProgressBar();

    interface QuestionsListViewListener {
        void onQuestionClicked(Question item);
    }
}