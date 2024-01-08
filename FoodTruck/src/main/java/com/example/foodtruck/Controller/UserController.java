package com.example.foodtruck.Controller;

import com.example.foodtruck.DTO.UserDTO;
import com.example.foodtruck.Model.User;
import com.example.foodtruck.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity GetAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity AddUser(@RequestBody @Valid UserDTO user){
        userService.addClient(user);
        return ResponseEntity.status(HttpStatus.OK).body("added Client");
    }
    @PutMapping("/put/{id}")
    public ResponseEntity UpdateUser(@PathVariable Integer id,@RequestBody @Valid User user){
        userService.updateClint(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("update Client");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete Client");
    }
    @GetMapping("/check")
    private ResponseEntity check(){

        return ResponseEntity.status(200).body(userService.check());
    }
    @GetMapping("/best/{number}/{re}")
    private ResponseEntity  best(@PathVariable Double number,@PathVariable Integer re){
        return ResponseEntity.status(200).body(userService.best(number,re));
    }

}
