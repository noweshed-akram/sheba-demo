package com.btlbd.sheba.model;

public class OfferItem {
    private String ImageUrl;
    private String offerName;

    public OfferItem(String imageUrl, String offerName) {
        ImageUrl = imageUrl;
        this.offerName = offerName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getOfferName() {
        return offerName;
    }
}
