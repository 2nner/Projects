package com.dev.inner.owntrip.MakeCourse.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {
    @SerializedName("tId")
    @Expose
    private Integer tId;
    @SerializedName("pId")
    @Expose
    private Integer pId;
    @SerializedName("tagName")
    @Expose
    private String tagName;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getTId() {
        return tId;
    }

    public void setTId(Integer tId) {
        this.tId = tId;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


}
