package com.mambure.mvcapp.screens.questiondetail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mambure.mvcapp.R;
import com.mambure.mvcapp.questions.GetQuestionDetailUseCase;
import com.mambure.mvcapp.questions.QuestionDetail;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionDetailActivity extends AppCompatActivity implements GetQuestionDetailUseCase.Listener {
    public static final String QUESTION_ID = "question_id";

    @Inject
    GetQuestionDetailUseCase mGetQuestionDetailUseCase;

    private QuestionDetailMvcViewImpl mQuestionDetailMvcViewImpl;
    private int questionId;

    public static void launch(AppCompatActivity caller, int questionId) {
        Intent intent = new Intent(caller, QuestionDetailActivity.class);
        intent.putExtra(QUESTION_ID, questionId);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionDetailMvcViewImpl = new QuestionDetailMvcViewImpl(getLayoutInflater(), null);
        setContentView(mQuestionDetailMvcViewImpl.getRootView());
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
        mGetQuestionDetailUseCase.registerListener(this);
        mGetQuestionDetailUseCase.getQuestionDetail(questionId);
        mQuestionDetailMvcViewImpl.showProgressBar();
    }

    @Override
    public void onQuestionDetailFetched(QuestionDetail questionDetail) {
        mQuestionDetailMvcViewImpl.hideProgressBar();
        mQuestionDetailMvcViewImpl.bindQuestionDetail(questionDetail);
    }

    @Override
    public void onQuestionDetailFetchError() {
        mQuestionDetailMvcViewImpl.hideProgressBar();
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
        mGetQuestionDetailUseCase.unregisterListener(this);
        super.onStop();
    }
}