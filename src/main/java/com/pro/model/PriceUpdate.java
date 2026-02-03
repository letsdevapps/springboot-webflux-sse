package com.pro.model;

public class PriceUpdate {
    private String asset;
    private double price;
    private long timestamp;

    public PriceUpdate(String asset, double price, long timestamp) {
        this.asset = asset;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getAsset() { return asset; }
    public double getPrice() { return price; }
    public long getTimestamp() { return timestamp; }

    public void setAsset(String asset) { this.asset = asset; }
    public void setPrice(double price) { this.price = price; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}