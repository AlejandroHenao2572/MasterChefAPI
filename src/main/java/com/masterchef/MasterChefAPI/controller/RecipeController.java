package com.masterchef.MasterChefAPI.controller;

import com.masterchef.MasterChefAPI.dto.ChefRecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.ContestantRecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.RecipeRequestDto;
import com.masterchef.MasterChefAPI.dto.ViewerRecipeRequestDto;
import com.masterchef.MasterChefAPI.model.Recipe;
import com.masterchef.MasterChefAPI.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@Tag(name = "Recetas", description = "API de gestión de recetas de cocina")
public class RecipeController {
    
    private final RecipeService recipeService;
    
    @PostMapping("/viewer")
    @Operation(summary = "Registrar receta de televidente", 
               description = "Registra una nueva receta creada por un televidente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Receta registrada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Recipe> registerViewerRecipe(
            @Valid @RequestBody ViewerRecipeRequestDto dto) {
        Recipe recipe = recipeService.registerViewerRecipe(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
    }
    
    @PostMapping("/contestant")
    @Operation(summary = "Registrar receta de participante", 
               description = "Registra una nueva receta creada por un participante del programa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Receta registrada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Recipe> registerContestantRecipe(
            @Valid @RequestBody ContestantRecipeRequestDto dto) {
        Recipe recipe = recipeService.registerContestantRecipe(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
    }
    
    @PostMapping("/chef")
    @Operation(summary = "Registrar receta de chef", 
               description = "Registra una nueva receta creada por un chef jurado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Receta registrada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Recipe> registerChefRecipe(
            @Valid @RequestBody ChefRecipeRequestDto dto) {
        Recipe recipe = recipeService.registerChefRecipe(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
    }
    
    @GetMapping
    @Operation(summary = "Obtener todas las recetas", 
               description = "Devuelve todas las recetas guardadas en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }
    
    @GetMapping("/{consecutiveNumber}")
    @Operation(summary = "Obtener receta por número consecutivo", 
               description = "Devuelve una receta específica por su número consecutivo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta encontrada"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    public ResponseEntity<Recipe> getRecipeByConsecutiveNumber(
            @Parameter(description = "Número consecutivo de la receta") 
            @PathVariable Long consecutiveNumber) {
        Recipe recipe = recipeService.getRecipeByConsecutiveNumber(consecutiveNumber);
        return ResponseEntity.ok(recipe);
    }
    
    @GetMapping("/contestant")
    @Operation(summary = "Obtener recetas de participantes", 
               description = "Devuelve todas las recetas creadas por participantes del programa")
    @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente")
    public ResponseEntity<List<Recipe>> getContestantRecipes() {
        List<Recipe> recipes = recipeService.getContestantRecipes();
        return ResponseEntity.ok(recipes);
    }
    
    @GetMapping("/viewer")
    @Operation(summary = "Obtener recetas de televidentes", 
               description = "Devuelve todas las recetas creadas por televidentes")
    @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente")
    public ResponseEntity<List<Recipe>> getViewerRecipes() {
        List<Recipe> recipes = recipeService.getViewerRecipes();
        return ResponseEntity.ok(recipes);
    }
    
    @GetMapping("/chef")
    @Operation(summary = "Obtener recetas de chefs", 
               description = "Devuelve todas las recetas creadas por chefs jurados")
    @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente")
    public ResponseEntity<List<Recipe>> getChefRecipes() {
        List<Recipe> recipes = recipeService.getChefRecipes();
        return ResponseEntity.ok(recipes);
    }
    
    @GetMapping("/season/{season}")
    @Operation(summary = "Obtener recetas por temporada", 
               description = "Devuelve todas las recetas de una temporada específica")
    @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente")
    public ResponseEntity<List<Recipe>> getRecipesBySeason(
            @Parameter(description = "Número de temporada") 
            @PathVariable Integer season) {
        List<Recipe> recipes = recipeService.getRecipesBySeason(season);
        return ResponseEntity.ok(recipes);
    }
    
    @GetMapping("/search")
    @Operation(summary = "Buscar recetas por ingrediente", 
               description = "Busca recetas que contengan un ingrediente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda completada exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron recetas con el ingrediente especificado")
    })
    public ResponseEntity<List<Recipe>> searchRecipesByIngredient(
            @Parameter(description = "Ingrediente a buscar") 
            @RequestParam String ingredient) {
        List<Recipe> recipes = recipeService.searchRecipesByIngredient(ingredient);
        return ResponseEntity.ok(recipes);
    }
    
    @DeleteMapping("/{consecutiveNumber}")
    @Operation(summary = "Eliminar receta", 
               description = "Elimina una receta del sistema por su número consecutivo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Receta eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    public ResponseEntity<Void> deleteRecipe(
            @Parameter(description = "Número consecutivo de la receta") 
            @PathVariable Long consecutiveNumber) {
        recipeService.deleteRecipe(consecutiveNumber);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{consecutiveNumber}")
    @Operation(summary = "Actualizar receta", 
               description = "Actualiza los datos de una receta existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Recipe> updateRecipe(
            @Parameter(description = "Número consecutivo de la receta") 
            @PathVariable Long consecutiveNumber,
            @Valid @RequestBody RecipeRequestDto dto) {
        Recipe updatedRecipe = recipeService.updateRecipe(consecutiveNumber, dto);
        return ResponseEntity.ok(updatedRecipe);
    }
}
