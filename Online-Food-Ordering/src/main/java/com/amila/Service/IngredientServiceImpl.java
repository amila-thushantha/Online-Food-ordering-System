package com.amila.Service;

import com.amila.model.IngredientCategory;
import com.amila.model.IngredientsItem;
import com.amila.model.Restaurant;
import com.amila.repository.IngredientItemRepository;
import com.amila.repository.IngredientsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientsService {

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientsCategoryRepository ingredientsCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);
        ingredientsCategoryRepository.save(category);
        return category;
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {

        Optional<IngredientCategory> opt = ingredientsCategoryRepository.findById(id);
        if (opt.isEmpty()) {
            throw new Exception("Category not found with id " + id);
        }
        return opt.get();
    }


    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantsId(Long id) throws Exception {

        restaurantService.findRestaurantById(id);
        return ingredientsCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientsName, Long categoryId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);

        IngredientsItem item = new IngredientsItem();
        item.setRestaurant(restaurant);
        item.setName(ingredientsName);
        item.setCategory(category);

        IngredientsItem ingredient = ingredientItemRepository.save(item);

        category.getIngredients().add(ingredient);

        return ingredient;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {

        Optional<IngredientsItem> optionalIngredientsItem = ingredientItemRepository.findById(id);

        if (optionalIngredientsItem.isEmpty()){
            throw new Exception("Ingredient not found with id " + id);
        }

        IngredientsItem ingredientsItem = optionalIngredientsItem.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientItemRepository.save(ingredientsItem);
    }
}
