package com.example.foodtruck.Repository;

import com.example.foodtruck.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository

public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Profile findProfileById(Integer id);

}
