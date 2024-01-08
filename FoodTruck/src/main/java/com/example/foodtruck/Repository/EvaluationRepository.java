package com.example.foodtruck.Repository;

import com.example.foodtruck.Model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Integer> {
    Evaluation findEvaluationById(Integer id);
    List<Evaluation> findAllById(Integer id);
    List<Evaluation> findAllByFoodTruckId(Integer id);


}
