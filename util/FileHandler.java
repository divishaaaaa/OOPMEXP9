package smartcart.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public static File saveBillToFile(String filename, String content) throws IOException {
        File dir = new File("bills");
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, filename);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(content);
        }
        return file;
    }
}
