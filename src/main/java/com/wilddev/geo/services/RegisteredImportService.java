package com.wilddev.geo.services;

import com.wilddev.geo.entities.RegisteredImport;
import com.wilddev.geo.enums.ImportType;
import com.wilddev.geo.repositories.RegisteredImportRepository;

import lombok.AllArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegisteredImportService {

    private final RegisteredImportRepository registeredImportRepository;

    public RegisteredImport findByType(@NonNull ImportType type) {
        return registeredImportRepository.findByType(type);
    }

    public void setImported(@NonNull ImportType type, @NonNull String elapsed) {
        registeredImportRepository.save(new RegisteredImport(type, elapsed));
    }
}
