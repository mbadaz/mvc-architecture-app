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
        QuestionsListMvcView.QuestionsListViewListener, GetQuestionsUseCase.Listener {

    @Inject
    GetQuestionsUseCase mGetQuestionsUseCase;
    private QuestionsListMvcView mQuestionsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionsListView = new QuestionsListMvcViewImpl(getLayoutInflater(), null);
        setContentView(mQuestionsListView.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQuestionsListView.registerListener(this);
        mGetQuestionsUseCase.registerListener(this);
        mQuestionsListView.showProgressBar();
        mGetQuestionsUseCase.getQuestions();
    }

    @Override
    public void onQuestionsFetched(List<Question> questions) {
        mQuestionsListView.hideProgressBar();
        mQuestionsListView.bindQuestions(questions);
    }

    @Override
    public void onQuestionsFetchError() {
        mQuestionsListView.hideProgressBar();
        Toast.makeText(this, R.string.an_error_occurred, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onQuestionClicked(Question item) {
        QuestionDetailActivity.launch(this, item.getQuestionId());
    }

    @Override
    protected void onStop() {
        mQuestionsListView.unregisterListener(this);
        mGetQuestionsUseCase.unregisterListener(this);
        super.onStop();
    }
}