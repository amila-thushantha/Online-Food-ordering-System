package com.amila.Service;

import com.amila.model.IngredientCategory;
import com.amila.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {

    public IngredientCategory createIngredientCategory(String name,Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantsId(Long id) throws Exception;

    public IngredientsItem createIngredientsItem(Long restaurantId,String ingredientsName,Long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId);

    public IngredientsItem updateStock(Long id) throws Exception;
}
