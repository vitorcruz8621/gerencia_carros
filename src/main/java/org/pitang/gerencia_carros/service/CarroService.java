package org.pitang.gerencia_carros.service;

import java.util.List;
import java.util.Optional;

import org.pitang.gerencia_carros.model.CarroModel;
import org.pitang.gerencia_carros.repository.CarroRepository;
import org.springframework.stereotype.Service;

@Service
public class CarroService {
	private final CarroRepository carroRepository;
	
	public CarroService(CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}

	public List<CarroModel> getAllCarros() {
		return carroRepository.findAll();
	}

	public Optional<CarroModel> getCarroById(Integer id) {
		return carroRepository.findById(id);
	}

	public CarroModel saveCarro(CarroModel carromodel) {
		return carroRepository.save(carromodel);
	}

	public void deleteCarro(Integer id) {
		carroRepository.deleteById(id);
	}
}
