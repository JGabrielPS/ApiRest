package com.example.ApiRest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiRest.entities.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer>{

}
