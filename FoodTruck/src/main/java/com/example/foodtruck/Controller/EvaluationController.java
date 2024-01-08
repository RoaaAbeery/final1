package com.example.foodtruck.Controller;

import com.example.foodtruck.DTO.EvaluationDTO;
import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Service.EvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/evaluation")
public class EvaluationController {
    private final EvaluationService evaluationService;
    @GetMapping("/get")
    public ResponseEntity GetAllEvaluation(){
        return ResponseEntity.status(HttpStatus.OK).body(evaluationService.getAllEvaluation());
    }
    @PostMapping("/add")
    public ResponseEntity AddEvaluation(@RequestBody @Valid EvaluationDTO evaluation){
        evaluationService.add(evaluation);
        return ResponseEntity.status(HttpStatus.OK).body("added Evaluation");
    }

    @GetMapping("/findAllByFeedback/{c}")
    public ResponseEntity findAllByFeedback(@PathVariable String feesback){
        List<FoodTruck> cars = evaluationService.findAllByFeedback(feesback);
        return ResponseEntity.status(200).body(cars);
    }
}
