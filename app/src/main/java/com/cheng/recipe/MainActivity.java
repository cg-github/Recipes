package com.cheng.recipe;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cheng.recipe.data.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Recipe> recipeList = new ArrayList<Recipe>();
    private final static String RECIPE_URL = "http://go.udacity.com/android-baking-app-json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void dataInit(){
        URL recipeUrl = null;
        HttpURLConnection httpURLConnection = null;
        String data = null;

        try {
            recipeUrl = new URL(RECIPE_URL);
            httpURLConnection = (HttpURLConnection) recipeUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(1000);
            httpURLConnection.setConnectTimeout(10000);

            BufferedReader reader =new BufferedReader( new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String s;

            while((s=reader.readLine())!=null){
                sb.append(s+"\n");
            }
            data = sb.toString();
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
