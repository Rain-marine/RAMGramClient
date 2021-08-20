package view;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import models.LoggedUser;
import models.requests.UserRequest;
import models.responses.Response;
import models.responses.UserResponse;
import models.trimmed.TrimmedUser;

public class InfoLoader {

    public static void loadInfo(long userId, ImageView profilePhotoImage, Label info) {
        Response response = new UserRequest( userId).execute();
        TrimmedUser user = ((UserResponse)response).getTrimmedUser();
        byte[] byteArray = user.getProfilePhoto();
        Rectangle clip = new Rectangle(
                profilePhotoImage.getFitWidth(), profilePhotoImage.getFitHeight()
        );
        clip.setArcWidth(1000);
        clip.setArcHeight(1000);
        profilePhotoImage.setClip(clip);
        profilePhotoImage.setImage(ImageController.byteArrayToImage(byteArray));
        info.setText(InfoLoader.load(user));
    }


    public static String load(TrimmedUser user){
        String lastSeen = user.getLastSeen();
        String phoneNumber = user.getPhoneNumber();
        String email = user.getEmail();
        String birthday = user.getBirthday();
        String bio =user.getBio();

        String infoText = user.getUsername() + "'s Profile" +
                "\nName: " + user.getFullName() + "\nLast seen: " + lastSeen;
        if (!bio.equals(""))
            infoText += ("\nBio: " + bio);
        if (!email.equals("not visible"))
            infoText += ("\nEmail: " + email);
        if (!birthday.equals("not visible"))
            infoText += ("\nBirthday: " + birthday);
        if (!phoneNumber.equals("not visible"))
            infoText += ("\nPhone Number: " + phoneNumber);

        return infoText;
    }
}
