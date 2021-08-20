package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.LoggedUser;
import models.requests.Request;
import models.trimmed.TrimmedLoggedUser;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class Load {

    private static Load instance;

    public Load() {
    }

    public static Load getInstance(){
        if(instance == null)
            instance = new Load();
        return instance;
    }


    public void loadLoggedUser(){
        Gson gson = new Gson();
        File file = new File(ConfigLoader.readProperty("loggedUserData"));
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<TrimmedLoggedUser>(){}.getType();
        LoggedUser.setTrimmedLoggedUser(gson.fromJson(br, type));
        System.out.println(LoggedUser.getTrimmedLoggedUser()+ "\n" + LoggedUser.getUsername());
    }

    public ArrayList<Request> loadAllEvents(int authToken) throws IOException {
//        Config config = Config.getConfig("dataBaseAddress");
//        File file = new File(config.getProperty(String.class, "offlineSettingDirectory"));
//        ArrayList<Request> allEvents = new ArrayList<>();
//        for (File file1 : Objects.requireNonNull(file.listFiles())) {
//            FileReader fileReader = new FileReader(file1);
//            String name = file1.getName();
//            int length = name.length();
//            name = name.substring(0 , length - 5);
//            Request event = loadEventByTypeSetting(Integer.parseInt(name) , fileReader);
//            fileReader.close();
//            Objects.requireNonNull(event).setAuthToken(authToken);
//            allEvents.add(event);
//        }
//        clearDirectory(file);
//        return allEvents;
        return null;
    }
}
