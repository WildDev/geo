package com.wilddev.geo;

import com.wilddev.geo.importer.LocationImporterProxy;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeoApplication {

	@Autowired
	private LocationImporterProxy locationFileImporter;

	@PostConstruct
	public void init() {

		try {
			locationFileImporter.startImport();
		} catch (Exception ex) {
			throw new BeanCreationException("Failed to import locations", ex);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(GeoApplication.class, args);
	}
}
