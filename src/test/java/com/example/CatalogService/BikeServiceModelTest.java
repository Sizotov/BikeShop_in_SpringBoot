package com.example.CatalogService;

import com.example.CatalogService.model.Bike;
import com.example.CatalogService.repository.BikeRepository;
import com.example.CatalogService.service.BikeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BikeServiceModelTest {

    @InjectMocks
    private BikeServiceImpl bikeServiceImpl;

    @Mock
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

        // Устанавливаем поведение заглушки bikeRepository для метода findAll(), чтобы он возвращал expectedBikes.
        given(bikeRepository.findAll()).willReturn(expectedBikes);

        // Вызываем метод getAllBikes сервиса BikeService для получения фактического списка велосипедов.
        List<Bike> actualBikes = bikeServiceImpl.getAllBikes();

        // Проверяем, что ожидаемый список велосипедов равен фактическому списку.
        assertEquals(expectedBikes, actualBikes);

        // Проверяем, что метод findAll() у bikeRepository был вызван один раз.
        verify(bikeRepository, times(1)).findAll();
    }
}
