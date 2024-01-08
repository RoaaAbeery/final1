package com.example.foodtruck.Repository;

import com.example.foodtruck.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    Address findAddressById(Integer id);
    Address findAddressByCity(String city);

}
