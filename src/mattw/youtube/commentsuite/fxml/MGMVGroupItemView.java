package mattw.youtube.commentsuite.fxml;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import mattw.youtube.commentsuite.ImageCache;
import mattw.youtube.commentsuite.db.GroupItem;

import java.io.IOException;

public class MGMVGroupItemView extends HBox {

    private GroupItem groupItem;

    @FXML ImageView icon;
    @FXML Label title;
    @FXML Label author;
    @FXML Label type;

    public MGMVGroupItemView(GroupItem groupItem) {
        this.groupItem = groupItem;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MGMVGroupItemView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
            icon.setImage(groupItem.getDefaultThumb());
            title.setText(groupItem.getTitle());
            author.setText(groupItem.getChannelTitle());
            type.setText(groupItem.getTypeName());

            new Thread(() -> {
                Image image = ImageCache.findOrGetImage(groupItem);
                Platform.runLater(() -> icon.setImage(image));
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GroupItem getGroupItem() {
        return this.groupItem;
    }
}
