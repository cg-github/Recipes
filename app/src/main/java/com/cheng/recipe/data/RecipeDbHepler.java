package com.cheng.recipe.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 李国财 on 2017-11-06.
 */

public class RecipeDbHepler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recipe.db";
    private static  int DATABASE_VERSION = 1;

    public RecipeDbHepler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_RECIPE_TABLE = "CREATE TABLE "+ RecipeContract.RecipeEntry.TABLE_NAME+" ("+
                RecipeContract.RecipeEntry.COLUMN_RECIPE_ID+" INTEGER PRIMARY KEY, "+
                RecipeContract.RecipeEntry.COLUMN_NAME+" TEXT NOT NULL, "+
                RecipeContract.RecipeEntry.COLUMN_CLASS_ID+" INTEGER NOT NULL, "+
                RecipeContract.RecipeEntry.COLUMN_PEOPLE_NUM+" TEXT NOT NULL, "+
                RecipeContract.RecipeEntry.COLUMN_PREPARE_TIME+" TEXT NOT NULL, "+
                RecipeContract.RecipeEntry.COLUMN_COOKING_TIME+" TEXT NOT NULL, "+
                RecipeContract.RecipeEntry.COLUMN_CONTENT+" TEXT NOT NULL, "+
                RecipeContract.RecipeEntry.COLUMN_PIC+" TEXT NOT NULL, "+
                RecipeContract.RecipeEntry.COLUMN_TAG+" TEXT NOT NULL, "+
                RecipeContract.RecipeEntry.COLUMN_COLLECT_STATUS+" INTEGER DEFAULT 0"+
                ");";
        final String SQL_CREATE_INGREDIENT_TABLE = "CREATE TABLE "+ RecipeContract.IngredientEntry.TABLE_NAME+" ("+
                RecipeContract.IngredientEntry._ID+" INTEGER PRIMARY KEY, "+
                RecipeContract.IngredientEntry.COLUMN_RECIPE_ID+" INTEGER NOT NULL, "+
                RecipeContract.IngredientEntry.COLUMN_NAME+" TEXT NOT NULL, "+
                RecipeContract.IngredientEntry.COLUMN_TYPE+" INTEGER NOT NULL, "+
                RecipeContract.IngredientEntry.COLUMN_AMOUNT+" TEXT NOT NULL"+
                ");";
        final String SQL_CREATE_PROCESS_TABLE = "CREATE TABLE "+ RecipeContract.ProcessEntry.TABLE_NAME+" ("+
                RecipeContract.ProcessEntry._ID+" INTEGER PRIMARY KEY, "+
                RecipeContract.ProcessEntry.COLUMN_RECIPE_ID+" INTEGER NOT NULL, "+
                RecipeContract.ProcessEntry.COLUMN_STEP_ID+" INTEGER NOT NULL, "+
                RecipeContract.ProcessEntry.COLUMN_PCONTENT+" TEXT NOT NULL, "+
                RecipeContract.ProcessEntry.COLUMN_PIC+" TEXT NOT NULL"+
                ");";

        db.execSQL(SQL_CREATE_RECIPE_TABLE);
        db.execSQL(SQL_CREATE_INGREDIENT_TABLE);
        db.execSQL(SQL_CREATE_PROCESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion >= oldVersion){
            DATABASE_VERSION = newVersion;

            db.execSQL("DROP TABLE IF EXISTS"+ RecipeContract.RecipeEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS"+ RecipeContract.IngredientEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS"+ RecipeContract.ProcessEntry.TABLE_NAME);

            onCreate(db);
        }
    }
}
