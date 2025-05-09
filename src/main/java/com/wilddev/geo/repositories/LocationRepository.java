package com.wilddev.geo.repositories;

import com.wilddev.geo.entities.Location;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {

    List<Location> findTop10ByQualifierStartsWithOrderByCity(String qualifier);
}
