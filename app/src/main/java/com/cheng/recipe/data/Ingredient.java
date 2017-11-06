package com.cheng.recipe.data;

/**
 * Created by 李国财 on 2017-09-20.
 */

public class Ingredient {
    private int quantity = 0;
    private String measure = null;
    private String name = null;

    public Ingredient(String name , String measure , int quantity){
        this.name = name;
        this.measure = measure;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        String result = name +" "+ quantity+" "+measure;
        return result;
    }
}
