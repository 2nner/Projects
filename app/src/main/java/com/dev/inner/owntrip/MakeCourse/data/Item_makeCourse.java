package com.dev.inner.owntrip.MakeCourse.data;

import android.nfc.Tag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item_makeCourse {
    @SerializedName("pId")
    @Expose
    private Integer pId;
    @SerializedName("placeName")
    @Expose
    private String placeName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("Tags")
    @Expose
    private List<Tags> tags = null;

    public Item_makeCourse(Integer pId, String placeName, String address, String imagePath, String createdAt, String updatedAt, List<Tags> tags) {
        this.pId = pId;
        this.placeName = placeName;
        this.address = address;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tags = tags;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
}
