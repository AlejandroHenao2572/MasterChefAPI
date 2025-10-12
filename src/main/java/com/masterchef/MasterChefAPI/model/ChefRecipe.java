package com.masterchef.MasterChefAPI.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ChefRecipe extends Recipe {
    
    public ChefRecipe(String title, List<String> ingredients, 
                      List<String> preparationSteps, String chefName) {
        super(title, ingredients, preparationSteps, chefName, "CHEF");
    }
}
