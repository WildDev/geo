package com.wilddev.geo.repositories;

import com.wilddev.geo.entities.RegisteredImport;
import com.wilddev.geo.enums.ImportType;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisteredImportRepository extends MongoRepository<RegisteredImport, String> {

    RegisteredImport findByType(ImportType type);
}
