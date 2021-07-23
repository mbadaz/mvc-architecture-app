package com.mambure.mvcapp.screens.questionslist.questionlistitem;

import com.mambure.mvcapp.questions.Question;
import com.mambure.mvcapp.screens.common.mvcview.BaseObservableMvcView;

public abstract class QuestionListItemMvcView extends BaseObservableMvcView<QuestionListItemMvcViewImpl.Listener> {
    public abstract void bindQuestion(Question question);
}
