import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.Buffer;

public class FileUtils {

    public static boolean checkIfFileExists(String filePath) {
        try {
            File file = new File(filePath);
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static String readHtml(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            StringBuilder htmlContent = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                htmlContent.append(line).append("\n");
            }

            // Close the resources
            bufferedReader.close();
            return htmlContent.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
