import java.util.logging.Logger;

public class ResponseHandler {
    private static Logger logger;

    public ResponseHandler(Logger logger) {
        this.logger = logger;
    }

    public void createResponse(RequestObj requestObj) {
        if (requestObj.method().equals("GET")) {
            if (requestObj.path().equals("/")) {
                
            }
        }
    }

}
