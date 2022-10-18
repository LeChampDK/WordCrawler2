import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    private static Crawler crawler = new Crawler();
    private static String  root;

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        root = System.getProperty("user.dir") + "\\arnold-j\\arnold-j";
        File folder = new File(root);
        String folderDir = folder.getPath();
        System.out.println(folderDir);

        crawler.CrawlSequentially(folderDir);
    }
}