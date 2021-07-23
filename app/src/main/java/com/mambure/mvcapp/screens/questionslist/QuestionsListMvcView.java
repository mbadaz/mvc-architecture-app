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

public class QuestionsListMvcView extends MyObservable<QuestionsListMvcView.Listener> implements QuestionAdapter.OnItemClickListener {
    private final View rootView;
    private final QuestionAdapter questionAdapter;
    private final ProgressBar progressBar;

    public QuestionsListMvcView(LayoutInflater inflater, ViewGroup parent) {
        rootView = inflater.inflate(R.layout.activity_questions_list, parent, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_questions_list);
        questionAdapter = new QuestionAdapter(this);
        progressBar = rootView.findViewById(R.id.progressBar);
        recyclerView.setAdapter(questionAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(recyclerView.getContext(), RecyclerView.VERTICAL));
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
        for (Listener listener : getListeners()) {
            listener.onQuestionClicked(item);
        }
    }

    interface Listener {
        void onQuestionClicked(Question item);
    }
}