package com.mambure.mvcapp.questions;

import com.mambure.mvcapp.common.MyObservable;
import com.mambure.mvcapp.networking.StackOverflowApi;
import com.mambure.mvcapp.networking.schema.QuestionSchema;
import com.mambure.mvcapp.networking.schema.QuestionsResponseSchema;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetQuestionDetailUseCase extends MyObservable<GetQuestionDetailUseCase.Listener> {

    private final StackOverflowApi stackOverflowApi;

    @Inject
    public GetQuestionDetailUseCase(StackOverflowApi stackOverflowApi) {
        this.stackOverflowApi = stackOverflowApi;
    }

    public void getQuestionDetail(int questionId ) {
        Map<String, String> params = new HashMap<>();
        params.put("order", "desc");
        params.put("sort", "activity");
        params.put("site", "stackoverflow");
        params.put("filter", "!nKzQUR3Egv");

        stackOverflowApi.getQuestionDetail(questionId,params).enqueue(new Callback<QuestionsResponseSchema>() {
            @Override
            public void onResponse(@NotNull Call<QuestionsResponseSchema> call, @NotNull Response<QuestionsResponseSchema> response) {
                if (response.isSuccessful() && response.body() != null &&
                        !response.body().questionSchemas.isEmpty()) {
                    notifySuccess(response.body().questionSchemas);
                } else {
                    notifyError();
                }
            }

            @Override
            public void onFailure(@NotNull Call<QuestionsResponseSchema> call, @NotNull Throwable t) {
                notifyError();
            }
        });
    }

    private void notifySuccess(List<QuestionSchema> questionSchemas) {
        QuestionSchema questionSchema = questionSchemas.get(0);
        QuestionDetail questionDetail = new QuestionDetail();
        questionDetail.setBody(questionSchema.body);
        questionDetail.setTitle(questionSchema.title);
        questionDetail.setCreationDate(questionSchema.creationDate);
        questionDetail.setQuestionId(questionSchema.questionId);
        User user = new User(questionSchema.owner.profileImage, questionSchema.owner.displayName);
        questionDetail.setOwner(user);

        // In case this is a background thread, make sure to notify listeners on the UI/Main thread.
        for (Listener listener : getListeners()) {
            listener.onQuestionDetailFetched(questionDetail);
        }
    }

    private void notifyError() {
        // In case this is a background thread, make sure to notify listeners on the UI/Main thread.
        for (Listener listener : getListeners()) {
            listener.onQuestionDetailFetchError();
        }
    }

    public interface Listener {
        void onQuestionDetailFetched(QuestionDetail questions);
        void onQuestionDetailFetchError();
    }
}
