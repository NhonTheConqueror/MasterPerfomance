package application;
	
import javafx.application.Application;
import javafx.geometry.Pos; import javafx.geometry.Rectangle2D;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Main extends Application{
	
	public static void centerOnScreen(Stage stage) {
		Rectangle2D visualBound = Screen.getPrimary().getVisualBounds();
		stage.setX((visualBound.getWidth() - stage.getWidth())/2);
		stage.setY((visualBound.getHeight() - stage.getHeight())/2);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			/*
			 * root is the original board
			 */
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 500, 700);
			
			/**
			 * button for turn off windows defender, firewall
			 */
			Button button1 = new Button("Disable Defender & Update");
			root.setCenter(button1);
			BorderPane.setAlignment(button1, Pos.CENTER_LEFT);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			/*
			 * when app start, it placed at the center
			 * there's a same method from built-in javafx but it work like shiii
			 */
			centerOnScreen(primaryStage);
			
		}
		catch(Exception e) {
			e.printStackTrace();		
		}
	}
	public static void main(String[] agrs) {
		launch(agrs);
	}	
}
