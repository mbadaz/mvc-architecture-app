package com.mambure.mvcapp.screens.questionslist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mambure.mvcapp.screens.common.MvcViewFactory;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionsListActivity extends AppCompatActivity {
    @Inject
    MvcViewFactory mMvcViewFactory;
    @Inject
    QuestionsListController mQuestionsListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuestionsListMvcView mQuestionsListView = mMvcViewFactory.getQuestionsListMvcView(null);
        setContentView(mQuestionsListView.getRootView());
        mQuestionsListController.bindView(mQuestionsListView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQuestionsListController.executeOnStart();
    }

    @Override
    protected void onStop() {
        mQuestionsListController.executeOnStop();
        super.onStop();
    }
}