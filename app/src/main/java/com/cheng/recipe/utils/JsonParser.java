package com.cheng.recipe.utils;

import android.content.ContentValues;

import com.cheng.recipe.data.RecipeContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 2017-09-20.
 */

public class JsonParser {
    private List<ContentValues> mRecipes = new ArrayList<ContentValues>();
    private List<ContentValues> mIngredients = new ArrayList<ContentValues>();
    private List<ContentValues> mProcesses = new ArrayList<ContentValues>();

    public JsonParser(String s){
        parseData(s);
    }

    void parseData(String s){
        JSONObject jsonResult,jsonTotal,jsonRecipe,jsonIngredient,jsonProcess;
        JSONArray jsonRecipes,jsonIngredients,jsonProcesses;
        ContentValues valueRecipe,valueIngredient,valueProcess;
        int recipeNum;
        try{
            jsonTotal = new JSONObject(s);
            jsonResult = jsonTotal.getJSONObject("result");
            recipeNum = jsonResult.getInt("num");
            jsonRecipes = jsonResult.getJSONArray("list");

            for(int i=0;i<recipeNum;i++){
                jsonRecipe = jsonRecipes.getJSONObject(i);
                jsonIngredients = jsonRecipe.getJSONArray("material");
                jsonProcesses = jsonRecipe.getJSONArray("process");
                long recipeId = jsonRecipe.getLong("id");

                valueRecipe = new ContentValues();
                valueRecipe.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_ID,recipeId);
                valueRecipe.put(RecipeContract.RecipeEntry.COLUMN_NAME,jsonRecipe.getString("name"));
                valueRecipe.put(RecipeContract.RecipeEntry.COLUMN_CLASS_ID,jsonRecipe.getLong("classid"));
                valueRecipe.put(RecipeContract.RecipeEntry.COLUMN_PEOPLE_NUM,jsonRecipe.getString("peoplenum"));
                valueRecipe.put(RecipeContract.RecipeEntry.COLUMN_PREPARE_TIME,jsonRecipe.getString("preparetime"));
                valueRecipe.put(RecipeContract.RecipeEntry.COLUMN_COOKING_TIME,jsonRecipe.getString("cookingtime"));
                valueRecipe.put(RecipeContract.RecipeEntry.COLUMN_CONTENT,jsonRecipe.getString("content"));
                valueRecipe.put(RecipeContract.RecipeEntry.COLUMN_PIC,jsonRecipe.getString("pic"));
                valueRecipe.put(RecipeContract.RecipeEntry.COLUMN_TAG,jsonRecipe.getString("tag"));
                mRecipes.add(valueRecipe);

                for (int j=0;j<jsonIngredients.length();j++){
                    jsonIngredient = jsonIngredients.getJSONObject(j);
                    valueIngredient = new ContentValues();
                    valueIngredient.put(RecipeContract.IngredientEntry.COLUMN_RECIPE_ID,recipeId);
                    valueIngredient.put(RecipeContract.IngredientEntry.COLUMN_NAME,jsonIngredient.getString("mname"));
                    valueIngredient.put(RecipeContract.IngredientEntry.COLUMN_TYPE,jsonIngredient.getInt("type"));
                    valueIngredient.put(RecipeContract.IngredientEntry.COLUMN_AMOUNT,jsonIngredient.getString("amount"));
                    mIngredients.add(valueIngredient);
                }

                for (int k=0;k<jsonProcesses.length();k++){
                    jsonProcess = jsonProcesses.getJSONObject(k);
                    valueProcess = new ContentValues();
                    valueProcess.put(RecipeContract.ProcessEntry.COLUMN_RECIPE_ID,recipeId);
                    valueProcess.put(RecipeContract.ProcessEntry.COLUMN_STEP_ID,k+1);
                    valueProcess.put(RecipeContract.ProcessEntry.COLUMN_PCONTENT,jsonProcess.getString("pcontent"));
                    valueProcess.put(RecipeContract.ProcessEntry.COLUMN_PIC,jsonProcess.getString("pic"));
                    mProcesses.add(valueProcess);
                }


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public List<ContentValues> getmRecipes(){
        return  mRecipes;
    }

    public List<ContentValues> getmIngredients(){
        return mIngredients;
    }

    public List<ContentValues> getmProcesses(){
        return mProcesses;
    }
}
