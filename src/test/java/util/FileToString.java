package util;

import java.io.*;
import java.nio.file.Path;

public class FileToString {
    public static String read(Path path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path.toString()));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null){
            sb.append(line);
        }
        return sb.toString();
    }
}
