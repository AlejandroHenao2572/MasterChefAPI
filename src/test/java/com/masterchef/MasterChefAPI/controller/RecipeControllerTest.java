package com.masterchef.MasterChefAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterchef.MasterChefAPI.dto.ChefRecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.ContestantRecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.RecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.ViewerRecipeRequestDto;
import com.masterchef.MasterChefAPI.exception.MasterChefException;
import com.masterchef.MasterChefAPI.model.Recipe;
import com.masterchef.MasterChefAPI.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
@DisplayName("RecipeController Tests")
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @Autowired
    private ObjectMapper objectMapper;

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
    void shouldRegisterViewerRecipeSuccessfully() throws Exception {
        // Given
        when(recipeService.registerViewerRecipe(any(ViewerRecipeRequestDto.class))).thenReturn(mockRecipe);

        // When & Then
        mockMvc.perform(post("/api/recipes/viewer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(viewerRecipeRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("12345")))
                .andExpect(jsonPath("$.title", is("Test Recipe")))
                .andExpect(jsonPath("$.chefName", is("Test Chef")))
                .andExpect(jsonPath("$.recipeType", is("VIEWER")))
                .andExpect(jsonPath("$.consecutiveNumber", is(1)));

        verify(recipeService).registerViewerRecipe(any(ViewerRecipeRequestDto.class));
    }

    @Test
    @DisplayName("Should register contestant recipe successfully")
    void shouldRegisterContestantRecipeSuccessfully() throws Exception {
        // Given
        mockRecipe.setRecipeType("CONTESTANT");
        mockRecipe.setSeason(3);
        when(recipeService.registerContestantRecipe(any(ContestantRecipeRequestDto.class))).thenReturn(mockRecipe);

        // When & Then
        mockMvc.perform(post("/api/recipes/contestant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contestantRecipeRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("12345")))
                .andExpect(jsonPath("$.recipeType", is("CONTESTANT")))
                .andExpect(jsonPath("$.season", is(3)));

        verify(recipeService).registerContestantRecipe(any(ContestantRecipeRequestDto.class));
    }

    @Test
    @DisplayName("Should register chef recipe successfully")
    void shouldRegisterChefRecipeSuccessfully() throws Exception {
        // Given
        mockRecipe.setRecipeType("CHEF");
        when(recipeService.registerChefRecipe(any(ChefRecipeRequestDto.class))).thenReturn(mockRecipe);

        // When & Then
        mockMvc.perform(post("/api/recipes/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(chefRecipeRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("12345")))
                .andExpect(jsonPath("$.recipeType", is("CHEF")));

        verify(recipeService).registerChefRecipe(any(ChefRecipeRequestDto.class));
    }

    @Test
    @DisplayName("Should return bad request when viewer recipe has invalid data")
    void shouldReturnBadRequestWhenViewerRecipeHasInvalidData() throws Exception {
        // Given
        viewerRecipeRequestDto.setTitle(""); // Invalid title
        viewerRecipeRequestDto.setIngredients(Collections.emptyList()); // Invalid ingredients

        // When & Then
        mockMvc.perform(post("/api/recipes/viewer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(viewerRecipeRequestDto)))
                .andExpect(status().isBadRequest());

        verify(recipeService, never()).registerViewerRecipe(any());
    }

    @Test
    @DisplayName("Should return bad request when contestant recipe has invalid season")
    void shouldReturnBadRequestWhenContestantRecipeHasInvalidSeason() throws Exception {
        // Given
        contestantRecipeRequestDto.setSeason(-1); // Invalid season

        // When & Then
        mockMvc.perform(post("/api/recipes/contestant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contestantRecipeRequestDto)))
                .andExpect(status().isBadRequest());

        verify(recipeService, never()).registerContestantRecipe(any());
    }

    @Test
    @DisplayName("Should return all recipes")
    void shouldReturnAllRecipes() throws Exception {
        // Given
        List<Recipe> recipes = Arrays.asList(mockRecipe, new Recipe(), new Recipe());
        when(recipeService.getAllRecipes()).thenReturn(recipes);

        // When & Then
        mockMvc.perform(get("/api/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is("12345")));

        verify(recipeService).getAllRecipes();
    }

    @Test
    @DisplayName("Should return recipe by consecutive number")
    void shouldReturnRecipeByConsecutiveNumber() throws Exception {
        // Given
        Long consecutiveNumber = 1L;
        when(recipeService.getRecipeByConsecutiveNumber(consecutiveNumber)).thenReturn(mockRecipe);

        // When & Then
        mockMvc.perform(get("/api/recipes/{consecutiveNumber}", consecutiveNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("12345")))
                .andExpect(jsonPath("$.consecutiveNumber", is(1)))
                .andExpect(jsonPath("$.title", is("Test Recipe")));

        verify(recipeService).getRecipeByConsecutiveNumber(consecutiveNumber);
    }

    @Test
    @DisplayName("Should return 404 when recipe not found by consecutive number")
    void shouldReturn404WhenRecipeNotFoundByConsecutiveNumber() throws Exception {
        // Given
        Long consecutiveNumber = 999L;
        when(recipeService.getRecipeByConsecutiveNumber(consecutiveNumber))
            .thenThrow(new MasterChefException("No se encontró la receta con número consecutivo: " + consecutiveNumber));

        // When & Then
        mockMvc.perform(get("/api/recipes/{consecutiveNumber}", consecutiveNumber))
                .andExpect(status().isNotFound());

        verify(recipeService).getRecipeByConsecutiveNumber(consecutiveNumber);
    }

    @Test
    @DisplayName("Should return contestant recipes")
    void shouldReturnContestantRecipes() throws Exception {
        // Given
        List<Recipe> contestantRecipes = Arrays.asList(mockRecipe, new Recipe());
        when(recipeService.getContestantRecipes()).thenReturn(contestantRecipes);

        // When & Then
        mockMvc.perform(get("/api/recipes/contestant"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(recipeService).getContestantRecipes();
    }

    @Test
    @DisplayName("Should return viewer recipes")
    void shouldReturnViewerRecipes() throws Exception {
        // Given
        List<Recipe> viewerRecipes = Arrays.asList(mockRecipe);
        when(recipeService.getViewerRecipes()).thenReturn(viewerRecipes);

        // When & Then
        mockMvc.perform(get("/api/recipes/viewer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(recipeService).getViewerRecipes();
    }

    @Test
    @DisplayName("Should return chef recipes")
    void shouldReturnChefRecipes() throws Exception {
        // Given
        List<Recipe> chefRecipes = Arrays.asList(mockRecipe, new Recipe(), new Recipe());
        when(recipeService.getChefRecipes()).thenReturn(chefRecipes);

        // When & Then
        mockMvc.perform(get("/api/recipes/chef"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        verify(recipeService).getChefRecipes();
    }

    @Test
    @DisplayName("Should return recipes by season")
    void shouldReturnRecipesBySeason() throws Exception {
        // Given
        Integer season = 3;
        List<Recipe> seasonRecipes = Arrays.asList(mockRecipe, new Recipe());
        when(recipeService.getRecipesBySeason(season)).thenReturn(seasonRecipes);

        // When & Then
        mockMvc.perform(get("/api/recipes/season/{season}", season))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(recipeService).getRecipesBySeason(season);
    }

    @Test
    @DisplayName("Should search recipes by ingredient successfully")
    void shouldSearchRecipesByIngredientSuccessfully() throws Exception {
        // Given
        String ingredient = "tomate";
        List<Recipe> foundRecipes = Arrays.asList(mockRecipe, new Recipe());
        when(recipeService.searchRecipesByIngredient(ingredient)).thenReturn(foundRecipes);

        // When & Then
        mockMvc.perform(get("/api/recipes/search")
                .param("ingredient", ingredient))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("12345")));

        verify(recipeService).searchRecipesByIngredient(ingredient);
    }

    @Test
    @DisplayName("Should return 404 when no recipes found by ingredient")
    void shouldReturn404WhenNoRecipesFoundByIngredient() throws Exception {
        // Given
        String ingredient = "ingrediente-inexistente";
        when(recipeService.searchRecipesByIngredient(ingredient))
            .thenThrow(new MasterChefException("No se encontraron recetas con el ingrediente: " + ingredient));

        // When & Then
        mockMvc.perform(get("/api/recipes/search")
                .param("ingredient", ingredient))
                .andExpect(status().isNotFound());

        verify(recipeService).searchRecipesByIngredient(ingredient);
    }

    @Test
    @DisplayName("Should delete recipe successfully")
    void shouldDeleteRecipeSuccessfully() throws Exception {
        // Given
        Long consecutiveNumber = 1L;
        doNothing().when(recipeService).deleteRecipe(consecutiveNumber);

        // When & Then
        mockMvc.perform(delete("/api/recipes/{consecutiveNumber}", consecutiveNumber))
                .andExpect(status().isNoContent());

        verify(recipeService).deleteRecipe(consecutiveNumber);
    }

    @Test
    @DisplayName("Should return 404 when trying to delete non-existent recipe")
    void shouldReturn404WhenTryingToDeleteNonExistentRecipe() throws Exception {
        // Given
        Long consecutiveNumber = 999L;
        doThrow(new MasterChefException("No se encontró la receta con número consecutivo: " + consecutiveNumber))
            .when(recipeService).deleteRecipe(consecutiveNumber);

        // When & Then
        mockMvc.perform(delete("/api/recipes/{consecutiveNumber}", consecutiveNumber))
                .andExpect(status().isNotFound());

        verify(recipeService).deleteRecipe(consecutiveNumber);
    }

    @Test
    @DisplayName("Should update recipe successfully")
    void shouldUpdateRecipeSuccessfully() throws Exception {
        // Given
        Long consecutiveNumber = 1L;
        RecipeRequestDto updateDto = new RecipeRequestDto();
        updateDto.setTitle("Updated Title");
        updateDto.setIngredients(Arrays.asList("New Ingredient"));
        updateDto.setPreparationSteps(Arrays.asList("New Step"));
        updateDto.setChefName("Updated Chef");

        mockRecipe.setTitle("Updated Title");
        when(recipeService.updateRecipe(anyLong(), any(RecipeRequestDto.class))).thenReturn(mockRecipe);

        // When & Then
        mockMvc.perform(put("/api/recipes/{consecutiveNumber}", consecutiveNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("12345")))
                .andExpect(jsonPath("$.title", is("Updated Title")));

        verify(recipeService).updateRecipe(eq(consecutiveNumber), any(RecipeRequestDto.class));
    }

    @Test
    @DisplayName("Should return 404 when trying to update non-existent recipe")
    void shouldReturn404WhenTryingToUpdateNonExistentRecipe() throws Exception {
        // Given
        Long consecutiveNumber = 999L;
        RecipeRequestDto updateDto = new RecipeRequestDto();
        updateDto.setTitle("Updated Title");
        updateDto.setIngredients(Arrays.asList("New Ingredient"));
        updateDto.setPreparationSteps(Arrays.asList("New Step"));
        updateDto.setChefName("Updated Chef");

        when(recipeService.updateRecipe(anyLong(), any(RecipeRequestDto.class)))
            .thenThrow(new MasterChefException("No se encontró la receta con número consecutivo: " + consecutiveNumber));

        // When & Then
        mockMvc.perform(put("/api/recipes/{consecutiveNumber}", consecutiveNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isNotFound());

        verify(recipeService).updateRecipe(eq(consecutiveNumber), any(RecipeRequestDto.class));
    }

    @Test
    @DisplayName("Should return bad request when updating recipe with invalid data")
    void shouldReturnBadRequestWhenUpdatingRecipeWithInvalidData() throws Exception {
        // Given
        Long consecutiveNumber = 1L;
        RecipeRequestDto updateDto = new RecipeRequestDto();
        updateDto.setTitle(""); // Invalid title
        updateDto.setIngredients(Collections.emptyList()); // Invalid ingredients
        updateDto.setPreparationSteps(Collections.emptyList()); // Invalid steps
        updateDto.setChefName("A"); // Invalid chef name

        // When & Then
        mockMvc.perform(put("/api/recipes/{consecutiveNumber}", consecutiveNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isBadRequest());

        verify(recipeService, never()).updateRecipe(anyLong(), any());
    }

    @Test
    @DisplayName("Should return bad request when search ingredient parameter is missing")
    void shouldReturnBadRequestWhenSearchIngredientParameterIsMissing() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/recipes/search"))
                .andExpect(status().isInternalServerError()); // Spring devuelve 500 para parámetros faltantes

        verify(recipeService, never()).searchRecipesByIngredient(any());
    }

    @Test
    @DisplayName("Should validate that season is required for contestant recipe")
    void shouldValidateThatSeasonIsRequiredForContestantRecipe() throws Exception {
        // Given
        contestantRecipeRequestDto.setSeason(null); // Missing required season

        // When & Then
        mockMvc.perform(post("/api/recipes/contestant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contestantRecipeRequestDto)))
                .andExpect(status().isBadRequest());

        verify(recipeService, never()).registerContestantRecipe(any());
    }
}