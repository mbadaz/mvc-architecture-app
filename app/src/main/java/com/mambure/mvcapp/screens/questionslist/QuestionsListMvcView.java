package com.mambure.mvcapp.screens.questionslist;

import com.mambure.mvcapp.questions.Question;
import com.mambure.mvcapp.screens.common.mvcview.BaseObservableMvcView;

import java.util.List;

public abstract class QuestionsListMvcView extends BaseObservableMvcView<QuestionsListMvcViewImpl.Listener> {
    public abstract void bindQuestions(List<Question> questions);

    public abstract void showProgressBar();

    public abstract void hideProgressBar();

    interface Listener {
        void onQuestionClicked(Question item);
    }
}
