package com.mambure.mvcapp.questions;

public class User {
    private final String profileImage;
    private final String displayName;

    public User(String profileImage, String displayName) {
        this.profileImage = profileImage;
        this.displayName = displayName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

}
