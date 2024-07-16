package com.example.CatalogService;

import com.example.CatalogService.model.Bike;
import com.example.CatalogService.repository.BikeRepository;
import com.example.CatalogService.service.BikeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest

public class BikeServiceIntegrationTest {

    @Autowired
    private BikeServiceImpl bikeServiceImpl;

    @MockBean
    private BikeRepository bikeRepository;


    @Test
    public void testGetAllBikes() {

        // Создаем экземпляр класса
        Bike bike = new Bike();
        bike.setId(1L);
        bike.setBrand("Cube");
        bike.setColor("Black");
        bike.setPrice(Double.valueOf("700.00"));

        // Создаем ожидаемый список Bike, содержащий один велосипед.
        List<Bike> expectedBikes = Collections.singletonList(bike);

        // Задаем поведение заглушки bikeRepository для метода findAll(), чтобы он возвращал expectedBikes.
        when(bikeRepository.findAll()).thenReturn(expectedBikes);

        // Вызываем метод getAllBikes сервиса BikeService для получения фактического списка велосипедов.
        List<Bike> actualBikes = bikeServiceImpl.getAllBikes();

        // Проверяем, что ожидаемый список велосипедов равен фактическому списку.
        assertEquals(expectedBikes, actualBikes);

        // Проверяем, что метод findAll() у bikeRepository был вызван один раз.
        verify(bikeRepository, times(1)).findAll();
    }
}
