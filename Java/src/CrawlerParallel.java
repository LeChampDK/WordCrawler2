import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CrawlerParallel extends Thread {
    String file;

    Set<String> words;

    public CrawlerParallel(String fileName){
        file = fileName;
    }
    public void run(String file) throws IOException, InterruptedException {
        ExtractWordsInFileParrallel(file);
    }

    public void ExtractWordsInFileParrallel(String file) throws IOException, InterruptedException {
        System.out.println(this.getName() + ": Thread...");
        String regex = "([^a-zA-Z']+)'*\\1*";
        Path path = Paths.get(file);

        Set<String> res = new HashSet<String>();

        List<String> content = Files.readAllLines(path, StandardCharsets.UTF_8);

        for (String line : content) {
            for (String word : line.split(regex)
            ) {
                res.add(word);
            }
        }

        words = res;
        //return res;
    }
}
