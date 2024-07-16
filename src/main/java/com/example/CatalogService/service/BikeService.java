package com.example.CatalogService.service;

import com.example.CatalogService.model.Bike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Component
@Service
public interface BikeService {

    List<Bike> getAllBikes();
    List<Bike> getSixBikesByIds(List<Long> ids);
    //Метод удаляет велосипед и увеличивает сумму покупки
    @Modifying
    @Transactional
    void deleteBikeById(Long id);
    Bike getBikeById(Long id);
}
