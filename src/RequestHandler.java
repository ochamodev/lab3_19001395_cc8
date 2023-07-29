import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler {
    private static Logger LOGGER;

    public RequestHandler(Logger logger) {
        LOGGER = logger;
    }

    public RequestObj handleRequest(String requestLine) {
        var lines = requestLine.split("\n");

        var firstLine = lines[0];

        var separatedLine = firstLine.split(" ");
        for (int i = 0; i < separatedLine.length; i++) {
            LOGGER.log(Level.INFO, "firstLine: " + separatedLine[i]);
        }
        var method = separatedLine[0];
        var path = separatedLine[1];
        var httpVersion = separatedLine[2];
        LOGGER.log(Level.INFO, "method: " + method);
        LOGGER.log(Level.INFO, "path: " + path);
        LOGGER.log(Level.INFO, "httpVersion: " + httpVersion);
        
        return new RequestObj(method, path, httpVersion);
    }

}
