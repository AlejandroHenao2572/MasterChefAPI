package com.masterchef.MasterChefAPI.service;

import com.masterchef.MasterChefAPI.dto.ChefRecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.ContestantRecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.RecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.ViewerRecipeRequestDto;
import com.masterchef.MasterChefAPI.exception.MasterChefException;
import com.masterchef.MasterChefAPI.model.ChefRecipe;
import com.masterchef.MasterChefAPI.model.ContestantRecipe;
import com.masterchef.MasterChefAPI.model.Recipe;
import com.masterchef.MasterChefAPI.model.ViewerRecipe;
import com.masterchef.MasterChefAPI.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RecipeService Tests")
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    private ViewerRecipeRequestDto viewerRecipeRequestDto;
    private ContestantRecipeRequestDto contestantRecipeRequestDto;
    private ChefRecipeRequestDto chefRecipeRequestDto;
    private Recipe mockRecipe;
    private List<String> ingredients;
    private List<String> preparationSteps;

    @BeforeEach
    void setUp() {
        ingredients = Arrays.asList("Tomate", "Cebolla", "Ajo", "Aceite de oliva");
        preparationSteps = Arrays.asList(
            "Picar los vegetales",
            "Calentar el aceite",
            "Sofreír los ingredientes",
            "Cocinar hasta dorar"
        );

        viewerRecipeRequestDto = new ViewerRecipeRequestDto();
        viewerRecipeRequestDto.setTitle("Salsa de Tomate Casera");
        viewerRecipeRequestDto.setIngredients(ingredients);
        viewerRecipeRequestDto.setPreparationSteps(preparationSteps);
        viewerRecipeRequestDto.setChefName("María García");

        contestantRecipeRequestDto = new ContestantRecipeRequestDto();
        contestantRecipeRequestDto.setTitle("Paella Valenciana");
        contestantRecipeRequestDto.setIngredients(Arrays.asList("Arroz", "Pollo", "Azafrán"));
        contestantRecipeRequestDto.setPreparationSteps(Arrays.asList("Preparar ingredientes", "Cocinar paella"));
        contestantRecipeRequestDto.setChefName("Carlos Martínez");
        contestantRecipeRequestDto.setSeason(3);

        chefRecipeRequestDto = new ChefRecipeRequestDto();
        chefRecipeRequestDto.setTitle("Risotto de Hongos");
        chefRecipeRequestDto.setIngredients(Arrays.asList("Arroz Arborio", "Hongos", "Caldo"));
        chefRecipeRequestDto.setPreparationSteps(Arrays.asList("Preparar hongos", "Cocinar risotto"));
        chefRecipeRequestDto.setChefName("Chef Antonio");

        mockRecipe = new Recipe();
        mockRecipe.setId("12345");
        mockRecipe.setConsecutiveNumber(1L);
        mockRecipe.setTitle("Test Recipe");
        mockRecipe.setIngredients(ingredients);
        mockRecipe.setPreparationSteps(preparationSteps);
        mockRecipe.setChefName("Test Chef");
        mockRecipe.setRecipeType("VIEWER");
        mockRecipe.setCreatedAt(LocalDateTime.now());
        mockRecipe.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    @DisplayName("Should register viewer recipe successfully")
    void shouldRegisterViewerRecipeSuccessfully() {
        // Given
        when(recipeRepository.findTopByOrderByConsecutiveNumberDesc()).thenReturn(Optional.empty());
        when(recipeRepository.save(any(ViewerRecipe.class))).thenAnswer(invocation -> {
            ViewerRecipe recipe = invocation.getArgument(0);
            recipe.setId("generated-id");
            return recipe;
        });

        // When
        Recipe result = recipeService.registerViewerRecipe(viewerRecipeRequestDto);

        // Then
        assertNotNull(result);
        assertEquals("Salsa de Tomate Casera", result.getTitle());
        assertEquals("María García", result.getChefName());
        assertEquals("VIEWER", result.getRecipeType());
        assertEquals(1L, result.getConsecutiveNumber());
        verify(recipeRepository).save(any(ViewerRecipe.class));
        verify(recipeRepository).findTopByOrderByConsecutiveNumberDesc();
    }

    @Test
    @DisplayName("Should register contestant recipe successfully")
    void shouldRegisterContestantRecipeSuccessfully() {
        // Given
        when(recipeRepository.findTopByOrderByConsecutiveNumberDesc()).thenReturn(Optional.of(mockRecipe));
        when(recipeRepository.save(any(ContestantRecipe.class))).thenAnswer(invocation -> {
            ContestantRecipe recipe = invocation.getArgument(0);
            recipe.setId("generated-id");
            return recipe;
        });

        // When
        Recipe result = recipeService.registerContestantRecipe(contestantRecipeRequestDto);

        // Then
        assertNotNull(result);
        assertEquals("Paella Valenciana", result.getTitle());
        assertEquals("Carlos Martínez", result.getChefName());
        assertEquals("CONTESTANT", result.getRecipeType());
        assertEquals(3, result.getSeason());
        assertEquals(2L, result.getConsecutiveNumber());
        verify(recipeRepository).save(any(ContestantRecipe.class));
    }

    @Test
    @DisplayName("Should register chef recipe successfully")
    void shouldRegisterChefRecipeSuccessfully() {
        // Given
        when(recipeRepository.findTopByOrderByConsecutiveNumberDesc()).thenReturn(Optional.empty());
        when(recipeRepository.save(any(ChefRecipe.class))).thenAnswer(invocation -> {
            ChefRecipe recipe = invocation.getArgument(0);
            recipe.setId("generated-id");
            return recipe;
        });

        // When
        Recipe result = recipeService.registerChefRecipe(chefRecipeRequestDto);

        // Then
        assertNotNull(result);
        assertEquals("Risotto de Hongos", result.getTitle());
        assertEquals("Chef Antonio", result.getChefName());
        assertEquals("CHEF", result.getRecipeType());
        assertEquals(1L, result.getConsecutiveNumber());
        verify(recipeRepository).save(any(ChefRecipe.class));
    }

    @Test
    @DisplayName("Should return all recipes")
    void shouldReturnAllRecipes() {
        // Given
        List<Recipe> mockRecipes = Arrays.asList(mockRecipe, new Recipe(), new Recipe());
        when(recipeRepository.findAll()).thenReturn(mockRecipes);

        // When
        List<Recipe> result = recipeService.getAllRecipes();

        // Then
        assertNotNull(result);
        assertEquals(3, result.size());
        verify(recipeRepository).findAll();
    }

    @Test
    @DisplayName("Should return recipe by consecutive number")
    void shouldReturnRecipeByConsecutiveNumber() {
        // Given
        Long consecutiveNumber = 1L;
        when(recipeRepository.findByConsecutiveNumber(consecutiveNumber)).thenReturn(Optional.of(mockRecipe));

        // When
        Recipe result = recipeService.getRecipeByConsecutiveNumber(consecutiveNumber);

        // Then
        assertNotNull(result);
        assertEquals(mockRecipe.getId(), result.getId());
        assertEquals(mockRecipe.getTitle(), result.getTitle());
        verify(recipeRepository).findByConsecutiveNumber(consecutiveNumber);
    }

    @Test
    @DisplayName("Should throw exception when recipe not found by consecutive number")
    void shouldThrowExceptionWhenRecipeNotFoundByConsecutiveNumber() {
        // Given
        Long consecutiveNumber = 999L;
        when(recipeRepository.findByConsecutiveNumber(consecutiveNumber)).thenReturn(Optional.empty());

        // When & Then
        MasterChefException exception = assertThrows(MasterChefException.class, 
            () -> recipeService.getRecipeByConsecutiveNumber(consecutiveNumber));
        
        assertEquals("No se encontró la receta con número consecutivo: " + consecutiveNumber, 
            exception.getMessage());
        verify(recipeRepository).findByConsecutiveNumber(consecutiveNumber);
    }

    @Test
    @DisplayName("Should return contestant recipes")
    void shouldReturnContestantRecipes() {
        // Given
        List<Recipe> contestantRecipes = Arrays.asList(mockRecipe, new Recipe());
        when(recipeRepository.findByRecipeType("CONTESTANT")).thenReturn(contestantRecipes);

        // When
        List<Recipe> result = recipeService.getContestantRecipes();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(recipeRepository).findByRecipeType("CONTESTANT");
    }

    @Test
    @DisplayName("Should return viewer recipes")
    void shouldReturnViewerRecipes() {
        // Given
        List<Recipe> viewerRecipes = Arrays.asList(mockRecipe);
        when(recipeRepository.findByRecipeType("VIEWER")).thenReturn(viewerRecipes);

        // When
        List<Recipe> result = recipeService.getViewerRecipes();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(recipeRepository).findByRecipeType("VIEWER");
    }

    @Test
    @DisplayName("Should return chef recipes")
    void shouldReturnChefRecipes() {
        // Given
        List<Recipe> chefRecipes = Arrays.asList(mockRecipe, new Recipe(), new Recipe());
        when(recipeRepository.findByRecipeType("CHEF")).thenReturn(chefRecipes);

        // When
        List<Recipe> result = recipeService.getChefRecipes();

        // Then
        assertNotNull(result);
        assertEquals(3, result.size());
        verify(recipeRepository).findByRecipeType("CHEF");
    }

    @Test
    @DisplayName("Should return recipes by season")
    void shouldReturnRecipesBySeason() {
        // Given
        Integer season = 3;
        List<Recipe> seasonRecipes = Arrays.asList(mockRecipe, new Recipe());
        when(recipeRepository.findBySeason(season)).thenReturn(seasonRecipes);

        // When
        List<Recipe> result = recipeService.getRecipesBySeason(season);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(recipeRepository).findBySeason(season);
    }

    @Test
    @DisplayName("Should search recipes by ingredient successfully")
    void shouldSearchRecipesByIngredientSuccessfully() {
        // Given
        String ingredient = "tomate";
        List<Recipe> foundRecipes = Arrays.asList(mockRecipe, new Recipe());
        when(recipeRepository.findByIngredientContaining(ingredient)).thenReturn(foundRecipes);

        // When
        List<Recipe> result = recipeService.searchRecipesByIngredient(ingredient);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(recipeRepository).findByIngredientContaining(ingredient);
    }

    @Test
    @DisplayName("Should throw exception when no recipes found by ingredient")
    void shouldThrowExceptionWhenNoRecipesFoundByIngredient() {
        // Given
        String ingredient = "ingrediente-inexistente";
        when(recipeRepository.findByIngredientContaining(ingredient)).thenReturn(Collections.emptyList());

        // When & Then
        MasterChefException exception = assertThrows(MasterChefException.class, 
            () -> recipeService.searchRecipesByIngredient(ingredient));
        
        assertEquals("No se encontraron recetas con el ingrediente: " + ingredient, 
            exception.getMessage());
        verify(recipeRepository).findByIngredientContaining(ingredient);
    }

    @Test
    @DisplayName("Should delete recipe successfully")
    void shouldDeleteRecipeSuccessfully() {
        // Given
        Long consecutiveNumber = 1L;
        when(recipeRepository.findByConsecutiveNumber(consecutiveNumber)).thenReturn(Optional.of(mockRecipe));

        // When
        recipeService.deleteRecipe(consecutiveNumber);

        // Then
        verify(recipeRepository).findByConsecutiveNumber(consecutiveNumber);
        verify(recipeRepository).delete(mockRecipe);
    }

    @Test
    @DisplayName("Should throw exception when trying to delete non-existent recipe")
    void shouldThrowExceptionWhenTryingToDeleteNonExistentRecipe() {
        // Given
        Long consecutiveNumber = 999L;
        when(recipeRepository.findByConsecutiveNumber(consecutiveNumber)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MasterChefException.class, 
            () -> recipeService.deleteRecipe(consecutiveNumber));
        
        verify(recipeRepository).findByConsecutiveNumber(consecutiveNumber);
        verify(recipeRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Should update recipe successfully")
    void shouldUpdateRecipeSuccessfully() {
        // Given
        Long consecutiveNumber = 1L;
        RecipeRequestDto updateDto = new RecipeRequestDto();
        updateDto.setTitle("Updated Title");
        updateDto.setIngredients(Arrays.asList("New Ingredient"));
        updateDto.setPreparationSteps(Arrays.asList("New Step"));
        updateDto.setChefName("Updated Chef");

        when(recipeRepository.findByConsecutiveNumber(consecutiveNumber)).thenReturn(Optional.of(mockRecipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(mockRecipe);

        // When
        Recipe result = recipeService.updateRecipe(consecutiveNumber, updateDto);

        // Then
        assertNotNull(result);
        verify(recipeRepository).findByConsecutiveNumber(consecutiveNumber);
        verify(recipeRepository).save(mockRecipe);
        assertEquals("Updated Title", mockRecipe.getTitle());
        assertEquals("Updated Chef", mockRecipe.getChefName());
    }

    @Test
    @DisplayName("Should update contestant recipe with season")
    void shouldUpdateContestantRecipeWithSeason() {
        // Given
        Long consecutiveNumber = 1L;
        ContestantRecipeRequestDto updateDto = new ContestantRecipeRequestDto();
        updateDto.setTitle("Updated Contestant Recipe");
        updateDto.setIngredients(Arrays.asList("New Ingredient"));
        updateDto.setPreparationSteps(Arrays.asList("New Step"));
        updateDto.setChefName("Updated Contestant");
        updateDto.setSeason(5);

        when(recipeRepository.findByConsecutiveNumber(consecutiveNumber)).thenReturn(Optional.of(mockRecipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(mockRecipe);

        // When
        Recipe result = recipeService.updateRecipe(consecutiveNumber, updateDto);

        // Then
        assertNotNull(result);
        verify(recipeRepository).save(mockRecipe);
        assertEquals(5, mockRecipe.getSeason());
    }

    @Test
    @DisplayName("Should throw exception when trying to update non-existent recipe")
    void shouldThrowExceptionWhenTryingToUpdateNonExistentRecipe() {
        // Given
        Long consecutiveNumber = 999L;
        RecipeRequestDto updateDto = new RecipeRequestDto();
        when(recipeRepository.findByConsecutiveNumber(consecutiveNumber)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MasterChefException.class, 
            () -> recipeService.updateRecipe(consecutiveNumber, updateDto));
        
        verify(recipeRepository).findByConsecutiveNumber(consecutiveNumber);
        verify(recipeRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should generate consecutive number 1 when no recipes exist")
    void shouldGenerateConsecutiveNumber1WhenNoRecipesExist() {
        // Given
        when(recipeRepository.findTopByOrderByConsecutiveNumberDesc()).thenReturn(Optional.empty());
        when(recipeRepository.save(any(ViewerRecipe.class))).thenAnswer(invocation -> {
            ViewerRecipe recipe = invocation.getArgument(0);
            assertEquals(1L, recipe.getConsecutiveNumber());
            return recipe;
        });

        // When
        recipeService.registerViewerRecipe(viewerRecipeRequestDto);

        // Then
        verify(recipeRepository).findTopByOrderByConsecutiveNumberDesc();
    }

    @Test
    @DisplayName("Should generate incremented consecutive number when recipes exist")
    void shouldGenerateIncrementedConsecutiveNumberWhenRecipesExist() {
        // Given
        Recipe lastRecipe = new Recipe();
        lastRecipe.setConsecutiveNumber(5L);
        when(recipeRepository.findTopByOrderByConsecutiveNumberDesc()).thenReturn(Optional.of(lastRecipe));
        when(recipeRepository.save(any(ViewerRecipe.class))).thenAnswer(invocation -> {
            ViewerRecipe recipe = invocation.getArgument(0);
            assertEquals(6L, recipe.getConsecutiveNumber());
            return recipe;
        });

        // When
        recipeService.registerViewerRecipe(viewerRecipeRequestDto);

        // Then
        verify(recipeRepository).findTopByOrderByConsecutiveNumberDesc();
    }
}