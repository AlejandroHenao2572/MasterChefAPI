package com.masterchef.MasterChefAPI.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para ViewerRecipe")
class ViewerRecipeTest {

    private List<String> ingredients;
    private List<String> preparationSteps;

    @BeforeEach
    void setUp() {
        ingredients = Arrays.asList("Tomate", "Pepino", "Cebolla", "Aceite de oliva");
        preparationSteps = Arrays.asList("Picar los vegetales", "Mezclar en un bowl", "Aliñar con aceite");
    }

    @Test
    @DisplayName("Debe crear una receta de televidente con el constructor parametrizado")
    void testViewerRecipeConstructor() {
        // Arrange
        String title = "Ensalada Fresca";
        String chefName = "Carmen Rodríguez";

        // Act
        ViewerRecipe viewerRecipe = new ViewerRecipe(title, ingredients, preparationSteps, chefName);

        // Assert
        assertNotNull(viewerRecipe);
        assertEquals(title, viewerRecipe.getTitle());
        assertEquals(ingredients, viewerRecipe.getIngredients());
        assertEquals(preparationSteps, viewerRecipe.getPreparationSteps());
        assertEquals(chefName, viewerRecipe.getChefName());
        assertEquals("VIEWER", viewerRecipe.getRecipeType());
        assertNotNull(viewerRecipe.getCreatedAt());
        assertNotNull(viewerRecipe.getUpdatedAt());
        assertNull(viewerRecipe.getSeason());
    }

    @Test
    @DisplayName("Debe crear una receta de televidente vacía con el constructor sin argumentos")
    void testViewerRecipeNoArgsConstructor() {
        // Act
        ViewerRecipe viewerRecipe = new ViewerRecipe();

        // Assert
        assertNotNull(viewerRecipe);
        assertNull(viewerRecipe.getTitle());
        assertNull(viewerRecipe.getIngredients());
        assertNull(viewerRecipe.getPreparationSteps());
        assertNull(viewerRecipe.getChefName());
        assertNull(viewerRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debe establecer el tipo de receta como VIEWER automáticamente")
    void testRecipeTypeIsViewer() {
        // Arrange & Act
        ViewerRecipe viewerRecipe = new ViewerRecipe("Smoothie Verde", ingredients, preparationSteps, "Lucía Fernández");

        // Assert
        assertEquals("VIEWER", viewerRecipe.getRecipeType());
    }

    @Test
    @DisplayName("Debe heredar correctamente de la clase Recipe")
    void testInheritanceFromRecipe() {
        // Arrange & Act
        ViewerRecipe viewerRecipe = new ViewerRecipe("Guacamole Casero", ingredients, preparationSteps, "Miguel Ángel Soto");

        // Assert
        assertTrue(viewerRecipe instanceof Recipe);
        assertNotNull(viewerRecipe.getCreatedAt());
        assertNotNull(viewerRecipe.getUpdatedAt());
    }

    @Test
    @DisplayName("Debe no tener temporada asignada")
    void testSeasonIsNull() {
        // Arrange & Act
        ViewerRecipe viewerRecipe = new ViewerRecipe("Salsa Pico de Gallo", ingredients, preparationSteps, "Rosa María López");

        // Assert
        assertNull(viewerRecipe.getSeason());
    }

    @Test
    @DisplayName("Debe poder modificar los atributos heredados")
    void testModifyInheritedAttributes() {
        // Arrange
        ViewerRecipe viewerRecipe = new ViewerRecipe("Limonada Natural", ingredients, preparationSteps, "Gabriela Morales");
        
        // Act
        viewerRecipe.setConsecutiveNumber(25L);
        viewerRecipe.setId("viewer789");
        viewerRecipe.setTitle("Limonada con Hierbabuena");

        // Assert
        assertEquals(25L, viewerRecipe.getConsecutiveNumber());
        assertEquals("viewer789", viewerRecipe.getId());
        assertEquals("Limonada con Hierbabuena", viewerRecipe.getTitle());
    }

    @Test
    @DisplayName("Debe verificar igualdad entre dos recetas de televidente con los mismos datos")
    void testEquals() {
        // Arrange
        ViewerRecipe recipe1 = new ViewerRecipe("Agua de Jamaica", ingredients, preparationSteps, "Andrés Cruz");
        ViewerRecipe recipe2 = new ViewerRecipe("Agua de Jamaica", ingredients, preparationSteps, "Andrés Cruz");
        
        recipe1.setId("1");
        recipe2.setId("1");
        recipe1.setConsecutiveNumber(1L);
        recipe2.setConsecutiveNumber(1L);

        // Assert
        assertEquals(recipe1, recipe2);
    }

    @Test
    @DisplayName("Debe verificar desigualdad entre dos recetas de televidente con datos diferentes")
    void testNotEquals() {
        // Arrange
        ViewerRecipe recipe1 = new ViewerRecipe("Salsa Verde", ingredients, preparationSteps, "Fernando Ortiz");
        ViewerRecipe recipe2 = new ViewerRecipe("Salsa Roja", ingredients, preparationSteps, "Isabel Navarro");
        
        recipe1.setId("1");
        recipe2.setId("2");

        // Assert
        assertNotEquals(recipe1, recipe2);
    }

    @Test
    @DisplayName("Debe generar correctamente el método toString")
    void testToString() {
        // Arrange
        ViewerRecipe viewerRecipe = new ViewerRecipe("Té Helado", ingredients, preparationSteps, "Mónica Paz");

        // Act
        String result = viewerRecipe.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("Té Helado"));
        assertTrue(result.contains("Mónica Paz"));
        assertTrue(result.contains("VIEWER"));
    }

    @Test
    @DisplayName("Debe tener el mismo hashCode para recetas iguales")
    void testHashCode() {
        // Arrange
        ViewerRecipe recipe1 = new ViewerRecipe("Horchata", ingredients, preparationSteps, "Daniela Jiménez");
        ViewerRecipe recipe2 = new ViewerRecipe("Horchata", ingredients, preparationSteps, "Daniela Jiménez");
        
        recipe1.setId("1");
        recipe2.setId("1");

        // Assert
        assertEquals(recipe1.hashCode(), recipe2.hashCode());
    }

    @Test
    @DisplayName("Debe permitir crear múltiples recetas de televidentes")
    void testMultipleViewerRecipes() {
        // Arrange & Act
        ViewerRecipe recipe1 = new ViewerRecipe("Salsa Guacamole", ingredients, preparationSteps, "Televidente A");
        ViewerRecipe recipe2 = new ViewerRecipe("Agua de Tamarindo", ingredients, preparationSteps, "Televidente B");
        ViewerRecipe recipe3 = new ViewerRecipe("Ensalada César", ingredients, preparationSteps, "Televidente C");

        // Assert
        assertNotNull(recipe1);
        assertNotNull(recipe2);
        assertNotNull(recipe3);
        assertEquals("VIEWER", recipe1.getRecipeType());
        assertEquals("VIEWER", recipe2.getRecipeType());
        assertEquals("VIEWER", recipe3.getRecipeType());
        assertNotEquals(recipe1.getTitle(), recipe2.getTitle());
    }

    @Test
    @DisplayName("Debe mantener la lista de ingredientes intacta")
    void testIngredientsListIntegrity() {
        // Arrange
        ViewerRecipe viewerRecipe = new ViewerRecipe("Ceviche de Camarón", ingredients, preparationSteps, "Alejandra Santos");

        // Act
        List<String> retrievedIngredients = viewerRecipe.getIngredients();

        // Assert
        assertEquals(ingredients.size(), retrievedIngredients.size());
        assertEquals(ingredients, retrievedIngredients);
    }

    @Test
    @DisplayName("Debe mantener la lista de pasos de preparación intacta")
    void testPreparationStepsListIntegrity() {
        // Arrange
        ViewerRecipe viewerRecipe = new ViewerRecipe("Tostadas de Tinga", ingredients, preparationSteps, "Ricardo Vargas");

        // Act
        List<String> retrievedSteps = viewerRecipe.getPreparationSteps();

        // Assert
        assertEquals(preparationSteps.size(), retrievedSteps.size());
        assertEquals(preparationSteps, retrievedSteps);
    }

    @Test
    @DisplayName("Debe permitir nombres de chef de diferente longitud")
    void testDifferentChefNameLengths() {
        // Arrange & Act
        ViewerRecipe recipe1 = new ViewerRecipe("Receta 1", ingredients, preparationSteps, "Ana");
        ViewerRecipe recipe2 = new ViewerRecipe("Receta 2", ingredients, preparationSteps, "Juan Carlos de la Rosa");

        // Assert
        assertEquals("Ana", recipe1.getChefName());
        assertEquals("Juan Carlos de la Rosa", recipe2.getChefName());
        assertTrue(recipe1.getChefName().length() < recipe2.getChefName().length());
    }

    @Test
    @DisplayName("Debe permitir títulos de receta creativos")
    void testCreativeRecipeTitles() {
        // Arrange & Act
        ViewerRecipe recipe1 = new ViewerRecipe("Delicia Tropical", ingredients, preparationSteps, "Chef1");
        ViewerRecipe recipe2 = new ViewerRecipe("Sorpresa del Chef", ingredients, preparationSteps, "Chef2");
        ViewerRecipe recipe3 = new ViewerRecipe("Magia en el Plato", ingredients, preparationSteps, "Chef3");

        // Assert
        assertNotNull(recipe1.getTitle());
        assertNotNull(recipe2.getTitle());
        assertNotNull(recipe3.getTitle());
        assertTrue(recipe1.getTitle().contains("Tropical"));
        assertTrue(recipe2.getTitle().contains("Sorpresa"));
        assertTrue(recipe3.getTitle().contains("Magia"));
    }

    @Test
    @DisplayName("Debe permitir diferentes cantidades de ingredientes")
    void testDifferentIngredientsCount() {
        // Arrange
        List<String> fewIngredients = Arrays.asList("Sal", "Pimienta");
        List<String> manyIngredients = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");

        // Act
        ViewerRecipe recipe1 = new ViewerRecipe("Receta Simple", fewIngredients, preparationSteps, "Chef1");
        ViewerRecipe recipe2 = new ViewerRecipe("Receta Compleja", manyIngredients, preparationSteps, "Chef2");

        // Assert
        assertEquals(2, recipe1.getIngredients().size());
        assertEquals(8, recipe2.getIngredients().size());
    }

    @Test
    @DisplayName("Debe permitir diferentes cantidades de pasos de preparación")
    void testDifferentPreparationStepsCount() {
        // Arrange
        List<String> fewSteps = Arrays.asList("Paso 1");
        List<String> manySteps = Arrays.asList("Paso 1", "Paso 2", "Paso 3", "Paso 4", "Paso 5");

        // Act
        ViewerRecipe recipe1 = new ViewerRecipe("Receta Rápida", ingredients, fewSteps, "Chef1");
        ViewerRecipe recipe2 = new ViewerRecipe("Receta Elaborada", ingredients, manySteps, "Chef2");

        // Assert
        assertEquals(1, recipe1.getPreparationSteps().size());
        assertEquals(5, recipe2.getPreparationSteps().size());
    }
}
