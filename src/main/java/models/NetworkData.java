package models;

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
            socket = new Socket(ConfigLoader.readNetworkProperty("host"),Integer.parseInt(ConfigLoader.readNetworkProperty("port")));
            try {
                this.scanner = new Scanner(socket.getInputStream());
                this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e){
            socket = new Socket("localhost", Integer.parseInt(ConfigLoader.readNetworkProperty("defaultPort")));
            try {
                this.scanner = new Scanner(socket.getInputStream());
                this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

}
