package com.cheng.recipe.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 2017-09-20.
 */

public class Recipe {
    private List<Ingredient> ingredientList = new ArrayList<Ingredient>();
    private List<Step> stepList = new ArrayList<Step>();

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }
}
