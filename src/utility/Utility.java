package utility;

import java.io.*;
import java.nio.file.Path;

public abstract class Utility {

    public static void createInfoFile(File file, Path path, String fileName, FileWriter fileWriter, String exceptionMessage,
                                      BufferedWriter bufferedWriter, PrintWriter outFile) {
        file = new File(path + fileName);
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            System.out.println(exceptionMessage);
            e.printStackTrace();
        }
        bufferedWriter = new BufferedWriter(fileWriter);
        outFile = new PrintWriter(bufferedWriter);
    }

}
