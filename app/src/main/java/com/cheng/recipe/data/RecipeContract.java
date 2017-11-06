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
    }

    public static class IngredientEntry implements BaseColumns{
        public static final Uri BASE_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_INGREDIENT).build();

        public static final String TABLE_NAME = "ingredient";

        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_AMOUNT = "amount";
    }

    public static class ProcessEntry implements BaseColumns{
        public static final Uri BASE_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROCESS).build();

        public static final String TABLE_NAME = "process";

        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_STEP_ID = "step_id";
        public static final String COLUMN_PCONTENT = "pcontent";
        public static final String COLUMN_PIC = "pic";
    }

}
