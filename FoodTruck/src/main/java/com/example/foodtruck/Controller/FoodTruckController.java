package com.example.foodtruck.Controller;

import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Service.FoodTruckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/food")
public class FoodTruckController {
    private final FoodTruckService foodTruckService;

    @GetMapping("/get")
    public ResponseEntity GetAllFoodTruck(){
        return ResponseEntity.status(HttpStatus.OK).body(foodTruckService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity AddFoodTruck(@RequestBody @Valid FoodTruck foodTruck){
        foodTruckService.addFood(foodTruck);
        return ResponseEntity.status(HttpStatus.OK).body("added FoodTruck");
    }
    @PutMapping("/put/{id}")
    public ResponseEntity UpdateFoodTruck(@PathVariable Integer id,@RequestBody @Valid FoodTruck foodTruck){
        foodTruckService.updateFoodTruck(id, foodTruck);
        return ResponseEntity.status(HttpStatus.OK).body("update FoodTruck");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFoodTruck(@PathVariable Integer id){
        foodTruckService.deleteFoodTruck(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete FoodTruck");
    }


    @GetMapping("/getByCategory/{category}")
    public ResponseEntity getByCategory(@PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(foodTruckService.findOrderByCategory(category));
    }
    @GetMapping("/getByRating/{min}/{max}")
    public ResponseEntity getByRating(@PathVariable Double min,@PathVariable Double max){
        return ResponseEntity.status(HttpStatus.OK).body(foodTruckService.findFoodTruckByRating(min,max));
    }
    @PutMapping("/accept/{id}")
    public ResponseEntity acceptOrder(@PathVariable Integer id){
        foodTruckService.acceptOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body("accept order");
    }
    @PutMapping("/reject/{id}")
    public ResponseEntity rejectOrder(@PathVariable Integer id){
        foodTruckService.rejectOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body("reject order");
    }
    @PutMapping("/endOrder/{id}")
    public ResponseEntity endOrder(@PathVariable Integer id){
        foodTruckService.endOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body(" endOrder");
    }
    @PutMapping("/findOrder/{ca}")
    public ResponseEntity findOrderBycate(@PathVariable String ca){
        foodTruckService.findOrderByCategory(ca);
        return ResponseEntity.status(HttpStatus.OK).body(" find Order Bycate");
    }



}
