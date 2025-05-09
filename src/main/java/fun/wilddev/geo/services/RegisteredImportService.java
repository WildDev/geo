package fun.wilddev.geo.services;

import fun.wilddev.geo.entities.RegisteredImportD;
import fun.wilddev.geo.enums.ImportType;
import fun.wilddev.geo.repositories.RegisteredImportRepository;

import lombok.AllArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegisteredImportService {

    private final RegisteredImportRepository registeredImportRepository;

    public RegisteredImportD findByType(@NonNull ImportType type) {
        return registeredImportRepository.findByType(type);
    }

    public void setImported(@NonNull ImportType type, @NonNull String elapsed) {
        registeredImportRepository.save(new RegisteredImportD(type, elapsed));
    }
}
