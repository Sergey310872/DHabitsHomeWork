package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Reader implements FileReader {
    @Override
    public Stream<String> readLines(File file) {
        try {
//            System.out.println("Stream: " + Thread.currentThread().getName());
            return Files.lines(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
