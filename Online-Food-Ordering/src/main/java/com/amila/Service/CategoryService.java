package com.amila.Service;

import com.amila.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(String name,Long userId) throws Exception;

    public List<Category> findCategoryByRestaurantsId(Long id) throws Exception;

    public Category findCategoryById(Long id) throws Exception;
}
