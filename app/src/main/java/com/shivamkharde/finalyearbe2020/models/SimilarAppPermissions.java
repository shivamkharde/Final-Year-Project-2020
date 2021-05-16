package com.shivamkharde.finalyearbe2020.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SimilarAppPermissions {
    //    declare all the variable of api response
    private String applicationPermission;
    private String permissionsType;

    public String getApplicationPermission() {
        return applicationPermission;
    }

    public void setApplicationPermission(String applicationPermission) {
        this.applicationPermission = applicationPermission;
    }

    public String getPermissionsType() {
        return permissionsType;
    }

    public void setPermissionsType(String permissionsType) {
        this.permissionsType = permissionsType;
    }

    //    method to create object of class by passing all the data
    public static SimilarAppPermissions fromJson(JSONObject jsonObject) {
        SimilarAppPermissions similarAppPermissions = new SimilarAppPermissions();
        // Deserialize json into object fields
        try {
            similarAppPermissions.applicationPermission = jsonObject.getString("permission");
            similarAppPermissions.permissionsType = jsonObject.getString("type");


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return similarAppPermissions;
    }

    //    method to get array list of SimilarApps class object
    public static ArrayList<SimilarAppPermissions> fromJson(JSONArray jsonArray) {
        JSONObject similarAppsPermissionJson;
        ArrayList<SimilarAppPermissions> similarAppPermissionsArrayList = new ArrayList<SimilarAppPermissions>(jsonArray.length());
        // Process each result in json array, decode and convert to SimilarAppsPermissions object
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                similarAppsPermissionJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            SimilarAppPermissions similarAppPermissions = SimilarAppPermissions.fromJson(similarAppsPermissionJson);
            if (similarAppPermissions != null) {
                similarAppPermissionsArrayList.add(similarAppPermissions);
            }
        }

        return similarAppPermissionsArrayList;
    }
}
