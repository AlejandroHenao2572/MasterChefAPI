package com.masterchef.MasterChefAPI.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Recipe Model Tests")
class RecipeTest {

    private Recipe recipe;
    private List<String> ingredients;
    private List<String> preparationSteps;

    @BeforeEach
    void setUp() {
        ingredients = Arrays.asList(
                "2 tazas de arroz",
                "500g de pollo",
                "200g de mariscos",
                "Azafrán",
                "Caldo de pollo"
        );
        
        preparationSteps = Arrays.asList(
                "Preparar el sofrito",
                "Añadir el arroz",
                "Incorporar el caldo",
                "Cocinar por 20 minutos",
                "Dejar reposar"
        );
    }

    @Test
    @DisplayName("Debería crear una receta con constructor vacío")
    void shouldCreateRecipeWithNoArgsConstructor() {
        recipe = new Recipe();
        
        assertNotNull(recipe);
        assertNull(recipe.getId());
        assertNull(recipe.getTitle());
        assertNull(recipe.getIngredients());
        assertNull(recipe.getPreparationSteps());
        assertNull(recipe.getChefName());
        assertNull(recipe.getRecipeType());
        assertNull(recipe.getSeason());
        assertNull(recipe.getCreatedAt());
        assertNull(recipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debería crear una receta con constructor completo")
    void shouldCreateRecipeWithAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        
        recipe = new Recipe(
                "1", 
                1L, 
                "Paella Valenciana", 
                ingredients, 
                preparationSteps, 
                "Juan Pérez", 
                "CHEF", 
                null, 
                now, 
                now
        );
        
        assertNotNull(recipe);
        assertEquals("1", recipe.getId());
        assertEquals(1L, recipe.getConsecutiveNumber());
        assertEquals("Paella Valenciana", recipe.getTitle());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(preparationSteps, recipe.getPreparationSteps());
        assertEquals("Juan Pérez", recipe.getChefName());
        assertEquals("CHEF", recipe.getRecipeType());
        assertNull(recipe.getSeason());
        assertEquals(now, recipe.getCreatedAt());
        assertEquals(now, recipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debería crear una receta sin temporada usando constructor sin season")
    void shouldCreateRecipeWithoutSeasonConstructor() {
        recipe = new Recipe(
                "Paella Valenciana",
                ingredients,
                preparationSteps,
                "Juan Pérez",
                "CHEF"
        );
        
        assertNotNull(recipe);
        assertEquals("Paella Valenciana", recipe.getTitle());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(preparationSteps, recipe.getPreparationSteps());
        assertEquals("Juan Pérez", recipe.getChefName());
        assertEquals("CHEF", recipe.getRecipeType());
        assertNull(recipe.getSeason());
        assertNotNull(recipe.getCreatedAt());
        assertNotNull(recipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debería crear una receta con temporada usando constructor con season")
    void shouldCreateRecipeWithSeasonConstructor() {
        recipe = new Recipe(
                "Arroz con Pollo",
                ingredients,
                preparationSteps,
                "María García",
                "CONTESTANT",
                3
        );
        
        assertNotNull(recipe);
        assertEquals("Arroz con Pollo", recipe.getTitle());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(preparationSteps, recipe.getPreparationSteps());
        assertEquals("María García", recipe.getChefName());
        assertEquals("CONTESTANT", recipe.getRecipeType());
        assertEquals(3, recipe.getSeason());
        assertNotNull(recipe.getCreatedAt());
        assertNotNull(recipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debería establecer y obtener el ID correctamente")
    void shouldSetAndGetId() {
        recipe = new Recipe();
        String testId = "507f1f77bcf86cd799439011";
        
        recipe.setId(testId);
        
        assertEquals(testId, recipe.getId());
    }

    @Test
    @DisplayName("Debería establecer y obtener el número consecutivo correctamente")
    void shouldSetAndGetConsecutiveNumber() {
        recipe = new Recipe();
        Long consecutiveNumber = 100L;
        
        recipe.setConsecutiveNumber(consecutiveNumber);
        
        assertEquals(consecutiveNumber, recipe.getConsecutiveNumber());
    }

    @Test
    @DisplayName("Debería establecer y obtener el título correctamente")
    void shouldSetAndGetTitle() {
        recipe = new Recipe();
        String title = "Tarta de Chocolate";
        
        recipe.setTitle(title);
        
        assertEquals(title, recipe.getTitle());
    }

    @Test
    @DisplayName("Debería establecer y obtener ingredientes correctamente")
    void shouldSetAndGetIngredients() {
        recipe = new Recipe();
        
        recipe.setIngredients(ingredients);
        
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(5, recipe.getIngredients().size());
    }

    @Test
    @DisplayName("Debería establecer y obtener pasos de preparación correctamente")
    void shouldSetAndGetPreparationSteps() {
        recipe = new Recipe();
        
        recipe.setPreparationSteps(preparationSteps);
        
        assertEquals(preparationSteps, recipe.getPreparationSteps());
        assertEquals(5, recipe.getPreparationSteps().size());
    }

    @Test
    @DisplayName("Debería establecer y obtener el nombre del chef correctamente")
    void shouldSetAndGetChefName() {
        recipe = new Recipe();
        String chefName = "Gordon Ramsay";
        
        recipe.setChefName(chefName);
        
        assertEquals(chefName, recipe.getChefName());
    }

    @Test
    @DisplayName("Debería establecer y obtener el tipo de receta correctamente")
    void shouldSetAndGetRecipeType() {
        recipe = new Recipe();
        String recipeType = "VIEWER";
        
        recipe.setRecipeType(recipeType);
        
        assertEquals(recipeType, recipe.getRecipeType());
    }

    @Test
    @DisplayName("Debería establecer y obtener la temporada correctamente")
    void shouldSetAndGetSeason() {
        recipe = new Recipe();
        Integer season = 5;
        
        recipe.setSeason(season);
        
        assertEquals(season, recipe.getSeason());
    }

    @Test
    @DisplayName("Debería establecer y obtener fechas correctamente")
    void shouldSetAndGetDates() {
        recipe = new Recipe();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        
        recipe.setCreatedAt(createdAt);
        recipe.setUpdatedAt(updatedAt);
        
        assertEquals(createdAt, recipe.getCreatedAt());
        assertEquals(updatedAt, recipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debería ser igual a otra receta con los mismos valores")
    void shouldBeEqualToAnotherRecipeWithSameValues() {
        Recipe recipe1 = new Recipe(
                "Paella Valenciana",
                ingredients,
                preparationSteps,
                "Juan Pérez",
                "CHEF"
        );
        recipe1.setId("1");
        
        Recipe recipe2 = new Recipe(
                "Paella Valenciana",
                ingredients,
                preparationSteps,
                "Juan Pérez",
                "CHEF"
        );
        recipe2.setId("1");
        recipe2.setCreatedAt(recipe1.getCreatedAt());
        recipe2.setUpdatedAt(recipe1.getUpdatedAt());
        
        assertEquals(recipe1, recipe2);
        assertEquals(recipe1.hashCode(), recipe2.hashCode());
    }

    @Test
    @DisplayName("No debería ser igual a otra receta con valores diferentes")
    void shouldNotBeEqualToAnotherRecipeWithDifferentValues() {
        Recipe recipe1 = new Recipe(
                "Paella Valenciana",
                ingredients,
                preparationSteps,
                "Juan Pérez",
                "CHEF"
        );
        recipe1.setId("1");
        
        Recipe recipe2 = new Recipe(
                "Arroz con Pollo",
                ingredients,
                preparationSteps,
                "María García",
                "CONTESTANT",
                2
        );
        recipe2.setId("2");
        
        assertNotEquals(recipe1, recipe2);
    }

    @Test
    @DisplayName("toString debería contener información relevante")
    void toStringShouldContainRelevantInformation() {
        recipe = new Recipe(
                "Paella Valenciana",
                ingredients,
                preparationSteps,
                "Juan Pérez",
                "CHEF"
        );
        recipe.setId("1");
        
        String toString = recipe.toString();
        
        assertNotNull(toString);
        assertFalse(toString.isEmpty());
        // Verificar que el toString contiene al menos el nombre de la clase
        assertTrue(toString.contains("Recipe"));
    }

    @Test
    @DisplayName("Debería manejar listas vacías de ingredientes")
    void shouldHandleEmptyIngredientsList() {
        recipe = new Recipe();
        List<String> emptyIngredients = Arrays.asList();
        
        recipe.setIngredients(emptyIngredients);
        
        assertEquals(emptyIngredients, recipe.getIngredients());
        assertTrue(recipe.getIngredients().isEmpty());
    }

    @Test
    @DisplayName("Debería manejar listas vacías de pasos de preparación")
    void shouldHandleEmptyPreparationStepsList() {
        recipe = new Recipe();
        List<String> emptySteps = Arrays.asList();
        
        recipe.setPreparationSteps(emptySteps);
        
        assertEquals(emptySteps, recipe.getPreparationSteps());
        assertTrue(recipe.getPreparationSteps().isEmpty());
    }
}