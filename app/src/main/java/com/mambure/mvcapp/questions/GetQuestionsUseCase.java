package com.mambure.mvcapp.questions;

import com.mambure.mvcapp.common.MyObservable;
import com.mambure.mvcapp.networking.StackOverflowApi;
import com.mambure.mvcapp.networking.schema.QuestionSchema;
import com.mambure.mvcapp.networking.schema.QuestionsResponseSchema;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetQuestionsUseCase extends MyObservable<GetQuestionsUseCase.Listener> {

    private final StackOverflowApi stackOverflowApi;

    @Inject
    public GetQuestionsUseCase(StackOverflowApi stackOverflowApi) {
        this.stackOverflowApi = stackOverflowApi;
    }

    public void getQuestions() {
        Map<String, String> params = new HashMap<>();
        params.put("pagesize", "10");
        params.put("order", "desc");
        params.put("sort", "activity");
        params.put("site", "stackoverflow");
        params.put("tagged", "android");

        stackOverflowApi.getQuestions(params).enqueue(new Callback<QuestionsResponseSchema>() {
            @Override
            public void onResponse(@NotNull Call<QuestionsResponseSchema> call, @NotNull Response<QuestionsResponseSchema> response) {
                if (response.isSuccessful() && response.body() != null) {
                    notifySuccess(response.body().questionSchemas);
                }
            }

            @Override
            public void onFailure(@NotNull Call<QuestionsResponseSchema> call, @NotNull Throwable t) {
                notifyError();
            }
        });
    }

    private void notifySuccess(List<QuestionSchema> questionSchemas) {
        // If the data is large, take care to do this on a background thread...
        List<Question> questions = new ArrayList<>();
        for (QuestionSchema questionSchema : questionSchemas) {
            Question question = new Question();
            question.setTitle(questionSchema.title);
            question.setCreationDate(questionSchema.creationDate);
            question.setQuestionId(questionSchema.questionId);
            question.setTags(questionSchema.tags);
            questions.add(question);
        }

        // In case this is a background thread, make sure to notify listeners on the UI/Main thread.
        for (Listener listener : getListeners()) {
            listener.onQuestionsFetched(questions);
        }
    }

    private void notifyError() {
        // In case this is a background thread, make sure to notify listeners on the UI/Main thread.
        for (Listener listener : getListeners()) {
            listener.onQuestionsFetchError();
        }
    }

    public interface Listener {
        void onQuestionsFetched(List<Question> questions);
        void onQuestionsFetchError();
    }
}
