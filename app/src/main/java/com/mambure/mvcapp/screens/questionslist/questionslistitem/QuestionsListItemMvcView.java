package com.mambure.mvcapp.screens.questionslist.questionslistitem;

import com.mambure.mvcapp.questions.Question;
import com.mambure.mvcapp.screens.common.mvcview.BaseObservableMvcView;

public abstract class QuestionsListItemMvcView extends BaseObservableMvcView<QuestionsListItemMvcView.QuestionListItemListener> {
    public abstract void bindQuestion(Question question);

    public interface QuestionListItemListener {
        void onItemClicked(Question question);
    }
}
