package controllers;

import models.*;
import models.requests.MessageAccessRequest;
import models.requests.SendMessageRequest;
import models.responses.BooleanResponse;
import models.responses.ExploreResponse;
import models.responses.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageController implements Repository{

    public MessageController() {
    }

    public boolean canSendMessageToUser(String userToSendMessage) {
        Response accessResponse = new MessageAccessRequest(LoggedUser.getToken() , LoggedUser.getId() , MessageAccessRequest.TYPE.USER , userToSendMessage).execute();
        return ((BooleanResponse)accessResponse).isResult();
    }

    public boolean canSendMessageToGroup(String groupToSendMessage) {
        Response accessResponse = new MessageAccessRequest(LoggedUser.getToken() , LoggedUser.getId() , MessageAccessRequest.TYPE.GROUP , groupToSendMessage).execute();
        return ((BooleanResponse)accessResponse).isResult();
    }

    public void sendMessage(String message, byte[] image, List<String> users, List<String> groupsToSendMessage) {
        new SendMessageRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                SendMessageRequest.TYPE.SEND ,
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
                SendMessageRequest.TYPE.FORWARD_TWEET ,
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
                SendMessageRequest.TYPE.FORWARD ,
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
                SendMessageRequest.TYPE.CHAT ,
                userId ,
                null ,
                null ,
                null,
                null ,
                null).execute();
        return ((ExploreResponse)response).getFoundUserId();
    }


    public enum TYPE {EDIT, DELETE, BOTH, NONE}

}