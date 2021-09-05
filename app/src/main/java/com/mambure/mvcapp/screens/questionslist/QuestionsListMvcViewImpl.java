package com.mambure.mvcapp.screens.questionslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.mambure.mvcapp.R;
import com.mambure.mvcapp.questions.Question;
import com.mambure.mvcapp.screens.common.MvcViewFactory;

import java.util.List;

public class QuestionsListMvcViewImpl extends QuestionsListMvcView implements QuestionAdapter.OnItemClickListener {
    private final QuestionAdapter questionAdapter;
    private final ProgressBar progressBar;

    public QuestionsListMvcViewImpl(LayoutInflater inflater, ViewGroup parent, MvcViewFactory mvcViewFactory) {
        View rootView = inflater.inflate(R.layout.activity_questions_list, parent, false);
        setRootView(rootView);
        progressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.rv_questions_list);
        questionAdapter = new QuestionAdapter(this, mvcViewFactory);
        recyclerView.setAdapter(questionAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(recyclerView.getContext(), RecyclerView.VERTICAL));
    }

    @Override
    public void bindQuestions(List<Question> questions) {
        questionAdapter.addData(questions);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(Question item) {
        for (QuestionsListViewListener questionsListViewListener : getListeners()) {
            questionsListViewListener.onQuestionClicked(item);
        }
    }

}