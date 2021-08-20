package view.gui.controllers.messages;

import controllers.ChatController;
import controllers.MessageController;
import view.ImageController;
import view.SceneLoader;
import view.popups.messaging.EditMessage;
import view.popups.messaging.Forward;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import models.LoggedUser;
import models.requests.AddContentRequest;
import models.requests.ChatRequest;
import models.requests.MessageActionRequest;
import models.requests.MessageRequest;
import models.responses.ChatResponse;
import models.responses.MessageResponse;
import models.responses.Response;
import models.trimmed.TrimmedChat;
import models.trimmed.TrimmedMessage;
import models.types.AddContentType;
import models.types.ChatType;
import models.types.MessageActionType;
import util.ConfigLoader;

import java.util.ArrayList;

public class MessageCard {

    private TrimmedMessage trimmedMessage;
    private final long messageId;
    private VBox card;
    private ImageView messageImage;
    private final HBox buttonRow;
    private final HBox header;
    private MessageController.TYPE type;
    private final ArrayList<Button> buttons;

    public MessageCard(long messageId) {
        Response response = new MessageRequest( messageId).execute();
        this.trimmedMessage = ((MessageResponse) response).getTrimmedMessage();
        this.messageId = messageId;
        this.card = new VBox(5);
        this.header = new HBox(3);
        this.messageImage = new ImageView();
        this.buttonRow = new HBox(3);
        this.messageImage = new ImageView();
        this.buttons = new ArrayList<>();

        loadCard();

    }

    private void loadCard() {
        card.getChildren().clear();
        buttonRow.getChildren().clear();
        header.getChildren().clear();
        Label messageText = new Label(trimmedMessage.getMessageText());
        Label messageDate = new Label(trimmedMessage.getMessageDate());

        byte[] imageArray = trimmedMessage.getImageArray();

        if (imageArray != null) {
            messageImage.setImage(ImageController.byteArrayToImage(imageArray));
            messageImage.setPreserveRatio(true);
            messageImage.setFitWidth(200);
        }

        byte[] profileImageArray = trimmedMessage.getProfileImageArray();


        messageText.setTextFill(Color.PURPLE);
        messageText.setFont(Font.font(14));


        messageDate.setTextFill(Color.DARKVIOLET);
        messageDate.setFont(Font.font(9));

        String sender = trimmedMessage.getSender();
        String grandSender = trimmedMessage.getGrandSender();
        this.type = trimmedMessage.getType();

        ImageView profilePhoto = new ImageView();
        profilePhoto.setFitHeight(30);
        profilePhoto.setFitWidth(30);
        Rectangle clip = new Rectangle(
                profilePhoto.getFitWidth(), profilePhoto.getFitHeight()
        );
        clip.setArcWidth(1000);
        clip.setArcHeight(1000);
        profilePhoto.setClip(clip);
        profilePhoto.setImage(ImageController.byteArrayToImage(profileImageArray));

        String forwardInfo = sender.equals(grandSender) ? "" : "forwarded from " + grandSender;

        header.getChildren().addAll(profilePhoto, new Label(sender + ": "), new Label(forwardInfo));


        Label status = new Label(trimmedMessage.getSender().equals(LoggedUser.getUsername()) ? trimmedMessage.getStatus().toString() : "");

        initializeButtonRow();

        card.getChildren().addAll(header, messageText, messageImage, status, messageDate, buttonRow);
        card.setId(String.valueOf(this.messageId));
    }

    private void initializeButtonRow() {
        Button delete = new Button("delete");
        Button edit = new Button("edit");
        Button forward = new Button("forward");
        Button save = new Button("save");
        buttons.add(forward);
        buttons.add(save);
        buttonRow.getChildren().add(save);

        switch (type) {
            case NONE -> buttonRow.getChildren().add(forward);
            case DELETE -> {
                buttonRow.getChildren().addAll(forward, delete);
                buttons.add(delete);
            }
            case EDIT -> {
                buttonRow.getChildren().addAll(forward, edit);
                buttons.add(edit);
            }
            case BOTH -> {
                buttonRow.getChildren().addAll(forward, edit, delete);
                buttons.add(delete);
                buttons.add(edit);
            }
        }

        for (Button button : buttons) {
            button.setId(String.valueOf(this.messageId));
            button.setFont(Font.font(10));
        }

        delete.setOnAction(event -> {
            long id = Long.parseLong(delete.getId());
            new MessageActionRequest( MessageActionType.DELETE, id).execute();
            loadDeletedCard();
        });

        edit.setOnAction(event -> {
            boolean isEdited = EditMessage.display(Long.parseLong(edit.getId()));
            if (isEdited) {
                Response response = new MessageRequest( messageId).execute();
                this.trimmedMessage = ((MessageResponse) response).getTrimmedMessage();
                loadCard();
            }

        });

        forward.setOnAction(event -> Forward.display(Long.parseLong(forward.getId())));

        save.setOnAction(event -> new AddContentRequest(AddContentType.SAVE_MESSAGE , null ,null,0L ,Long.parseLong(save.getId())).execute());

        switch (trimmedMessage.getLink()){
            case NONE -> {
            }
            case CHAT-> {
                long chatId = Long.parseLong(trimmedMessage.getMessageText());
                Button goToChat = new Button("Show");
                Response response = new ChatRequest(LoggedUser.getToken() , LoggedUser.getId() , chatId , ChatType.VIEW , trimmedMessage.getSender()).execute();
                try{
                    TrimmedChat trimmedChat = ((ChatResponse) response).getTrimmedChat();
                    goToChat.setOnAction(event -> {
                        if (trimmedChat.isGroup()){
                            GroupChatShowerGuiController.setGroupId(trimmedChat.getId());
                            SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("groupChat"), event);
                        }
                        else{
                            ChatShowerGuiController.setChatId(trimmedChat.getId());
                            SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("chat"), event);
                        }
                    });
                }catch (ClassCastException e){
                    goToChat.setOnAction(event -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Access Denied!");
                        alert.show();
                    });
                }
                buttonRow.getChildren().add(goToChat);
            }
            case INVITE -> {
                long groupId = Long.parseLong(trimmedMessage.getMessageText());
                Button join = new Button("join");
                join.setOnAction(event -> {
                    new ChatController().addMemberToGroupChat(LoggedUser.getUsername() , groupId);
                    GroupChatShowerGuiController.setGroupId(groupId);
                    SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("groupChat"), event);
                });
                buttonRow.getChildren().add(join);
            }
        }
    }

    private void loadDeletedCard() {
        card.getChildren().clear();
        buttonRow.getChildren().clear();
        header.getChildren().clear();
        Label messageText = new Label("message deleted");

        byte[] profileImageArray = trimmedMessage.getProfileImageArray();
        String sender = trimmedMessage.getSender();
        ImageView profilePhoto = new ImageView();
        profilePhoto.setFitHeight(30);
        profilePhoto.setFitWidth(30);
        Rectangle clip = new Rectangle(
                profilePhoto.getFitWidth(), profilePhoto.getFitHeight()
        );
        clip.setArcWidth(1000);
        clip.setArcHeight(1000);
        profilePhoto.setClip(clip);
        profilePhoto.setImage(ImageController.byteArrayToImage(profileImageArray));


        header.getChildren().addAll(profilePhoto, new Label(sender + ": "));

        card.getChildren().addAll(header, messageText);
        card.setId(String.valueOf(this.messageId));
    }

    public VBox getCard() {
        return card;
    }

    public void setCard(VBox card) {
        this.card = card;
    }
}
