package org.nhlstenden.jabberpoint.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


public class ResourceAccessor {
    public static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    public static InputStream getResource(String resource) {
        if (resource == null) {
            throw new IllegalArgumentException("Resource file name is null.");
        }

        InputStream stream = classLoader.getResourceAsStream(resource);

        if (stream == null) {
            throw new IllegalStateException("Resource file not found: " + resource);
        }

        return stream;
    }

    public static String getResourceAsString(String resource) {
        try (InputStream resourceStream = getResource(resource);
             BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new IllegalStateException("Error reading resource: " + resource, e);
        }
    }
}
