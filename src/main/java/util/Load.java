package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Alert;
import models.LoggedUser;
import models.requests.*;
import models.trimmed.TrimmedLoggedUser;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class Load {

    private static Load instance;

    public Load() {
    }

    public static Load getInstance() {
        if (instance == null)
            instance = new Load();
        return instance;
    }


    public void loadLoggedUser() {
        Gson gson = new Gson();
        File file = new File(ConfigLoader.readProperty("loggedUserData"));
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<TrimmedLoggedUser>() {
        }.getType();
        LoggedUser.setTrimmedLoggedUser(gson.fromJson(br, type));
        LoggedUser.setToken("");
    }


    public void sendRequests() {
        try {
            ArrayList<Request> requests = loadAllEvents();
            requests.forEach(Request::execute);
            for (Request request : requests) {
                System.out.println(request);
            }
            FileUtils.cleanDirectory(new File(ConfigLoader.readProperty("offlineSettingAdd")+"/"));
            LoggedUser.update();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "something went wrong while updating offline setting \n"
                    + e.getMessage());
            alert.show();
        }
    }


    public ArrayList<Request> loadAllEvents() throws IOException, ClassNotFoundException {
        File file = new File(ConfigLoader.readProperty("offlineSettingAdd")+"/");
        ArrayList<Request> allEvents = new ArrayList<>();
        for (File file1 : Objects.requireNonNull(file.listFiles())) {
            FileReader fileReader = new FileReader(file1);
            Request event = loadEventByTypeSetting(file1);
            fileReader.close();
            allEvents.add(event);
        }
        return allEvents;
    }


    private Request loadEventByTypeSetting(File file) {
        Gson gson = new Gson();
        BufferedReader br;
        try {
            FileReader reader = new FileReader(file);
            br = new BufferedReader(reader);
            Type type = getType(file.getName().replaceFirst("[.][^.]+$", ""));
            Request request = gson.fromJson(br, type);
            reader.close();
            return request;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public Type getType(String name) {
        switch (name) {
            case "ChangeAccountVisibilityRequest": {
                return new TypeToken<ChangeAccountVisibilityRequest>() {
                }.getType();
            }
            case "ChangeBirthdayRequest": {
                return new TypeToken<ChangeBirthdayRequest>() {
                }.getType();
            }
            case "ChangeInfoRequest": {
                return new TypeToken<ChangeInfoRequest>() {
                }.getType();
            }
            case "DeActiveRequest": {
                return new TypeToken<DeActiveRequest>() {
                }.getType();
            }
            default:
                return null;
        }

    }

}
