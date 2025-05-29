package fun.wilddev.geo.config.aot;

import org.springframework.lang.NonNull;

import org.springframework.aot.hint.*;

public class ResourceHintsRegistrar implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(@NonNull RuntimeHints hints, @NonNull ClassLoader classLoader) {
        hints.resources().registerPattern("csv/*");
    }
}
