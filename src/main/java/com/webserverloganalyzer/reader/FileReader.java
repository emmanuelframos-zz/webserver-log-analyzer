package com.webserverloganalyzer.reader;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class FileReader {

    public Stream<String> readFile(String filePath) throws IOException {

        Path path = Paths.get(filePath);

        return Files.lines(path);
    }

    public Long getSize(String filePath) throws IOException {

        Path path = Paths.get(filePath);

        return Files.size(path);
    }
}