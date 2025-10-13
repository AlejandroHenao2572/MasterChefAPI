package com.masterchef.MasterChefAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para solicitud de receta")
public class RecipeRequestDto {
    
    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 200, message = "El título debe tener entre 3 y 200 caracteres")
    @Schema(description = "Título de la receta", example = "Paella Valenciana")
    private String title;
    
    @NotEmpty(message = "La lista de ingredientes no puede estar vacía")
    @Schema(description = "Lista de ingredientes", example = "[\"arroz\", \"pollo\", \"azafrán\"]")
    private List<@NotBlank(message = "Los ingredientes no pueden estar vacíos") String> ingredients;
    
    @NotEmpty(message = "Los pasos de preparación no pueden estar vacíos")
    @Schema(description = "Pasos de preparación", example = "[\"Paso 1: Preparar ingredientes\", \"Paso 2: Cocinar\"]")
    private List<@NotBlank(message = "Los pasos no pueden estar vacíos") String> preparationSteps;
    
    @NotBlank(message = "El nombre del chef es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del chef debe tener entre 2 y 100 caracteres")
    @Schema(description = "Nombre del chef", example = "Juan Pérez")
    private String chefName;
}