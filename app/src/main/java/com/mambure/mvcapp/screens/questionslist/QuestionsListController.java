package com.mambure.mvcapp.screens.questionslist;

import com.mambure.mvcapp.questions.GetQuestionsUseCase;
import com.mambure.mvcapp.questions.Question;
import com.mambure.mvcapp.screens.common.MyNavigator;
import com.mambure.mvcapp.screens.common.ToastManager;

import java.util.List;

import javax.inject.Inject;

public class QuestionsListController  implements QuestionsListMvcView.QuestionsListViewListener,
        GetQuestionsUseCase.Listener {

    private final GetQuestionsUseCase mGetQuestionsUseCase;
    private QuestionsListMvcView mQuestionsListMvcView;
    private final ToastManager mToastManager;
    private final MyNavigator mMyNavigator;

    @Inject
    public QuestionsListController(GetQuestionsUseCase mGetQuestionsUseCase,
                                   ToastManager mToastManager, MyNavigator mMyNavigator) {
        this.mGetQuestionsUseCase = mGetQuestionsUseCase;

        this.mToastManager = mToastManager;
        this.mMyNavigator = mMyNavigator;
    }

    public void bindView(QuestionsListMvcView questionsListMvcView) {
        this.mQuestionsListMvcView = questionsListMvcView;
    }

    public void executeOnStart() {
        mQuestionsListMvcView .registerListener(this);
        mGetQuestionsUseCase.registerListener(this);
        mQuestionsListMvcView .showProgressBar();
        mGetQuestionsUseCase.getQuestions();
    }

    @Override
    public void onQuestionsFetched(List<Question> questions) {
        mQuestionsListMvcView .hideProgressBar();
        mQuestionsListMvcView .bindQuestions(questions);
    }

    @Override
    public void onQuestionsFetchError() {
        mQuestionsListMvcView .hideProgressBar();
        mToastManager.showErrorOccurredMessage();
    }

    @Override
    public void onQuestionClicked(Question item) {
        mMyNavigator.toQuestionDetailScreen(item.getQuestionId());
    }

    public void executeOnStop() {
        mQuestionsListMvcView .unregisterListener(this);
        mGetQuestionsUseCase.unregisterListener(this);
    }
}
