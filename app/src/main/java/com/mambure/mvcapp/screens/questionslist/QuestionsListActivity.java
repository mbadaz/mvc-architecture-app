package com.mambure.mvcapp.screens.questionslist;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mambure.mvcapp.R;
import com.mambure.mvcapp.questions.GetQuestionsUseCase;
import com.mambure.mvcapp.questions.Question;
import com.mambure.mvcapp.screens.questiondetail.QuestionDetailActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionsListActivity extends AppCompatActivity implements
        QuestionsListMvcView.Listener, GetQuestionsUseCase.Listener {

    @Inject
    GetQuestionsUseCase getQuestionsUseCase;
    private QuestionsListMvcView questionsListMvcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionsListMvcView = new QuestionsListMvcView(getLayoutInflater(), null);
        setContentView(questionsListMvcView.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        questionsListMvcView.registerListener(this);
        getQuestionsUseCase.registerListener(this);
        questionsListMvcView.showProgressBar();
        getQuestionsUseCase.getQuestions();
    }

    @Override
    public void onQuestionsFetched(List<Question> questions) {
        questionsListMvcView.hideProgressBar();
        questionsListMvcView.bindQuestions(questions);
    }

    @Override
    public void onQuestionsFetchError() {
        questionsListMvcView.hideProgressBar();
        Toast.makeText(this, R.string.an_error_occurred, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onQuestionClicked(Question item) {
        QuestionDetailActivity.launch(this, item.getQuestionId());
    }

    @Override
    protected void onStop() {
        questionsListMvcView.unregisterListener(this);
        getQuestionsUseCase.unregisterListener(this);
        super.onStop();
    }
}