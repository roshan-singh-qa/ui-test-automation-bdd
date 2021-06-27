package com.rk.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {

    public String jsonReader(String getJson, int list, String feature, String path) {
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(path);
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray companyDetailsList = (JSONArray) obj;
            JSONObject company = (JSONObject) companyDetailsList.get(list);
            JSONObject user = (JSONObject) company.get(feature);
            return (String) user.get(getJson);
        } catch (Exception exception) {
        }
        return null;
    }
}
