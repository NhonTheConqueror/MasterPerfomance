package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox; import javafx.scene.layout.HBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Main extends Application{
	
	//use for set the primeryStage on screen's center properly
	public void centerOnScreen(Stage stage) { // instance methods
		Rectangle2D visualBound = Screen.getPrimary().getVisualBounds();
		stage.setX((visualBound.getWidth() - stage.getWidth())/2);
		stage.setY((visualBound.getHeight() - stage.getHeight())/2);
	}
	
	/*
	 * the line color behind slider button
	 * of course i stealth it from stackOverflow
	 */
	private void ColorLinerTrack(Slider slider) {
        // hello
        StackPane trackPane = (StackPane) slider.lookup(".track");
        // color (50% Blue, 50% Gray)
        trackPane.setStyle("-fx-background-color: linear-gradient(to right, #0078D4 50%, #D3D3D3 50%);");
        // add listener
        slider.valueProperty().addListener(new ChangeListener<Number>() {
        		//method of ChangeListener
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                // Calculate percentage just in case you change the Max value later
                double percentage = (new_val.doubleValue() / slider.getMax()) * 100;
                // gradient dynamically
                String style = String.format("-fx-background-color: linear-gradient(to right, #0078D4 %d%%, #D3D3D3 %d%%);", 
                                             (int) percentage, (int) percentage);
                trackPane.setStyle(style);
            }
        } );	
	}
	
	
	@Override
	public void start(Stage primaryStage) {
	    try {
	        //THE MAIN CONTAINER
	    	BorderPane root = new BorderPane();
	        VBox mainLayout = new VBox(50);
	        mainLayout.setAlignment(Pos.CENTER); 
	        mainLayout.setPadding(new Insets(0,50,0,50)); // set a "foam" thick 50px all around in site mainlayouts
	        mainLayout.getStyleClass().add("box2");

	        
	        //windows bar
	        HBox bar = new HBox();
	        bar.setPrefSize(650,30);
	        bar.setAlignment(Pos.CENTER_LEFT);
	        //title
	        Label title = new Label("Performance Master");
	        title.getStyleClass().add("window-title");
	        //add a little left margin so the text isn't glued to the edge
	        HBox.setMargin(title, new Insets(0, 0, 0, 10));
	        
	        //The Invisible Spacer
	        javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
	        // This line tells the spacer to push everything else apart
	        javafx.scene.layout.HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
	        
	        //minimize button
	        Button minimizeButton = new Button("—");
	        minimizeButton.setPrefSize(45, 30);
	        minimizeButton.getStyleClass().add("window-btn");
	        //Image minimizeImg = new Image(getClass().getResourceAsStream("/img/close-window.jpg"));
	        //minimizeButton.setGraphic(new ImageView(minimizeImg));
	        
	        //close button
	        Button closeButton = new Button("X");
	        closeButton.setPrefSize(45, 30);
	        closeButton.getStyleClass().add("window-close-btn");
	        //Image closeImg = new Image(getClass().getResourceAsStream("/img/close-window.jpg"));

	        bar.getChildren().addAll(title,spacer, minimizeButton, closeButton);
	        
	        //bar.getStyleClass().add("box");
	        root.setTop(bar);

	        
	        //the pane which contain buttons, in center
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

	     
	        
	        
	        //the pane that contain slider
	        VBox box = new VBox(12);// 12 is height distance between child themself
	        box.setAlignment(Pos.CENTER);
	        box.getStyleClass().add("box");
	        box.setPadding(new Insets(10, 20, 10, 20));// distance between child and the box itself
	        //the labels above the slider
	        GridPane labels = new GridPane();
	        labels.setMaxWidth(375); // MUST be identical to slider width	        
	        
	        ColumnConstraints col1 = new ColumnConstraints(); col1.setPercentWidth(33.3); col1.setHalignment(HPos.LEFT);
	        ColumnConstraints col2 = new ColumnConstraints(); col2.setPercentWidth(33.3); col2.setHalignment(HPos.CENTER);
	        ColumnConstraints col3 = new ColumnConstraints(); col3.setPercentWidth(33.3); col3.setHalignment(HPos.RIGHT);
	        labels.getColumnConstraints().addAll(col1, col2, col3);

	        Label min = new Label("Minimum");
	        Label bal = new Label("Balanced");       
	        Label max = new Label("Maximum");
	        // labels look modern like the picture
	        String labelStyle = "-fx-font-family: 'Segoe UI'; -fx-font-size: 14px; -fx-text-fill: #1C1C1C;";
	        min.setStyle(labelStyle);
	        bal.setStyle(labelStyle);
	        max.setStyle(labelStyle);
	        labels.add(min, 0, 0);
	        labels.add(bal, 1, 0);
	        labels.add(max, 2, 0);
	        
	        //the slider
	        Slider slider = new Slider(0, 100, 50); // (min, max, start)
	        slider.setMajorTickUnit(25); // divide slider by 25 => there 4 option point 0, 25, 50, 75, 100
	        slider.setSnapToTicks(true);	// the slider jump to the closet tick...
	        slider.setMinorTickCount(0);// make the jump work better.
	        slider.setMaxWidth(400);// slider length
	        //add those to the boxs
	        box.getChildren().addAll(labels, slider);
	        //add the box to main layout
	        mainLayout.getChildren().addAll(grid, box);
	        root.setCenter(mainLayout);

	        
	        
	        Scene scene = new Scene(root, 650, 650);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setResizable(false);//make the app window cant be resize
            primaryStage.initStyle(StageStyle.UNDECORATED); // Removes title bar to use my custom
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
