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
        QuestionsListMvcViewImpl.QuestionsListViewListener, GetQuestionsUseCase.Listener {

    @Inject
    GetQuestionsUseCase mGetQuestionsUseCase;
    private QuestionsListMvcViewImpl mQuestionsListViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionsListViewImpl = new QuestionsListMvcViewImpl(getLayoutInflater(), null);
        setContentView(mQuestionsListViewImpl.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQuestionsListViewImpl.registerListener(this);
        mGetQuestionsUseCase.registerListener(this);
        mQuestionsListViewImpl.showProgressBar();
        mGetQuestionsUseCase.getQuestions();
    }

    @Override
    public void onQuestionsFetched(List<Question> questions) {
        mQuestionsListViewImpl.hideProgressBar();
        mQuestionsListViewImpl.bindQuestions(questions);
    }

    @Override
    public void onQuestionsFetchError() {
        mQuestionsListViewImpl.hideProgressBar();
        Toast.makeText(this, R.string.an_error_occurred, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onQuestionClicked(Question item) {
        QuestionDetailActivity.launch(this, item.getQuestionId());
    }

    @Override
    protected void onStop() {
        mQuestionsListViewImpl.unregisterListener(this);
        mGetQuestionsUseCase.unregisterListener(this);
        super.onStop();
    }
}