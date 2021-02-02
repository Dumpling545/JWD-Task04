package by.tc.task04.client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import by.tc.task04.transfer.Request;
import by.tc.task04.transfer.RequestType;
import by.tc.task04.transfer.Response;

public class Client {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;

    public void init() throws UnknownHostException, IOException {
        ResourceBundle serverInfo = ResourceBundle.getBundle("by.tc.task04.properties.ServerInfo");
        int port = Integer.parseInt(serverInfo.getString("portNumber"));
        String hostName = serverInfo.getString("hostAddress");
        socket = new Socket(hostName, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public Response send(Request request) throws IOException, ClassNotFoundException {
        out.writeObject(request);
        return (Response) in.readObject();
    }

    public static String convertFileToString(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String str = new String(bytes);
        return str;
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.init();
            // IMPORTANT NOTE: all tasks solved in case-SENSITIVE mode
            // send text to server side
            File textFile = new File("exampleOfText.txt");
            String requestContent = convertFileToString(textFile);
            Request setTextRequest = new Request(RequestType.SET_TEXT, requestContent);
            Response setTextResponse = client.send(setTextRequest);
            System.out.println(setTextResponse.getContent());
            // task 1
            Request task1Request = new Request(RequestType.TASK_1, "");
            Response task1Response = client.send(task1Request);
            System.out.println("task 1 response:");
            System.out.println(task1Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 2 *
            Request task2Request = new Request(RequestType.TASK_2, "");
            Response task2Response = client.send(task2Request);
            System.out.println("task 2 response:");
            System.out.println(task2Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 3
            Request task3Request = new Request(RequestType.TASK_3, "");
            Response task3Response = client.send(task3Request);
            System.out.println("task 3 response:");
            System.out.println(task3Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 4
            Request task4Request = new Request(RequestType.TASK_4, 4 + "");
            Response task4Response = client.send(task4Request);
            System.out.println("task 4 response:");
            System.out.println(task4Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 5 *
            Request task5Request = new Request(RequestType.TASK_5, "");
            Response task5Response = client.send(task5Request);
            System.out.println("task 5 response:");
            System.out.println(task5Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 6 *
            Request task6Request = new Request(RequestType.TASK_6, "");
            Response task6Response = client.send(task6Request);
            System.out.println("task 6 response:");
            System.out.println(task6Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 7 *
            Request task7Request = new Request(RequestType.TASK_7, "");
            Response task7Response = client.send(task7Request);
            System.out.println("task 7 response:");
            System.out.println(task7Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 8 *
            Request task8Request = new Request(RequestType.TASK_8, "");
            Response task8Response = client.send(task8Request);
            System.out.println("task 8 response:");
            System.out.println(task8Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 9 *
            Request task9Request = new Request(RequestType.TASK_9, "a");
            Response task9Response = client.send(task9Request);
            System.out.println("task 9 response:");
            System.out.println(task9Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 10 *
            String[] words10 = {"eu", "ut", "in", "est"};
            Request task10Request = new Request(RequestType.TASK_10, String.join(" ", words10));
            Response task10Response = client.send(task10Request);
            System.out.println("task 10 response:");
            System.out.println(task10Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 11 *
            String[] words11 = {"n", "is"};
            Request task11Request = new Request(RequestType.TASK_11, String.join(" ", words11));
            Response task11Response = client.send(task11Request);
            System.out.println("task 11 response:");
            System.out.println(task11Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 12 *
            Request task12Request = new Request(RequestType.TASK_12, 5 + "");
            Response task12Response = client.send(task12Request);
            System.out.println("task 12 response:");
            System.out.println(task12Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 13 *
            Request task13Request = new Request(RequestType.TASK_13, "s");
            Response task13Response = client.send(task13Request);
            System.out.println("task 13 response:");
            System.out.println(task13Response.getContent());
            System.out.println("_________________________________________________________________");
            /* task 14 */
            // task 15 *
            Request task15Request = new Request(RequestType.TASK_15, "first");
            Response task15Response = client.send(task15Request);
            System.out.println("task 15 response:");
            System.out.println(task15Response.getContent());
            System.out.println("_________________________________________________________________");
            // task 16 *
            String[] params16 =
                    {/* paragraph */ 1 + "", /* sentence */ 3 + "", /* word length */ 5 + "",
                            /* string to replace */ "<This word has just been replaced>"};
            Request task16Request = new Request(RequestType.TASK_16, String.join(" ", params16));
            Response task16Response = client.send(task16Request);
            System.out.println("task 16 response:");
            System.out.println(task16Response.getContent());
            System.out.println("_________________________________________________________________");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
