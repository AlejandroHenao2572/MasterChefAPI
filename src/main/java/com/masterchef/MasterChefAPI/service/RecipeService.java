package com.masterchef.MasterChefAPI.service;

import java.util.List;

import com.masterchef.MasterChefAPI.dto.ChefRecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.ContestantRecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.RecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.ViewerRecipeRequestDto;
import com.masterchef.MasterChefAPI.model.Recipe;

public interface RecipeService {
    
    Recipe registerViewerRecipe(ViewerRecipeRequestDto dto);
    
    Recipe registerContestantRecipe(ContestantRecipeRequestDto dto);
    
    Recipe registerChefRecipe(ChefRecipeRequestDto dto);
    
    List<Recipe> getAllRecipes();
    
    Recipe getRecipeByConsecutiveNumber(Long consecutiveNumber);
    
    List<Recipe> getContestantRecipes();
    
    List<Recipe> getViewerRecipes();
    
    List<Recipe> getChefRecipes();
    
    List<Recipe> getRecipesBySeason(Integer season);
    
    List<Recipe> searchRecipesByIngredient(String ingredient);
    
    void deleteRecipe(Long consecutiveNumber);
    
    Recipe updateRecipe(Long consecutiveNumber, RecipeRequestDto dto);
}
