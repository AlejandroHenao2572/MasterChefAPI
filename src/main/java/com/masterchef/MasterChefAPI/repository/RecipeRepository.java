package com.masterchef.MasterChefAPI.repository;

import com.masterchef.MasterChefAPI.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
    
    Optional<Recipe> findByConsecutiveNumber(Long consecutiveNumber);
    
    List<Recipe> findByRecipeType(String recipeType);
    
    @Query("{ 'season': ?0 }")
    List<Recipe> findBySeason(Integer season);
    
    @Query("{ 'ingredients': { $regex: ?0, $options: 'i' } }")
    List<Recipe> findByIngredientContaining(String ingredient);
    
    Optional<Recipe> findTopByOrderByConsecutiveNumberDesc();
}