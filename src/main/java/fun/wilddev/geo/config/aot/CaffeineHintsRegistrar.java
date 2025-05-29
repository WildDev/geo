package fun.wilddev.geo.config.aot;

import fun.wilddev.geo.config.aot.classes.ReflectionMethod;
import org.springframework.lang.NonNull;

import java.util.*;
import org.springframework.aot.hint.*;

public class CaffeineHintsRegistrar implements RuntimeHintsRegistrar {

    private final static String INIT_METHOD = "<init>";

    private final static ReflectionMethod DEFAULT_CONSTRUCTOR = new ReflectionMethod(INIT_METHOD,
            Collections.emptySet());

    private final static ReflectionMethod COMMON_CONSTRUCTOR = new ReflectionMethod(INIT_METHOD, Set.of(
            "com.github.benmanes.caffeine.cache.Caffeine",
            "com.github.benmanes.caffeine.cache.AsyncCacheLoader",
            "boolean"));

    private List<TypeReference> buildTypeReferences(Set<String> src) {
        return src.stream().map(TypeReference::of).toList();
    }

    @SuppressWarnings("SameParameterValue")
    private void addType(ReflectionHints hints, String name, String reachableType,
                         String[] fields, ReflectionMethod[] methods) {

        hints.registerType(TypeReference.of(name), builder -> {

            builder = builder.onReachableType(TypeReference.of(reachableType));

            if (fields != null)
                for (String i : fields)
                    builder.withField(i);

            if (methods != null)
                for (ReflectionMethod i : methods)
                    builder.withMethod(i.method(), buildTypeReferences(i.argTypes()), ExecutableMode.INVOKE);
        });
    }

    private void addType(ReflectionHints hints, String name, String reachableType, String ...fields) {
        addType(hints, name, reachableType, fields, null);
    }

    private void addType(ReflectionHints hints, String name, String reachableType, ReflectionMethod ...methods) {
        addType(hints, name, reachableType, null, methods);
    }

    @Override
    public void registerHints(@NonNull RuntimeHints runtimeHints, @NonNull ClassLoader classLoader) {

        final ReflectionHints hints = runtimeHints.reflection();

        addType(hints, "com.github.benmanes.caffeine.cache.BBHeader$ReadAndWriteCounterRef",
                "com.github.benmanes.caffeine.cache.BBHeader$ReadAndWriteCounterRef", "writeCounter");

        addType(hints, "com.github.benmanes.caffeine.cache.BBHeader$ReadCounterRef",
                "com.github.benmanes.caffeine.cache.BBHeader$ReadAndWriteCounterRef", "readCounter");

        addType(hints, "com.github.benmanes.caffeine.cache.BLCHeader$DrainStatusRef",
                "com.github.benmanes.caffeine.cache.BLCHeader$DrainStatusRef", "drainStatus");

        addType(hints, "com.github.benmanes.caffeine.cache.BaseMpscLinkedArrayQueueColdProducerFields",
                "com.github.benmanes.caffeine.cache.BaseMpscLinkedArrayQueue", "producerLimit");

        addType(hints, "com.github.benmanes.caffeine.cache.BaseMpscLinkedArrayQueueConsumerFields",
                "com.github.benmanes.caffeine.cache.BaseMpscLinkedArrayQueue", "consumerIndex");

        addType(hints, "com.github.benmanes.caffeine.cache.BaseMpscLinkedArrayQueueProducerFields",
                "com.github.benmanes.caffeine.cache.BaseMpscLinkedArrayQueue", "producerIndex");

        addType(hints, "com.github.benmanes.caffeine.cache.BoundedLocalCache",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache", "refreshes");

        addType(hints, "com.github.benmanes.caffeine.cache.FS",
                "com.github.benmanes.caffeine.cache.FS", "key", "value");

        addType(hints, "com.github.benmanes.caffeine.cache.FW"
                , "com.github.benmanes.caffeine.cache.FW", "value");

        addType(hints, "com.github.benmanes.caffeine.cache.PD",
                "com.github.benmanes.caffeine.cache.PD", "value");

        addType(hints, "com.github.benmanes.caffeine.cache.PS",
                "com.github.benmanes.caffeine.cache.PS", "key", "value");

        addType(hints, "com.github.benmanes.caffeine.cache.PSA",
                "com.github.benmanes.caffeine.cache.PSA", "accessTime");

        addType(hints, "com.github.benmanes.caffeine.cache.PSR",
                "com.github.benmanes.caffeine.cache.PSR", "writeTime");

        addType(hints, "com.github.benmanes.caffeine.cache.PSW",
                "com.github.benmanes.caffeine.cache.PSW", "writeTime");

        addType(hints, "com.github.benmanes.caffeine.cache.PW",
                "com.github.benmanes.caffeine.cache.PW", "value");

        addType(hints, "com.github.benmanes.caffeine.cache.StripedBuffer",
                "com.github.benmanes.caffeine.cache.StripedBuffer", "tableBusy");

        addType(hints, "com.github.benmanes.caffeine.cache.UnboundedLocalCache",
                "com.github.benmanes.caffeine.cache.UnboundedLocalCache", "refreshes");

        addType(hints, "com.github.benmanes.caffeine.cache.CacheLoader",
                "com.github.benmanes.caffeine.cache.Caffeine",
                new ReflectionMethod("asyncLoadAll", Set.of("java.util.Set", "java.util.concurrent.Executor")),
                new ReflectionMethod("loadAll", Set.of("java.util.Set")));

        addType(hints, "com.github.benmanes.caffeine.cache.CacheLoader",
                "com.github.benmanes.caffeine.cache.LocalLoadingCache",
                new ReflectionMethod("loadAll", Set.of("java.util.Set")));

        addType(hints, "com.github.benmanes.caffeine.cache.FS",
                "com.github.benmanes.caffeine.cache.WS", DEFAULT_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.FW",
                "com.github.benmanes.caffeine.cache.WI", DEFAULT_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.PSA",
                "com.github.benmanes.caffeine.cache.SSA", DEFAULT_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.PSMS",
                "com.github.benmanes.caffeine.cache.SSSMS", DEFAULT_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.PSMW",
                "com.github.benmanes.caffeine.cache.SSMW", DEFAULT_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.PSRMS",
                "com.github.benmanes.caffeine.cache.SSMSR", DEFAULT_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.PSW",
                "com.github.benmanes.caffeine.cache.SSA", DEFAULT_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.PSW",
                "com.github.benmanes.caffeine.cache.SSW", DEFAULT_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.PSWMW",
                "com.github.benmanes.caffeine.cache.SSSMW", DEFAULT_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SI",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalManualCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SSA",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalLoadingCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SSMS",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalLoadingCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SSMSR",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalLoadingCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SSMSW",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalAsyncCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SSMSW",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalAsyncLoadingCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SSMSW",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalManualCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SSMW",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalLoadingCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SSSMS",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalManualCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SSSMSW",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalManualCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SSSMWW",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalLoadingCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.SSW",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalLoadingCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.WI",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalLoadingCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.WS",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalLoadingCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.WS",
                "com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalLoadingCache",
                COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.WS",
                "com.github.benmanes.caffeine.cache.Caffeine", COMMON_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.PSWMS",
                "com.github.benmanes.caffeine.cache.NodeFactory", DEFAULT_CONSTRUCTOR);

        addType(hints, "com.github.benmanes.caffeine.cache.PD",
                "com.github.benmanes.caffeine.cache.NodeFactory",
                new String[] { "value" }, new ReflectionMethod[] { DEFAULT_CONSTRUCTOR });

        addType(hints, "com.github.benmanes.caffeine.cache.PW",
                "com.github.benmanes.caffeine.cache.NodeFactory",
                new String[] { "value" }, new ReflectionMethod[] { DEFAULT_CONSTRUCTOR });
    }
}
