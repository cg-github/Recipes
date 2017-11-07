package com.cheng.recipe.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by 李国财 on 2017-11-07.
 */

public class RecipeContentProvider extends ContentProvider {

    private static final int CODE_RECIPE = 1;
    private static final int CODE_RECIPE_WITH_ID = 2;
    private static final int CODE_INGREDIENT = 3;
    private static final int CODE_INGREDIENT_WITH_ID = 4;
    private static final int CODE_PROCESS = 5;
    private static final int CODE_PROCESS_WITH_ID = 6;

    private RecipeDbHepler mDbHepler;
    private UriMatcher mUriMatcher;


    @Override
    public boolean onCreate() {
        mDbHepler = new RecipeDbHepler(getContext());
        mUriMatcher = buildUriMatcher();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mDbHepler.getReadableDatabase();
        long id;
        String OptionStr;
        Cursor cursor = null;

        switch (mUriMatcher.match(uri)){
            case CODE_RECIPE:
                cursor = db.query(RecipeContract.RecipeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                        );
                break;
            case CODE_RECIPE_WITH_ID:
                id = RecipeContract.RecipeEntry.getRecipeIdFromUri(uri);
                OptionStr = RecipeContract.RecipeEntry.TABLE_NAME+"."+ RecipeContract.RecipeEntry.COLUMN_RECIPE_ID+"=?";
                cursor = db.query(RecipeContract.RecipeEntry.TABLE_NAME,
                        projection,
                        OptionStr,
                        new String[]{""+id},
                        null,
                        null,
                        sortOrder
                );
                break;
            case CODE_INGREDIENT:
                cursor = db.query(RecipeContract.IngredientEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case CODE_INGREDIENT_WITH_ID:
                id = RecipeContract.IngredientEntry.getRecipeIdFromUri(uri);
                OptionStr = RecipeContract.IngredientEntry.TABLE_NAME+"."+ RecipeContract.IngredientEntry.COLUMN_RECIPE_ID+"=?";
                cursor = db.query(RecipeContract.IngredientEntry.TABLE_NAME,
                        projection,
                        OptionStr,
                        new String[]{""+id},
                        null,
                        null,
                        sortOrder
                );
                break;
            case CODE_PROCESS:
                cursor = db.query(RecipeContract.ProcessEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case CODE_PROCESS_WITH_ID:
                id = RecipeContract.ProcessEntry.getRecipeIdFromUri(uri);
                OptionStr = RecipeContract.ProcessEntry.TABLE_NAME+"."+ RecipeContract.ProcessEntry.COLUMN_RECIPE_ID+"=?";
                cursor = db.query(RecipeContract.ProcessEntry.TABLE_NAME,
                        projection,
                        OptionStr,
                        new String[]{""+id},
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                break;
        }
        db.close();
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mDbHepler.getWritableDatabase();
        long id;
        String OptionStr;
        Cursor cursor;
        Uri resultUri=null,tmpUri;
        switch (mUriMatcher.match(uri)){
            case CODE_RECIPE:
                id = values.getAsLong(RecipeContract.RecipeEntry.COLUMN_RECIPE_ID);
                tmpUri = RecipeContract.RecipeEntry.buildUriWithPrimaryKey(id);
                cursor = query(tmpUri, RecipeContract.RecipeEntry.PROJECTION,null,null,null);
                if (cursor!=null && cursor.moveToFirst()
                        && (id==cursor.getLong(RecipeContract.RecipeEntry.CODE_RECIPE_ID))){
                    id = update(tmpUri,values,null,null);
                    cursor.close();
                }else {
                    id = db.insert(
                            RecipeContract.RecipeEntry.TABLE_NAME,
                            null,
                            values
                    );
                }
                resultUri = RecipeContract.RecipeEntry.buildUriWithPrimaryKey(id);
                break;
            case CODE_INGREDIENT:
                id = db.insert(RecipeContract.IngredientEntry.TABLE_NAME,
                        null,
                        values
                );
                resultUri = RecipeContract.IngredientEntry.buildUriWithPrimaryKey(id);
                break;
            case CODE_PROCESS:
                id = db.insert(RecipeContract.ProcessEntry.TABLE_NAME,
                        null,
                        values
                );
                resultUri = RecipeContract.ProcessEntry.buildUriWithPrimaryKey(id);
                break;
            default:
                break;
        }
        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDbHepler.getWritableDatabase();
        long id;
        int result = 0;
        String OptionStr;
        Cursor cursor = null;
        switch (mUriMatcher.match(uri)){
            case CODE_RECIPE:
                result = db.delete(
                        RecipeContract.RecipeEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
                break;
            case CODE_RECIPE_WITH_ID:
                id = RecipeContract.RecipeEntry.getRecipeIdFromUri(uri);
                OptionStr = RecipeContract.RecipeEntry.TABLE_NAME+"."+ RecipeContract.RecipeEntry.COLUMN_RECIPE_ID+"=?";
                result = db.delete(
                        RecipeContract.RecipeEntry.TABLE_NAME,
                        OptionStr,
                        new String[]{""+id}
                );
                break;
            case CODE_INGREDIENT:
                result = db.delete(
                        RecipeContract.RecipeEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
                break;
            case CODE_INGREDIENT_WITH_ID:
                id = RecipeContract.IngredientEntry.getRecipeIdFromUri(uri);
                OptionStr = RecipeContract.IngredientEntry.TABLE_NAME+"."+ RecipeContract.IngredientEntry.COLUMN_RECIPE_ID+"=?";
                result = db.delete(
                        RecipeContract.IngredientEntry.TABLE_NAME,
                        OptionStr,
                        new String[]{""+id}
                );
                break;
            case CODE_PROCESS:
                result = db.delete(
                        RecipeContract.ProcessEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
                break;
            case CODE_PROCESS_WITH_ID:
                id = RecipeContract.ProcessEntry.getRecipeIdFromUri(uri);
                OptionStr = RecipeContract.ProcessEntry.TABLE_NAME+"."+ RecipeContract.ProcessEntry.COLUMN_RECIPE_ID+"=?";
                result = db.delete(
                        RecipeContract.ProcessEntry.TABLE_NAME,
                        OptionStr,
                        new String[]{""+id}
                );
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDbHepler.getWritableDatabase();
        int result = 0;
        long id;
        String OptionStr;
        Cursor cursor = null;
        switch (mUriMatcher.match(uri)){
            case CODE_RECIPE:
                result = db.update(
                        RecipeContract.RecipeEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs
                );
                break;
            case CODE_RECIPE_WITH_ID:
                id = RecipeContract.RecipeEntry.getRecipeIdFromUri(uri);
                OptionStr = RecipeContract.RecipeEntry.TABLE_NAME+"."+ RecipeContract.RecipeEntry.COLUMN_RECIPE_ID+"=?";
                result = db.update(
                        RecipeContract.RecipeEntry.TABLE_NAME,
                        values,
                        OptionStr,
                        new String[]{""+id}
                );
                break;
            case CODE_INGREDIENT:
                result = db.update(
                        RecipeContract.RecipeEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs
                );
                break;
            case CODE_INGREDIENT_WITH_ID:
                id = RecipeContract.IngredientEntry.getRecipeIdFromUri(uri);
                OptionStr = RecipeContract.IngredientEntry.TABLE_NAME+"."+ RecipeContract.IngredientEntry.COLUMN_RECIPE_ID+"=?";
                result = db.update(
                        RecipeContract.IngredientEntry.TABLE_NAME,
                        values,
                        OptionStr,
                        new String[]{""+id}
                );
                break;
            case CODE_PROCESS:
                result = db.update(
                        RecipeContract.ProcessEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs
                );
                break;
            case CODE_PROCESS_WITH_ID:
                id = RecipeContract.ProcessEntry.getRecipeIdFromUri(uri);
                OptionStr = RecipeContract.ProcessEntry.TABLE_NAME+"."+ RecipeContract.ProcessEntry.COLUMN_RECIPE_ID+"=?";
                result = db.update(
                        RecipeContract.ProcessEntry.TABLE_NAME,
                        values,
                        OptionStr,
                        new String[]{""+id}
                );
                break;
            default:
                break;
        }
        return result;
    }

    static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(RecipeContract.CONTENT_AUTHRITY,"recipe",CODE_RECIPE);
        uriMatcher.addURI(RecipeContract.CONTENT_AUTHRITY,"recipe/#",CODE_RECIPE_WITH_ID);
        uriMatcher.addURI(RecipeContract.CONTENT_AUTHRITY,"ingredient",CODE_INGREDIENT);
        uriMatcher.addURI(RecipeContract.CONTENT_AUTHRITY,"ingredient/#",CODE_INGREDIENT_WITH_ID);
        uriMatcher.addURI(RecipeContract.CONTENT_AUTHRITY,"process",CODE_PROCESS);
        uriMatcher.addURI(RecipeContract.CONTENT_AUTHRITY,"process/#",CODE_PROCESS_WITH_ID);
        return uriMatcher;
    }
}
