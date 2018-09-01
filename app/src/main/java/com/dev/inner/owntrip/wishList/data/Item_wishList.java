package com.dev.inner.owntrip.wishList.data;

public class Item_wishList {
    private String tv_wishList_title;
    private String tv_wishList_content;

    public Item_wishList(String tv_wishList_title, String tv_wishList_content) {
        this.tv_wishList_title = tv_wishList_title;
        this.tv_wishList_content = tv_wishList_content;
    }

    public String getTv_wishList_title() {
        return tv_wishList_title;
    }

    public void setTv_wishList_title(String tv_wishList_title) {
        this.tv_wishList_title = tv_wishList_title;
    }

    public String getTv_wishList_content() {
        return tv_wishList_content;
    }

    public void setTv_wishList_content(String tv_wishList_content) {
        this.tv_wishList_content = tv_wishList_content;
    }
}
