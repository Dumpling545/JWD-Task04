package by.tc.task04.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;
import by.tc.task04.server.handlers.ClientHandler;

public class Server {
    public void start(){
        int port = Integer.parseInt(ResourceBundle.getBundle("by.tc.task04.properties.ServerInfo")
                .getString("portNumber"));
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                (new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
