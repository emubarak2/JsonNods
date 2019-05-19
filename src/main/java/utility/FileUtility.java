package utility;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class FileUtility {

    private FileUtility(){};
    
    public static File getFile(String path, final String fileName) {
        File folder = new File(System.getProperty("user.dir") + "/" + path);
        Optional<File> file = Arrays.stream(folder.listFiles()).filter(d -> d.getName().equals(fileName)).findFirst();
        return file.get();
    }


    public static String convertStreamToString(String fileName) {
        File file = getFile("json", fileName);
        String text = "";
        try {
            text = IOUtils.toString(new FileInputStream(file), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}
