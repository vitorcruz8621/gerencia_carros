package org.pitang.gerencia_carros.controller;


import java.util.List;

import org.pitang.gerencia_carros.model.CarroModel;
import org.pitang.gerencia_carros.service.CarroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carros")
public class CarroController {
    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public ResponseEntity<List<CarroModel>> getAllCarroModels() {
        return ResponseEntity.ok(carroService.getAllCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroModel> getCarById(@PathVariable Integer id) {
        return carroService.getCarroById(id)
            .map(entity -> ResponseEntity.ok(entity))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CarroModel> createCarroModel(@RequestBody CarroModel carmodel) {
        return ResponseEntity.ok(carroService.saveCarro(carmodel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroModel> updateCarroModel(@PathVariable Integer id, @RequestBody CarroModel carmodel) {
        return ResponseEntity.ok(carroService.saveCarro(carmodel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarroModel(@PathVariable Integer id) {
        carroService.deleteCarro(id);
        return ResponseEntity.noContent().build();
    }
}
    