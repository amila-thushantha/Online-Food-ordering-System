package com.amila.controller;

import com.amila.Service.RestaurantService;
import com.amila.Service.UserService;
import com.amila.model.Restaurant;
import com.amila.model.User;
import com.amila.request.CreateRestaurantRequest;
import com.amila.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")


public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.createRestaurant(req, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurant(id, req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        restaurantService.deleteRestaurant(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Restaurant deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.updateRestaurantStatus(id);


        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }









}
