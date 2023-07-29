import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.System.err;
import static java.lang.System.out;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private static Logger LOGGER;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public ClientHandler(Socket clientSocket, Logger logger) {
        this.clientSocket = clientSocket;
        LOGGER = logger;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String requestLine;
            StringBuilder requestBuilder = new StringBuilder();
            while ((requestLine = reader.readLine()) != null && !requestLine.isEmpty()) {
            	requestBuilder.append(requestLine).append("\n");
            }

            LOGGER.log(Level.INFO, "[Request]\n\r" + requestBuilder.toString().trim()); 
            var reqHandler = new RequestHandler(LOGGER);
            var reqObj = reqHandler.handleRequest(requestBuilder.toString().trim()); 
            var responseGenerator = new ResponseHandler(LOGGER);

            writer.println("HTTP/1.1 200 OK\r\n\r\nHello, world!");
            
            
            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
        	LOGGER.log(Level.WARNING, "Error: " + e.getMessage() );
            e.printStackTrace();
        }
    }
}
