package controllers;

import models.*;
import models.requests.SendMessageRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repository.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChatController implements Repository {

    public ChatController() {
    }


    public void createGroupChat(List<String> members, String name) {
        new SendMessageRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                SendMessageRequest.TYPE.NEW_GROUP ,
                0L ,
                members ,
                null ,
                name,
                null ,
                null).execute();

    }

    public void addMemberToGroupChat(String member, long chatId) {
        new SendMessageRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                SendMessageRequest.TYPE.ADD_MEMBER ,
                chatId ,
                null ,
                null ,
                null,
                null ,
                member).execute();

    }

}