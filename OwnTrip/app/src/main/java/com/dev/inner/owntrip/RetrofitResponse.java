package com.dev.inner.owntrip;

import com.dev.inner.owntrip.MakeCourse.data.Item_makeCourse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetrofitResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<Item_makeCourse> item_makeCourses = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Item_makeCourse> getItem_makeCourses() {
        return item_makeCourses;
    }

    public void setItem_makeCourses(List<Item_makeCourse> item_makeCourses) {
        this.item_makeCourses = item_makeCourses;
    }
}
