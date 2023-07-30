public class Response {
    private String status;
    private String statusMessage;

    private String htmlContent;

	public String getStatus() {
		return status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

    private Response(ResponseBuilder builder) {
        this.status = builder.status;
        this.statusMessage = builder.statusMessage;
        this.htmlContent = builder.htmlContent;
    }

    public static class ResponseBuilder {
        private String status;
        private String statusMessage;
        private String htmlContent;
        private String contentType;

        private static String CRLF = "\r\n";

        public ResponseBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ResponseBuilder setStatusMessage(String statusMessage) {
            this.statusMessage = statusMessage;
            return this;
        }

        public ResponseBuilder setHtmlContent(String htmlContent) {
            this.htmlContent = htmlContent;
            return this;
        }

        public ResponseBuilder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public String build() {
            StringBuilder builder = new StringBuilder();

            return builder
            .append(String.format("HTTP/1.1 %s %s", status, statusMessage))
            .append("\n")
            .append(String.format("Content-type: %s", contentType))
            .append(CRLF)
            .append(String.format("Content-length: %d", htmlContent.length()))
            .append(CRLF)
            .append(CRLF)
            .append(htmlContent.trim())
            .toString();
        }
    }

}
