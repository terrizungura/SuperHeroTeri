package com.tererai.superheroteri.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class SuperHeroModel {

    private String superName;
    private String superWork;
    private String superWeight;
    private String superUrl;

    private String aliases;
    private String fullName;
    private String powerStats;

    public static SuperHeroModel fromJson(JSONObject jsonObject){

        try {

            SuperHeroModel superHeroData = new SuperHeroModel();

            superHeroData.superName = jsonObject.getJSONArray("results").getJSONObject(1).getString("name");
            superHeroData.superWork = jsonObject.getJSONArray("results").getJSONObject(1).getJSONObject("work").getString("base");
            superHeroData.superWeight = jsonObject.getJSONArray("results").getJSONObject(1)
                    .getJSONObject("appearance").getJSONArray("weight").getString(1);
            superHeroData.superUrl = jsonObject.getJSONArray("results").getJSONObject(1).getJSONObject("image").getString("url");
            superHeroData.aliases = jsonObject.getJSONArray("results").getJSONObject(1)
                    .getJSONObject("biography").getJSONArray("aliases").toString();
            superHeroData.fullName = jsonObject.getJSONArray("results").getJSONObject(1)
                    .getJSONObject("biography").getString("full-name");
            superHeroData.powerStats= jsonObject.getJSONArray("results").getJSONObject(1)
                    .getJSONObject("powerstats").toString();

            return superHeroData;
        }
        catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public String getSuperName() {
        return superName;
    }

    public String getSuperWork() {
        return superWork;
    }

    public String getSuperWeight() {
        return superWeight;
    }

    public String getSuperUrl() {
        return superUrl;
    }

    public String getAliases() {
        return aliases;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPowerStats() {
        return powerStats;
    }
}
