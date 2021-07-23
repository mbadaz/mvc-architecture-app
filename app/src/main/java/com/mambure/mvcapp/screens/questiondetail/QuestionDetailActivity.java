package com.mambure.mvcapp.screens.questiondetail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mambure.mvcapp.R;
import com.mambure.mvcapp.questions.GetQuestionDetailUseCase;
import com.mambure.mvcapp.questions.QuestionDetail;
import com.mambure.mvcapp.screens.common.MvcViewFactory;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionDetailActivity extends AppCompatActivity implements GetQuestionDetailUseCase.Listener {
    public static final String QUESTION_ID = "question_id";

    @Inject
    GetQuestionDetailUseCase getQuestionDetailUseCase;
    @Inject
    MvcViewFactory mvcViewFactory;
    private QuestionDetailMvcView questionDetailMvcView;
    private int questionId;

    public static void launch(AppCompatActivity caller, int questionId) {
        Intent intent = new Intent(caller, QuestionDetailActivity.class);
        intent.putExtra(QUESTION_ID, questionId);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionDetailMvcView = mvcViewFactory.getQuestionDetailMvcView(null);
        setContentView(questionDetailMvcView.getRootView());
        if (savedInstanceState != null) {
            questionId = savedInstanceState.getInt(QUESTION_ID, -1);
        } else {
            questionId = getIntent().getIntExtra(QUESTION_ID, -1);
        }
        if (questionId == -1) {
            throw new IllegalStateException("No question id");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getQuestionDetailUseCase.registerListener(this);
        getQuestionDetailUseCase.getQuestionDetail(questionId);
        questionDetailMvcView.showProgressBar();
    }

    @Override
    public void onQuestionDetailFetched(QuestionDetail questionDetail) {
        questionDetailMvcView.hideProgressBar();
        questionDetailMvcView.bindQuestionDetail(questionDetail);
    }

    @Override
    public void onQuestionDetailFetchError() {
        questionDetailMvcView.hideProgressBar();
        Toast.makeText(this, R.string.an_error_occurred, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        outState.putInt(QUESTION_ID, questionId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        getQuestionDetailUseCase.unregisterListener(this);
        super.onStop();
    }
}