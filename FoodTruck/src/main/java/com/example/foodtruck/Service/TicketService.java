package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.DTO.TicketDTO;
import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Model.Orders;
import com.example.foodtruck.Model.Ticket;
import com.example.foodtruck.Model.User;
import com.example.foodtruck.Repository.FoodTruckRepository;
import com.example.foodtruck.Repository.OrderRepository;
import com.example.foodtruck.Repository.TicketRepository;
import com.example.foodtruck.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FoodTruckRepository foodTruckRepository;
    public List<Ticket> getAllOrder(){
        return ticketRepository.findAll();
    }
    public void addTicket(TicketDTO ticketDTO){
        Orders order=orderRepository.findOrderById(ticketDTO.getOrder_id());
        if (order == null) {
            throw new ApiException("the order not found");
        }
        User user=userRepository.findUserById(order.getUser().getId());
        if (user == null) {
            throw new ApiException("the id user not found");
        }
        FoodTruck foodTruck=foodTruckRepository.findFoodTruckById(ticketDTO.getFood_id());
        Ticket ticket=new Ticket(null,ticketDTO.getStatus(),foodTruck,order,user);
        ticket.setStatus(null);
        ticketRepository.save(ticket);
    }
    public void updateTicket(Integer auth,TicketDTO ticketDTO){
        Ticket ticket=ticketRepository.findTicketById(auth);
        if (ticket == null) {
            throw new ApiException("the ticket not found");
        }


        ticket.setStatus(ticketDTO.getStatus());
        ticketRepository.save(ticket);


    }
    public void deleteTicket(Integer auth){
        Ticket ticket=ticketRepository.findTicketById(auth);
        if (ticket == null) {
            throw new ApiException("the id user not found");
        }
        ticketRepository.delete(ticket);
    }
    public Set<Ticket> findByUserId(Integer id){
        User user=userRepository.findUserById(id);
        if(user==null){
            throw new ApiException(" user id not found");
        }
        return user.getTicket();
    }
}
