package com.geshaofeng.recipe.web.entity;


import com.geshaofeng.recipe.web.utils.FileUtils;
import com.alibaba.fastjson.JSON;
import java.io.Serializable;
import java.util.List;

public class RecipeEntity implements Serializable {
    private static final long serialVersionUID = -6547007142520060037L;
    private String id;

    private Recipe recipe;

    private List<CookStep> cookSteps;

    public RecipeEntity() {
    }

    public RecipeEntity(String id, Recipe recipe, List<CookStep> cookSteps) {
        this.id = id;
        this.recipe = recipe;
        this.cookSteps = cookSteps;
    }

    public RecipeEntity(Recipe recipe, List<CookStep> cookSteps) {
        this(FileUtils.getMD5(JSON.toJSONString(recipe)), recipe, cookSteps);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<CookStep> getCookSteps() {
        return cookSteps;
    }

    public void setCookSteps(List<CookStep> cookSteps) {
        this.cookSteps = cookSteps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
