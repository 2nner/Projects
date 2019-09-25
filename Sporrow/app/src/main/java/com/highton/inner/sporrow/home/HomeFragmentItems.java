package com.highton.inner.sporrow.home;

public class HomeFragmentItems {
    private int borrowPrice;
    private int cartCount;
    private boolean inMyCart;
    private boolean includeWeekend;
    private String owner;
    private String title;
    private String tradeArea;
    private int tradeEndHour;
    private int tradeStartHour;

    public HomeFragmentItems(int borrowPrice, String title, String tradeArea) {
        this.borrowPrice = borrowPrice;
        this.title = title;
        this.tradeArea = tradeArea;
    }

    public int getBorrowPrice() {
        return borrowPrice;
    }

    public void setBorrowPrice(int borrowPrice) {
        this.borrowPrice = borrowPrice;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public boolean isInMyCart() {
        return inMyCart;
    }

    public void setInMyCart(boolean inMyCart) {
        this.inMyCart = inMyCart;
    }

    public boolean isIncludeWeekend() {
        return includeWeekend;
    }

    public void setIncludeWeekend(boolean includeWeekend) {
        this.includeWeekend = includeWeekend;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTradeArea() {
        return tradeArea;
    }

    public void setTradeArea(String tradeArea) {
        this.tradeArea = tradeArea;
    }

    public int getTradeEndHour() {
        return tradeEndHour;
    }

    public void setTradeEndHour(int tradeEndHour) {
        this.tradeEndHour = tradeEndHour;
    }

    public int getTradeStartHour() {
        return tradeStartHour;
    }

    public void setTradeStartHour(int tradeStartHour) {
        this.tradeStartHour = tradeStartHour;
    }
}
