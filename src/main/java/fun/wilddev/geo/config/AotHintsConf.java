package fun.wilddev.geo.config;

import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import org.springframework.aot.hint.*;

@ImportRuntimeHints(AotHintsConf.AotHintsRegistrar.class)
@Component
public class AotHintsConf {

    public static class AotHintsRegistrar implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(@NonNull RuntimeHints hints, @NonNull ClassLoader classLoader) {
            hints.resources().registerPattern("csv/*");
        }
    }
}
