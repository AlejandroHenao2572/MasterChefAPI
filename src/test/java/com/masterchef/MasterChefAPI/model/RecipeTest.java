package com.masterchef.MasterChefAPI.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para Recipe")
class RecipeTest {

    private List<String> ingredients;
    private List<String> preparationSteps;

    @BeforeEach
    void setUp() {
        ingredients = Arrays.asList("Tomate", "Cebolla", "Ajo");
        preparationSteps = Arrays.asList("Cortar", "Freír", "Mezclar");
    }

    @Test
    @DisplayName("Debe crear una receta con el constructor completo (con todos los campos)")
    void testAllArgsConstructor() {
        // Arrange
        String id = "123abc";
        Long consecutiveNumber = 1L;
        String title = "Paella Valenciana";
        String chefName = "Juan Pérez";
        String recipeType = "VIEWER";
        Integer season = null;
        LocalDateTime now = LocalDateTime.now();

        // Act
        Recipe recipe = new Recipe(id, consecutiveNumber, title, ingredients, 
                                   preparationSteps, chefName, recipeType, season, now, now);

        // Assert
        assertNotNull(recipe);
        assertEquals(id, recipe.getId());
        assertEquals(consecutiveNumber, recipe.getConsecutiveNumber());
        assertEquals(title, recipe.getTitle());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(preparationSteps, recipe.getPreparationSteps());
        assertEquals(chefName, recipe.getChefName());
        assertEquals(recipeType, recipe.getRecipeType());
        assertNull(recipe.getSeason());
        assertEquals(now, recipe.getCreatedAt());
        assertEquals(now, recipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debe crear una receta con el constructor sin temporada")
    void testConstructorWithoutSeason() {
        // Arrange
        String title = "Gazpacho Andaluz";
        String chefName = "María García";
        String recipeType = "VIEWER";

        // Act
        Recipe recipe = new Recipe(title, ingredients, preparationSteps, chefName, recipeType);

        // Assert
        assertNotNull(recipe);
        assertEquals(title, recipe.getTitle());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(preparationSteps, recipe.getPreparationSteps());
        assertEquals(chefName, recipe.getChefName());
        assertEquals(recipeType, recipe.getRecipeType());
        assertNull(recipe.getSeason());
        assertNotNull(recipe.getCreatedAt());
        assertNotNull(recipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debe crear una receta con el constructor con temporada")
    void testConstructorWithSeason() {
        // Arrange
        String title = "Arroz con Pollo";
        String chefName = "Carlos Rodríguez";
        String recipeType = "CONTESTANT";
        Integer season = 3;

        // Act
        Recipe recipe = new Recipe(title, ingredients, preparationSteps, chefName, recipeType, season);

        // Assert
        assertNotNull(recipe);
        assertEquals(title, recipe.getTitle());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(preparationSteps, recipe.getPreparationSteps());
        assertEquals(chefName, recipe.getChefName());
        assertEquals(recipeType, recipe.getRecipeType());
        assertEquals(season, recipe.getSeason());
        assertNotNull(recipe.getCreatedAt());
        assertNotNull(recipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debe crear una receta vacía con el constructor sin argumentos")
    void testNoArgsConstructor() {
        // Act
        Recipe recipe = new Recipe();

        // Assert
        assertNotNull(recipe);
        assertNull(recipe.getId());
        assertNull(recipe.getConsecutiveNumber());
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
    @DisplayName("Debe establecer y obtener correctamente el ID")
    void testSetAndGetId() {
        // Arrange
        Recipe recipe = new Recipe();
        String id = "abc123";

        // Act
        recipe.setId(id);

        // Assert
        assertEquals(id, recipe.getId());
    }

    @Test
    @DisplayName("Debe establecer y obtener correctamente el número consecutivo")
    void testSetAndGetConsecutiveNumber() {
        // Arrange
        Recipe recipe = new Recipe();
        Long consecutiveNumber = 10L;

        // Act
        recipe.setConsecutiveNumber(consecutiveNumber);

        // Assert
        assertEquals(consecutiveNumber, recipe.getConsecutiveNumber());
    }

    @Test
    @DisplayName("Debe establecer y obtener correctamente el título")
    void testSetAndGetTitle() {
        // Arrange
        Recipe recipe = new Recipe();
        String title = "Tortilla Española";

        // Act
        recipe.setTitle(title);

        // Assert
        assertEquals(title, recipe.getTitle());
    }

    @Test
    @DisplayName("Debe establecer y obtener correctamente los ingredientes")
    void testSetAndGetIngredients() {
        // Arrange
        Recipe recipe = new Recipe();
        List<String> newIngredients = Arrays.asList("Huevo", "Patata", "Sal");

        // Act
        recipe.setIngredients(newIngredients);

        // Assert
        assertEquals(newIngredients, recipe.getIngredients());
        assertEquals(3, recipe.getIngredients().size());
    }

    @Test
    @DisplayName("Debe establecer y obtener correctamente los pasos de preparación")
    void testSetAndGetPreparationSteps() {
        // Arrange
        Recipe recipe = new Recipe();
        List<String> newSteps = Arrays.asList("Batir", "Cocinar", "Servir");

        // Act
        recipe.setPreparationSteps(newSteps);

        // Assert
        assertEquals(newSteps, recipe.getPreparationSteps());
        assertEquals(3, recipe.getPreparationSteps().size());
    }

    @Test
    @DisplayName("Debe establecer y obtener correctamente el nombre del chef")
    void testSetAndGetChefName() {
        // Arrange
        Recipe recipe = new Recipe();
        String chefName = "Ana López";

        // Act
        recipe.setChefName(chefName);

        // Assert
        assertEquals(chefName, recipe.getChefName());
    }

    @Test
    @DisplayName("Debe establecer y obtener correctamente el tipo de receta")
    void testSetAndGetRecipeType() {
        // Arrange
        Recipe recipe = new Recipe();
        String recipeType = "CHEF";

        // Act
        recipe.setRecipeType(recipeType);

        // Assert
        assertEquals(recipeType, recipe.getRecipeType());
    }

    @Test
    @DisplayName("Debe establecer y obtener correctamente la temporada")
    void testSetAndGetSeason() {
        // Arrange
        Recipe recipe = new Recipe();
        Integer season = 5;

        // Act
        recipe.setSeason(season);

        // Assert
        assertEquals(season, recipe.getSeason());
    }

    @Test
    @DisplayName("Debe establecer y obtener correctamente la fecha de creación")
    void testSetAndGetCreatedAt() {
        // Arrange
        Recipe recipe = new Recipe();
        LocalDateTime createdAt = LocalDateTime.now();

        // Act
        recipe.setCreatedAt(createdAt);

        // Assert
        assertEquals(createdAt, recipe.getCreatedAt());
    }

    @Test
    @DisplayName("Debe establecer y obtener correctamente la fecha de actualización")
    void testSetAndGetUpdatedAt() {
        // Arrange
        Recipe recipe = new Recipe();
        LocalDateTime updatedAt = LocalDateTime.now();

        // Act
        recipe.setUpdatedAt(updatedAt);

        // Assert
        assertEquals(updatedAt, recipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debe verificar igualdad entre dos recetas con los mismos datos")
    void testEquals() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        Recipe recipe1 = new Recipe("1", 1L, "Paella", ingredients, preparationSteps, 
                                    "Chef1", "VIEWER", null, now, now);
        Recipe recipe2 = new Recipe("1", 1L, "Paella", ingredients, preparationSteps, 
                                    "Chef1", "VIEWER", null, now, now);

        // Assert
        assertEquals(recipe1, recipe2);
        assertEquals(recipe1.hashCode(), recipe2.hashCode());
    }

    @Test
    @DisplayName("Debe verificar desigualdad entre dos recetas con datos diferentes")
    void testNotEquals() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        Recipe recipe1 = new Recipe("1", 1L, "Paella", ingredients, preparationSteps, 
                                    "Chef1", "VIEWER", null, now, now);
        Recipe recipe2 = new Recipe("2", 2L, "Gazpacho", ingredients, preparationSteps, 
                                    "Chef2", "CHEF", null, now, now);

        // Assert
        assertNotEquals(recipe1, recipe2);
    }

    @Test
    @DisplayName("Debe generar correctamente el método toString")
    void testToString() {
        // Arrange
        Recipe recipe = new Recipe("Paella", ingredients, preparationSteps, "Chef1", "VIEWER");

        // Act
        String result = recipe.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("Paella"));
        assertTrue(result.contains("Chef1"));
        assertTrue(result.contains("VIEWER"));
    }

    @Test
    @DisplayName("Debe poder modificar una receta existente")
    void testModifyRecipe() {
        // Arrange
        Recipe recipe = new Recipe("Paella", ingredients, preparationSteps, "Chef1", "VIEWER");
        
        // Act
        recipe.setTitle("Paella Modificada");
        recipe.setChefName("Nuevo Chef");
        recipe.setConsecutiveNumber(99L);

        // Assert
        assertEquals("Paella Modificada", recipe.getTitle());
        assertEquals("Nuevo Chef", recipe.getChefName());
        assertEquals(99L, recipe.getConsecutiveNumber());
    }

    @Test
    @DisplayName("Debe manejar correctamente valores null en los campos opcionales")
    void testNullValues() {
        // Arrange & Act
        Recipe recipe = new Recipe();
        recipe.setId(null);
        recipe.setSeason(null);
        recipe.setCreatedAt(null);

        // Assert
        assertNull(recipe.getId());
        assertNull(recipe.getSeason());
        assertNull(recipe.getCreatedAt());
    }
}
