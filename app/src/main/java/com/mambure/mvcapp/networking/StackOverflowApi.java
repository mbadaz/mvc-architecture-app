package com.mambure.mvcapp.networking;

import com.mambure.mvcapp.networking.schema.QuestionsResponseSchema;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface StackOverflowApi {

    @GET("search")
    Call<QuestionsResponseSchema> getQuestions(@QueryMap Map<String, String> params);

    @GET("questions/{questionId}")
    Call<QuestionsResponseSchema> getQuestionDetail(@Path("questionId") int id, @QueryMap(encoded = true) Map<String, String> params);
}
