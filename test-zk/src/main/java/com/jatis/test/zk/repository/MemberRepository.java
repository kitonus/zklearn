package com.jatis.test.zk.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jatis.test.zk.entity.MemberEntity;

public interface MemberRepository extends PagingAndSortingRepository<MemberEntity, String> {

	public List<MemberEntity> findAll();
}
