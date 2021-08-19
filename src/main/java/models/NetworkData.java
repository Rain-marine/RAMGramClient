package models;

import javafx.scene.control.Alert;
import models.requests.Request;
import models.responses.ConnectionErrorResponse;
import models.responses.Response;
import org.codehaus.jackson.map.ObjectMapper;
import util.ConfigLoader;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class NetworkData {

    public static Socket socket;
    public static Scanner scanner;
    public static PrintWriter printWriter;
    public static ObjectMapper objectMapper;

    public NetworkData() throws IOException {
        objectMapper = new ObjectMapper();
        try {
            socket = new Socket(ConfigLoader.readNetworkProperty("host"), Integer.parseInt(ConfigLoader.readNetworkProperty("port")));
            try {
                scanner = new Scanner(socket.getInputStream());
                printWriter = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            socket = new Socket("localhost", Integer.parseInt(ConfigLoader.readNetworkProperty("defaultPort")));
            try {
                scanner = new Scanner(socket.getInputStream());
                printWriter = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static Response sendRequest(Request request) {
        try {
            String s = objectMapper.writeValueAsString(request);
            printWriter.println(s);
            Response response = objectMapper.readValue(NetworkData.scanner.nextLine(), Response.class);
            try {
                ConnectionErrorResponse errorResponse = (ConnectionErrorResponse) response;
                System.err.println(errorResponse.getMessage() + "\nTerminate the application");
                Alert alert = new Alert(Alert.AlertType.ERROR, errorResponse.getMessage());
                alert.show();

            }
            catch (ClassCastException classCastException){
                return response;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close() {
        try {
            scanner.close();
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
