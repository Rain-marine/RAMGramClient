package gui.controllers.messages;

import controllers.Controllers;
import gui.controllers.SceneLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import models.LoggedUser;
import models.requests.ChatRequest;
import models.requests.MessageRequest;
import models.responses.ChatResponse;
import models.responses.MessageResponse;
import models.responses.Response;
import models.trimmed.TrimmedChat;
import util.ConfigLoader;

public class ChatCard {

    private TrimmedChat trimmedChat;
    private final long chatId;
    private VBox card;
    private final boolean isGroup;

    public ChatCard(Long chatId) {
        Response response = new ChatRequest(LoggedUser.getToken() , LoggedUser.getId() , chatId).execute();
        this.trimmedChat = ((ChatResponse)response).getTrimmedChat();
        this.chatId = chatId;
        this.card = new VBox(5);
        this.isGroup = trimmedChat.isGroup();

        Label name = new Label(trimmedChat.getName());
        name.setFont(Font.font(14));
        name.setTextFill(Color.INDIGO);

        Label unseenCountLabel = new Label(trimmedChat.getUnseenCount());
        unseenCountLabel.setFont(Font.font(11));
        unseenCountLabel.setTextFill(Color.MEDIUMVIOLETRED);

        Button goToChat = new Button("Show");
        goToChat.setId(String.valueOf(this.chatId));
        goToChat.setOnAction(event -> {
            if (this.isGroup){
                GroupChatShowerGuiController.setGroupId(chatId);
                SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("groupChat"), event);
            }
            else{
                ChatShowerGuiController.setChatId(chatId);
                SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("chat"), event);
            }

        });

        HBox row = new HBox(10);
        row.getChildren().addAll(name, goToChat);

        card.getChildren().addAll(row, unseenCountLabel);
    }

    public VBox getCard() {
        return card;
    }

    public void setCard(VBox card) {
        this.card = card;
    }
}
