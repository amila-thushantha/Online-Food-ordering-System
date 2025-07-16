package com.amila.Service;

import com.amila.model.Category;
import com.amila.model.Food;
import com.amila.model.Restaurant;
import com.amila.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegetarian,
                                         boolean isNonveg ,
                                         boolean isSeasonal,
                                         String foodCategory);


    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStaus(Long foodId) throws Exception;



}
