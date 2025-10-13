package com.masterchef.MasterChefAPI.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de receta")
public class Recipe {
    
    @Id
    @Schema(description = "ID único de la receta", example = "507f1f77bcf86cd799439011")
    private String id;
    
    @Schema(description = "Número consecutivo de la receta", example = "1")
    private Long consecutiveNumber;
    
    @Schema(description = "Título de la receta", example = "Paella Valenciana")
    private String title;
    
    @Schema(description = "Lista de ingredientes")
    private List<String> ingredients;
    
    @Schema(description = "Pasos de preparación")
    private List<String> preparationSteps;
    
    @Schema(description = "Nombre del chef", example = "Juan Pérez")
    private String chefName;
    
    @Schema(description = "Tipo de receta", example = "VIEWER", allowableValues = {"VIEWER", "CONTESTANT", "CHEF"})
    private String recipeType; // VIEWER, CONTESTANT, CHEF
    
    @Schema(description = "Número de temporada (solo para participantes)", example = "1")
    private Integer season;
    
    @Schema(description = "Fecha de creación")
    private LocalDateTime createdAt;
    
    @Schema(description = "Fecha de última actualización")
    private LocalDateTime updatedAt;
    
    public Recipe(String title, List<String> ingredients, 
                  List<String> preparationSteps, String chefName, String recipeType) {
        this.title = title;
        this.ingredients = ingredients;
        this.preparationSteps = preparationSteps;
        this.chefName = chefName;
        this.recipeType = recipeType;
        this.season = null; // Por defecto null, solo se asigna para CONTESTANT
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public Recipe(String title, List<String> ingredients, 
                  List<String> preparationSteps, String chefName, String recipeType, Integer season) {
        this.title = title;
        this.ingredients = ingredients;
        this.preparationSteps = preparationSteps;
        this.chefName = chefName;
        this.recipeType = recipeType;
        this.season = season;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
