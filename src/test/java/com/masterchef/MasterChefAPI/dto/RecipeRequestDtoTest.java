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

@DisplayName("RecipeRequestDto Tests")
class RecipeRequestDtoTest {

    private Validator validator;
    private RecipeRequestDto recipeRequestDto;
    private List<String> validIngredients;
    private List<String> validPreparationSteps;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
        validIngredients = Arrays.asList("Arroz", "Pollo", "Azafrán", "Caldo de pollo");
        validPreparationSteps = Arrays.asList(
            "Preparar todos los ingredientes",
            "Calentar aceite en la paellera",
            "Sofreír el pollo",
            "Agregar el arroz y el caldo"
        );
        
        recipeRequestDto = new RecipeRequestDto();
        recipeRequestDto.setTitle("Paella Valenciana");
        recipeRequestDto.setIngredients(validIngredients);
        recipeRequestDto.setPreparationSteps(validPreparationSteps);
        recipeRequestDto.setChefName("Juan Pérez");
    }

    @Test
    @DisplayName("Should create valid RecipeRequestDto")
    void shouldCreateValidRecipeRequestDto() {
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should validate title is not blank")
    void shouldValidateTitleIsNotBlank() {
        recipeRequestDto.setTitle("");
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("título es obligatorio")));
    }

    @Test
    @DisplayName("Should validate title is not null")
    void shouldValidateTitleIsNotNull() {
        recipeRequestDto.setTitle(null);
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("título es obligatorio")));
    }

    @Test
    @DisplayName("Should validate title minimum length")
    void shouldValidateTitleMinimumLength() {
        recipeRequestDto.setTitle("Ab");
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("debe tener entre 3 y 200 caracteres")));
    }

    @Test
    @DisplayName("Should validate title maximum length")
    void shouldValidateTitleMaximumLength() {
        String longTitle = "A".repeat(201);
        recipeRequestDto.setTitle(longTitle);
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("debe tener entre 3 y 200 caracteres")));
    }

    @Test
    @DisplayName("Should validate ingredients list is not empty")
    void shouldValidateIngredientsListIsNotEmpty() {
        recipeRequestDto.setIngredients(Collections.emptyList());
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("lista de ingredientes no puede estar vacía")));
    }

    @Test
    @DisplayName("Should validate ingredients list is not null")
    void shouldValidateIngredientsListIsNotNull() {
        recipeRequestDto.setIngredients(null);
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("lista de ingredientes no puede estar vacía")));
    }

    @Test
    @DisplayName("Should validate individual ingredients are not blank")
    void shouldValidateIndividualIngredientsAreNotBlank() {
        recipeRequestDto.setIngredients(Arrays.asList("Arroz", "", "Azafrán"));
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("ingredientes no pueden estar vacíos")));
    }

    @Test
    @DisplayName("Should validate preparation steps list is not empty")
    void shouldValidatePreparationStepsListIsNotEmpty() {
        recipeRequestDto.setPreparationSteps(Collections.emptyList());
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("pasos de preparación no pueden estar vacíos")));
    }

    @Test
    @DisplayName("Should validate preparation steps list is not null")
    void shouldValidatePreparationStepsListIsNotNull() {
        recipeRequestDto.setPreparationSteps(null);
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("pasos de preparación no pueden estar vacíos")));
    }

    @Test
    @DisplayName("Should validate individual preparation steps are not blank")
    void shouldValidateIndividualPreparationStepsAreNotBlank() {
        recipeRequestDto.setPreparationSteps(Arrays.asList("Paso 1", "", "Paso 3"));
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("pasos no pueden estar vacíos")));
    }

    @Test
    @DisplayName("Should validate chef name is not blank")
    void shouldValidateChefNameIsNotBlank() {
        recipeRequestDto.setChefName("");
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("nombre del chef es obligatorio")));
    }

    @Test
    @DisplayName("Should validate chef name is not null")
    void shouldValidateChefNameIsNotNull() {
        recipeRequestDto.setChefName(null);
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("nombre del chef es obligatorio")));
    }

    @Test
    @DisplayName("Should validate chef name minimum length")
    void shouldValidateChefNameMinimumLength() {
        recipeRequestDto.setChefName("A");
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("debe tener entre 2 y 100 caracteres")));
    }

    @Test
    @DisplayName("Should validate chef name maximum length")
    void shouldValidateChefNameMaximumLength() {
        String longName = "A".repeat(101);
        recipeRequestDto.setChefName(longName);
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("debe tener entre 2 y 100 caracteres")));
    }

    @Test
    @DisplayName("Should test constructor with parameters")
    void shouldTestConstructorWithParameters() {
        RecipeRequestDto dto = new RecipeRequestDto("Test Recipe", validIngredients, validPreparationSteps, "Test Chef");
        
        assertEquals("Test Recipe", dto.getTitle());
        assertEquals(validIngredients, dto.getIngredients());
        assertEquals(validPreparationSteps, dto.getPreparationSteps());
        assertEquals("Test Chef", dto.getChefName());
    }

    @Test
    @DisplayName("Should test setters and getters")
    void shouldTestSettersAndGetters() {
        RecipeRequestDto dto = new RecipeRequestDto();
        
        dto.setTitle("New Title");
        dto.setIngredients(validIngredients);
        dto.setPreparationSteps(validPreparationSteps);
        dto.setChefName("New Chef");
        
        assertEquals("New Title", dto.getTitle());
        assertEquals(validIngredients, dto.getIngredients());
        assertEquals(validPreparationSteps, dto.getPreparationSteps());
        assertEquals("New Chef", dto.getChefName());
    }

    @Test
    @DisplayName("Should test equals and hashCode")
    void shouldTestEqualsAndHashCode() {
        RecipeRequestDto dto1 = new RecipeRequestDto("Title", validIngredients, validPreparationSteps, "Chef");
        RecipeRequestDto dto2 = new RecipeRequestDto("Title", validIngredients, validPreparationSteps, "Chef");
        RecipeRequestDto dto3 = new RecipeRequestDto("Different", validIngredients, validPreparationSteps, "Chef");
        
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    @DisplayName("Should test toString method")
    void shouldTestToStringMethod() {
        String toString = recipeRequestDto.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Paella Valenciana"));
        assertTrue(toString.contains("Juan Pérez"));
    }

    @Test
    @DisplayName("Should accept valid title at boundaries")
    void shouldAcceptValidTitleAtBoundaries() {
        // Test minimum valid length (3 characters)
        recipeRequestDto.setTitle("ABC");
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertTrue(violations.isEmpty());
        
        // Test maximum valid length (200 characters)
        String maxTitle = "A".repeat(200);
        recipeRequestDto.setTitle(maxTitle);
        violations = validator.validate(recipeRequestDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should accept valid chef name at boundaries")
    void shouldAcceptValidChefNameAtBoundaries() {
        // Test minimum valid length (2 characters)
        recipeRequestDto.setChefName("AB");
        Set<ConstraintViolation<RecipeRequestDto>> violations = validator.validate(recipeRequestDto);
        assertTrue(violations.isEmpty());
        
        // Test maximum valid length (100 characters)
        String maxName = "A".repeat(100);
        recipeRequestDto.setChefName(maxName);
        violations = validator.validate(recipeRequestDto);
        assertTrue(violations.isEmpty());
    }
}