import javafx.application.Application;
import javafx.stage.Stage;
import models.LoggedUser;
import models.NetworkData;
import view.StartUp;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        try {
            new NetworkData();
            LoggedUser.setMode(LoggedUser.Mode.ONLINE);
        } catch (IOException e) {
            System.err.println("connection to server failed. you're going in offline mode...");
            LoggedUser.setMode(LoggedUser.Mode.OFFLINE);
        } finally {
            launch(args);
        }

    }

    @Override
    public void start(Stage primaryStage) {
        if (LoggedUser.getMode() == LoggedUser.Mode.ONLINE)
            new StartUp().onlineInitialize(primaryStage);
        else
            new StartUp().offlineInitialize(primaryStage);
    }
}
