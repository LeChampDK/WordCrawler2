package com.example.wordcrawler2;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Crawler {
    private char[] sep = " $'!,?;.:-_**+=)([]{}<>/@&%€#".toCharArray();
    List<String> extensions = Arrays.asList(new String[]{".txt"});
    Path root = Paths.get(new File("").getAbsolutePath());

    public void runCrawler(){
        Path dir = root;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file: stream) {

            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }
    }

    public void IndexFilesIn(DirectoryStream dir, List<String> extensions) {

        System.out.println("Crawling " + dir.getClass().getName());

        for (Object file: dir) {
            
        }

//        foreach (var file in dir.EnumerateFiles())
//        if (extensions.Contains(file.Extension))
//        {
//            documents.Add(file.FullName, documents.Count+1);
//            mdatabase.InsertDocument(documents[file.FullName], file.FullName);
//            Dictionary<string, int> newWords = new Dictionary<string, int>();
//            ISet<string> wordsInFile = ExtractWordsInFile(file);
//            foreach (var aWord in wordsInFile)
//            {
//                if (!words.ContainsKey(aWord))
//                {
//                    words.Add(aWord, words.Count + 1);
//                    newWords.Add(aWord, words[aWord]);
//                }
//            }
//            mdatabase.InsertAllWords(newWords);
//
//            mdatabase.InsertAllOcc(documents[file.FullName], GetWordIdFromWords(wordsInFile));
//
//
//        }
//        foreach (var d in dir.EnumerateDirectories())
//        IndexFilesIn(d, extensions);
    }

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
