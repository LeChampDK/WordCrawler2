import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import static java.lang.System.nanoTime;

public class Main {
    private static Crawler crawler = new Crawler();
    private static String  root;

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        root = System.getProperty("user.dir") + "\\arnold-j\\arnold-j";
        File folder = new File(root);
        String folderDir = folder.getPath();
        System.out.println(folderDir);

        long startTime = nanoTime();
        crawler.CrawlSequentially(folderDir);
        long estimatedTime = nanoTime() - startTime;
        long estimatedTimeinMiliseconds = TimeUnit.NANOSECONDS.toMillis(estimatedTime);
        System.out.println("time in nanoseconds: " + estimatedTime);
        System.out.println("time in Miliseconds: " + estimatedTimeinMiliseconds);
    }
}