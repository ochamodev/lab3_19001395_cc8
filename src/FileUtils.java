import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {

    public static boolean checkIfFileExists(String filePath, Logger logger) {
        try {
            String currentWorkingDir = System.getProperty("user.dir");
            logger.log(Level.INFO, "Current Working Directory: " + currentWorkingDir);
            File file = new File(filePath);
            return file.exists();
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
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
