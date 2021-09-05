package com.mambure.mvcapp.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mambure.mvcapp.screens.questiondetail.QuestionDetailMvcView;
import com.mambure.mvcapp.screens.questiondetail.QuestionDetailMvcViewImpl;
import com.mambure.mvcapp.screens.questionslist.QuestionsListMvcView;
import com.mambure.mvcapp.screens.questionslist.QuestionsListMvcViewImpl;
import com.mambure.mvcapp.screens.questionslist.questionslistitem.QuestionsListItemMvcView;
import com.mambure.mvcapp.screens.questionslist.questionslistitem.QuestionsListItemMvcViewImpl;

public class MvcViewFactory {

    private final LayoutInflater mLayoutInflater;

    public MvcViewFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    public QuestionsListMvcView getQuestionsListMvcView(ViewGroup parent) {
        return new QuestionsListMvcViewImpl(mLayoutInflater, parent, this);
    }

    public QuestionDetailMvcView getQuestionDetailsMvcView(ViewGroup parent) {
        return new QuestionDetailMvcViewImpl(mLayoutInflater, parent);
    }

    public QuestionsListItemMvcView getQuestionListItemMvcView(ViewGroup parent) {
        return new QuestionsListItemMvcViewImpl(mLayoutInflater, parent);
    }
}