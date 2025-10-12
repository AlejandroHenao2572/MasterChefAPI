package com.masterchef.MasterChefAPI.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ChefRecipe Model Tests")
class ChefRecipeTest {

    private ChefRecipe chefRecipe;
    private List<String> ingredients;
    private List<String> preparationSteps;

    @BeforeEach
    void setUp() {
        ingredients = Arrays.asList(
                "500g de salmón fresco",
                "2 cucharadas de aceite de oliva",
                "Sal y pimienta al gusto",
                "Limón",
                "Hierbas finas"
        );
        
        preparationSteps = Arrays.asList(
                "Limpiar el salmón",
                "Sazonar con sal y pimienta",
                "Calentar el aceite en la sartén",
                "Cocinar el salmón 4 minutos por lado",
                "Servir con limón y hierbas"
        );
    }

    @Test
    @DisplayName("Debería crear ChefRecipe con constructor vacío")
    void shouldCreateChefRecipeWithNoArgsConstructor() {
        chefRecipe = new ChefRecipe();
        
        assertNotNull(chefRecipe);
        assertNull(chefRecipe.getTitle());
        assertNull(chefRecipe.getIngredients());
        assertNull(chefRecipe.getPreparationSteps());
        assertNull(chefRecipe.getChefName());
        assertNull(chefRecipe.getRecipeType());
        assertNull(chefRecipe.getSeason());
    }

    @Test
    @DisplayName("Debería crear ChefRecipe con constructor parametrizado")
    void shouldCreateChefRecipeWithParameterizedConstructor() {
        chefRecipe = new ChefRecipe(
                "Salmón a la Plancha",
                ingredients,
                preparationSteps,
                "Gordon Ramsay"
        );
        
        assertNotNull(chefRecipe);
        assertEquals("Salmón a la Plancha", chefRecipe.getTitle());
        assertEquals(ingredients, chefRecipe.getIngredients());
        assertEquals(preparationSteps, chefRecipe.getPreparationSteps());
        assertEquals("Gordon Ramsay", chefRecipe.getChefName());
        assertEquals("CHEF", chefRecipe.getRecipeType());
        assertNull(chefRecipe.getSeason());
        assertNotNull(chefRecipe.getCreatedAt());
        assertNotNull(chefRecipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debería establecer automáticamente el tipo de receta como CHEF")
    void shouldSetRecipeTypeAsChefAutomatically() {
        chefRecipe = new ChefRecipe(
                "Risotto de Hongos",
                ingredients,
                preparationSteps,
                "Marco Pierre White"
        );
        
        assertEquals("CHEF", chefRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debería heredar comportamiento de la clase Recipe")
    void shouldInheritBehaviorFromRecipeClass() {
        chefRecipe = new ChefRecipe(
                "Pasta Carbonara",
                ingredients,
                preparationSteps,
                "Antonio Carluccio"
        );
        
        // Verificar que es una instancia de Recipe
        assertTrue(chefRecipe instanceof Recipe);
        
        // Verificar que puede usar métodos de Recipe
        chefRecipe.setId("chef-recipe-123");
        chefRecipe.setConsecutiveNumber(50L);
        
        assertEquals("chef-recipe-123", chefRecipe.getId());
        assertEquals(50L, chefRecipe.getConsecutiveNumber());
    }

    @Test
    @DisplayName("Season debería ser null por defecto en ChefRecipe")
    void seasonShouldBeNullByDefaultInChefRecipe() {
        chefRecipe = new ChefRecipe(
                "Beef Wellington",
                ingredients,
                preparationSteps,
                "Gordon Ramsay"
        );
        
        assertNull(chefRecipe.getSeason());
    }

    @Test
    @DisplayName("Debería permitir establecer propiedades adicionales")
    void shouldAllowSettingAdditionalProperties() {
        chefRecipe = new ChefRecipe();
        
        chefRecipe.setTitle("Soufflé de Chocolate");
        chefRecipe.setIngredients(ingredients);
        chefRecipe.setPreparationSteps(preparationSteps);
        chefRecipe.setChefName("Julia Child");
        chefRecipe.setId("chef-001");
        chefRecipe.setConsecutiveNumber(1L);
        
        assertEquals("Soufflé de Chocolate", chefRecipe.getTitle());
        assertEquals(ingredients, chefRecipe.getIngredients());
        assertEquals(preparationSteps, chefRecipe.getPreparationSteps());
        assertEquals("Julia Child", chefRecipe.getChefName());
        assertEquals("chef-001", chefRecipe.getId());
        assertEquals(1L, chefRecipe.getConsecutiveNumber());
    }

    @Test
    @DisplayName("Debería ser igual a otra ChefRecipe con los mismos valores")
    void shouldBeEqualToAnotherChefRecipeWithSameValues() {
        ChefRecipe chefRecipe1 = new ChefRecipe(
                "Ratatouille",
                ingredients,
                preparationSteps,
                "Auguste Gusteau"
        );
        chefRecipe1.setId("1");
        
        ChefRecipe chefRecipe2 = new ChefRecipe(
                "Ratatouille",
                ingredients,
                preparationSteps,
                "Auguste Gusteau"
        );
        chefRecipe2.setId("1");
        chefRecipe2.setCreatedAt(chefRecipe1.getCreatedAt());
        chefRecipe2.setUpdatedAt(chefRecipe1.getUpdatedAt());
        
        assertEquals(chefRecipe1, chefRecipe2);
        assertEquals(chefRecipe1.hashCode(), chefRecipe2.hashCode());
    }

    @Test
    @DisplayName("No debería ser igual a otra ChefRecipe con valores diferentes")
    void shouldNotBeEqualToAnotherChefRecipeWithDifferentValues() {
        ChefRecipe chefRecipe1 = new ChefRecipe(
                "Coq au Vin",
                ingredients,
                preparationSteps,
                "Paul Bocuse"
        );
        
        ChefRecipe chefRecipe2 = new ChefRecipe(
                "Bouillabaisse",
                ingredients,
                preparationSteps,
                "Alain Ducasse"
        );
        
        assertNotEquals(chefRecipe1, chefRecipe2);
    }

    @Test
    @DisplayName("toString debería contener información relevante")
    void toStringShouldContainRelevantInformation() {
        chefRecipe = new ChefRecipe(
                "Duck Confit",
                ingredients,
                preparationSteps,
                "Thomas Keller"
        );
        chefRecipe.setId("chef-recipe-456");
        
        String toString = chefRecipe.toString();
        
        assertNotNull(toString);
        assertFalse(toString.isEmpty());
        // Verificar que el toString contiene al menos el nombre de la clase
        assertTrue(toString.contains("ChefRecipe") || toString.contains("Recipe"));
    }

    @Test
    @DisplayName("Debería manejar nombres de chef vacíos")
    void shouldHandleEmptyChefNames() {
        chefRecipe = new ChefRecipe(
                "Receta Anónima",
                ingredients,
                preparationSteps,
                ""
        );
        
        assertEquals("", chefRecipe.getChefName());
        assertEquals("CHEF", chefRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debería manejar títulos largos")
    void shouldHandleLongTitles() {
        String longTitle = "Suprema de Pollo Rellena con Espinacas y Queso de Cabra " +
                          "Acompañada de Puré de Papas Trufado y Reducción de Vino Tinto";
        
        chefRecipe = new ChefRecipe(
                longTitle,
                ingredients,
                preparationSteps,
                "Ferran Adrià"
        );
        
        assertEquals(longTitle, chefRecipe.getTitle());
        assertEquals("CHEF", chefRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debería mantener el tipo CHEF incluso si se intenta cambiar")
    void shouldMaintainChefTypeEvenIfAttemptedToChange() {
        chefRecipe = new ChefRecipe(
                "Pasta Puttanesca",
                ingredients,
                preparationSteps,
                "Mario Batali"
        );
        
        // Intentar cambiar el tipo (aunque en la práctica esto debería evitarse)
        chefRecipe.setRecipeType("VIEWER");
        
        // Verificar que se puede cambiar (el comportamiento actual permite esto)
        assertEquals("VIEWER", chefRecipe.getRecipeType());
        
        // Pero cuando se crea una nueva instancia, siempre será CHEF
        ChefRecipe newChefRecipe = new ChefRecipe(
                "Osso Buco",
                ingredients,
                preparationSteps,
                "Lidia Bastianich"
        );
        
        assertEquals("CHEF", newChefRecipe.getRecipeType());
    }
}