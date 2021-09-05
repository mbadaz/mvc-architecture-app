package com.mambure.mvcapp.screens.questionslist.questionlistfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mambure.mvcapp.screens.common.MvcViewFactory;
import com.mambure.mvcapp.screens.questionslist.QuestionsListController;
import com.mambure.mvcapp.screens.questionslist.QuestionsListMvcView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionsListFragment extends Fragment {

    @Inject
    MvcViewFactory mMvcViewFactory;
    @Inject
    QuestionsListController mQuestionsListController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        QuestionsListMvcView mQuestionsListView = mMvcViewFactory.getQuestionsListMvcView(container);
        mQuestionsListController.bindView(mQuestionsListView);
        return mQuestionsListView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mQuestionsListController.executeOnStart();
    }

    @Override
    public void onStop() {
        mQuestionsListController.executeOnStop();
        super.onStop();
    }
}

