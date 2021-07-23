package com.mambure.mvcapp.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mambure.mvcapp.screens.questiondetail.QuestionDetailMvcView;
import com.mambure.mvcapp.screens.questiondetail.QuestionDetailMvcViewImpl;
import com.mambure.mvcapp.screens.questionslist.QuestionsListMvcView;
import com.mambure.mvcapp.screens.questionslist.QuestionsListMvcViewImpl;

import javax.inject.Inject;

public class MvcViewFactory {

    private final LayoutInflater mLayoutInflater;

    @Inject
    public MvcViewFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    public QuestionsListMvcView getQuestionsListMvcView(ViewGroup parent) {
        return new QuestionsListMvcViewImpl(mLayoutInflater, parent);
    }

    public QuestionDetailMvcView getQuestionDetailMvcView(ViewGroup parent) {
        return new QuestionDetailMvcViewImpl(mLayoutInflater, parent);
    }
}
