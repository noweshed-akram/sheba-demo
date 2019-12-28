package com.btlbd.sheba.model;

public class Item {

    private String itemImageUrl;
    private String itemOfferName;

    public Item(String itemImageUrl, String itemOfferName) {
        this.itemImageUrl = itemImageUrl;
        this.itemOfferName = itemOfferName;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public String getItemOfferName() {
        return itemOfferName;
    }
}
