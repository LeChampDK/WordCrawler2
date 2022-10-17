package com.example.wordcrawler2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Path("/hello-world")
public class HelloResource {
    Crawler crawler = new Crawler();
    List<String> extensions = Arrays.asList(new String[]{".txt"});
    java.nio.file.Path root = Paths.get(new File("").getAbsolutePath());

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, Worlds!";
    }


    @POST
    @Produces("text/plain")
    public String getWords() {
        java.nio.file.Path dir = root;
        try (DirectoryStream<java.nio.file.Path> stream = Files.newDirectoryStream(dir)) {
            for (java.nio.file.Path file: stream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }
        return "Hello";
    }
}