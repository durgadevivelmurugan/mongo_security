package com.mon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mon.entity.AggregationEx;

@Repository
public interface AggregateRepo extends MongoRepository<AggregationEx, String>{

	Optional<AggregationEx> findByUsername(String username);
	List<AggregationEx> findByUsernameAndPlace(String username,String place);

}
