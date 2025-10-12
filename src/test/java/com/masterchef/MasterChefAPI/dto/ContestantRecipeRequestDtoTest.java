package com.masterchef.MasterChefAPI.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ContestantRecipeRequestDto Tests")
class ContestantRecipeRequestDtoTest {

    private Validator validator;
    private ContestantRecipeRequestDto contestantRecipeRequestDto;
    private List<String> validIngredients;
    private List<String> validPreparationSteps;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
        validIngredients = Arrays.asList("Carne de res", "Papas", "Cebolla", "Especias");
        validPreparationSteps = Arrays.asList(
            "Cortar la carne en cubos",
            "Pelar y cortar las papas",
            "Sofreír la cebolla",
            "Cocinar todo junto"
        );
        
        contestantRecipeRequestDto = new ContestantRecipeRequestDto();
        contestantRecipeRequestDto.setTitle("Estofado de Carne");
        contestantRecipeRequestDto.setIngredients(validIngredients);
        contestantRecipeRequestDto.setPreparationSteps(validPreparationSteps);
        contestantRecipeRequestDto.setChefName("María González");
        contestantRecipeRequestDto.setSeason(3);
    }

    @Test
    @DisplayName("Should create valid ContestantRecipeRequestDto")
    void shouldCreateValidContestantRecipeRequestDto() {
        Set<ConstraintViolation<ContestantRecipeRequestDto>> violations = validator.validate(contestantRecipeRequestDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should validate season is not null")
    void shouldValidateSeasonIsNotNull() {
        contestantRecipeRequestDto.setSeason(null);
        Set<ConstraintViolation<ContestantRecipeRequestDto>> violations = validator.validate(contestantRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("temporada es obligatoria")));
    }

    @Test
    @DisplayName("Should validate season minimum value")
    void shouldValidateSeasonMinimumValue() {
        contestantRecipeRequestDto.setSeason(0);
        Set<ConstraintViolation<ContestantRecipeRequestDto>> violations = validator.validate(contestantRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("temporada debe ser mayor a 0")));
    }

    @Test
    @DisplayName("Should validate season negative value")
    void shouldValidateSeasonNegativeValue() {
        contestantRecipeRequestDto.setSeason(-1);
        Set<ConstraintViolation<ContestantRecipeRequestDto>> violations = validator.validate(contestantRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("temporada debe ser mayor a 0")));
    }

    @Test
    @DisplayName("Should accept valid season values")
    void shouldAcceptValidSeasonValues() {
        // Test season = 1 (minimum valid)
        contestantRecipeRequestDto.setSeason(1);
        Set<ConstraintViolation<ContestantRecipeRequestDto>> violations = validator.validate(contestantRecipeRequestDto);
        assertTrue(violations.isEmpty());
        
        // Test season = 10 (any positive value)
        contestantRecipeRequestDto.setSeason(10);
        violations = validator.validate(contestantRecipeRequestDto);
        assertTrue(violations.isEmpty());
        
        // Test season = 100 (large positive value)
        contestantRecipeRequestDto.setSeason(100);
        violations = validator.validate(contestantRecipeRequestDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should inherit validation from parent class")
    void shouldInheritValidationFromParentClass() {
        contestantRecipeRequestDto.setTitle("");
        Set<ConstraintViolation<ContestantRecipeRequestDto>> violations = validator.validate(contestantRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("título es obligatorio")));
    }

    @Test
    @DisplayName("Should test no-args constructor")
    void shouldTestNoArgsConstructor() {
        ContestantRecipeRequestDto dto = new ContestantRecipeRequestDto();
        assertNull(dto.getTitle());
        assertNull(dto.getIngredients());
        assertNull(dto.getPreparationSteps());
        assertNull(dto.getChefName());
        assertNull(dto.getSeason());
    }

    @Test
    @DisplayName("Should test setters and getters")
    void shouldTestSettersAndGetters() {
        ContestantRecipeRequestDto dto = new ContestantRecipeRequestDto();
        
        dto.setTitle("Pollo al Curry");
        dto.setIngredients(validIngredients);
        dto.setPreparationSteps(validPreparationSteps);
        dto.setChefName("Carlos Martínez");
        dto.setSeason(5);
        
        assertEquals("Pollo al Curry", dto.getTitle());
        assertEquals(validIngredients, dto.getIngredients());
        assertEquals(validPreparationSteps, dto.getPreparationSteps());
        assertEquals("Carlos Martínez", dto.getChefName());
        assertEquals(5, dto.getSeason());
    }

    @Test
    @DisplayName("Should test season getter and setter specifically")
    void shouldTestSeasonGetterAndSetterSpecifically() {
        ContestantRecipeRequestDto dto = new ContestantRecipeRequestDto();
        
        // Test setting different values
        dto.setSeason(1);
        assertEquals(1, dto.getSeason());
        
        dto.setSeason(25);
        assertEquals(25, dto.getSeason());
        
        dto.setSeason(null);
        assertNull(dto.getSeason());
    }

    @Test
    @DisplayName("Should test equals and hashCode")
    void shouldTestEqualsAndHashCode() {
        ContestantRecipeRequestDto dto1 = new ContestantRecipeRequestDto();
        dto1.setTitle("Lasagna");
        dto1.setIngredients(validIngredients);
        dto1.setPreparationSteps(validPreparationSteps);
        dto1.setChefName("Luigi Bianchi");
        dto1.setSeason(2);
        
        ContestantRecipeRequestDto dto2 = new ContestantRecipeRequestDto();
        dto2.setTitle("Lasagna");
        dto2.setIngredients(validIngredients);
        dto2.setPreparationSteps(validPreparationSteps);
        dto2.setChefName("Luigi Bianchi");
        dto2.setSeason(2);
        
        ContestantRecipeRequestDto dto3 = new ContestantRecipeRequestDto();
        dto3.setTitle("Lasagna");
        dto3.setIngredients(validIngredients);
        dto3.setPreparationSteps(validPreparationSteps);
        dto3.setChefName("Luigi Bianchi");
        dto3.setSeason(3); // Different season
        
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    @DisplayName("Should test toString method")
    void shouldTestToStringMethod() {
        String toString = contestantRecipeRequestDto.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("ContestantRecipeRequestDto") || toString.contains("Estofado de Carne"));
    }

    @Test
    @DisplayName("Should be instance of RecipeRequestDto")
    void shouldBeInstanceOfRecipeRequestDto() {
        assertTrue(contestantRecipeRequestDto instanceof RecipeRequestDto);
    }

    @Test
    @DisplayName("Should test inheritance with multiple violations")
    void shouldTestInheritanceWithMultipleViolations() {
        ContestantRecipeRequestDto dto = new ContestantRecipeRequestDto();
        dto.setTitle(null);
        dto.setIngredients(null);
        dto.setPreparationSteps(null);
        dto.setChefName(null);
        dto.setSeason(null);
        
        Set<ConstraintViolation<ContestantRecipeRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        
        // Should have violations for all required fields including season
        assertTrue(violations.size() >= 5);
    }

    @Test
    @DisplayName("Should validate with all parent class constraints plus season")
    void shouldValidateWithAllParentClassConstraintsPlusSeason() {
        ContestantRecipeRequestDto dto = new ContestantRecipeRequestDto();
        dto.setTitle("A"); // Too short
        dto.setIngredients(Arrays.asList("")); // Empty ingredient
        dto.setPreparationSteps(Arrays.asList("")); // Empty step
        dto.setChefName("B"); // Too short
        dto.setSeason(-5); // Invalid season
        
        Set<ConstraintViolation<ContestantRecipeRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        
        // Should have multiple violations
        assertTrue(violations.size() >= 5);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("título")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("ingredientes")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("pasos")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("chef")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("temporada")));
    }

    @Test
    @DisplayName("Should test class type")
    void shouldTestClassType() {
        assertEquals(ContestantRecipeRequestDto.class, contestantRecipeRequestDto.getClass());
        assertEquals("ContestantRecipeRequestDto", contestantRecipeRequestDto.getClass().getSimpleName());
    }

    @Test
    @DisplayName("Should validate edge case with very large season number")
    void shouldValidateEdgeCaseWithVeryLargeSeasonNumber() {
        contestantRecipeRequestDto.setSeason(Integer.MAX_VALUE);
        Set<ConstraintViolation<ContestantRecipeRequestDto>> violations = validator.validate(contestantRecipeRequestDto);
        assertTrue(violations.isEmpty());
    }
}