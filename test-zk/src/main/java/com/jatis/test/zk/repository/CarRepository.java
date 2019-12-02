package com.jatis.test.zk.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jatis.test.zk.entity.CarEntity;

public interface CarRepository extends PagingAndSortingRepository<CarEntity, UUID> {
	
	public List<CarEntity> findByModelContaining(String name);
}
