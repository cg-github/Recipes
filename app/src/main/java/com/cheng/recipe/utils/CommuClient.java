package com.cheng.recipe.utils;

import android.net.Uri;

import com.cheng.recipe.data.RecipeContract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 李国财 on 2017-11-10.
 */

public class CommuClient {
    public static final String RECIPE_NUM ="10";

    private final static String RECIPE_SCHEME= "http";
    private final static String RECIPE_AUTHRITY = "api.jisuapi.com";
    private final static String PATH_RECIPE ="recipe";
    private final static String PATH_SEARCH = "search";
    private final static String PATH_CLASS = "class";
    private final static String PATH_SEARCH_BY_CLASS = "byclass";
    private final static String PATH_DETAIL = "detail";
    private final static String PARA_KEYWORD = "keyword";
    private final static String PARA_CLASSID = "classid";
    private final static String PARA_NUM = "num";
    private final static String PARA_RECIPE_ID = "id";
    private final static String PARA_START = "start";
    private final static String PARA_APPKEY = "appkey";

    private final static String APP_KEY = "5086ad3ce4877d5a";

    HttpURLConnection mConnection;
    URL mUrl;

    public String searchByKeyword(String keyword){
        StringBuilder sb = new StringBuilder();
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme(RECIPE_SCHEME)
                    .authority(RECIPE_AUTHRITY)
                    .appendPath(PATH_RECIPE)
                    .appendPath(PATH_SEARCH)
                    .appendQueryParameter(PARA_KEYWORD,keyword)
                    .appendQueryParameter(PARA_NUM,RECIPE_NUM)
                    .appendQueryParameter(PARA_APPKEY,APP_KEY);

            mUrl = new URL(builder.toString());
            mConnection = (HttpURLConnection) mUrl.openConnection();
            mConnection.setRequestMethod("GET");
            mConnection.setConnectTimeout(1000);
            mConnection.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
            String s;
            while ((s=reader.readLine())!=null){
                sb.append(s+"\n");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String searchByClass(int classid){
        StringBuilder sb = new StringBuilder();
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme(RECIPE_SCHEME)
                    .authority(RECIPE_AUTHRITY)
                    .appendPath(PATH_RECIPE)
                    .appendPath(PATH_SEARCH_BY_CLASS)
                    .appendQueryParameter(PARA_CLASSID,""+classid)
                    .appendQueryParameter(PARA_START,"0")
                    .appendQueryParameter(PARA_NUM,RECIPE_NUM)
                    .appendQueryParameter(PARA_APPKEY,APP_KEY);

            mUrl = new URL(builder.toString());
            mConnection = (HttpURLConnection) mUrl.openConnection();
            mConnection.setRequestMethod("GET");
            mConnection.setConnectTimeout(1000);
            mConnection.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
            String s;
            while ((s=reader.readLine())!=null){
                sb.append(s+"\n");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String searchClasses(){
        StringBuilder sb = new StringBuilder();
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme(RECIPE_SCHEME)
                    .authority(RECIPE_AUTHRITY)
                    .appendPath(PATH_RECIPE)
                    .appendPath(PATH_CLASS)
                    .appendQueryParameter(PARA_APPKEY,APP_KEY);

            mUrl = new URL(builder.toString());
            mConnection = (HttpURLConnection) mUrl.openConnection();
            mConnection.setRequestMethod("GET");
            mConnection.setConnectTimeout(1000);
            mConnection.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
            String s;
            while ((s=reader.readLine())!=null){
                sb.append(s+"\n");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String searchDetail(int recipeId){
        StringBuilder sb = new StringBuilder();
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme(RECIPE_SCHEME)
                    .authority(RECIPE_AUTHRITY)
                    .appendPath(PATH_RECIPE)
                    .appendPath(PATH_DETAIL)
                    .appendQueryParameter(PARA_RECIPE_ID,""+recipeId)
                    .appendQueryParameter(PARA_APPKEY,APP_KEY);

            mUrl = new URL(builder.toString());
            mConnection = (HttpURLConnection) mUrl.openConnection();
            mConnection.setRequestMethod("GET");
            mConnection.setConnectTimeout(1000);
            mConnection.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
            String s;
            while ((s=reader.readLine())!=null){
                sb.append(s+"\n");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
