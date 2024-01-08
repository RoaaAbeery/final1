package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.DTO.AddressDto;
import com.example.foodtruck.Model.Address;
import com.example.foodtruck.Model.Profile;
import com.example.foodtruck.Model.User;
import com.example.foodtruck.Repository.AddressRepository;
import com.example.foodtruck.Repository.ProfileRepository;
import com.example.foodtruck.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    public List<Address> getAll(){
        return addressRepository.findAll();
    }
    public void addAddress(AddressDto addressDTO){

        Profile profile=profileRepository.findProfileById(addressDTO.getProfile_id());
        if (profile == null) {
            throw new ApiException("the id profile not found");
        }
        User user=userRepository.findUserById(profile.getUser().getId());
        if (user == null) {
            throw new ApiException("the id user not found");
        }

        Address address =new Address(null,addressDTO.getAddress(),addressDTO.getStartDate(),addressDTO.getNumberWeek(),addressDTO.getCity(),addressDTO.getStreet(),profile,null);
        addressRepository.save(address);
    }
    public void updateAddress(Integer auth , AddressDto addressDTO) {
        Address address=addressRepository.findAddressById(auth);
        if (address == null) {
            throw new ApiException("the id address not found");
        }
        address.setAddress(address.getAddress());
        address.setStartDate(addressDTO.getStartDate());
        address.setNumberWeek(addressDTO.getNumberWeek());
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        addressRepository.save(address);
    }
    public void deleteAddress(Integer auth){
        Address address = addressRepository.findAddressById(auth);
        if (address == null) {
            throw new ApiException("the id nt found");
        }
        addressRepository.delete(address);
    }
    public Address getByCity(String city){
        Address address=addressRepository.findAddressByCity(city);
        if(address==null){
            throw new ApiException("address not found");
        }
        return address;
    }
    public Set<Address> getByUserId(Integer id){
        User user=userRepository.findUserById(id);
        if(user==null){
            throw new ApiException("user not found");
        }
        return user.getProfile().getAddresses();
    }
}
