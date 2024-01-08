package com.example.foodtruck.Controller;

import com.example.foodtruck.DTO.OrderDTO;
import com.example.foodtruck.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/order")
public class OrderControoler {
    private final OrderService orderService;
    @GetMapping("/get")
    public ResponseEntity GetAllOrder(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAll());
    }
    @PostMapping("/add")
    public  ResponseEntity AddOrder(@RequestBody @Valid OrderDTO orderDTO){
        orderService.addOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.OK).body("added Order");
    }
    @PutMapping("/put/{id}")
    public ResponseEntity UpdateOrder(@PathVariable Integer id,@RequestBody @Valid OrderDTO orderDTO){
        orderService.updateOrder(id, orderDTO);
        return ResponseEntity.status(HttpStatus.OK).body("update Order");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete Order");
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrderById(id));
    }
    @GetMapping("/getByFoodTruckId/{id}")
    public ResponseEntity getByFoodTruckId(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findFoodTruckById(id));
    }
    @GetMapping("/getByTotalPrice/{totalPrice}")
    public ResponseEntity getByTotalPrice(@PathVariable Double totalPrice){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrdersByTotalPrice(totalPrice));
    }
    @GetMapping("/getByDate/{start}/{end}")
    public ResponseEntity getByRangDate(@PathVariable LocalDate start, @PathVariable LocalDate end){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrdersByDate(start, end));
    }
    @GetMapping("/getByEqualDate/{date}")
    public ResponseEntity getByEqualDate(@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrdersByDateEqual(date));
    }
//    @GetMapping("/getByBeforeDate/{date}")
//    public ResponseEntity getByBeforeDate(@PathVariable LocalDate date){
//        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrdersBefore(date));
//    }
}
