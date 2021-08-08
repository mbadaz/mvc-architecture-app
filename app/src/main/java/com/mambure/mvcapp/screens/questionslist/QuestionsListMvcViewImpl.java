package com.mambure.mvcapp.screens.questionslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.mambure.mvcapp.R;
import com.mambure.mvcapp.common.MyObservable;
import com.mambure.mvcapp.questions.Question;

import java.util.List;

public class QuestionsListMvcViewImpl extends MyObservable<QuestionsListMvcViewImpl.QuestionsListViewListener> implements QuestionAdapter.OnItemClickListener {
    private final QuestionAdapter questionAdapter;
    private final ProgressBar progressBar;
    private final View rootView;

    public QuestionsListMvcViewImpl(LayoutInflater inflater, ViewGroup parent) {
        rootView = inflater.inflate(R.layout.activity_questions_list, parent, false);
        progressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.rv_questions_list);
        questionAdapter = new QuestionAdapter(this);
        recyclerView.setAdapter(questionAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(recyclerView.getContext(), RecyclerView.VERTICAL));
    }

    private <T extends View> T findViewById(int viewId) {
        return rootView.findViewById(viewId);
    }

    public View getRootView() {
        return rootView;
    }

    public void bindQuestions(List<Question> questions) {
        questionAdapter.addData(questions);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(Question item) {
        for (QuestionsListViewListener questionsListViewListener : getListeners()) {
            questionsListViewListener.onQuestionClicked(item);
        }
    }

    interface QuestionsListViewListener {
        void onQuestionClicked(Question item);
    }

}