package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application{
	
	//use for set the primeryStage on screen's center properly
	public void centerOnScreen(Stage stage) {
		Rectangle2D visualBound = Screen.getPrimary().getVisualBounds();
		stage.setX((visualBound.getWidth() - stage.getWidth())/2);
		stage.setY((visualBound.getHeight() - stage.getHeight())/2);
	}
	
	@Override
	public void start(Stage primaryStage) {
	    try {
	        //THE MAIN CONTAINER
	        BorderPane mainLayout = new BorderPane();
	        mainLayout.setPadding(new Insets(20)); // add space between obj, container


	        //the pane which contain buttons, center
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(30); //horizon gap between buttons
	        grid.setVgap(30); //vertical gap between buttons

	        Button button1 = new Button("Disable\n Defender & Update");
	        Button button2 = new Button("Performance's insight");
	        Button button3 = new Button("Terminate\n background apps");
	        Button button4 = new Button("Clean Ram & Storage");

	        // add a CSS class to all buttons for styling
	        button1.getStyleClass().add("modern-button");
	        button2.getStyleClass().add("modern-button");
	        button3.getStyleClass().add("modern-button");
	        button4.getStyleClass().add("modern-button");

	        // add buttons to grid, also position
	        grid.add(button1, 0, 0);//top left
	        grid.add(button2, 1, 0);//top right
	        grid.add(button3, 0, 1);//bot left
	        grid.add(button4, 1, 1);//bot right
	        //grid contain those buttons
	        mainLayout.setCenter(grid);
	        
	        
	        //slider
	        VBox box = new VBox();
	        box.setAlignment(Pos.CENTER);

	        box.getStyleClass().add("box");
	        
	        HBox labels = new HBox(140);
	        labels.setAlignment(Pos.TOP_CENTER);
	        Label min = new Label("Minimum");
	        Label bal = new Label("Balanced");       
	        Label max = new Label("Maximum");       
	        labels.getChildren().addAll(min,bal,max);	        
	        
	        Slider slider = new Slider(0, 100, 50);
	        slider.setMaxWidth(400);
	        
	        box.getChildren().addAll(labels, slider);
	        
	        mainLayout.setBottom(box);
	        

	        //FINALIZE
	        Scene scene = new Scene(mainLayout, 650, 650);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        //make the app window cant be resize
            primaryStage.setMinWidth(650);
            primaryStage.setMaxWidth(650);
            primaryStage.setMinHeight(650);
            primaryStage.setMaxHeight(650);
            
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        /*
	         * javafx default centerOnScreen is suck!
	         * btw, this funct center the app at the screen when its first appear
	         */
	        centerOnScreen(primaryStage);
	        
	    } catch(Exception e) { e.printStackTrace(); }
	}

	public static void main(String[] agrs) {
		launch(agrs);
	}	
}
