package view;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));//FXML
			root.getStylesheets().add(getClass().getResource("Colors.css").toExternalForm());//CSS
			AudioClip sound=new AudioClip("file:mda/sounds/menu.mp3"); sound.play();//Sound
			primaryStage.getIcons().add(new Image("file:../../mda/images/icon.png"));
			primaryStage.setTitle("Xtreme Minesweeper");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
