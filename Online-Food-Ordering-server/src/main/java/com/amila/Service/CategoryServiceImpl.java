package com.amila.Service;

import com.amila.model.Category;
import com.amila.model.Restaurant;
import com.amila.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public List<Category> findCategoryByRestaurantsId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            throw new Exception("Category not found with id " + id);
        }
        return optionalCategory.get();
    }
}
