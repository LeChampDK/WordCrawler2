import Models.BEDocument;
import jdk.jshell.spi.ExecutionControl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class Crawler {

    Map<String, Integer> words = new HashMap<String, Integer>();
    List<BEDocument> documents = new ArrayList<BEDocument>();

    public Set<String> ExtractWordsInFile(String file) throws IOException {
        String regex = "([^a-zA-Z']+)'*\\1*";
        Path path = Paths.get(file);
//        Path path = Paths.get(URI.create(file));

        Set<String> res = new HashSet<String>();

        List<String> content = Files.readAllLines(path, StandardCharsets.UTF_8);

        for (String line : content) {
            for (String word : line.split(regex)
            ) {
                res.add(word);
            }
        }

        return res;
    }

    public void CrawlSequentially(String folder) throws IOException {

        File dir = new File(folder);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                // Check if the file is a directory
                if (file.isDirectory()) {
                    // We will not print the directory name, just use it as a new
                    // starting point to list files from
                    CrawlSequentially(file.getAbsolutePath());
                } else {
                    if (!file.getPath().endsWith(".txt")) {
                        continue;
                    }

                    BEDocument doc = new BEDocument();
                    doc.setmId(documents.size() + 1);
                    doc.setmUrl(file.getName());
                    doc.setmIdxTimel(LocalDateTime.now().toString());
                    doc.setmCreationTime(Files.getAttribute(file.toPath(), "creationTime").toString());
                    documents.add(doc);

                    String absolutePath = file.getAbsolutePath();

                    Set<String> foundWords = ExtractWordsInFile(absolutePath);
                    for (String word : foundWords) {
                        if (!words.containsKey(word)) {
                            words.put(word, 1);
                        } else {
                            words.put(word, words.get(word) + 1);
                        }

                    }

                    // We can use .length() to get the file size
                    System.out.println(file.getName() + " (size in bytes: " + file.length() + ")");
                }
            }
        }

        System.out.println(words.size());
        System.out.println(documents.size());
    }

    public void CrawlParallel() {
        //throw new ExecutionControl.NotImplementedException();
    }
}
