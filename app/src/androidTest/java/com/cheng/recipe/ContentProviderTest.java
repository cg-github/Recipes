package com.cheng.recipe;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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


   /* @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.cheng.recipe", appContext.getPackageName());
    }

    @Test
    public void testInsert(){
        Uri testReicpeUri = RecipeContract.RecipeEntry.BASE_URI;
        Uri testIngredientUri = RecipeContract.IngredientEntry.BASE_URI;
        Uri testProcessUri = RecipeContract.ProcessEntry.BASE_URI;
        Context context = InstrumentationRegistry.getTargetContext();
        ContentResolver contentResolver = context.getContentResolver();
        JsonParser jsonParser = new JsonParser(TEST_JSON_STRING);
        List<ContentValues> listRecipe = jsonParser.getmRecipes();
        List<ContentValues> listIngredient = jsonParser.getmIngredients();
        List<ContentValues> listProcess = jsonParser.getmProcesses();

        for(ContentValues contentValues:listRecipe){
            contentResolver.insert(testReicpeUri,contentValues);
        }
        for (ContentValues contentValues:listIngredient){
            contentResolver.insert(testIngredientUri,contentValues);
        }
        for (ContentValues contentValues:listProcess){
            contentResolver.insert(testProcessUri,contentValues);
        }
    }*/

    @Test
    public void testQuery(){
        final int recipeId = 878;
        Uri testRecipeUri = RecipeContract.RecipeEntry.buildUriWithRecipeId(recipeId);
        Uri testIngredientUri = RecipeContract.IngredientEntry.buildUriWithRecipeId(recipeId);
        Uri testProcessUri = RecipeContract.ProcessEntry.buildUriWithRecipeId(recipeId);

        ContentResolver contentResolver = InstrumentationRegistry.getContext().getContentResolver();

        Cursor recipeCursor = contentResolver.query(
                testRecipeUri,
                RecipeContract.RecipeEntry.PROJECTION,
                null,
                null,
                null
        );
        Cursor ingredientCursor = contentResolver.query(
                testIngredientUri,
                RecipeContract.IngredientEntry.PROJECTION,
                null,
                null,
                RecipeContract.IngredientEntry.SORT_BY_ID
        );
        Cursor processCursor = contentResolver.query(
                testProcessUri,
                RecipeContract.ProcessEntry.PROJECTION,
                null,
                null,
                RecipeContract.ProcessEntry.SORT_BY_STEP_ID
        );
        String recipeName="",ingredientName="",processContent="";
        if (recipeCursor!=null && recipeCursor.moveToFirst()){
            recipeName = recipeCursor.getString(RecipeContract.RecipeEntry.CODE_NAME);
        }
        assertEquals(recipeName,"哈哈test1");

        if (ingredientCursor!=null && ingredientCursor.moveToFirst()){
            ingredientCursor.moveToLast();
            ingredientName = ingredientCursor.getString(RecipeContract.IngredientEntry.CODE_NAME);
        }
        assertEquals(ingredientName,"哈哈test2");

        if (processCursor!=null && processCursor.moveToFirst()){

            processCursor.moveToNext();
            processContent = processCursor.getString(RecipeContract.ProcessEntry.CODE_PCONTENT);
        }
        assertEquals(processContent,"哈哈test3");
        assertEquals(13,processCursor.getCount());
        assertEquals(1,ingredientCursor.getCount());

        ingredientCursor.close();
        recipeCursor.close();
        processCursor.close();
    }

/*    @Test
    public void testDelete(){
        final int recipeId = 878;
        Uri testRecipeUri = RecipeContract.RecipeEntry.buildUriWithRecipeId(recipeId);
        Uri testIngredientUri = RecipeContract.IngredientEntry.buildUriWithRecipeId(recipeId);
        Uri testProcessUri = RecipeContract.ProcessEntry.buildUriWithRecipeId(recipeId);

        ContentResolver contentResolver = InstrumentationRegistry.getContext().getContentResolver();

        int rowid = contentResolver.delete(
                testRecipeUri,
                null,
                null
        );

        int ingredientNum = contentResolver.delete(
                testIngredientUri,
                null,
                null
        );

        int processNum = contentResolver.delete(
                testProcessUri,
                null,
                null
        );

        assertEquals(1,rowid);
        assertEquals(9,ingredientNum);
        assertEquals(13,processNum);
    }*/

   /* @Test
    public void testUpdata(){
        final int recipeId = 878;
        Uri testRecipeUri = RecipeContract.RecipeEntry.buildUriWithRecipeId(recipeId);
        Uri testIngredientUri = RecipeContract.IngredientEntry.buildUriWithRecipeId(recipeId);
        Uri testProcessUri = RecipeContract.ProcessEntry.buildUriWithRecipeId(recipeId);

        ContentValues recipeCV = new ContentValues();
        ContentValues ingredientCV = new ContentValues();
        ContentValues processCV = new ContentValues();

        recipeCV.put(RecipeContract.RecipeEntry.COLUMN_NAME,"哈哈test1");
        ingredientCV.put(RecipeContract.IngredientEntry.COLUMN_NAME,"哈哈test2");
        processCV.put(RecipeContract.ProcessEntry.COLUMN_PCONTENT,"哈哈test3");


        ContentResolver contentResolver = InstrumentationRegistry.getContext().getContentResolver();

        int recipeUpdated = contentResolver.update(
                testRecipeUri,
                recipeCV,
                null,
                null
        );
        int ingredientUpdated = contentResolver.update(
                testIngredientUri,
                ingredientCV,
                null,
                null
        );
        int processUpdated = contentResolver.update(
                testProcessUri,
                processCV,
                null,
                null
        );

        assertEquals(1,recipeUpdated);
        assertEquals(9,ingredientUpdated);
        assertEquals(13,processUpdated);
    }*/
}
