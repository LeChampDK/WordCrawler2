package com.example.wordcrawler2;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Crawler {
    private char[] sep = " $'!,?;.:-_**+=)([]{}<>/@&%€#".toCharArray();

    public void RunCrawler()


    public Set<String> GetWordsFromFile(String filename) throws IOException {
        Path path = Paths.get(URI.create(filename));

        Set<String> res = new HashSet<String>();
        List<String> content = Files.readAllLines(path);

        for (String line: content) {
            for (String word: line.split(String.valueOf(sep))
                 ) {
                res.add(word);
            }
        }

        return res;
    }
}
