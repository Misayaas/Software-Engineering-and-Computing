import java.io.*;

public class FileReader {
    public String readFile(String filePath) throws IOException{
        FileInputStream fis = new FileInputStream(filePath);
        byte[] data = new byte[fis.available()];
        fis.read(data);
        fis.close();
        return new String(data);
    }
}