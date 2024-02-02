import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path backUp = FileService.makeBackUpDir(".\\src");

        FileService.backUpFile(".\\src", backUp);

    }
}