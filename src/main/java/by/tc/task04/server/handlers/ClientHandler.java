package by.tc.task04.server.handlers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import by.tc.task04.server.context.ClientContext;
import by.tc.task04.server.exceptions.ParseException;
import by.tc.task04.transfer.Request;
import by.tc.task04.transfer.Response;

public class ClientHandler extends Thread {
    private Socket socket;
    private List<RequestHandler> requestHandlers;
    private static ThreadLocal<ClientContext> context = new ThreadLocal<ClientContext>() {  
        @Override protected ClientContext initialValue()   
        {  
            return new ClientContext();  
        }  
    };  
    public ClientHandler(Socket socket) {
        this.socket = socket;
        requestHandlers = context.get().getRequestHandlers();
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            Request request;
            Response response = null;
            while ((request = (Request) in.readObject()) != null) {
                for(RequestHandler requestHandler : requestHandlers){
                    response = requestHandler.handle(request, context.get());
                    if(response != null){
                        break;
                    }
                }
                if(response != null){
                    out.writeObject(response);
                } else {
                    System.err.println("Unsupported request type");
                }
            }
            socket.close();
        } catch (IOException|ClassNotFoundException|CloneNotSupportedException|ParseException e) {
            System.err.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
