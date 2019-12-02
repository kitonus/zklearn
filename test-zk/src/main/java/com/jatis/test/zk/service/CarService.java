package com.jatis.test.zk.service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jatis.test.zk.entity.CarEntity;
import com.jatis.test.zk.repository.CarRepository;

@Component("carService")
public class CarService {
	
	@Autowired
	private CarRepository carRepo;
	public List<CarEntity> findAll(){
		Iterable<CarEntity> carIt = carRepo.findAll();
		List<CarEntity> result = new LinkedList<CarEntity>();
		for (CarEntity car : carIt) {
			result.add(car);
		}
		return result;
	}
	
	public List<CarEntity> search(String keyword){
		List<CarEntity> cars = carRepo.findByModelContaining(keyword == null ? "" : keyword);
		return cars;
	}
	
	@Transactional
	public CarEntity save(CarEntity selected) {
		return carRepo.save(selected);
	}

	public CarEntity findById(UUID id) {
		return carRepo.findById(id).orElse(null);
	}
}
