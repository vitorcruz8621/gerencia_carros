package org.pitang.gerencia_carros.repository;

import org.pitang.gerencia_carros.model.CarroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<CarroModel, Integer> {}