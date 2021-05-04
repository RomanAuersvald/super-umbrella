package com.reported.sparest.auth;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class SecretKeyProvider {
    public byte[] getKey() throws URISyntaxException, IOException {
        File file = new File(getClass().getResource("/sample.key").getFile());
        return Files.readAllBytes(Paths.get(this.getClass().getResource("/sample.key").toURI()));//Files.readAllBytes(Path.of(file.toURI()));
    }
}