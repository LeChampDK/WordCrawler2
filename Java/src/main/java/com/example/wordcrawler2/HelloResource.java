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


    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, Worlds!";
    }


    @POST
    @Produces("text/plain")
    public String getWords() {
        crawler.runCrawler();
        return "Hello";
    }
}