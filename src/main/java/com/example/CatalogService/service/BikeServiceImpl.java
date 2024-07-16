package com.example.CatalogService.service;

import com.example.CatalogService.aspect.ToLog;
import com.example.CatalogService.model.Bike;
import com.example.CatalogService.repository.BikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class BikeServiceImpl implements BikeService{

    private final BikeRepository bikeRepository;

    double purchase = 0.0; //переменная покупки

    public BikeServiceImpl (BikeRepository bikeRepository){
        this.bikeRepository = bikeRepository;
    }
    @Override
    @ToLog
    public List<Bike> getAllBikes(){
        bikeRepository.save(new Bike (null, "Cube", "Black", 750.0));
        bikeRepository.save(new Bike (null, "TREK", "White", 800.0));
        bikeRepository.save(new Bike (null, "GT", "Black", 700.00));
        bikeRepository.save(new Bike (null, "Merida", "Red", 500.00));
        bikeRepository.save(new Bike (null, "TREK", "Blue", 550.00));
        bikeRepository.save(new Bike (null, "BMW", "Green", 900.00));
        return bikeRepository.findAll();
    }

    @Override
    @ToLog
    public List<Bike> getSixBikesByIds(List<Long> ids) {
        return bikeRepository.findAllById(ids.subList(0, 6));
    }

    @Override
    @ToLog
    public Bike getBikeById(Long id){
        return bikeRepository.findById(id).orElse(null);
    }

    @Override
    @ToLog
    public void deleteBikeById(Long id) {
        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bike not found with Id " + id));
        bikeRepository.deleteBikeById(id);
        purchase += bike.getPrice();
    }

}
