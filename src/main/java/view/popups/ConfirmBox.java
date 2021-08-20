package view.popups;

import javafx.scene.image.Image;

import java.util.Objects;

public interface ConfirmBox {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    Image icon = new Image(Objects.requireNonNull(classloader.getResourceAsStream("Images/warning.png")));

//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,message);
//        alert.show();

}
