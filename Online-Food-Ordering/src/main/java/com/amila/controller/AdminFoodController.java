package com.amila.controller;

import com.amila.Service.FoodService;
import com.amila.Service.RestaurantService;
import com.amila.Service.UserService;
import com.amila.model.Food;
import com.amila.model.Restaurant;
import com.amila.model.User;
import com.amila.request.CreateFoodRequest;
import com.amila.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {


    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        Food food = foodService.createFood(req,req.getCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);


        foodService.deleteFood(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Food deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);



    }


    //search food

    @PutMapping("/{id}/update-availability")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);


       Food food =  foodService.updateAvailabilityStaus(id);

        MessageResponse res = new MessageResponse();
        return new ResponseEntity<>(food, HttpStatus.OK);



    }
}
