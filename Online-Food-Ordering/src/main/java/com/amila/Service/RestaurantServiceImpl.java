package com.amila.Service;

import com.amila.dto.RestaurantDto;
import com.amila.model.Address;
import com.amila.model.Restaurant;
import com.amila.model.User;
import com.amila.repository.AddressRepository;
import com.amila.repository.RestaurantRepository;
import com.amila.repository.UserRepository;
import com.amila.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;



    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setName(req.getName());
        restaurant.setDescription(req.getDescription());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setImages(req.getImages());
        restaurant.setOwner(user);
        restaurant.setDescription(req.getDescription());
        restaurantRepository.save(restaurant);

        return restaurant;
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {

        Restaurant restaurant= findRestaurantById(restaurantId);

        if(restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(restaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if(restaurant.getName() != null) {
            restaurant.setName(updatedRestaurant.getName());
        }


        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);

    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Restaurant not found with id "+id);
        }

        return opt.get();

    }

    @Override
    public Restaurant getRestaurantByUserId(Long id, Long userId) throws Exception {
        return null;
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);

        if(restaurant==null){
            throw new Exception("Restaurant not found with owner id "+userId);
        }
        return restaurant;
    }


    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();
        dto.setTitle(restaurant.getName());
        dto.setImages(restaurant.getImages());
        dto.setDescription(restaurant.getDescription());
        dto.setId(restaurantId);

        if(user.getFavourites().contains(dto)){
            user.getFavourites().remove(dto);
        }
        else user.getFavourites().add(dto);

        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);

        restaurant.setOpen(!restaurant.isOpen());


        return restaurantRepository.save(restaurant);
    }
}
