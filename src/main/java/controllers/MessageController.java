package controllers;

import models.LoggedUser;
import models.requests.AddContentRequest;
import models.requests.MessageAccessRequest;
import models.requests.SendMessageRequest;
import models.responses.BooleanResponse;
import models.responses.ExploreResponse;
import models.responses.Response;
import models.types.AddContentType;
import models.types.MessageAccessType;
import models.types.SendMessageType;

import java.util.ArrayList;
import java.util.List;

public class MessageController{

    public MessageController() {
    }

    public boolean canSendMessageToUser(String userToSendMessage) {
        return (Boolean) new MessageAccessRequest(LoggedUser.getToken() , LoggedUser.getId() , MessageAccessType.USER , userToSendMessage).execute().unleash();
    }

    public boolean canSendMessageToGroup(String groupToSendMessage) {
        return (Boolean)  new MessageAccessRequest(LoggedUser.getToken() , LoggedUser.getId() , MessageAccessType.GROUP , groupToSendMessage).execute().unleash();
    }

    public void sendMessage(String message, byte[] image, List<String> users, List<String> groupsToSendMessage) {
        new SendMessageRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                SendMessageType.SEND ,
                0L ,
                users ,
                groupsToSendMessage,
                message,
                image ,
                null).execute();
    }



    public void forwardTweet(long tweetId , String receiver) {
        new SendMessageRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                SendMessageType.FORWARD_TWEET ,
                tweetId ,
                null ,
                null ,
                null,
                null ,
                receiver).execute();
    }

    public void forward(long messageID, List<String> users, List<String> factions) {
        new SendMessageRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                SendMessageType.FORWARD ,
                messageID ,
                users ,
                factions ,
                null,
                null ,
                null).execute();
    }



    public long getChatWithUser(long userId) {
        Response response = new SendMessageRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                SendMessageType.CHAT ,
                userId ,
                null ,
                null ,
                null,
                null ,
                null).execute();
        return ((ExploreResponse)response).getFoundUserId();
    }

    public void sendLink(String username, long chatId) {
        new SendMessageRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                SendMessageType.LINK ,
                0L,
                new ArrayList<>() {{
                    add(username);
                }},
                null ,
                String.valueOf(chatId),
                null ,
                null).execute();

    }

    public void sendInvitation(String username, long chatId) {
        new SendMessageRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                SendMessageType.INVITE ,
                0L,
                new ArrayList<>() {{
                    add(username);
                }},
                null ,
                String.valueOf(chatId),
                null ,
                null).execute();
    }


    public enum TYPE {EDIT, DELETE, BOTH, NONE}

}