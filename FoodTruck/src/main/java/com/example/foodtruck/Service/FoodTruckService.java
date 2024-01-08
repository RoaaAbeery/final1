package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.Model.*;
import com.example.foodtruck.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class FoodTruckService {
    private final FoodTruckRepository foodTruckRepository;
    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final EvaluationRepository evaluationRepository;
private final UserRepository userRepository;
    public List<FoodTruck> getAll() {
        return foodTruckRepository.findAll();
    }

    public void addFood(FoodTruck foodTruck) {

        foodTruckRepository.save(foodTruck);
    }

    public void updateFoodTruck(Integer auth, FoodTruck foodTruck) {
        FoodTruck OldFoodTruck = foodTruckRepository.findFoodTruckById(auth);
        if (OldFoodTruck == null) {
            throw new ApiException("the id nt found");
        }
        foodTruck.setId(OldFoodTruck.getId());
        foodTruckRepository.save(foodTruck);
    }

    public void deleteFoodTruck(Integer auth) {
        FoodTruck food = foodTruckRepository.findFoodTruckById(auth);
        if (food == null) {
            throw new ApiException("the id nt found");
        }
        foodTruckRepository.delete(food);
    }

    public Set<FoodTruck> findOrderByCategory(String category) {
        Category categor = categoryRepository.findCategoriesByName(category);
        if (categor == null) {
            throw new ApiException("Category not found");
        }
        return categor.getFoodTrucks();
    }

    //    public  void orde(Integer id){
//        FoodTruck foodTruck=foodTruckRepository.findFoodTruckById(id);
//        if (foodTruck == null) {
//            throw new ApiException("the id nt found");
//        }
//        if (foodTruck.getCond().equals("available")) {
//            foodTruck.setCond("zz");
//            foodTruckRepository.save(foodTruck);
//        }else throw new ApiException("the food  zz ");
//    }
//
    public void acceptOrder(Integer id) {
        Ticket ticket = ticketRepository.findTicketById(id);

        if (ticket == null) {
            throw new ApiException("the id nt found");
        }

        if (ticket.getOrder().getFoodTruck().getCond().equals("Available")){
            if(ticket.getStatus()==null) {
                ticket.getOrder().getFoodTruck().setCond("tenant");
                if (ticket.getOrder().getFoodTruck().getRequests() == null) {
                    ticket.getOrder().getFoodTruck().setRequests(0);
                }
                ticket.getOrder().getFoodTruck().setRequests(ticket.getOrder().getFoodTruck().getRequests() + 1);
                foodTruckRepository.save(ticket.getOrder().getFoodTruck());
                ticket.setStatus("Accept");
                ticketRepository.save(ticket);

        } else throw new ApiException("the food  tenant");
    }else throw new ApiException("the food  2");
    }

    public void rejectOrder(Integer id) {
        Ticket ticket = ticketRepository.findTicketById(id);

        if (ticket == null) {
            throw new ApiException("the id nt found");
        }
        if (ticket.getOrder().getFoodTruck().getCond().equals("avalaible") && ticket.getStatus().equals("null") ) {
            ticket.setStatus("Reject");
            ticketRepository.save(ticket);
        } else throw new ApiException("the food  tenant");
    }


    public void endOrder(Integer id) {
        FoodTruck foodTruck = foodTruckRepository.findFoodTruckById(id);
        if (foodTruck == null) {
            throw new ApiException("the id nt found");
        }
        if (foodTruck.getCond().equals("zz")) {
            foodTruck.setCond("ava");
            foodTruckRepository.save(foodTruck);
        } else throw new ApiException("the food not zz ");
    }

    public void getAverageRatingForFood(Integer food_id) {
        FoodTruck foodTruck = foodTruckRepository.findFoodTruckById(food_id);
        List<Evaluation> evaluations = evaluationRepository.findAllByFoodTruckId(food_id);
        if (foodTruck == null) {
            throw new ApiException("food Truck id incorrect");
        }
        double totalRating = evaluations.stream().mapToInt(Evaluation::getRating).sum();
//        foodTruck.setRating(totalRating / evaluations.size());
        foodTruck.setRating(totalRating/evaluations.size());
        foodTruckRepository.save(foodTruck);
    }

    public List<FoodTruck> findFoodTruckByRating(Double min, Double max) {
        List<FoodTruck> foodTruck = foodTruckRepository.findFoodTruckByRating(min, max);
        if (foodTruck == null) {
            throw new ApiException("food Truck id incorrect");
        }
        return foodTruck;
    }


    public Services getByFoodTruckName(String name){
        FoodTruck foodTruck=foodTruckRepository.findFoodTruckByName(name);
        if (foodTruck == null) {
            throw new ApiException("food truck not found");
        }
        return foodTruck.getServices();
    }
    public void FoodTruckAcceptCheck(Integer food_id, Integer user_id) {
        FoodTruck food = foodTruckRepository.findFoodTruckById(food_id);
        User user = userRepository.findUserById(user_id);

        if (food == null || user == null) {
            throw new ApiException("car id or employee id incorrect");
        }
        if (user.getUserName().equals("admin")) {

            food.setIsChecked(true);
            foodTruckRepository.save(food);
        } else throw new ApiException("the employee not admin");

    }

    public List<FoodTruck> getAllFoodTruckNotChecked() {
        List<FoodTruck> food = foodTruckRepository.getAllFoodTruckNotChecked();
        if (food.isEmpty()) {
            throw new ApiException("All cars checked");
        }
        return food;
    }


}
