package fun.wilddev.geo.repositories;

import fun.wilddev.geo.entities.RegisteredImportD;
import fun.wilddev.geo.enums.ImportType;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisteredImportRepository extends MongoRepository<RegisteredImportD, String> {

    RegisteredImportD findByType(ImportType type);
}
