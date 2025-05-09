package fun.wilddev.geo.repositories;

import fun.wilddev.geo.entities.LocationD;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<LocationD, String> {

    List<LocationD> findTop10ByQualifierStartsWithOrderByCity(String qualifier);
}
