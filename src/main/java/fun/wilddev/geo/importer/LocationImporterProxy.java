package fun.wilddev.geo.importer;

import fun.wilddev.geo.enums.ImportType;
import fun.wilddev.geo.exceptions.FileReaderException;
import fun.wilddev.geo.repositories.LocationRepository;
import fun.wilddev.geo.services.RegisteredImportService;

import fun.wilddev.spring.core.services.date.DurationValue;
import fun.wilddev.spring.core.services.date.formatters.DurationFormatter;

import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import fun.wilddev.spring.core.services.date.converters.*;

@Slf4j
@AllArgsConstructor
@Component
public class LocationImporterProxy implements Importer {

    private final static ImportType IMPORT_TYPE = ImportType.LOCATIONS;

    private final LocationFileImporter importer;

    private final LocationRepository locationRepository;

    private final RegisteredImportService registeredImportService;

    private final DurationConverter durationConverter;

    private final DurationFormatter durationFormatter;

    private int calcElapsed(long started) {
        return (int) (System.currentTimeMillis() - started);
    }

    private String getTimeElapsed(long started) {

        DurationValue value = new DurationValue(calcElapsed(started), TimeUnit.MILLISECONDS);
        log.debug("Duration value: {}", value);

        return durationFormatter.format(durationConverter.convert(value, TimeUnit.SECONDS));
    }

    @Override
    public int startImport() throws FileReaderException {

        if (registeredImportService.findByType(IMPORT_TYPE) == null) {

            if (locationRepository.count() > 0) {

                log.warn("Malformed data found, re-importing ...");
                locationRepository.deleteAll();
            }

            long started = System.currentTimeMillis();
            int counter = importer.startImport();
            String elapsed = getTimeElapsed(started);

            registeredImportService.setImported(IMPORT_TYPE, elapsed);
            log.info("{} locations imported in {}", counter, elapsed);

            return counter;

        } else
            log.info("Locations imported");

        return 0;
    }
}
