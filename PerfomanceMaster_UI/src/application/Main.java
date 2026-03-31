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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Main extends Application{
	
	//use for set the primeryStage on screen's center properly
	public void centerOnScreen(Stage stage) { // instance methods
		Rectangle2D visualBound = Screen.getPrimary().getVisualBounds();
		stage.setX((visualBound.getWidth() - stage.getWidth())/2);
		stage.setY((visualBound.getHeight() - stage.getHeight())/2);
	}
	
	//
	private void ColorLinerTrack(Slider slider) {
        //
        StackPane trackPane = (StackPane) slider.lookup(".track");
        // color (50% Blue, 50% Gray)
        trackPane.setStyle("-fx-background-color: linear-gradient(to right, #0078D4 50%, #D3D3D3 50%);");
        // add listener
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                // Calculate percentage just in case you change the Max value later
                double percentage = (new_val.doubleValue() / slider.getMax()) * 100;
                
                // Update the gradient dynamically
                String style = String.format("-fx-background-color: linear-gradient(to right, #0078D4 %d%%, #D3D3D3 %d%%);", 
                                             (int) percentage, (int) percentage);
                trackPane.setStyle(style);
            }
        });	
	}
	
	
	@Override
	public void start(Stage primaryStage) {
	    try {
	        //THE MAIN CONTAINER
	        BorderPane mainLayout = new BorderPane();
	        mainLayout.setPadding(new Insets(50)); // set a "foam" thick 50px all around in site mainlayouts


	        //the pane which contain buttons, in center
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(30); //horizon gap between buttons
	        grid.setVgap(30); //vertical gap between buttons
	        //grid.setStyle("-fx-background-color: #C0C0C0;"); // for debugging
	        //create buttons
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
	        
	        
	        //the pane that contain slider
	        VBox box = new VBox(12);// 12 is height distance between child themself
	        box.setAlignment(Pos.CENTER);
	        box.getStyleClass().add("box");
	        box.setPadding(new Insets(12));// distance between child and the box itself
	        //the labels above the slider
	        HBox labels = new HBox(140);//weight distance between its child (those labels)
	        labels.setAlignment(Pos.CENTER);
	        Label min = new Label("Minimum");
	        Label bal = new Label("Balanced");       
	        Label max = new Label("Maximum");       
	        labels.getChildren().addAll(min,bal,max);	        
	        //the slider
	        Slider slider = new Slider(0, 100, 50); // (min, max, start)
	        slider.setShowTickMarks(true);// enable the marks
	        slider.setMajorTickUnit(25); // divide slider by 25 => there 4 option point 0, 25, 50, 75, 100
	        slider.setSnapToTicks(true);	// the slider jump to the closet tick...
	        slider.setMinorTickCount(0);// make the jump work better.
	        slider.setMaxWidth(440); // slider length
	        /*
	         * SliderColorLinerTrack(slider);
	         * move to before primaryStage.show() because the internal parts of the slider
	         * (like the .track and the .thumb) do not exist yet
	         */
	        //add those to the boxs
	        box.getChildren().addAll(labels, slider);
	        //add the box to main layout
	        mainLayout.setBottom(box);
	        

	        //FINALIZE
	        Scene scene = new Scene(mainLayout, 650, 650);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        //make the app window cant be resize
            primaryStage.setMinWidth(650);
            primaryStage.setMaxWidth(650);
            primaryStage.setMinHeight(650);
            primaryStage.setMaxHeight(650);
            //setup to primaryStage
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        ColorLinerTrack(slider); //explained in slider
	        /*
	         * javafx default centerOnScreen is suck!
	         * btw, this funct center the app at the screen when its first appear
	         */
	        centerOnScreen(primaryStage);
	        
	    } 
	    catch(Exception e) { e.printStackTrace(); } //error catching
	}



	//main
	public static void main(String[] agrs) {
		//launch the app
		launch(agrs);
	}	
}
