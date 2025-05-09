package fun.wilddev.geo.repositories;

import fun.wilddev.geo.entities.RegisteredImport;
import fun.wilddev.geo.enums.ImportType;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisteredImportRepository extends MongoRepository<RegisteredImport, String> {

    RegisteredImport findByType(ImportType type);
}
