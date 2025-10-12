package com.masterchef.MasterChefAPI.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ContestantRecipe extends Recipe {
    
    public ContestantRecipe(String title, List<String> ingredients, 
                           List<String> preparationSteps, String chefName, Integer season) {
        super(title, ingredients, preparationSteps, chefName, "CONTESTANT", season);
    }
}