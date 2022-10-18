import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CrawlerParallel extends Thread {
    public Set<String> ExtractWordsInFileParrallel(String file) throws IOException {
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

        return res;
    }
}
