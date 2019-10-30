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
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.Buscaminas;
import model.Casilla;


public class ControlMinesweeper implements Initializable{

	//Attributes
	private Buscaminas minesweeper;
	
	//Nodes
	@FXML
	private BorderPane pane;
	@FXML
	private Label label;
	private GridPane grid;
	@FXML
	private Button clue;
	@FXML
	private Button solve;
	@FXML
	private Button back;

	//On Action
	public void clue() {
		if(!minesweeper.darPerdio() && !minesweeper.gano()){
			minesweeper.darPista();
			showFields();
			if(minesweeper.gano()){
				label.setText("YOU WIN!");
			}
		}
	}
	
	public void solve(){
		if(!minesweeper.darPerdio() && !minesweeper.gano()){
			minesweeper.resolver();
			showFields();
		}
	}
	
	public void back(ActionEvent e){
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/Menu.fxml"));//FXML
			Parent root = (Parent) loader.load();
			root.getStylesheets().add("/view/Colors.css");//CSS
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	//Methods...
		//Initialize
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
		//Set
	public void setLevel(int level){
		minesweeper=new Buscaminas(level);
		showFields();
	}
	
		//Show
	public void showFields(){
		
		Casilla[][] fields=minesweeper.darCasillas();
		grid=new GridPane();
		pane.setCenter(grid);
		
		for(int x=0; x<fields.length; x++){//Filas
			RowConstraints row=new RowConstraints();
			row.setVgrow(Priority.ALWAYS);
			grid.getRowConstraints().add(row);
		}
		for(int y=0; y<fields[0].length; y++){//Columnas
			ColumnConstraints column=new ColumnConstraints();
			column.setHgrow(Priority.ALWAYS);
			grid.getColumnConstraints().add(column);
		}
		
		for(int x=0; x<fields.length; x++){
			for(int y=0; y<fields[0].length; y++){
				int i=x;
				int j=y;
				Button field=new Button();
				field.setMaxWidth(Double.MAX_VALUE);
				field.setMaxHeight(Double.MAX_VALUE);
				styleField(field, fields[x][y]);
				//On Action
				field.setOnMousePressed((event) -> {
					if(!minesweeper.darPerdio() && !minesweeper.gano()){
						openField(event,i,j);
						styleField(field, fields[i][j]);
					}
				});
				//...
				
				grid.add(field, y, x);
			}
		}
		
	}
	
		//Open(On Action)
	public void openField(MouseEvent e, int x, int y){//[New OnAction]
		if(e.getButton()==MouseButton.PRIMARY){
			minesweeper.abrirCasilla(x,y);
			if(minesweeper.darPerdio()){
				label.getStyleClass().add("lose");//Style
				label.setText("YOU LOSE!");
			}
			else if(minesweeper.gano()){
				label.setText("YOU WIN!");
				label.getStyleClass().add("win");//Style
			}
		}
		else if(e.getButton()==MouseButton.SECONDARY){
			minesweeper.marcar(x,y);
		}
	}
	
		//Style(On Action)
	public void styleField(Button button, Casilla field){
		//Style
		if(field.mostrarValorCasilla().equals("+")){
			button.getStyleClass().add("flag");
			button.setText("");
		}
		else{
			button.getStyleClass().removeAll("flag");
			button.setText(field.mostrarValorCasilla());
			if(field.mostrarValorCasilla().equals("*")){
				button.getStyleClass().add("mine");
				button.setText("");
			}
			else if(!field.mostrarValorCasilla().equals("-")){
				button.getStyleClass().add("f"+field.mostrarValorCasilla());
			}
		}
		//...
	}
	
}
