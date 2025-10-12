package com.masterchef.MasterChefAPI.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ViewerRecipe extends Recipe {
    
    public ViewerRecipe(String title, List<String> ingredients, 
                        List<String> preparationSteps, String chefName) {
        super(title, ingredients, preparationSteps, chefName, "VIEWER");
    }
}
