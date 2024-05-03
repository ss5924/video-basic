package me.songha.basic.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping(value = "/simple")
public class VideoController {
    @Autowired
    private VideoStreamingService videoStreamingService;

    @GetMapping("/video/{filename}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String filename,
                                                @RequestHeader HttpHeaders headers) throws IOException {
        Resource video = videoStreamingService.loadVideo(filename);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.valueOf(Files.probeContentType(video.getFile().toPath())));
        
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(video);
    }
}