package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.DTO.ProfileDTO;
import com.example.foodtruck.Model.Address;
import com.example.foodtruck.Model.Profile;
import com.example.foodtruck.Model.User;
import com.example.foodtruck.Repository.AddressRepository;
import com.example.foodtruck.Repository.ProfileRepository;
import com.example.foodtruck.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public List<Profile> getProfile(){
        return profileRepository.findAll();
    }
    public void addProfile(ProfileDTO profileDTO){
        User user=userRepository.findUserById(profileDTO.getUser_id());
        if (user == null) {
            throw new ApiException("the id user not found");
        }

        Profile profile=new Profile(null, profileDTO.getDescription(), profileDTO.getAccountCreationDate(),null,user);
        profileRepository.save(profile);
    }
    public void updateProfile(Integer auth,ProfileDTO profileDTO){
        Profile profile=profileRepository.findProfileById(auth);
        if (profile == null) {
            throw new ApiException("the id profile not found");
        }

        profile.setDescription(profileDTO.getDescription());
        profile.setAccountCreationDate(profileDTO.getAccountCreationDate());
        profileRepository.save(profile);


    }
    public void deleteProfile(Integer auth){
        Profile profile=profileRepository.findProfileById(auth);
        if (profile == null) {
            throw new ApiException("the id user not found");
        }
        profileRepository.delete(profile);
    }
    public Profile findUserById(Integer id){
        User user=userRepository.findUserById(id);
        if(user==null){
            throw new ApiException(" user id not found");
        }
        return user.getProfile();
    }

}
