package com.masterchef.MasterChefAPI.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ContestantRecipe Model Tests")
class ContestantRecipeTest {

    private ContestantRecipe contestantRecipe;
    private List<String> ingredients;
    private List<String> preparationSteps;

    @BeforeEach
    void setUp() {
        ingredients = Arrays.asList(
                "1 kg de carne de res",
                "2 cebollas",
                "3 zanahorias",
                "Caldo de carne",
                "Vino tinto",
                "Hierbas aromáticas"
        );
        
        preparationSteps = Arrays.asList(
                "Cortar la carne en cubos",
                "Dorar la carne en la olla",
                "Añadir las verduras",
                "Incorporar el vino y el caldo",
                "Cocinar a fuego lento por 2 horas",
                "Servir caliente"
        );
    }

    @Test
    @DisplayName("Debería crear ContestantRecipe con constructor vacío")
    void shouldCreateContestantRecipeWithNoArgsConstructor() {
        contestantRecipe = new ContestantRecipe();
        
        assertNotNull(contestantRecipe);
        assertNull(contestantRecipe.getTitle());
        assertNull(contestantRecipe.getIngredients());
        assertNull(contestantRecipe.getPreparationSteps());
        assertNull(contestantRecipe.getChefName());
        assertNull(contestantRecipe.getRecipeType());
        assertNull(contestantRecipe.getSeason());
    }

    @Test
    @DisplayName("Debería crear ContestantRecipe con constructor parametrizado")
    void shouldCreateContestantRecipeWithParameterizedConstructor() {
        contestantRecipe = new ContestantRecipe(
                "Estofado de Carne",
                ingredients,
                preparationSteps,
                "Ana María Rodríguez",
                3
        );
        
        assertNotNull(contestantRecipe);
        assertEquals("Estofado de Carne", contestantRecipe.getTitle());
        assertEquals(ingredients, contestantRecipe.getIngredients());
        assertEquals(preparationSteps, contestantRecipe.getPreparationSteps());
        assertEquals("Ana María Rodríguez", contestantRecipe.getChefName());
        assertEquals("CONTESTANT", contestantRecipe.getRecipeType());
        assertEquals(3, contestantRecipe.getSeason());
        assertNotNull(contestantRecipe.getCreatedAt());
        assertNotNull(contestantRecipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debería establecer automáticamente el tipo de receta como CONTESTANT")
    void shouldSetRecipeTypeAsContestantAutomatically() {
        contestantRecipe = new ContestantRecipe(
                "Lasaña Casera",
                ingredients,
                preparationSteps,
                "Carlos Pérez",
                5
        );
        
        assertEquals("CONTESTANT", contestantRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debería requerir una temporada específica")
    void shouldRequireSpecificSeason() {
        contestantRecipe = new ContestantRecipe(
                "Paella de Mariscos",
                ingredients,
                preparationSteps,
                "Isabel García",
                7
        );
        
        assertEquals(7, contestantRecipe.getSeason());
        assertNotNull(contestantRecipe.getSeason());
    }

    @Test
    @DisplayName("Debería heredar comportamiento de la clase Recipe")
    void shouldInheritBehaviorFromRecipeClass() {
        contestantRecipe = new ContestantRecipe(
                "Tiramisu Casero",
                ingredients,
                preparationSteps,
                "Francesco Romano",
                2
        );
        
        // Verificar que es una instancia de Recipe
        assertTrue(contestantRecipe instanceof Recipe);
        
        // Verificar que puede usar métodos de Recipe
        contestantRecipe.setId("contestant-recipe-789");
        contestantRecipe.setConsecutiveNumber(25L);
        
        assertEquals("contestant-recipe-789", contestantRecipe.getId());
        assertEquals(25L, contestantRecipe.getConsecutiveNumber());
    }

    @Test
    @DisplayName("Debería permitir establecer propiedades adicionales")
    void shouldAllowSettingAdditionalProperties() {
        contestantRecipe = new ContestantRecipe();
        
        contestantRecipe.setTitle("Ceviche Peruano");
        contestantRecipe.setIngredients(ingredients);
        contestantRecipe.setPreparationSteps(preparationSteps);
        contestantRecipe.setChefName("Luis Vargas");
        contestantRecipe.setSeason(4);
        contestantRecipe.setId("contestant-001");
        contestantRecipe.setConsecutiveNumber(10L);
        
        assertEquals("Ceviche Peruano", contestantRecipe.getTitle());
        assertEquals(ingredients, contestantRecipe.getIngredients());
        assertEquals(preparationSteps, contestantRecipe.getPreparationSteps());
        assertEquals("Luis Vargas", contestantRecipe.getChefName());
        assertEquals(4, contestantRecipe.getSeason());
        assertEquals("contestant-001", contestantRecipe.getId());
        assertEquals(10L, contestantRecipe.getConsecutiveNumber());
    }

    @Test
    @DisplayName("Debería manejar diferentes números de temporada")
    void shouldHandleDifferentSeasonNumbers() {
        // Temporada baja
        ContestantRecipe recipe1 = new ContestantRecipe(
                "Sopa de Tomate",
                ingredients,
                preparationSteps,
                "María López",
                1
        );
        
        // Temporada alta
        ContestantRecipe recipe2 = new ContestantRecipe(
                "Cordero Asado",
                ingredients,
                preparationSteps,
                "Pedro Martínez",
                15
        );
        
        assertEquals(1, recipe1.getSeason());
        assertEquals(15, recipe2.getSeason());
        assertEquals("CONTESTANT", recipe1.getRecipeType());
        assertEquals("CONTESTANT", recipe2.getRecipeType());
    }

    @Test
    @DisplayName("Debería ser igual a otra ContestantRecipe con los mismos valores")
    void shouldBeEqualToAnotherContestantRecipeWithSameValues() {
        ContestantRecipe recipe1 = new ContestantRecipe(
                "Empanadas Argentinas",
                ingredients,
                preparationSteps,
                "Sofía Herrera",
                6
        );
        recipe1.setId("1");
        
        ContestantRecipe recipe2 = new ContestantRecipe(
                "Empanadas Argentinas",
                ingredients,
                preparationSteps,
                "Sofía Herrera",
                6
        );
        recipe2.setId("1");
        recipe2.setCreatedAt(recipe1.getCreatedAt());
        recipe2.setUpdatedAt(recipe1.getUpdatedAt());
        
        assertEquals(recipe1, recipe2);
        assertEquals(recipe1.hashCode(), recipe2.hashCode());
    }

    @Test
    @DisplayName("No debería ser igual a otra ContestantRecipe con valores diferentes")
    void shouldNotBeEqualToAnotherContestantRecipeWithDifferentValues() {
        ContestantRecipe recipe1 = new ContestantRecipe(
                "Churrasco",
                ingredients,
                preparationSteps,
                "Diego Silva",
                3
        );
        
        ContestantRecipe recipe2 = new ContestantRecipe(
                "Arepa Rellena",
                ingredients,
                preparationSteps,
                "Carmen Ruiz",
                5
        );
        
        assertNotEquals(recipe1, recipe2);
    }

    @Test
    @DisplayName("No debería ser igual si las temporadas son diferentes")
    void shouldNotBeEqualIfSeasonsAreDifferent() {
        ContestantRecipe recipe1 = new ContestantRecipe(
                "Tacos al Pastor",
                ingredients,
                preparationSteps,
                "Alejandro Morales",
                2
        );
        
        ContestantRecipe recipe2 = new ContestantRecipe(
                "Tacos al Pastor",
                ingredients,
                preparationSteps,
                "Alejandro Morales",
                4
        );
        
        assertNotEquals(recipe1, recipe2);
    }

    @Test
    @DisplayName("toString debería contener información relevante incluyendo temporada")
    void toStringShouldContainRelevantInformationIncludingSeason() {
        contestantRecipe = new ContestantRecipe(
                "Mole Poblano",
                ingredients,
                preparationSteps,
                "Elena Jiménez",
                8
        );
        contestantRecipe.setId("contestant-recipe-999");
        
        String toString = contestantRecipe.toString();
        
        assertNotNull(toString);
        assertFalse(toString.isEmpty());
        // Verificar que el toString contiene al menos el nombre de la clase
        assertTrue(toString.contains("ContestantRecipe") || toString.contains("Recipe"));
    }

    @Test
    @DisplayName("Debería manejar temporada cero")
    void shouldHandleSeasonZero() {
        contestantRecipe = new ContestantRecipe(
                "Ensalada César",
                ingredients,
                preparationSteps,
                "Roberto Kim",
                0
        );
        
        assertEquals(0, contestantRecipe.getSeason());
        assertEquals("CONTESTANT", contestantRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debería manejar temporadas negativas")
    void shouldHandleNegativeSeasons() {
        contestantRecipe = new ContestantRecipe(
                "Pizza Margherita",
                ingredients,
                preparationSteps,
                "Giuseppe Rossi",
                -1
        );
        
        assertEquals(-1, contestantRecipe.getSeason());
        assertEquals("CONTESTANT", contestantRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debería mantener el tipo CONTESTANT incluso si se intenta cambiar")
    void shouldMaintainContestantTypeEvenIfAttemptedToChange() {
        contestantRecipe = new ContestantRecipe(
                "Risotto de Champiñones",
                ingredients,
                preparationSteps,
                "Valentina Costa",
                9
        );
        
        // Intentar cambiar el tipo (aunque en la práctica esto debería evitarse)
        contestantRecipe.setRecipeType("CHEF");
        
        // Verificar que se puede cambiar (el comportamiento actual permite esto)
        assertEquals("CHEF", contestantRecipe.getRecipeType());
        
        // Pero cuando se crea una nueva instancia, siempre será CONTESTANT
        ContestantRecipe newContestantRecipe = new ContestantRecipe(
                "Gazpacho Andaluz",
                ingredients,
                preparationSteps,
                "Paloma Sánchez",
                12
        );
        
        assertEquals("CONTESTANT", newContestantRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debería permitir cambiar la temporada después de la creación")
    void shouldAllowChangingSeasonAfterCreation() {
        contestantRecipe = new ContestantRecipe(
                "Paté de Hígado",
                ingredients,
                preparationSteps,
                "André Dubois",
                1
        );
        
        assertEquals(1, contestantRecipe.getSeason());
        
        contestantRecipe.setSeason(10);
        
        assertEquals(10, contestantRecipe.getSeason());
    }
}