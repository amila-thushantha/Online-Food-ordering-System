package com.amila.controller;

import com.amila.Service.CategoryService;
import com.amila.Service.UserService;
import com.amila.model.Category;
import com.amila.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Category createdCategory = categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);


    }


    @GetMapping("category/restaurant")
    public ResponseEntity<List<Category>> getRestaurantCategory(
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Category> categories = categoryService.findCategoryByRestaurantsId(user.getId());
        return new ResponseEntity<>(categories, HttpStatus.CREATED);


    }
}















