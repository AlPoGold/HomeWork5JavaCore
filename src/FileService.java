import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileService {

    public static void backUpFile(String path, Path backUpDir){
        File root = new File(path);
          try{
                for(File file: root.listFiles()){
                    if(file.isDirectory()){
                        backUpFile(file.getAbsolutePath(), backUpDir);
                    }
                    else {
                        Path filePath = Paths.get(file.getAbsolutePath());
                        Path target = Paths.get(String.valueOf(backUpDir), file.getName());
                        System.out.println(filePath);
                        Files.copy(filePath,target, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static Path makeBackUpDir(String directory) throws IOException {

        Path backupDir = Paths.get(directory, "backup");
        Files.createDirectory(backupDir);

        return backupDir;
    }
}
