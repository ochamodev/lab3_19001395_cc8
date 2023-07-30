import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponseHandler {
    private static Logger LOGGER;
    private static String HTML_ROOT = "./src/www";
    private static String ASSETS_ROOT = "./src/www/assets";
    private static String INDEX_PATH = HTML_ROOT + "/index.html";
    private static String NOT_FOUND_PATH = HTML_ROOT + "/notFound.html";
    private static String INTERNAL_SERVER_ERROR_PATH = HTML_ROOT + "/serverError.html";

    public ResponseHandler(Logger logger) {
        LOGGER = logger;
    }

    public String createResponse(RequestObj requestObj) {
        String response = "";
        if (requestObj.method().equals("GET") || requestObj.method().equals("HEAD")) {
            if (requestObj.path().equals("/")) {
                response = handleFileExistence(INDEX_PATH, LOGGER, requestObj);
            } else {
                var rootPath = HTML_ROOT;
                var fullPath = rootPath + requestObj.path();
                LOGGER.log(Level.INFO, "[PATH]" + fullPath);
                response = handleFileExistence(fullPath, LOGGER, requestObj);
            }
        }

        return response;
    }

    private static String handleFileExistence(String path, Logger logger, RequestObj requestObj) {
        String response = "";
        if (FileUtils.checkIfFileExists(path, LOGGER)) {
            var content = FileUtils.readHtml(path);
            if (content == null) {
                content = FileUtils.readHtml(INTERNAL_SERVER_ERROR_PATH);
                response = new Response.ResponseBuilder()
                        .setStatus(StatusCodesAndMessage.INTERNAL_SERVER_ERROR)
                        .setStatusMessage(StatusCodesAndMessage.INTERNAL_SERVER_ERROR_MESSAGE)
                        .setHtmlContent(content)
                        .setContentType(MimeTypes.HTML_TYPE)
                        .build();
            } else {
                response = new Response.ResponseBuilder()
                        .setStatus(StatusCodesAndMessage.SUCCESS)
                        .setStatusMessage(StatusCodesAndMessage.SUCCESS_MESSAGE)
                        .setHtmlContent(content)
                        .setContentType(requestObj.type())
                        .build();
            }
        } else {
            var content = FileUtils.readHtml(NOT_FOUND_PATH);
            response = new Response.ResponseBuilder()
                    .setStatus(StatusCodesAndMessage.NOT_FOUND)
                    .setStatusMessage(StatusCodesAndMessage.NOT_FOUND_MESSAGE)
                    .setHtmlContent(content)
                    .setContentType(MimeTypes.HTML_TYPE)
                    .build();
        }
        return response;
    }
}
