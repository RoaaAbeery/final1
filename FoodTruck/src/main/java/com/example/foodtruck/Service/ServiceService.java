package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.DTO.ServiceDTO;
import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Model.Services;
import com.example.foodtruck.Repository.FoodTruckRepository;
import com.example.foodtruck.Repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final FoodTruckRepository foodTruckRepository;

    public List<Services> getFood(){
        return serviceRepository.findAll();
    }
    public void AddService(ServiceDTO serviceDTO){
        FoodTruck foodTruck=foodTruckRepository.findFoodTruckById(serviceDTO.getFood_truck_id());
        if (foodTruck == null) {
            throw new ApiException("the food truck not found");
        }
        Services services=new Services(null,serviceDTO.getProduct(),serviceDTO.getSize(),serviceDTO.getBasicDevice(),serviceDTO.getSideDevice(),serviceDTO.getBrief(),serviceDTO.getStartDate(),foodTruck);
        serviceRepository.save(services);
    }
    public void updateService(Integer auth,ServiceDTO serviceDTO){
//        FoodTruck foodTruck=foodTruckRepository.findFoodTruckById(auth);
        Services service=serviceRepository.findServiceById(auth);
        if (service == null) {
            throw new ApiException("the food truck not found");
        }
        service.setProduct(serviceDTO.getProduct());
        service.setBasicDevice(serviceDTO.getBasicDevice());
        service.setSideDevice(serviceDTO.getSideDevice());
        service.setBrief(serviceDTO.getBrief());
        service.setStartDate(serviceDTO.getStartDate());
        serviceRepository.save(service);

    }
    public void deleteService(Integer auth){
        Services services=serviceRepository.findServiceById(auth);
        if (services == null) {
            throw new ApiException("the id service not found");
        }
        serviceRepository.delete(services);
    }

public Services getByFoodTruck(String name){
        FoodTruck foodTruck=foodTruckRepository.findFoodTruckByName(name);
        if(foodTruck==null){
            throw new ApiException("food Truck not found");
        }
        return  foodTruck.getServices();
}
}
