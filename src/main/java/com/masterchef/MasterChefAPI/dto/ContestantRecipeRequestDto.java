package com.masterchef.MasterChefAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "DTO para solicitud de receta de participante")
public class ContestantRecipeRequestDto extends RecipeRequestDto {
    
    @NotNull(message = "La temporada es obligatoria para recetas de participantes")
    @Min(value = 1, message = "La temporada debe ser mayor a 0")
    @Schema(description = "Número de temporada", example = "1")
    private Integer season;
}
