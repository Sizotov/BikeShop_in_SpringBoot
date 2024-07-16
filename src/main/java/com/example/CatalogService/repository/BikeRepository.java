package com.example.CatalogService.repository;

import com.example.CatalogService.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
    void deleteBikeById(Long id);
}


