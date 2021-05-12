package com.shivamkharde.finalyearbe2020.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SimilarApps {
//    declare all the variable of api response
    private String applicationName;
    private String packageName;
    private String detailInfoUrl;
    private String applicationIcon;
    private String developer;
    private String priceText;
    private int price;
    private boolean isFree;
    private String appDescription;
    private String rating;
    private String playstoreUrl;
    private String permissionsUrl;
    private String similarUrl;
    private String reviewsUrl;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDetailInfoUrl() {
        return detailInfoUrl;
    }

    public void setDetailInfoUrl(String detailInfoUrl) {
        this.detailInfoUrl = detailInfoUrl;
    }

    public String getApplicationIcon() {
        return applicationIcon;
    }

    public void setApplicationIcon(String applicationIcon) {
        this.applicationIcon = applicationIcon;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPlaystoreUrl() {
        return playstoreUrl;
    }

    public void setPlaystoreUrl(String playstoreUrl) {
        this.playstoreUrl = playstoreUrl;
    }

    public String getPermissionsUrl() {
        return permissionsUrl;
    }

    public void setPermissionsUrl(String permissionsUrl) {
        this.permissionsUrl = permissionsUrl;
    }

    public String getSimilarUrl() {
        return similarUrl;
    }

    public void setSimilarUrl(String similarUrl) {
        this.similarUrl = similarUrl;
    }

    public String getReviewsUrl() {
        return reviewsUrl;
    }

    public void setReviewsUrl(String reviewsUrl) {
        this.reviewsUrl = reviewsUrl;
    }

//    method to create object of class by passing all the data
    public static SimilarApps fromJson(JSONObject jsonObject) {
        SimilarApps similarApps = new SimilarApps();
        // Deserialize json into object fields
        try {
            similarApps.applicationName = jsonObject.getString("title");
            similarApps.packageName = jsonObject.getString("appId");
            similarApps.detailInfoUrl = jsonObject.getString("url");
            similarApps.applicationIcon = jsonObject.getString("icon");
            similarApps.developer = jsonObject.getJSONObject("developer").getString("devId");
            similarApps.priceText = jsonObject.getString("priceText");
            similarApps.price = jsonObject.getInt("price");
            similarApps.isFree = jsonObject.getBoolean("free");
            similarApps.appDescription = jsonObject.getString("summary");
            similarApps.rating = jsonObject.getString("scoreText");
            similarApps.playstoreUrl = jsonObject.getString("playstoreUrl");
            similarApps.permissionsUrl = jsonObject.getString("permissions");
            similarApps.similarUrl = jsonObject.getString("similar");
            similarApps.reviewsUrl = jsonObject.getString("reviews");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return similarApps;
    }

//    method to get array list of SimilarApps class object
    public static ArrayList<SimilarApps> fromJson(JSONArray jsonArray) {
        JSONObject similarAppsJson;
        ArrayList<SimilarApps> similarAppsArrayList = new ArrayList<SimilarApps>(jsonArray.length());
        // Process each result in json array, decode and convert to SimilarApps object
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                similarAppsJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            SimilarApps similarApps = SimilarApps.fromJson(similarAppsJson);
            if (similarApps != null) {
                similarAppsArrayList.add(similarApps);
            }
        }

        return similarAppsArrayList;
    }
}
