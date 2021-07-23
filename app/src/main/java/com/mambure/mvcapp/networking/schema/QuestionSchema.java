package com.mambure.mvcapp.networking.schema;

import com.squareup.moshi.Json;

import java.util.List;

public class QuestionSchema {
    @Json(name = "tags")
    public List<String> tags;
    @Json(name = "owner")
    public UserSchema owner;
    @Json(name = "is_answered")
    public boolean isAnswered;
    @Json(name = "view_count")
    public int viewCount;
    @Json(name = "favorite_count")
    public int favoriteCount;
    @Json(name = "down_vote_count")
    public int downVoteCount;
    @Json(name = "up_vote_count")
    public int upVoteCount;
    @Json(name = "answer_count")
    public int answerCount;
    @Json(name = "score")
    public int score;
    @Json(name = "last_activity_date")
    public int lastActivityDate;
    @Json(name = "creation_date")
    public long creationDate;
    @Json(name = "last_edit_date")
    public int lastEditDate;
    @Json(name = "question_id")
    public int questionId;
    @Json(name = "link")
    public String link;
    @Json(name = "title")
    public String title;
    @Json(name = "body")
    public String body;
}
