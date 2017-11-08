package com.cheng.recipe;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;

import com.cheng.recipe.data.RecipeContract;
import com.cheng.recipe.utils.JsonParser;

import org.junit.Test;

import java.util.List;

import static com.cheng.recipe.JsonParseTest.TEST_JSON_STRING;
import static org.junit.Assert.assertEquals;

/**
 * Created by 李国财 on 2017-11-08.
 */

public class ContentProviderTest {


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.cheng.recipe", appContext.getPackageName());
    }

    @Test
    public void testInsert(){
        Uri testUri = RecipeContract.RecipeEntry.BASE_URI;
        Context context = InstrumentationRegistry.getTargetContext();
        ContentResolver contentResolver = context.getContentResolver();
        JsonParser jsonParser = new JsonParser(TEST_JSON_STRING);
        List<ContentValues> listRecipe = jsonParser.getmRecipes();
        List<ContentValues> listIngredient = jsonParser.getmIngredients();
        List<ContentValues> listProcess = jsonParser.getmProcesses();

        contentResolver.insert(testUri,listRecipe.get(0));
    }
}
