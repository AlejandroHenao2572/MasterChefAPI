package com.masterchef.MasterChefAPI.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para ContestantRecipe")
class ContestantRecipeTest {

    private List<String> ingredients;
    private List<String> preparationSteps;

    @BeforeEach
    void setUp() {
        ingredients = Arrays.asList("Arroz", "Pollo", "Pimiento", "Azafrán");
        preparationSteps = Arrays.asList("Sofreír el pollo", "Agregar el arroz", "Cocinar a fuego lento");
    }

    @Test
    @DisplayName("Debe crear una receta de participante con el constructor parametrizado")
    void testContestantRecipeConstructor() {
        // Arrange
        String title = "Arroz con Pollo";
        String chefName = "Pedro Martínez";
        Integer season = 3;

        // Act
        ContestantRecipe contestantRecipe = new ContestantRecipe(title, ingredients, preparationSteps, chefName, season);

        // Assert
        assertNotNull(contestantRecipe);
        assertEquals(title, contestantRecipe.getTitle());
        assertEquals(ingredients, contestantRecipe.getIngredients());
        assertEquals(preparationSteps, contestantRecipe.getPreparationSteps());
        assertEquals(chefName, contestantRecipe.getChefName());
        assertEquals("CONTESTANT", contestantRecipe.getRecipeType());
        assertEquals(season, contestantRecipe.getSeason());
        assertNotNull(contestantRecipe.getCreatedAt());
        assertNotNull(contestantRecipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debe crear una receta de participante vacía con el constructor sin argumentos")
    void testContestantRecipeNoArgsConstructor() {
        // Act
        ContestantRecipe contestantRecipe = new ContestantRecipe();

        // Assert
        assertNotNull(contestantRecipe);
        assertNull(contestantRecipe.getTitle());
        assertNull(contestantRecipe.getIngredients());
        assertNull(contestantRecipe.getPreparationSteps());
        assertNull(contestantRecipe.getChefName());
        assertNull(contestantRecipe.getRecipeType());
        assertNull(contestantRecipe.getSeason());
    }

    @Test
    @DisplayName("Debe establecer el tipo de receta como CONTESTANT automáticamente")
    void testRecipeTypeIsContestant() {
        // Arrange & Act
        ContestantRecipe contestantRecipe = new ContestantRecipe("Lasagna", ingredients, preparationSteps, "Ana Silva", 1);

        // Assert
        assertEquals("CONTESTANT", contestantRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debe heredar correctamente de la clase Recipe")
    void testInheritanceFromRecipe() {
        // Arrange & Act
        ContestantRecipe contestantRecipe = new ContestantRecipe("Tacos al Pastor", ingredients, preparationSteps, "Luis Gómez", 2);

        // Assert
        assertTrue(contestantRecipe instanceof Recipe);
        assertNotNull(contestantRecipe.getCreatedAt());
        assertNotNull(contestantRecipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debe almacenar correctamente la temporada")
    void testSeasonStorage() {
        // Arrange
        Integer season = 5;

        // Act
        ContestantRecipe contestantRecipe = new ContestantRecipe("Ceviche", ingredients, preparationSteps, "María López", season);

        // Assert
        assertEquals(season, contestantRecipe.getSeason());
        assertNotNull(contestantRecipe.getSeason());
    }

    @Test
    @DisplayName("Debe poder modificar los atributos heredados")
    void testModifyInheritedAttributes() {
        // Arrange
        ContestantRecipe contestantRecipe = new ContestantRecipe("Empanadas", ingredients, preparationSteps, "Carlos Ruiz", 4);
        
        // Act
        contestantRecipe.setConsecutiveNumber(20L);
        contestantRecipe.setId("contestant456");
        contestantRecipe.setTitle("Empanadas Argentinas");
        contestantRecipe.setSeason(6);

        // Assert
        assertEquals(20L, contestantRecipe.getConsecutiveNumber());
        assertEquals("contestant456", contestantRecipe.getId());
        assertEquals("Empanadas Argentinas", contestantRecipe.getTitle());
        assertEquals(6, contestantRecipe.getSeason());
    }

    @Test
    @DisplayName("Debe crear recetas de diferentes temporadas")
    void testDifferentSeasons() {
        // Arrange & Act
        ContestantRecipe recipe1 = new ContestantRecipe("Receta T1", ingredients, preparationSteps, "Chef1", 1);
        ContestantRecipe recipe2 = new ContestantRecipe("Receta T2", ingredients, preparationSteps, "Chef2", 2);
        ContestantRecipe recipe3 = new ContestantRecipe("Receta T3", ingredients, preparationSteps, "Chef3", 3);

        // Assert
        assertEquals(1, recipe1.getSeason());
        assertEquals(2, recipe2.getSeason());
        assertEquals(3, recipe3.getSeason());
        assertNotEquals(recipe1.getSeason(), recipe2.getSeason());
    }

    @Test
    @DisplayName("Debe verificar igualdad entre dos recetas de participante con los mismos datos")
    void testEquals() {
        // Arrange
        ContestantRecipe recipe1 = new ContestantRecipe("Pozole", ingredients, preparationSteps, "Sandra Torres", 2);
        ContestantRecipe recipe2 = new ContestantRecipe("Pozole", ingredients, preparationSteps, "Sandra Torres", 2);
        
        recipe1.setId("1");
        recipe2.setId("1");
        recipe1.setConsecutiveNumber(1L);
        recipe2.setConsecutiveNumber(1L);

        // Assert
        assertEquals(recipe1, recipe2);
    }

    @Test
    @DisplayName("Debe verificar desigualdad entre dos recetas de participante con datos diferentes")
    void testNotEquals() {
        // Arrange
        ContestantRecipe recipe1 = new ContestantRecipe("Mole Poblano", ingredients, preparationSteps, "Roberto Díaz", 1);
        ContestantRecipe recipe2 = new ContestantRecipe("Chiles en Nogada", ingredients, preparationSteps, "Patricia Vega", 2);
        
        recipe1.setId("1");
        recipe2.setId("2");

        // Assert
        assertNotEquals(recipe1, recipe2);
    }

    @Test
    @DisplayName("Debe verificar desigualdad entre recetas de la misma temporada pero diferente título")
    void testNotEqualsSameSeason() {
        // Arrange
        ContestantRecipe recipe1 = new ContestantRecipe("Tamales", ingredients, preparationSteps, "Juan Pérez", 3);
        ContestantRecipe recipe2 = new ContestantRecipe("Quesadillas", ingredients, preparationSteps, "María García", 3);

        // Assert
        assertNotEquals(recipe1.getTitle(), recipe2.getTitle());
        assertEquals(recipe1.getSeason(), recipe2.getSeason());
    }

    @Test
    @DisplayName("Debe generar correctamente el método toString")
    void testToString() {
        // Arrange
        ContestantRecipe contestantRecipe = new ContestantRecipe("Enchiladas", ingredients, preparationSteps, "Laura Méndez", 4);

        // Act
        String result = contestantRecipe.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("Enchiladas"));
        assertTrue(result.contains("Laura Méndez"));
        assertTrue(result.contains("CONTESTANT"));
        assertTrue(result.contains("4"));
    }

    @Test
    @DisplayName("Debe tener el mismo hashCode para recetas iguales")
    void testHashCode() {
        // Arrange
        ContestantRecipe recipe1 = new ContestantRecipe("Flan", ingredients, preparationSteps, "Diego Castro", 1);
        ContestantRecipe recipe2 = new ContestantRecipe("Flan", ingredients, preparationSteps, "Diego Castro", 1);
        
        recipe1.setId("1");
        recipe2.setId("1");

        // Assert
        assertEquals(recipe1.hashCode(), recipe2.hashCode());
    }

    @Test
    @DisplayName("Debe permitir crear múltiples recetas de participantes")
    void testMultipleContestantRecipes() {
        // Arrange & Act
        ContestantRecipe recipe1 = new ContestantRecipe("Sopa Azteca", ingredients, preparationSteps, "Participante A", 1);
        ContestantRecipe recipe2 = new ContestantRecipe("Cochinita Pibil", ingredients, preparationSteps, "Participante B", 2);
        ContestantRecipe recipe3 = new ContestantRecipe("Aguachile", ingredients, preparationSteps, "Participante C", 3);

        // Assert
        assertNotNull(recipe1);
        assertNotNull(recipe2);
        assertNotNull(recipe3);
        assertEquals("CONTESTANT", recipe1.getRecipeType());
        assertEquals("CONTESTANT", recipe2.getRecipeType());
        assertEquals("CONTESTANT", recipe3.getRecipeType());
        assertNotEquals(recipe1.getSeason(), recipe2.getSeason());
    }

    @Test
    @DisplayName("Debe mantener la lista de ingredientes intacta")
    void testIngredientsListIntegrity() {
        // Arrange
        ContestantRecipe contestantRecipe = new ContestantRecipe("Barbacoa", ingredients, preparationSteps, "Héctor Ramírez", 2);

        // Act
        List<String> retrievedIngredients = contestantRecipe.getIngredients();

        // Assert
        assertEquals(ingredients.size(), retrievedIngredients.size());
        assertEquals(ingredients, retrievedIngredients);
    }

    @Test
    @DisplayName("Debe mantener la lista de pasos de preparación intacta")
    void testPreparationStepsListIntegrity() {
        // Arrange
        ContestantRecipe contestantRecipe = new ContestantRecipe("Birria", ingredients, preparationSteps, "Sofía Hernández", 1);

        // Act
        List<String> retrievedSteps = contestantRecipe.getPreparationSteps();

        // Assert
        assertEquals(preparationSteps.size(), retrievedSteps.size());
        assertEquals(preparationSteps, retrievedSteps);
    }

    @Test
    @DisplayName("Debe permitir temporadas con valores altos")
    void testHighSeasonNumber() {
        // Arrange
        Integer highSeason = 99;

        // Act
        ContestantRecipe contestantRecipe = new ContestantRecipe("Receta Futura", ingredients, preparationSteps, "Chef Futuro", highSeason);

        // Assert
        assertEquals(highSeason, contestantRecipe.getSeason());
    }

    @Test
    @DisplayName("Debe permitir temporada 1 como valor mínimo común")
    void testSeasonOne() {
        // Arrange & Act
        ContestantRecipe contestantRecipe = new ContestantRecipe("Primera Receta", ingredients, preparationSteps, "Primer Participante", 1);

        // Assert
        assertEquals(1, contestantRecipe.getSeason());
    }
}
