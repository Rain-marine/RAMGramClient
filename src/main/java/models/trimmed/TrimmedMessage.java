package models.trimmed;

import controllers.Controllers;
import controllers.MessageController;

public class TrimmedMessage{

    private long messageId;
    private String messageText;
    private String messageDate;
    private byte[] imageArray;
    private byte[] profileImageArray;
    private MessageController.TYPE type;
    private String sender;
    private String grandSender;

    public TrimmedMessage() {
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public byte[] getImageArray() {
        return imageArray;
    }

    public void setImageArray(byte[] imageArray) {
        this.imageArray = imageArray;
    }

    public byte[] getProfileImageArray() {
        return profileImageArray;
    }

    public void setProfileImageArray(byte[] profileImageArray) {
        this.profileImageArray = profileImageArray;
    }

    public MessageController.TYPE getType() {
        return type;
    }

    public void setType(MessageController.TYPE type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGrandSender() {
        return grandSender;
    }

    public void setGrandSender(String grandSender) {
        this.grandSender = grandSender;
    }
}

