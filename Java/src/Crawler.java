import Models.BEDocument;
import jdk.jshell.spi.ExecutionControl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
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
        Path path = Paths.get(URI.create(file));
        List<String> lines = Files.readAllLines(path);

        Set<String> res = new HashSet<String>();
        List<String> content = Files.readAllLines(path);

        for (String line: content) {
            for (String word: line.split(regex)
            ) {
                res.add(word);
            }
        }

        return res;
    }

    public void CrawlSequentially(File folder) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                CrawlSequentially(fileEntry);
            } else {
                BEDocument doc = new BEDocument();
                doc.setmId(documents.size() + 1);
                doc.setmUrl(fileEntry.getName());
                doc.setmIdxTimel(LocalDateTime.now().toString());
                doc.setmCreationTime(Files.getAttribute(fileEntry.toPath(), "creationTime").toString());
                documents.add(doc);

                Set<String> foundWords = ExtractWordsInFile(fileEntry.getName());
                for(String word : foundWords){
                    if(!words.containsKey(word)){
                        words.put(word, 1);
                    } else {
                        words.put(word, words.get(word) + 1);
                    }
                }
            }
        }
    }

    public void CrawlParallel(){
        throw new ExecutionControl.NotImplementedException();
    }
}
