package com.masterchef.MasterChefAPI.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ViewerRecipeRequestDto Tests")
class ViewerRecipeRequestDtoTest {

    private Validator validator;
    private ViewerRecipeRequestDto viewerRecipeRequestDto;
    private List<String> validIngredients;
    private List<String> validPreparationSteps;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
        validIngredients = Arrays.asList("Huevos", "Leche", "Harina", "Azúcar", "Mantequilla");
        validPreparationSteps = Arrays.asList(
            "Batir los huevos con el azúcar",
            "Agregar la leche",
            "Incorporar la harina tamizada",
            "Derretir la mantequilla y mezclar"
        );
        
        viewerRecipeRequestDto = new ViewerRecipeRequestDto();
        viewerRecipeRequestDto.setTitle("Panqueques Caseros");
        viewerRecipeRequestDto.setIngredients(validIngredients);
        viewerRecipeRequestDto.setPreparationSteps(validPreparationSteps);
        viewerRecipeRequestDto.setChefName("Ana Rodríguez");
    }

    @Test
    @DisplayName("Should create valid ViewerRecipeRequestDto")
    void shouldCreateValidViewerRecipeRequestDto() {
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should inherit validation from parent class")
    void shouldInheritValidationFromParentClass() {
        viewerRecipeRequestDto.setTitle("");
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("título es obligatorio")));
    }

    @Test
    @DisplayName("Should validate title constraints from parent")
    void shouldValidateTitleConstraintsFromParent() {
        viewerRecipeRequestDto.setTitle("AB"); // Too short
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("debe tener entre 3 y 200 caracteres")));
    }

    @Test
    @DisplayName("Should validate ingredients constraints from parent")
    void shouldValidateIngredientsConstraintsFromParent() {
        viewerRecipeRequestDto.setIngredients(Collections.emptyList());
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("lista de ingredientes no puede estar vacía")));
    }

    @Test
    @DisplayName("Should validate preparation steps constraints from parent")
    void shouldValidatePreparationStepsConstraintsFromParent() {
        viewerRecipeRequestDto.setPreparationSteps(Collections.emptyList());
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("pasos de preparación no pueden estar vacíos")));
    }

    @Test
    @DisplayName("Should validate chef name constraints from parent")
    void shouldValidateChefNameConstraintsFromParent() {
        viewerRecipeRequestDto.setChefName("A"); // Too short
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("debe tener entre 2 y 100 caracteres")));
    }

    @Test
    @DisplayName("Should test no-args constructor")
    void shouldTestNoArgsConstructor() {
        ViewerRecipeRequestDto dto = new ViewerRecipeRequestDto();
        assertNull(dto.getTitle());
        assertNull(dto.getIngredients());
        assertNull(dto.getPreparationSteps());
        assertNull(dto.getChefName());
    }

    @Test
    @DisplayName("Should test setters and getters")
    void shouldTestSettersAndGetters() {
        ViewerRecipeRequestDto dto = new ViewerRecipeRequestDto();
        
        dto.setTitle("Torta de Chocolate");
        dto.setIngredients(validIngredients);
        dto.setPreparationSteps(validPreparationSteps);
        dto.setChefName("Pedro Jiménez");
        
        assertEquals("Torta de Chocolate", dto.getTitle());
        assertEquals(validIngredients, dto.getIngredients());
        assertEquals(validPreparationSteps, dto.getPreparationSteps());
        assertEquals("Pedro Jiménez", dto.getChefName());
    }

    @Test
    @DisplayName("Should test equals and hashCode")
    void shouldTestEqualsAndHashCode() {
        ViewerRecipeRequestDto dto1 = new ViewerRecipeRequestDto();
        dto1.setTitle("Galletas de Avena");
        dto1.setIngredients(validIngredients);
        dto1.setPreparationSteps(validPreparationSteps);
        dto1.setChefName("Laura Sánchez");
        
        ViewerRecipeRequestDto dto2 = new ViewerRecipeRequestDto();
        dto2.setTitle("Galletas de Avena");
        dto2.setIngredients(validIngredients);
        dto2.setPreparationSteps(validPreparationSteps);
        dto2.setChefName("Laura Sánchez");
        
        ViewerRecipeRequestDto dto3 = new ViewerRecipeRequestDto();
        dto3.setTitle("Different Recipe");
        dto3.setIngredients(validIngredients);
        dto3.setPreparationSteps(validPreparationSteps);
        dto3.setChefName("Different Chef");
        
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    @DisplayName("Should test toString method")
    void shouldTestToStringMethod() {
        String toString = viewerRecipeRequestDto.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("ViewerRecipeRequestDto") || toString.contains("Panqueques Caseros"));
    }

    @Test
    @DisplayName("Should be instance of RecipeRequestDto")
    void shouldBeInstanceOfRecipeRequestDto() {
        assertTrue(viewerRecipeRequestDto instanceof RecipeRequestDto);
    }

    @Test
    @DisplayName("Should test inheritance with null values")
    void shouldTestInheritanceWithNullValues() {
        ViewerRecipeRequestDto dto = new ViewerRecipeRequestDto();
        dto.setTitle(null);
        dto.setIngredients(null);
        dto.setPreparationSteps(null);
        dto.setChefName(null);
        
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        
        // Should have violations for all required fields
        assertTrue(violations.size() >= 4);
    }

    @Test
    @DisplayName("Should validate with minimum valid data")
    void shouldValidateWithMinimumValidData() {
        ViewerRecipeRequestDto dto = new ViewerRecipeRequestDto();
        dto.setTitle("ABC"); // minimum length
        dto.setIngredients(Arrays.asList("ingredient1"));
        dto.setPreparationSteps(Arrays.asList("step1"));
        dto.setChefName("AB"); // minimum length
        
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should validate with maximum valid data")
    void shouldValidateWithMaximumValidData() {
        ViewerRecipeRequestDto dto = new ViewerRecipeRequestDto();
        dto.setTitle("A".repeat(200)); // maximum length
        dto.setIngredients(Arrays.asList("ingredient1", "ingredient2", "ingredient3"));
        dto.setPreparationSteps(Arrays.asList("step1", "step2", "step3"));
        dto.setChefName("A".repeat(100)); // maximum length
        
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should test class type")
    void shouldTestClassType() {
        assertEquals(ViewerRecipeRequestDto.class, viewerRecipeRequestDto.getClass());
        assertEquals("ViewerRecipeRequestDto", viewerRecipeRequestDto.getClass().getSimpleName());
    }

    @Test
    @DisplayName("Should validate complex ingredients list")
    void shouldValidateComplexIngredientsList() {
        List<String> complexIngredients = Arrays.asList(
            "2 tazas de harina todo uso",
            "3 huevos grandes",
            "1 taza de leche entera",
            "2 cucharadas de azúcar",
            "1 cucharadita de vainilla",
            "1/2 cucharadita de sal",
            "2 cucharadas de mantequilla derretida"
        );
        
        viewerRecipeRequestDto.setIngredients(complexIngredients);
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should validate complex preparation steps")
    void shouldValidateComplexPreparationSteps() {
        List<String> complexSteps = Arrays.asList(
            "En un tazón grande, tamizar la harina con la sal",
            "En otro recipiente, batir los huevos hasta que estén espumosos",
            "Agregar gradualmente la leche a los huevos batidos",
            "Incorporar el azúcar y la vainilla, mezclando bien",
            "Verter la mezcla líquida sobre la harina tamizada",
            "Batir hasta obtener una masa lisa y sin grumos",
            "Dejar reposar la masa por 10 minutos",
            "Calentar una sartén antiadherente a fuego medio"
        );
        
        viewerRecipeRequestDto.setPreparationSteps(complexSteps);
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should reject invalid ingredients in list")
    void shouldRejectInvalidIngredientsInList() {
        List<String> invalidIngredients = Arrays.asList(
            "Harina",
            "", // Invalid empty ingredient
            "Azúcar"
        );
        
        viewerRecipeRequestDto.setIngredients(invalidIngredients);
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("ingredientes no pueden estar vacíos")));
    }

    @Test
    @DisplayName("Should reject invalid preparation steps in list")
    void shouldRejectInvalidPreparationStepsInList() {
        List<String> invalidSteps = Arrays.asList(
            "Paso 1: Preparar ingredientes",
            "", // Invalid empty step
            "Paso 3: Terminar la receta"
        );
        
        viewerRecipeRequestDto.setPreparationSteps(invalidSteps);
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("pasos no pueden estar vacíos")));
    }

    @Test
    @DisplayName("Should test edge cases for title length")
    void shouldTestEdgeCasesForTitleLength() {
        // Test exactly 3 characters (minimum valid)
        viewerRecipeRequestDto.setTitle("Pan");
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertTrue(violations.isEmpty());
        
        // Test exactly 200 characters (maximum valid)
        String maxTitle = "A".repeat(200);
        viewerRecipeRequestDto.setTitle(maxTitle);
        violations = validator.validate(viewerRecipeRequestDto);
        assertTrue(violations.isEmpty());
        
        // Test 201 characters (invalid)
        String tooLongTitle = "A".repeat(201);
        viewerRecipeRequestDto.setTitle(tooLongTitle);
        violations = validator.validate(viewerRecipeRequestDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Should test edge cases for chef name length")
    void shouldTestEdgeCasesForChefNameLength() {
        // Test exactly 2 characters (minimum valid)
        viewerRecipeRequestDto.setChefName("Lu");
        Set<ConstraintViolation<ViewerRecipeRequestDto>> violations = validator.validate(viewerRecipeRequestDto);
        assertTrue(violations.isEmpty());
        
        // Test exactly 100 characters (maximum valid)
        String maxName = "A".repeat(100);
        viewerRecipeRequestDto.setChefName(maxName);
        violations = validator.validate(viewerRecipeRequestDto);
        assertTrue(violations.isEmpty());
        
        // Test 101 characters (invalid)
        String tooLongName = "A".repeat(101);
        viewerRecipeRequestDto.setChefName(tooLongName);
        violations = validator.validate(viewerRecipeRequestDto);
        assertFalse(violations.isEmpty());
    }
}