package com.example.foodtruck.Repository;

import com.example.foodtruck.Model.Orders;
import com.example.foodtruck.Model.Ticket;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    Orders findOrderById(Integer id);
    @Query("select d from Orders d where d.date BETWEEN :start and :end")
     List<Orders> findOrdersByDateBetween(LocalDate start, LocalDate end);
    List<Orders> findOrdersByTotalPrice(Double totalPrice);
    List<Orders> findOrdersByDateAfter(LocalDate date);
    Orders findOrdersByDateEquals(LocalDate date);

//    @Query("select c from Orders c where c.user.id=?1 and c.foodTruck.id=?2")
//    Orders findTicketByUserIdAndFoodTruckId(Integer user_id, Integer food_id);



}
