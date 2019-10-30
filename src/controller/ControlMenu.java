package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Buscaminas;


public class ControlMenu implements Initializable{

	//Nodes
	@FXML
	private Button easy;
	@FXML
	private Button normal;
	@FXML
	private Button hard;
	
	//On Action
	public void loadEasy(ActionEvent e){
		loadGame(e,Buscaminas.PRINCIPIANTE);
	}
	
	public void loadNormal(ActionEvent e){
		loadGame(e,Buscaminas.INTERMEDIO);
	}
	
	public void loadHard(ActionEvent e){
		loadGame(e,Buscaminas.EXPERTO);
	}
	
	//Methods...
		//Initialize
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
		//Load
	public void loadGame(ActionEvent e, int level){
		
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/Minesweeper.fxml"));//FXML
			Parent root = (Parent) loader.load();
			root.getStylesheets().add("/view/Colors.css");//CSS
			
			ControlMinesweeper nextController=loader.getController();
			nextController.setLevel(level);
			
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	//..........
}
