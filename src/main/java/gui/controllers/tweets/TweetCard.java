package gui.controllers.tweets;

import controllers.ProfileAccessController;
import gui.controllers.ImageController;
import gui.controllers.SceneLoader;
import gui.controllers.popups.AlertBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.LoggedUser;
import models.requests.*;
import models.responses.BooleanResponse;
import models.responses.ListResponse;
import models.responses.Response;
import models.responses.TweetResponse;
import models.trimmed.TrimmedTweet;
import util.ConfigLoader;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;

public class TweetCard {

    private long tweetId;
    private TrimmedTweet trimmedTweet;
    private long writeId;
    private VBox vBox;
    private Label tweetText;
    private ImageView profilePhoto;
    private Label dateTime;

    private Button writerName;
    private Button save;
    private Button forward;
    private Button comments;

    private TextField commentText = new TextField();
    private Button addComment;
    private VBox addCommentLayout = new VBox(5);
    private Button commentImage;
    private byte[] commentImageArray;

    private Button retweet;
    private Button block;
    private Button mute;
    private Button report;
    private Button like;

    private HBox header;
    private HBox buttons = new HBox(10);
    private HBox generalButtons = new HBox(10);
    private ImageView tweetPhoto;
    private Label likedNumber;

    public enum MODE {OWNER, TIMELINE, EXPLORER, PROFILE}


    public TweetCard(long tweetId, MODE mode) {
        this.tweetId = tweetId;
        Response response = new TweetRequest(LoggedUser.getToken() , LoggedUser.getId() , tweetId).execute();
        this.trimmedTweet = ((TweetResponse)response).getTrimmedTweet();
        if (trimmedTweet.getWriterId() == LoggedUser.getId()) {
            mode = MODE.OWNER;
        }
        MODE finalMode = mode;
        writeId = trimmedTweet.getWriterId();
        vBox = new VBox(5);
        vBox.setPadding(new Insets(0, 0, 0, 0));
        vBox.setPrefWidth(430);
        vBox.setStyle("-fx-background-color: #000000");
        tweetText = new Label(trimmedTweet.getTweetText());
        tweetText.setWrapText(true);
        tweetText.setTextFill(Color.SNOW);
        tweetText.setFont(Font.font(15));

        writerName = new Button(trimmedTweet.getWriterUsername());
        writerName.setOnAction(event -> {
            if (finalMode != MODE.PROFILE) {
                ProfileAccessController profileAccessController = new ProfileAccessController(finalMode == MODE.EXPLORER ? 1 : (finalMode == MODE.TIMELINE ? 2 : 3), writeId, 0);
                SceneLoader.getInstance().changeScene(profileAccessController.checkAccessibility(), event);
            }
        });
        writerName.setStyle("-fx-background-color: #000000");
        writerName.setPrefHeight(50);
        writerName.setTextFill(Color.MEDIUMORCHID);
        writerName.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        dateTime = new Label(trimmedTweet.getDate());
        dateTime.setTextFill(Color.DARKVIOLET);

        profilePhoto = new ImageView();
        profilePhoto.setFitHeight(50);
        profilePhoto.setFitWidth(50);
        byte[] byteArray = trimmedTweet.getWriterProfile();
        Rectangle clip = new Rectangle(
                profilePhoto.getFitWidth(), profilePhoto.getFitHeight()
        );
        clip.setArcWidth(1000);
        clip.setArcHeight(1000);
        profilePhoto.setClip(clip);
        profilePhoto.setImage(ImageController.byteArrayToImage(byteArray));
        header = new HBox(10);

        tweetPhoto = new ImageView();
        if (trimmedTweet.getTweetImage() != null) {
            tweetPhoto.setImage(ImageController.byteArrayToImage(trimmedTweet.getTweetImage()));
            tweetPhoto.setPreserveRatio(true);
            tweetPhoto.setFitWidth(350);
        }

        save = new Button("save");
        save.setStyle("-fx-background-color: #690081");
        save.setTextFill(Color.LEMONCHIFFON);
        save.setOnAction(event -> {
            new TweetActionRequest(LoggedUser.getToken() ,LoggedUser.getId() , tweetId , TweetActionRequest.TWEET_ACTION.SAVE).execute();
            AlertBox.display("done!", "tweet saved");
        });

        forward = new Button("forward");
        forward.setStyle("-fx-background-color: #690081");
        forward.setTextFill(Color.LEMONCHIFFON);
        forward.setOnAction(event -> ForwardTweet.display(tweetId));


        comments = new Button("comments");
        comments.setStyle("-fx-background-color: #690081");
        comments.setTextFill(Color.LEMONCHIFFON);
        comments.setOnAction(event -> {
            if (trimmedTweet.getCommentsIds().size() == 0) {
                AlertBox.display("empty", "no comments to show");
            } else {
                Response response3 = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.COMMENT , tweetId).execute();
                TweetShowerGuiController.setListOfTweets(((ListResponse)response3).getIds());
                TweetShowerGuiController.setPreviousMenu(finalMode == MODE.EXPLORER ? 1 : (finalMode == MODE.TIMELINE ? 2 : (finalMode == MODE.OWNER ? 6 : 5)));
                SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("tweetShower"), event);
            }
        });

        commentImage = new Button("add Image");
        commentImage.setOnAction(event -> {
            try {
                commentImageArray = ImageController.pickImage();
            } catch (SizeLimitExceededException e) {
                AlertBox.display("size limit error", "Image size is too large. \nImage size should be less than 2MB");
            }
        });

        commentText.setPromptText("write comment");
        commentText.setStyle("-fx-background-color: #36214d;" + "-fx-text-fill: #ffd29e;");
        commentText.setMaxWidth(300);
        addComment = new Button("add comment");
        addComment.setStyle("-fx-background-color: #690081");
        addComment.setTextFill(Color.LEMONCHIFFON);
        addComment.setOnAction(event -> {
            String commentTextString = commentText.getText();
            if ((!commentTextString.equals("")) || (commentImageArray != null)) {
                new AddCommentRequest(LoggedUser.getToken() , LoggedUser.getId() , tweetId , commentTextString ,commentImageArray == null ? null : commentImageArray).execute();
            }
        });
        commentImage.setStyle("-fx-background-color: #690081");
        commentImage.setTextFill(Color.LEMONCHIFFON);

        Label separator = new Label("*********************************************");
        separator.setTextFill(Color.VIOLET);
        HBox row = new HBox(5);
        row.getChildren().addAll(commentImage, addComment);
        addCommentLayout.getChildren().addAll(commentText, row);
        generalButtons.getChildren().addAll(save, forward, comments);

        likedNumber = new Label();
        likedNumber.setTextFill(Color.MAGENTA);
        ArrayList<String> peopleLiked = trimmedTweet.getLikedUsernames();
        likedNumber.setText("liked by " + peopleLiked.size() + " people: " + String.join(", ", peopleLiked));

        if (finalMode != MODE.OWNER) {

            retweet = new Button("retweet");
            retweet.setStyle("-fx-background-color: #690081");
            retweet.setTextFill(Color.LEMONCHIFFON);
            retweet.setOnAction(event -> {
                new TweetActionRequest(LoggedUser.getToken() ,LoggedUser.getId() , tweetId , TweetActionRequest.TWEET_ACTION.RETWEET).execute();

                AlertBox.display("done!", "retweeted!");
            });

            block = new Button("block");
            block.setStyle("-fx-background-color: #690081");
            block.setTextFill(Color.LEMONCHIFFON);
            block.setOnAction(event -> {
                new UserActionRequest(LoggedUser.getToken() ,LoggedUser.getId() , writeId , UserActionRequest.USER_ACTION.BLOCK).execute();
                AlertBox.display("done!", "user blocked");
            });

            mute = new Button("mute");
            mute.setStyle("-fx-background-color: #690081");
            mute.setTextFill(Color.LEMONCHIFFON);
            mute.setOnAction(event -> {
                new UserActionRequest(LoggedUser.getToken() ,LoggedUser.getId() , writeId , UserActionRequest.USER_ACTION.MUTE).execute();

                AlertBox.display("done!", "user muted!");
            });

            report = new Button("report");
            report.setStyle("-fx-background-color: #690081");
            report.setTextFill(Color.LEMONCHIFFON);
            report.setOnAction(event -> {
                Response response1 = new TweetActionRequest(LoggedUser.getToken() ,LoggedUser.getId() , tweetId , TweetActionRequest.TWEET_ACTION.REPORT).execute();
                boolean isDeleted = ((BooleanResponse)response1).isResult();
                if (isDeleted) {
                    AlertBox.display("refresh", "you need to refresh the page");
                } else {
                    AlertBox.display("done!", "tweet reported!");
                }
            });

            boolean isLiked = trimmedTweet.getLikedUsernames().stream().anyMatch(it -> it.equals(LoggedUser.getUsername()));
            like = new Button(isLiked ? "liked" : "like");
            like.setStyle("-fx-background-color: #690081");
            like.setTextFill(Color.LEMONCHIFFON);
            like.setOnAction(event -> {
                if (!isLiked) {
                    new TweetActionRequest(LoggedUser.getToken() ,LoggedUser.getId() , tweetId , TweetActionRequest.TWEET_ACTION.LIKE).execute();
                    like.setText("liked");
                    updateLikedList();
                }
            });
            header.getChildren().addAll(profilePhoto, writerName);
            header.getChildren().add(new VBox(5, dateTime, generalButtons));
            buttons.getChildren().addAll(like, report, retweet, block, mute);
            vBox.getChildren().addAll(header, tweetText, tweetPhoto, likedNumber, buttons, addCommentLayout, separator);
        } else {
            header.getChildren().addAll(profilePhoto, writerName );
            header.getChildren().add(new VBox(5, dateTime , generalButtons));
            vBox.getChildren().addAll(header, tweetText, tweetPhoto, likedNumber, addCommentLayout, separator);
        }


    }

    public void updateLikedList() {
        likedNumber.setTextFill(Color.MAGENTA);
        Response response = new TweetRequest(LoggedUser.getToken() , LoggedUser.getId() , tweetId).execute();
        this.trimmedTweet = ((TweetResponse)response).getTrimmedTweet();
        ArrayList<String> peopleLiked = trimmedTweet.getLikedUsernames();
        likedNumber.setText("liked by " + peopleLiked.size() + " people: " + String.join(", ", peopleLiked));

    }

    public VBox getVBox() {
        return vBox;
    }

    public void setVBox(VBox vBox) {
        this.vBox = vBox;
    }

}
