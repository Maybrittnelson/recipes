package com.geshaofeng.recipe.web.entity;

import java.io.Serializable;

public class Ingredient implements Serializable {

    private static final long serialVersionUID = -3745649887740616909L;
    private  String ingredientName;//配料名称
    private  String quantityDesc;  //用量描述
    private String url;

    public Ingredient(String ingredientName, String quantityDesc, String url) {
        this.ingredientName = ingredientName;
        this.quantityDesc = quantityDesc;
        this.url = url;
    }

    public Ingredient(String ingredientName, String quantityDesc) {
        this.ingredientName = ingredientName;
        this.quantityDesc = quantityDesc;
    }

    public Ingredient() {
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getQuantityDesc() {
        return quantityDesc;
    }

    public void setQuantityDesc(String quantityDesc) {
        this.quantityDesc = quantityDesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
