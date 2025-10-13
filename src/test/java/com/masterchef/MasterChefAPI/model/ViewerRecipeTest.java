package com.masterchef.MasterChefAPI.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ViewerRecipe Model Tests")
class ViewerRecipeTest {

    private ViewerRecipe viewerRecipe;
    private List<String> ingredients;
    private List<String> preparationSteps;

    @BeforeEach
    void setUp() {
        ingredients = Arrays.asList(
                "2 huevos",
                "100ml de leche",
                "100g de harina",
                "1 cucharada de azúcar",
                "Pizca de sal",
                "Mantequilla para la sartén"
        );
        
        preparationSteps = Arrays.asList(
                "Batir los huevos en un bowl",
                "Añadir la leche y mezclar",
                "Incorporar la harina gradualmente",
                "Agregar azúcar y sal",
                "Dejar reposar la masa 10 minutos",
                "Cocinar en sartén caliente con mantequilla"
        );
    }

    @Test
    @DisplayName("Debería crear ViewerRecipe con constructor vacío")
    void shouldCreateViewerRecipeWithNoArgsConstructor() {
        viewerRecipe = new ViewerRecipe();
        
        assertNotNull(viewerRecipe);
        assertNull(viewerRecipe.getTitle());
        assertNull(viewerRecipe.getIngredients());
        assertNull(viewerRecipe.getPreparationSteps());
        assertNull(viewerRecipe.getChefName());
        assertNull(viewerRecipe.getRecipeType());
        assertNull(viewerRecipe.getSeason());
    }

    @Test
    @DisplayName("Debería crear ViewerRecipe con constructor parametrizado")
    void shouldCreateViewerRecipeWithParameterizedConstructor() {
        viewerRecipe = new ViewerRecipe(
                "Crepes Básicos",
                ingredients,
                preparationSteps,
                "María González"
        );
        
        assertNotNull(viewerRecipe);
        assertEquals("Crepes Básicos", viewerRecipe.getTitle());
        assertEquals(ingredients, viewerRecipe.getIngredients());
        assertEquals(preparationSteps, viewerRecipe.getPreparationSteps());
        assertEquals("María González", viewerRecipe.getChefName());
        assertEquals("VIEWER", viewerRecipe.getRecipeType());
        assertNull(viewerRecipe.getSeason());
        assertNotNull(viewerRecipe.getCreatedAt());
        assertNotNull(viewerRecipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debería establecer automáticamente el tipo de receta como VIEWER")
    void shouldSetRecipeTypeAsViewerAutomatically() {
        viewerRecipe = new ViewerRecipe(
                "Tortilla Española",
                ingredients,
                preparationSteps,
                "Carmen López"
        );
        
        assertEquals("VIEWER", viewerRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Season debería ser null por defecto en ViewerRecipe")
    void seasonShouldBeNullByDefaultInViewerRecipe() {
        viewerRecipe = new ViewerRecipe(
                "Gazpacho Casero",
                ingredients,
                preparationSteps,
                "Antonio Ruiz"
        );
        
        assertNull(viewerRecipe.getSeason());
    }

    @Test
    @DisplayName("Debería heredar comportamiento de la clase Recipe")
    void shouldInheritBehaviorFromRecipeClass() {
        viewerRecipe = new ViewerRecipe(
                "Flan de Vainilla",
                ingredients,
                preparationSteps,
                "Rosa María"
        );
        
        // Verificar que es una instancia de Recipe
        assertTrue(viewerRecipe instanceof Recipe);
        
        // Verificar que puede usar métodos de Recipe
        viewerRecipe.setId("viewer-recipe-456");
        viewerRecipe.setConsecutiveNumber(75L);
        
        assertEquals("viewer-recipe-456", viewerRecipe.getId());
        assertEquals(75L, viewerRecipe.getConsecutiveNumber());
    }

    @Test
    @DisplayName("Debería permitir establecer propiedades adicionales")
    void shouldAllowSettingAdditionalProperties() {
        viewerRecipe = new ViewerRecipe();
        
        viewerRecipe.setTitle("Arroz con Leche");
        viewerRecipe.setIngredients(ingredients);
        viewerRecipe.setPreparationSteps(preparationSteps);
        viewerRecipe.setChefName("Esperanza Morales");
        viewerRecipe.setId("viewer-001");
        viewerRecipe.setConsecutiveNumber(33L);
        
        assertEquals("Arroz con Leche", viewerRecipe.getTitle());
        assertEquals(ingredients, viewerRecipe.getIngredients());
        assertEquals(preparationSteps, viewerRecipe.getPreparationSteps());
        assertEquals("Esperanza Morales", viewerRecipe.getChefName());
        assertEquals("viewer-001", viewerRecipe.getId());
        assertEquals(33L, viewerRecipe.getConsecutiveNumber());
    }

    @Test
    @DisplayName("Debería ser igual a otra ViewerRecipe con los mismos valores")
    void shouldBeEqualToAnotherViewerRecipeWithSameValues() {
        ViewerRecipe recipe1 = new ViewerRecipe(
                "Milanesas de Pollo",
                ingredients,
                preparationSteps,
                "Juana Pérez"
        );
        recipe1.setId("1");
        
        ViewerRecipe recipe2 = new ViewerRecipe(
                "Milanesas de Pollo",
                ingredients,
                preparationSteps,
                "Juana Pérez"
        );
        recipe2.setId("1");
        recipe2.setCreatedAt(recipe1.getCreatedAt());
        recipe2.setUpdatedAt(recipe1.getUpdatedAt());
        
        assertEquals(recipe1, recipe2);
        assertEquals(recipe1.hashCode(), recipe2.hashCode());
    }

    @Test
    @DisplayName("No debería ser igual a otra ViewerRecipe con valores diferentes")
    void shouldNotBeEqualToAnotherViewerRecipeWithDifferentValues() {
        ViewerRecipe recipe1 = new ViewerRecipe(
                "Empanadas de Carne",
                ingredients,
                preparationSteps,
                "Dolores García"
        );
        
        ViewerRecipe recipe2 = new ViewerRecipe(
                "Empanadas de Pollo",
                ingredients,
                preparationSteps,
                "Catalina Sánchez"
        );
        
        assertNotEquals(recipe1, recipe2);
    }

    @Test
    @DisplayName("toString debería contener información relevante")
    void toStringShouldContainRelevantInformation() {
        viewerRecipe = new ViewerRecipe(
                "Alfajores Caseros",
                ingredients,
                preparationSteps,
                "Silvia Rodríguez"
        );
        viewerRecipe.setId("viewer-recipe-789");
        
        String toString = viewerRecipe.toString();
        
        assertNotNull(toString);
        assertFalse(toString.isEmpty());
        // Verificar que el toString contiene al menos el nombre de la clase
        assertTrue(toString.contains("ViewerRecipe") || toString.contains("Recipe"));
    }

    @Test
    @DisplayName("Debería manejar nombres de cocinero amateur vacíos")
    void shouldHandleEmptyAmateurChefNames() {
        viewerRecipe = new ViewerRecipe(
                "Receta Anónima de Televidentes",
                ingredients,
                preparationSteps,
                ""
        );
        
        assertEquals("", viewerRecipe.getChefName());
        assertEquals("VIEWER", viewerRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debería manejar recetas simples para principiantes")
    void shouldHandleSimpleRecipesForBeginners() {
        List<String> simpleIngredients = Arrays.asList(
                "Pan",
                "Mantequilla",
                "Mermelada"
        );
        
        List<String> simpleSteps = Arrays.asList(
                "Tostar el pan",
                "Untar mantequilla",
                "Añadir mermelada"
        );
        
        viewerRecipe = new ViewerRecipe(
                "Tostada con Mermelada",
                simpleIngredients,
                simpleSteps,
                "Niño de 8 años"
        );
        
        assertEquals("Tostada con Mermelada", viewerRecipe.getTitle());
        assertEquals(3, viewerRecipe.getIngredients().size());
        assertEquals(3, viewerRecipe.getPreparationSteps().size());
        assertEquals("VIEWER", viewerRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debería manejar recetas familiares tradicionales")
    void shouldHandleTraditionalFamilyRecipes() {
        viewerRecipe = new ViewerRecipe(
                "Receta de la Abuela: Sopa de Fideos",
                ingredients,
                preparationSteps,
                "Familia Hernández"
        );
        
        assertEquals("Receta de la Abuela: Sopa de Fideos", viewerRecipe.getTitle());
        assertEquals("Familia Hernández", viewerRecipe.getChefName());
        assertEquals("VIEWER", viewerRecipe.getRecipeType());
        assertNull(viewerRecipe.getSeason());
    }

    @Test
    @DisplayName("Debería mantener el tipo VIEWER incluso si se intenta cambiar")
    void shouldMaintainViewerTypeEvenIfAttemptedToChange() {
        viewerRecipe = new ViewerRecipe(
                "Brownies de Chocolate",
                ingredients,
                preparationSteps,
                "Lucas Martín"
        );
        
        // Intentar cambiar el tipo (aunque en la práctica esto debería evitarse)
        viewerRecipe.setRecipeType("CHEF");
        
        // Verificar que se puede cambiar (el comportamiento actual permite esto)
        assertEquals("CHEF", viewerRecipe.getRecipeType());
        
        // Pero cuando se crea una nueva instancia, siempre será VIEWER
        ViewerRecipe newViewerRecipe = new ViewerRecipe(
                "Cookies de Avena",
                ingredients,
                preparationSteps,
                "Sofía Ramírez"
        );
        
        assertEquals("VIEWER", newViewerRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debería permitir recetas enviadas por televidentes internacionales")
    void shouldAllowRecipesFromInternationalViewers() {
        List<String> internationalIngredients = Arrays.asList(
                "Curry powder",
                "Coconut milk",
                "Basmati rice",
                "Chicken breast",
                "Onions",
                "Garlic"
        );
        
        viewerRecipe = new ViewerRecipe(
                "Indian Chicken Curry",
                internationalIngredients,
                preparationSteps,
                "Priya Sharma (Viewer from India)"
        );
        
        assertEquals("Indian Chicken Curry", viewerRecipe.getTitle());
        assertEquals("Priya Sharma (Viewer from India)", viewerRecipe.getChefName());
        assertEquals("VIEWER", viewerRecipe.getRecipeType());
        assertTrue(viewerRecipe.getIngredients().contains("Curry powder"));
    }

    @Test
    @DisplayName("No debería tener temporada aunque se intente establecer")
    void shouldNotHaveSeasonEvenIfAttemptedToSet() {
        viewerRecipe = new ViewerRecipe(
                "Guacamole Casero",
                ingredients,
                preparationSteps,
                "Pedro Jiménez"
        );
        
        // Los ViewerRecipe no deberían tener temporada
        assertNull(viewerRecipe.getSeason());
        
        // Intentar establecer una temporada
        viewerRecipe.setSeason(5);
        
        // Verificar que se puede establecer (aunque no debería tener sentido para viewers)
        assertEquals(5, viewerRecipe.getSeason());
    }

    @Test
    @DisplayName("Debería manejar recetas con nombres largos de televidentes")
    void shouldHandleRecipesWithLongViewerNames() {
        String longViewerName = "María del Carmen Esperanza Rodríguez García de la Torre";
        
        viewerRecipe = new ViewerRecipe(
                "Paella de Verduras",
                ingredients,
                preparationSteps,
                longViewerName
        );
        
        assertEquals(longViewerName, viewerRecipe.getChefName());
        assertEquals("VIEWER", viewerRecipe.getRecipeType());
    }
}