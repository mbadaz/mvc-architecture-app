package com.mambure.mvcapp.networking.schema;

import com.squareup.moshi.Json;

import java.util.List;

public class QuestionsResponseSchema {
    @Json(name = "items")
    public List<QuestionSchema> questionSchemas;
}
