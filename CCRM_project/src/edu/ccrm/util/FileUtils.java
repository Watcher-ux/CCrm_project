package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileUtils {
    
    // This method uses Files.walk to recursively traverse the directory stream
    public static long calculateDirectorySize(Path path) throws IOException {
        if (Files.notExists(path)) {
            return 0L;
        }

        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                .filter(Files::isRegularFile) // We only want the size of files, not directories
                .mapToLong(p -> {
                    try {
                        return Files.size(p);
                    } catch (IOException e) {
                        return 0L; // If a file can't be accessed, treat its size as 0
                    }
                })
                .sum();
        }
    }
}