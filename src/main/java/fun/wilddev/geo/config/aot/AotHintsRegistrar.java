package fun.wilddev.geo.config.aot;

import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.stereotype.Component;

@ImportRuntimeHints({
        ResourceHintsRegistrar.class,
        CaffeineHintsRegistrar.class
})
@Component
public class AotHintsRegistrar {

}
