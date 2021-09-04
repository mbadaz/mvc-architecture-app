package com.mambure.mvcapp.screens.questiondetail;

import com.mambure.mvcapp.questions.QuestionDetail;
import com.mambure.mvcapp.screens.common.mvcview.BaseMvcView;

public abstract class QuestionDetailMvcView extends BaseMvcView {

    public abstract void bindQuestionDetail(QuestionDetail questionDetail);

    public abstract void showProgressBar();

    public abstract void hideProgressBar();
}