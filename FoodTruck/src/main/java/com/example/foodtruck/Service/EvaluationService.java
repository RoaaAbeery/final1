package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.DTO.EvaluationDTO;
import com.example.foodtruck.Model.*;
import com.example.foodtruck.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EvaluationService {
    private final UserRepository userRepository;
    private final FoodTruckRepository foodTruckRepository;
    private final EvaluationRepository evaluationRepository;
    private final TicketRepository ticketRepository;
    private final FoodTruckService foodTruckService;
    private final OrderRepository orderRepository;
    public List<Evaluation> getAllEvaluation(){
        return evaluationRepository.findAll();}
    public void add(EvaluationDTO evaluationDTO){
        FoodTruck foodTruck =foodTruckRepository.findFoodTruckById(evaluationDTO.getFoodTruck_id());
        User user =userRepository.findUserById(evaluationDTO.getUser_id()) ;
        Ticket ticket = ticketRepository.findTicketByUserIdAndFoodTruckId(evaluationDTO.getUser_id(),evaluationDTO.getFoodTruck_id());
        if (ticket == null) {
            throw new ApiException("ticket not found");
        }
        Evaluation review = new Evaluation(null,evaluationDTO.getFeedback(),evaluationDTO.getRating(),foodTruck,user);
        evaluationRepository.save(review);
        foodTruckService.getAverageRatingForFood(evaluationDTO.getFoodTruck_id());
        foodTruckRepository.save(foodTruck);
    }
    public List<FoodTruck> findAllByFeedback(String feedback) {
        List<FoodTruck> foodTrucks = new ArrayList<>();
        for (int i = 0; i < getAllEvaluation().size(); i++) {
            if (getAllEvaluation().get(i).getFeedback().contains(feedback)) {
                foodTrucks.add(getAllEvaluation().get(i).getFoodTruck());
            }
        }
        return foodTrucks;
    }

    }
