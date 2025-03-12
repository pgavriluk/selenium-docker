package com.pavelg.tests.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ResourceLoader {

    private static final Logger logger = LoggerFactory.getLogger(ResourceLoader.class);

    public static InputStream getResource(String path) throws IOException {
        logger.info("Reading resource from location {}", path);
        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        if(Objects.nonNull(stream)){
            return stream;
        }

        return Files.newInputStream(Path.of(path));
    }
}
