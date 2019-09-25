package com.highton.inner.sporrow.login;

import java.util.ArrayList;

public class UserInfo {

    private String nickname;
    private ArrayList<String> categories;

    public UserInfo(String nickname, ArrayList<String> categories) {
        this.nickname = nickname;
        this.categories = categories;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }
}
