package com.mambure.mvcapp.networking.schema;

import com.squareup.moshi.Json;

public class UserSchema {
    @Json(name = "reputation")
    public int reputation;
    @Json(name = "user_id")
    public int userId;
    @Json(name = "user_type")
    public String userType;
    @Json(name = "accept_rate")
    public int acceptRate;
    @Json(name = "profile_image")
    public String profileImage;
    @Json(name = "display_name")
    public String displayName;
    @Json(name = "link")
    public String link;
}
