package view;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));//FXML
			root.getStylesheets().add(getClass().getResource("Colors.css").toExternalForm());//CSS
			primaryStage.setTitle("Xtreme Minesweeper");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
//			primaryStage.setMinHeight(primaryStage.getHeight());
//			primaryStage.setMinWidth(primaryStage.getWidth());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
