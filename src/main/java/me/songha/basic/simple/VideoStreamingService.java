package me.songha.basic.simple;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VideoStreamingService {
    private final Path videoLocation = Paths.get("C:\\Users\\user\\OneDrive\\바탕 화면");

    public Resource loadVideo(String filename) {
        try {
            Path file = videoLocation.resolve(filename);
            return new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not load file: " + filename, e);
        }
    }
}