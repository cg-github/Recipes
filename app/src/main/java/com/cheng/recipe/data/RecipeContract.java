package com.cheng.recipe.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by 李国财 on 2017-11-06.
 */

public class RecipeContract {
    public static final String CONTENT_AUTHRITY = "com.cheng.recipe";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHRITY);

    public static final String PATH_RECIPE = "recipe";
    public static final String PATH_INGREDIENT = "ingredient";
    public static final String PATH_PROCESS = "process";


    public static class RecipeEntry implements BaseColumns{
        public static final Uri BASE_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPE).build();

        public static final String TABLE_NAME = "recipe";

        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CLASS_ID = "classid";
        public static final String COLUMN_PEOPLE_NUM = "peoplenum";
        public static final String COLUMN_PREPARE_TIME = "preparetime";
        public static final String COLUMN_COOKING_TIME = "cookingtime";
        public static final String COLUMN_PIC = "pic";
        public static final String COLUMN_TAG = "tag";
        public static final String COLUMN_COLLECT_STATUS = "status";

        public static long getRecipeIdFromUri(Uri uri){
            long id = Long.parseLong(uri.getPathSegments().get(1));
            return id;
        }

        public static Uri buildUriWithPrimaryKey(long id){
            return BASE_URI.buildUpon().appendPath(""+id).build();
        }

        public static final String[] PROJECTION = {
                COLUMN_RECIPE_ID,
                COLUMN_NAME,
                COLUMN_CLASS_ID,
                COLUMN_PEOPLE_NUM,
                COLUMN_PREPARE_TIME,
                COLUMN_COOKING_TIME,
                COLUMN_PIC,
                COLUMN_TAG,
                COLUMN_COLLECT_STATUS
        };

        public static final int CODE_RECIPE_ID = 0;
        public static final int CODE_NAME = 1;
        public static final int CODE_CLASS_ID = 2;
        public static final int CODE_PEOPLE_NUM = 3;
        public static final int CODE_PREPARE_TIME = 4;
        public static final int CODE_COOKING_TIME = 5;
        public static final int CODE_PIC = 6;
        public static final int CODE_TAG = 7;
        public static final int CODE_COLLECT_STATUS = 8;
    }

    public static class IngredientEntry implements BaseColumns{
        public static final Uri BASE_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_INGREDIENT).build();

        public static final String TABLE_NAME = "ingredient";

        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_AMOUNT = "amount";

        public static long getRecipeIdFromUri(Uri uri){
            long id = Long.parseLong(uri.getPathSegments().get(1));
            return id;
        }

        public static Uri buildUriWithPrimaryKey(long id){
            return BASE_URI.buildUpon().appendPath(""+id).build();
        }

        public static final String[] PROJECTION = {
                _ID,
                COLUMN_RECIPE_ID,
                COLUMN_NAME,
                COLUMN_TYPE,
                COLUMN_AMOUNT
        };

        public static final int CODE_ID = 0;
        public static final int CODE_RECIPE_ID = 1;
        public static final int CODE_NAME = 2;
        public static final int CODE_TYPE = 3;
        public static final int CODE_AMOUNT = 4;

    }

    public static class ProcessEntry implements BaseColumns{
        public static final Uri BASE_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROCESS).build();

        public static final String TABLE_NAME = "process";

        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_STEP_ID = "step_id";
        public static final String COLUMN_PCONTENT = "pcontent";
        public static final String COLUMN_PIC = "pic";

        public static long getRecipeIdFromUri(Uri uri){
            long id = Long.parseLong(uri.getPathSegments().get(1));
            return id;
        }
        public static Uri buildUriWithPrimaryKey(long id){
            return BASE_URI.buildUpon().appendPath(""+id).build();
        }

        public static final String[] PROJECTION = {
                _ID,
                COLUMN_RECIPE_ID,
                COLUMN_STEP_ID,
                COLUMN_PCONTENT,
                COLUMN_PIC
        };

        public static final int CODE_ID = 0;
        public static final int CODE_RECIPE_ID = 1;
        public static final int CODE_STEP_ID = 2;
        public static final int CODE_PCONTENT = 3;
        public static final int CODE_PIC = 4;
    }

}
