package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.LoggedUser;
import models.requests.Request;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Save {

    private static Save instance;

    public Save() {
    }

    public static Save getInstance(){
        if(instance == null)
            instance = new Save();
        return instance;
    }

    public void update(Request event, String type) {
        try {
            File file = new File(ConfigLoader.readProperty("offlineDataAdd")+type +".json");
            if (!file.exists()) {
                file.createNewFile();
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter(file, false);
            gson.toJson(event, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.printf("", e.getCause());
        }
    }

    public void saveLoggedUser() {
        try {
            File file = new File(ConfigLoader.readProperty("loggedUserData"));
            if (!file.exists()) {
                file.createNewFile();
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter(file, false);
            gson.toJson(LoggedUser.getTrimmedLoggedUser(), fileWriter);
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
