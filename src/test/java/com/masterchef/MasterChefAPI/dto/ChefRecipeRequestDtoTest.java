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

@DisplayName("ChefRecipeRequestDto Tests")
class ChefRecipeRequestDtoTest {

    private Validator validator;
    private ChefRecipeRequestDto chefRecipeRequestDto;
    private List<String> validIngredients;
    private List<String> validPreparationSteps;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
        validIngredients = Arrays.asList("Tomate", "Albahaca", "Mozzarella", "Aceite de oliva");
        validPreparationSteps = Arrays.asList(
            "Preparar la masa de pizza",
            "Extender la masa",
            "Agregar salsa de tomate",
            "Añadir mozzarella y albahaca"
        );
        
        chefRecipeRequestDto = new ChefRecipeRequestDto();
        chefRecipeRequestDto.setTitle("Pizza Margherita");
        chefRecipeRequestDto.setIngredients(validIngredients);
        chefRecipeRequestDto.setPreparationSteps(validPreparationSteps);
        chefRecipeRequestDto.setChefName("Marco Rossi");
    }

    @Test
    @DisplayName("Should create valid ChefRecipeRequestDto")
    void shouldCreateValidChefRecipeRequestDto() {
        Set<ConstraintViolation<ChefRecipeRequestDto>> violations = validator.validate(chefRecipeRequestDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should inherit validation from parent class")
    void shouldInheritValidationFromParentClass() {
        chefRecipeRequestDto.setTitle("");
        Set<ConstraintViolation<ChefRecipeRequestDto>> violations = validator.validate(chefRecipeRequestDto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("título es obligatorio")));
    }

    @Test
    @DisplayName("Should test no-args constructor")
    void shouldTestNoArgsConstructor() {
        ChefRecipeRequestDto dto = new ChefRecipeRequestDto();
        assertNull(dto.getTitle());
        assertNull(dto.getIngredients());
        assertNull(dto.getPreparationSteps());
        assertNull(dto.getChefName());
    }

    @Test
    @DisplayName("Should test setters and getters")
    void shouldTestSettersAndGetters() {
        ChefRecipeRequestDto dto = new ChefRecipeRequestDto();
        
        dto.setTitle("Risotto al Funghi");
        dto.setIngredients(validIngredients);
        dto.setPreparationSteps(validPreparationSteps);
        dto.setChefName("Giuseppe Verdi");
        
        assertEquals("Risotto al Funghi", dto.getTitle());
        assertEquals(validIngredients, dto.getIngredients());
        assertEquals(validPreparationSteps, dto.getPreparationSteps());
        assertEquals("Giuseppe Verdi", dto.getChefName());
    }

    @Test
    @DisplayName("Should test equals and hashCode")
    void shouldTestEqualsAndHashCode() {
        ChefRecipeRequestDto dto1 = new ChefRecipeRequestDto();
        dto1.setTitle("Pasta Carbonara");
        dto1.setIngredients(validIngredients);
        dto1.setPreparationSteps(validPreparationSteps);
        dto1.setChefName("Antonio Carluccio");
        
        ChefRecipeRequestDto dto2 = new ChefRecipeRequestDto();
        dto2.setTitle("Pasta Carbonara");
        dto2.setIngredients(validIngredients);
        dto2.setPreparationSteps(validPreparationSteps);
        dto2.setChefName("Antonio Carluccio");
        
        ChefRecipeRequestDto dto3 = new ChefRecipeRequestDto();
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
        String toString = chefRecipeRequestDto.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("ChefRecipeRequestDto") || toString.contains("Pizza Margherita"));
    }

    @Test
    @DisplayName("Should be instance of RecipeRequestDto")
    void shouldBeInstanceOfRecipeRequestDto() {
        assertTrue(chefRecipeRequestDto instanceof RecipeRequestDto);
    }

    @Test
    @DisplayName("Should test inheritance with null values")
    void shouldTestInheritanceWithNullValues() {
        ChefRecipeRequestDto dto = new ChefRecipeRequestDto();
        dto.setTitle(null);
        dto.setIngredients(null);
        dto.setPreparationSteps(null);
        dto.setChefName(null);
        
        Set<ConstraintViolation<ChefRecipeRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        
        // Should have violations for all required fields
        assertTrue(violations.size() >= 4);
    }

    @Test
    @DisplayName("Should validate with minimum valid data")
    void shouldValidateWithMinimumValidData() {
        ChefRecipeRequestDto dto = new ChefRecipeRequestDto();
        dto.setTitle("ABC"); // minimum length
        dto.setIngredients(Arrays.asList("ingredient1"));
        dto.setPreparationSteps(Arrays.asList("step1"));
        dto.setChefName("AB"); // minimum length
        
        Set<ConstraintViolation<ChefRecipeRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should validate with maximum valid data")
    void shouldValidateWithMaximumValidData() {
        ChefRecipeRequestDto dto = new ChefRecipeRequestDto();
        dto.setTitle("A".repeat(200)); // maximum length
        dto.setIngredients(Arrays.asList("ingredient1", "ingredient2", "ingredient3"));
        dto.setPreparationSteps(Arrays.asList("step1", "step2", "step3"));
        dto.setChefName("A".repeat(100)); // maximum length
        
        Set<ConstraintViolation<ChefRecipeRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should test class type")
    void shouldTestClassType() {
        assertEquals(ChefRecipeRequestDto.class, chefRecipeRequestDto.getClass());
        assertEquals("ChefRecipeRequestDto", chefRecipeRequestDto.getClass().getSimpleName());
    }
}