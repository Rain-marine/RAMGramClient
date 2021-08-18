package controllers;

import models.LoggedUser;
import models.requests.SendMessageRequest;
import models.types.SendMessageType;

import java.util.List;

public class ChatController {

    public ChatController() {
    }


    public void createGroupChat(List<String> members, String name) {
        new SendMessageRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                SendMessageType.NEW_GROUP ,
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
                SendMessageType.ADD_MEMBER ,
                chatId ,
                null ,
                null ,
                null,
                null ,
                member).execute();

    }

}