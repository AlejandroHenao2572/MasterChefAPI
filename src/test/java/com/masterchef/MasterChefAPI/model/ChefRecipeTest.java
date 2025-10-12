package com.masterchef.MasterChefAPI.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para ChefRecipe")
class ChefRecipeTest {

    private List<String> ingredients;
    private List<String> preparationSteps;

    @BeforeEach
    void setUp() {
        ingredients = Arrays.asList("Salmón", "Mantequilla", "Limón");
        preparationSteps = Arrays.asList("Limpiar el pescado", "Sazonar", "Cocinar al horno");
    }

    @Test
    @DisplayName("Debe crear una receta de chef con el constructor parametrizado")
    void testChefRecipeConstructor() {
        // Arrange
        String title = "Salmón al Horno";
        String chefName = "Gordon Ramsay";

        // Act
        ChefRecipe chefRecipe = new ChefRecipe(title, ingredients, preparationSteps, chefName);

        // Assert
        assertNotNull(chefRecipe);
        assertEquals(title, chefRecipe.getTitle());
        assertEquals(ingredients, chefRecipe.getIngredients());
        assertEquals(preparationSteps, chefRecipe.getPreparationSteps());
        assertEquals(chefName, chefRecipe.getChefName());
        assertEquals("CHEF", chefRecipe.getRecipeType());
        assertNotNull(chefRecipe.getCreatedAt());
        assertNotNull(chefRecipe.getUpdatedAt());
        assertNull(chefRecipe.getSeason());
    }

    @Test
    @DisplayName("Debe crear una receta de chef vacía con el constructor sin argumentos")
    void testChefRecipeNoArgsConstructor() {
        // Act
        ChefRecipe chefRecipe = new ChefRecipe();

        // Assert
        assertNotNull(chefRecipe);
        assertNull(chefRecipe.getTitle());
        assertNull(chefRecipe.getIngredients());
        assertNull(chefRecipe.getPreparationSteps());
        assertNull(chefRecipe.getChefName());
        assertNull(chefRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debe establecer el tipo de receta como CHEF automáticamente")
    void testRecipeTypeIsChef() {
        // Arrange & Act
        ChefRecipe chefRecipe = new ChefRecipe("Risotto", ingredients, preparationSteps, "Jamie Oliver");

        // Assert
        assertEquals("CHEF", chefRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debe heredar correctamente de la clase Recipe")
    void testInheritanceFromRecipe() {
        // Arrange & Act
        ChefRecipe chefRecipe = new ChefRecipe("Beef Wellington", ingredients, preparationSteps, "Marco Pierre White");

        // Assert
        assertTrue(chefRecipe instanceof Recipe);
        assertNotNull(chefRecipe.getCreatedAt());
        assertNotNull(chefRecipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debe poder modificar los atributos heredados")
    void testModifyInheritedAttributes() {
        // Arrange
        ChefRecipe chefRecipe = new ChefRecipe("Pasta Carbonara", ingredients, preparationSteps, "Antonio Carluccio");
        
        // Act
        chefRecipe.setConsecutiveNumber(15L);
        chefRecipe.setId("chef123");
        chefRecipe.setTitle("Pasta Carbonara Modificada");

        // Assert
        assertEquals(15L, chefRecipe.getConsecutiveNumber());
        assertEquals("chef123", chefRecipe.getId());
        assertEquals("Pasta Carbonara Modificada", chefRecipe.getTitle());
    }

    @Test
    @DisplayName("Debe no tener temporada asignada")
    void testSeasonIsNull() {
        // Arrange & Act
        ChefRecipe chefRecipe = new ChefRecipe("Tarta Tatin", ingredients, preparationSteps, "Julia Child");

        // Assert
        assertNull(chefRecipe.getSeason());
    }

    @Test
    @DisplayName("Debe verificar igualdad entre dos recetas de chef con los mismos datos")
    void testEquals() {
        // Arrange
        ChefRecipe recipe1 = new ChefRecipe("Bouillabaisse", ingredients, preparationSteps, "Paul Bocuse");
        ChefRecipe recipe2 = new ChefRecipe("Bouillabaisse", ingredients, preparationSteps, "Paul Bocuse");
        
        recipe1.setId("1");
        recipe2.setId("1");
        recipe1.setConsecutiveNumber(1L);
        recipe2.setConsecutiveNumber(1L);

        // Assert
        assertEquals(recipe1, recipe2);
    }

    @Test
    @DisplayName("Debe verificar desigualdad entre dos recetas de chef con datos diferentes")
    void testNotEquals() {
        // Arrange
        ChefRecipe recipe1 = new ChefRecipe("Coq au Vin", ingredients, preparationSteps, "Auguste Escoffier");
        ChefRecipe recipe2 = new ChefRecipe("Duck Confit", ingredients, preparationSteps, "Alain Ducasse");
        
        recipe1.setId("1");
        recipe2.setId("2");

        // Assert
        assertNotEquals(recipe1, recipe2);
    }

    @Test
    @DisplayName("Debe generar correctamente el método toString")
    void testToString() {
        // Arrange
        ChefRecipe chefRecipe = new ChefRecipe("Ratatouille", ingredients, preparationSteps, "Thomas Keller");

        // Act
        String result = chefRecipe.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("Ratatouille"));
        assertTrue(result.contains("Thomas Keller"));
        assertTrue(result.contains("CHEF"));
    }

    @Test
    @DisplayName("Debe tener el mismo hashCode para recetas iguales")
    void testHashCode() {
        // Arrange
        ChefRecipe recipe1 = new ChefRecipe("Soufflé", ingredients, preparationSteps, "Marie-Antoine Carême");
        ChefRecipe recipe2 = new ChefRecipe("Soufflé", ingredients, preparationSteps, "Marie-Antoine Carême");
        
        recipe1.setId("1");
        recipe2.setId("1");

        // Assert
        assertEquals(recipe1.hashCode(), recipe2.hashCode());
    }

    @Test
    @DisplayName("Debe permitir crear múltiples recetas de chef con diferentes datos")
    void testMultipleChefRecipes() {
        // Arrange & Act
        ChefRecipe recipe1 = new ChefRecipe("Crème Brûlée", ingredients, preparationSteps, "Chef A");
        ChefRecipe recipe2 = new ChefRecipe("Foie Gras", ingredients, preparationSteps, "Chef B");
        ChefRecipe recipe3 = new ChefRecipe("Lobster Thermidor", ingredients, preparationSteps, "Chef C");

        // Assert
        assertNotNull(recipe1);
        assertNotNull(recipe2);
        assertNotNull(recipe3);
        assertEquals("CHEF", recipe1.getRecipeType());
        assertEquals("CHEF", recipe2.getRecipeType());
        assertEquals("CHEF", recipe3.getRecipeType());
        assertNotEquals(recipe1.getChefName(), recipe2.getChefName());
    }

    @Test
    @DisplayName("Debe mantener la lista de ingredientes intacta")
    void testIngredientsListIntegrity() {
        // Arrange
        ChefRecipe chefRecipe = new ChefRecipe("Turbot Pochado", ingredients, preparationSteps, "Ferran Adrià");

        // Act
        List<String> retrievedIngredients = chefRecipe.getIngredients();

        // Assert
        assertEquals(ingredients.size(), retrievedIngredients.size());
        assertEquals(ingredients, retrievedIngredients);
    }

    @Test
    @DisplayName("Debe mantener la lista de pasos de preparación intacta")
    void testPreparationStepsListIntegrity() {
        // Arrange
        ChefRecipe chefRecipe = new ChefRecipe("Caviar con Blinis", ingredients, preparationSteps, "Joël Robuchon");

        // Act
        List<String> retrievedSteps = chefRecipe.getPreparationSteps();

        // Assert
        assertEquals(preparationSteps.size(), retrievedSteps.size());
        assertEquals(preparationSteps, retrievedSteps);
    }
}
