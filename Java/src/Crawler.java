import java.io.File;

public class Crawler {

    public void CountWordsInFile(String fileName){
        
    }

    public void GetFileFromFolder(File folder){
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                GetFileFromFolder(fileEntry);
            } else {
                CountWordsInFile(fileEntry.getName());
            }
        }
    }
}
