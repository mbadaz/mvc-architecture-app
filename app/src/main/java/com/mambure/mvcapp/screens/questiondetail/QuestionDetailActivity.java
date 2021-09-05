package com.mambure.mvcapp.screens.questiondetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mambure.mvcapp.R;
import com.mambure.mvcapp.questions.GetQuestionDetailUseCase;
import com.mambure.mvcapp.questions.QuestionDetail;
import com.mambure.mvcapp.screens.common.MvcViewFactory;
import com.mambure.mvcapp.screens.common.ToastManager;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionDetailActivity extends AppCompatActivity implements GetQuestionDetailUseCase.Listener {
    private static final String QUESTION_ID = "question_id";

    public static void launch(Activity caller, int questionId) {
        Intent intent = new Intent(caller, QuestionDetailActivity.class);
        intent.putExtra(QUESTION_ID, questionId);
        caller.startActivity(intent);
    }

    @Inject
    GetQuestionDetailUseCase mGetQuestionDetailUseCase;
    @Inject
    MvcViewFactory mMvcViewFactory;
    @Inject
    ToastManager mToastManager
    private QuestionDetailMvcView mQuestionDetailMvcView;
    private int questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionDetailMvcView = mMvcViewFactory.getQuestionDetailsMvcView(null);
        setContentView(mQuestionDetailMvcView.getRootView());
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
        mQuestionDetailMvcView.showProgressBar();
    }

    @Override
    public void onQuestionDetailFetched(QuestionDetail questionDetail) {
        mQuestionDetailMvcView.hideProgressBar();
        mQuestionDetailMvcView.bindQuestionDetail(questionDetail);
    }

    @Override
    public void onQuestionDetailFetchError() {
        mQuestionDetailMvcView.hideProgressBar();
        mToastManager.showErrorOccurredMessage();
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