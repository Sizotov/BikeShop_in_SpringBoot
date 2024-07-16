package com.example.CatalogService.controller;

import com.example.CatalogService.model.Bike;
import com.example.CatalogService.service.BikeService;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Controller
public class BikeController {

    private final BikeService bikeService;
    private double revenue = 0.0;
    private double purchase = 0.0;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    // Из БД извлекается список из шести велосипедов по их ID-шникам
    @GetMapping("/bikes")
    public String catalog(Model model) {
        List<Long> ids = new ArrayList<>();
        ids.add(1L); ids.add(2L); ids.add(3L); ids.add(4L); ids.add(5L); ids.add(6L);
        model.addAttribute("bikes", bikeService.getSixBikesByIds(ids));
        return "bikes";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    // Из БД извлекается список из шести велосипедов по их ID-шникам
    // Добавляются эти 6 велосипедов и значение purchase (покупки) в качестве атрибутов в модель
    @GetMapping("/user-page")
    public String userPage(Model model) {
        List<Long> ids = new ArrayList<>();
        ids.add(1L); ids.add(2L); ids.add(3L); ids.add(4L); ids.add(5L); ids.add(6L);
        List<Bike> bikes = bikeService.getSixBikesByIds(ids);
        model.addAttribute("bikes", bikes);
        model.addAttribute("purchase", purchase);
        return "user-page"; // or admin-page
    }

    // Из БД извлекается список из шести велосипедов по их ID-шникам
    // Добавляются эти 6 велосипедов и значение revenue (продажи) в качестве атрибутов в модель
    @GetMapping("/admin-page")
    public String adminPage(Model model) {
        List<Long> ids = new ArrayList<>();
        ids.add(1L); ids.add(2L); ids.add(3L); ids.add(4L); ids.add(5L); ids.add(6L);
        List<Bike> bikes = bikeService.getSixBikesByIds(ids);
        model.addAttribute("bikes", bikes);
        model.addAttribute("revenue", revenue);
        return "admin-page";
    }

    // Удаляется велосипед из БД по его ID. К величине purchase (покупки) добавляется цена удаленного велосипеда
    // и затем возвращается "перенаправление" к "user-page".
    @PostMapping("/buy-purchase/{id}")
    public String buyBike(@PathVariable Long id) {
        Bike bike = bikeService.getBikeById(id);
        purchase += bike.getPrice();
        bikeService.deleteBikeById(id);
        return "redirect:/user-page";
    }

    // Удаляется велосипед из БД по его ID. К величине revenue (продажи) добавляется цена удаленного велосипеда
    // и затем возвращается "перенаправление" к "admin-page".
    @PostMapping ("/buy-revenue/{id}")
    public String sellBike(@PathVariable Long id) {
        Bike bike = bikeService.getBikeById(id);
        revenue += bike.getPrice();
        bikeService.deleteBikeById(id);
        return "redirect:/admin-page";
    }

}