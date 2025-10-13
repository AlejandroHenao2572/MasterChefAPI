package com.masterchef.MasterChefAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "DTO para solicitud de receta de televidente")
public class ViewerRecipeRequestDto extends RecipeRequestDto {
}