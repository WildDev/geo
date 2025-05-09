package fun.wilddev.geo.importer;

import fun.wilddev.geo.entities.Location;
import fun.wilddev.geo.exceptions.FileReaderException;
import fun.wilddev.geo.models.Country;
import fun.wilddev.geo.repositories.LocationRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.opencsv.*;

import java.io.*;
import java.util.*;

@Slf4j
@Component
public class LocationFileImporter implements Importer {

    private final static int BUFF_SIZE = 100;

    private final String filename;

    private final Character separator;

    private final LocationRepository locationRepository;

    public LocationFileImporter(@Value("${locations.file.name}") String filename,
                                @Value("${locations.file.separator}") Character separator,
                                LocationRepository locationRepository) {

        this.filename = filename;
        this.separator = separator;
        this.locationRepository = locationRepository;
    }

    private CSVReader getCsvReader(Reader reader) {
        return new CSVReaderBuilder(reader)
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(separator)
                        .withIgnoreQuotations(true)
                        .build())
                .build();
    }

    public int startImport() throws FileReaderException {

        int counter = 0;

        try (InputStream is = new ClassPathResource(filename).getInputStream();
             Reader reader = new InputStreamReader(is);
             CSVReader csv = getCsvReader(reader)) {

            String[] line;
            List<Location> buff = new ArrayList<>(BUFF_SIZE);

            for (int i = 0; (line = csv.readNext()) != null; i++) {

                if (i % BUFF_SIZE == 0) {

                    locationRepository.saveAll(buff);
                    buff.clear();

                    log.debug("Flushed");

                } else {

                    if (line.length < 3) {

                        log.debug("Bad entry, skipping ...");
                        continue;
                    }

                    Location location = new Location(new Country(line[1], line[0]), line[2]);

                    buff.add(location);
                    counter++;

                    log.info("Location read: {}", location);
                }
            }

        } catch (Exception ex) {
            throw new FileReaderException("Failed to read location file", ex);
        }

        return counter;
    }
}
