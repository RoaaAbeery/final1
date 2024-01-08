package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.DTO.UserDTO;
import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Model.Services;
import com.example.foodtruck.Model.User;
import com.example.foodtruck.Repository.FoodTruckRepository;
import com.example.foodtruck.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private  final UserRepository userRepository;
    private  final FoodTruckRepository foodTruckRepository;
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public void addClient(UserDTO user){
//        User add= userRepository.findUserById(user.getId());
//        if (add == null) {
//            throw new ApiException("the id nt found");
//        }
        User u=new User(null, user.getUserName(), user.getPassword(), user.getEmail(),user.getPhone() ,null,null,null,null);
        userRepository.save(u);
    }
    public void updateClint(Integer auth , User user) {
        User oldadd = userRepository.findUserById(auth);
        if (oldadd == null) {
            throw new ApiException("the id nt found");
        }
        user.setId(oldadd.getId());
        userRepository.save(user);
    }
    public void deleteUser(Integer auth){
        User user = userRepository.findUserById(auth);
        if (user == null) {
            throw new ApiException("the id nt found");
        }
        userRepository.delete(user);
    }
    public List<FoodTruck> check(){
       List<FoodTruck> foodTruck =foodTruckRepository.check();
        if (foodTruck == null) {
            throw new ApiException("the id nt found");
        }
        return foodTruck;
    }
    public List<FoodTruck> best(Double min, Integer ret) {
        List<FoodTruck> foodTruck = foodTruckRepository.findFByDay(min, ret);
        if (foodTruck == null) {
            throw new ApiException("food Truck id incorr");

        }
        return foodTruck;
    }
//    public String logIn(String username, String password){
//        User user = userRepository.logIn(username,password);
//        if(user==null){
//            throw  new ApiException("username or password is incorrect");
//        }
//        return "Login successfully";
//    }
//    public List<FoodTruck> ret(Integer num){
//        List<FoodTruck> foodTruck=foodTruckRepository.findFoodTotByDay(num);
//        if (foodTruck == null) {
//            throw new ApiException("food Truck id incorrect");
//        }
//        return foodTruck;
//    }
//    public String currentStatus(Integer id) {
//        FoodTruck foodTruck = foodTruckRepository.findFoodTruckById(id);
//        if (foodTruck == null) {
//            throw new ApiException("food truck id incorrect");
//
//        }
//        return "Current Status: " + foodTruck.getCond();
//    }

    }