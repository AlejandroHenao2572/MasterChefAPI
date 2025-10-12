package com.masterchef.MasterChefAPI.service;

import com.masterchef.MasterChefAPI.dto.*;
import com.masterchef.MasterChefAPI.exception.MasterChefException;
import com.masterchef.MasterChefAPI.model.*;
import com.masterchef.MasterChefAPI.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    
    private final RecipeRepository recipeRepository;
    
    @Override
    @Transactional
    public Recipe registerViewerRecipe(ViewerRecipeRequestDto dto) {
        ViewerRecipe recipe = new ViewerRecipe(
            dto.getTitle(),
            dto.getIngredients(),
            dto.getPreparationSteps(),
            dto.getChefName()
        );
        recipe.setConsecutiveNumber(generateConsecutiveNumber());
        return recipeRepository.save(recipe);
    }
    
    @Override
    @Transactional
    public Recipe registerContestantRecipe(ContestantRecipeRequestDto dto) {
        ContestantRecipe recipe = new ContestantRecipe(
            dto.getTitle(),
            dto.getIngredients(),
            dto.getPreparationSteps(),
            dto.getChefName(),
            dto.getSeason()
        );
        recipe.setConsecutiveNumber(generateConsecutiveNumber());
        return recipeRepository.save(recipe);
    }
    
    @Override
    @Transactional
    public Recipe registerChefRecipe(ChefRecipeRequestDto dto) {
        ChefRecipe recipe = new ChefRecipe(
            dto.getTitle(),
            dto.getIngredients(),
            dto.getPreparationSteps(),
            dto.getChefName()
        );
        recipe.setConsecutiveNumber(generateConsecutiveNumber());
        return recipeRepository.save(recipe);
    }
    
    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
    
    @Override
    public Recipe getRecipeByConsecutiveNumber(Long consecutiveNumber) {
        return recipeRepository.findByConsecutiveNumber(consecutiveNumber)
            .orElseThrow(() -> new MasterChefException(
                "No se encontró la receta con número consecutivo: " + consecutiveNumber));
    }
    
    @Override
    public List<Recipe> getContestantRecipes() {
        return recipeRepository.findByRecipeType("CONTESTANT");
    }
    
    @Override
    public List<Recipe> getViewerRecipes() {
        return recipeRepository.findByRecipeType("VIEWER");
    }
    
    @Override
    public List<Recipe> getChefRecipes() {
        return recipeRepository.findByRecipeType("CHEF");
    }
    
    @Override
    public List<Recipe> getRecipesBySeason(Integer season) {
        return recipeRepository.findBySeason(season);
    }
    
    @Override
    public List<Recipe> searchRecipesByIngredient(String ingredient) {
        List<Recipe> recipes = recipeRepository.findByIngredientContaining(ingredient);
        
        if (recipes.isEmpty()) {
            throw new MasterChefException(
                "No se encontraron recetas con el ingrediente: " + ingredient);
        }
        
        return recipes;
    }
    
    @Override
    @Transactional
    public void deleteRecipe(Long consecutiveNumber) {
        Recipe recipe = getRecipeByConsecutiveNumber(consecutiveNumber);
        recipeRepository.delete(recipe);
    }
    
    @Override
    @Transactional
    public Recipe updateRecipe(Long consecutiveNumber, RecipeRequestDto dto) {
        Recipe recipe = getRecipeByConsecutiveNumber(consecutiveNumber);
        
        recipe.setTitle(dto.getTitle());
        recipe.setIngredients(dto.getIngredients());
        recipe.setPreparationSteps(dto.getPreparationSteps());
        recipe.setChefName(dto.getChefName());
        recipe.setUpdatedAt(LocalDateTime.now());
        
        if (dto instanceof ContestantRecipeRequestDto contestantDto) {
            recipe.setSeason(contestantDto.getSeason());
        }
        
        return recipeRepository.save(recipe);
    }
    
    private Long generateConsecutiveNumber() {
        return recipeRepository.findTopByOrderByConsecutiveNumberDesc()
            .map(recipe -> recipe.getConsecutiveNumber() + 1)
            .orElse(1L);
    }
}