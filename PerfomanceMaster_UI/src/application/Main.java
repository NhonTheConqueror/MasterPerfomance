package application;
	
import javafx.application.Application;
import javafx.application.Platform;
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
	
	private double X_offSet = 0;
	private double Y_offSet = 0;
	
	//use for set the primeryStage on screen's center properly
	public void centerOnScreen(Stage stage) { // instance methods
		Rectangle2D visualBound = Screen.getPrimary().getVisualBounds();
		stage.setX((visualBound.getWidth() - stage.getWidth())/2);
		stage.setY((visualBound.getHeight() - stage.getHeight())/2);
	}
	
	/*
	 * the line color track behind slider button
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
	//to create icon in 4 main button (^_~)
	private ImageView createIcon(String str) {
		try {
			Image img = new Image(getClass().getResourceAsStream(str));
			ImageView res = new ImageView(img);
			// standard icon size
			res.setFitHeight(60); 
		    res.setFitWidth(60);
		    return res;
		}
		catch(NullPointerException e) {
			System.err.println("Could not find icon resources. Please check your file paths!");
			return null;
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
	    try {
	    	
	        //THE MAIN CONTAINER
	    		BorderPane root = new BorderPane();
	        VBox mainLayout = new VBox(50);
	        mainLayout.setAlignment(Pos.CENTER); 
	        mainLayout.setPadding(new Insets(20, 50, 50, 50)); // set a "foam" thick **dpx all around in site mainlayouts
	        //mainLayout.getStyleClass().add("box2");

	        
	        //windows bar
	        HBox bar = new HBox();
	        bar.setPrefSize(650,30);
	        bar.setAlignment(Pos.CENTER_LEFT);
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
	        minimizeButton.setOnKeyPressed(null);
	        minimizeButton.setOnAction(event -> {
	        		Platform.exit();
	        });
	        //close button
	        Button closeButton = new Button("X");
	        closeButton.setPrefSize(45, 30);
	        closeButton.getStyleClass().add("window-close-btn");
	        closeButton.setOnAction(event -> {
	        		System.exit(0);
	        });
	        bar.getChildren().addAll(title,spacer, minimizeButton, closeButton);
	        //bar.getStyleClass().add("box");
	        root.setTop(bar);
	        
	        
	        //the pane which contain buttons, in center
	        GridPane ButtonGrid = new GridPane();
	        ButtonGrid.setAlignment(Pos.CENTER);
	        ButtonGrid.setHgap(30); //horizon gap between buttons
	        ButtonGrid.setVgap(30); //vertical gap between buttons
	        Button button1 = new Button("Defender & Update");
	        Button button2 = new Button("Performance's insight");
	        Button button3 = new Button("Terminate\n background apps");
	        Button button4 = new Button("Clean Ram & Storage");
	        // add a CSS class and icon to all buttons for styling
	        button1.getStyleClass().add("modern-button"); button1.setGraphic(createIcon("/img/icon/shield.png"));
	        button2.getStyleClass().add("modern-button"); button2.setGraphic(createIcon("/img/icon/chart.png"));
	        button3.getStyleClass().add("modern-button"); button3.setGraphic(createIcon("/img/icon/app.png"));
	        button4.getStyleClass().add("modern-button"); button4.setGraphic(createIcon("/img/icon/storage.png"));
	        // add buttons to grid, also position
	        ButtonGrid.add(button1, 0, 0);//top left
	        ButtonGrid.add(button2, 1, 0);//top right
	        ButtonGrid.add(button3, 0, 1);//bot left
	        ButtonGrid.add(button4, 1, 1);//bot right

	     
	        
	        
	        //the pane that contain slider
	        VBox Sliderbox = new VBox(12);// 12 is height distance between child themself
	        Sliderbox.setAlignment(Pos.CENTER);
	        Sliderbox.getStyleClass().add("box");
	        Sliderbox.setPadding(new Insets(10, 20, 10, 25));// distance between child and the box itself
	        //the labels above the slider
	        GridPane labels = new GridPane();
	        labels.setMaxWidth(505); 
	        ColumnConstraints col1 = new ColumnConstraints(); col1.setPercentWidth(100/3); col1.setHalignment(HPos.LEFT);
	        ColumnConstraints col2 = new ColumnConstraints(); col2.setPercentWidth(100/3); col2.setHalignment(HPos.CENTER);
	        ColumnConstraints col3 = new ColumnConstraints(); col3.setPercentWidth(100/3); col3.setHalignment(HPos.RIGHT);
	        labels.getColumnConstraints().addAll(col1, col2, col3);

	        Label min = new Label("Minimum");
	        Label bal = new Label("Balanced");       
	        Label max = new Label("Maximum");
	        // labels look modern like
	        String labelStyle = "-fx-font-family: 'Segoe UI'; -fx-font-size: 14px; -fx-text-fill: #FFFFFF;";
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
	        slider.setMinWidth(505);// slider length
	        //add those to the boxs
	        Sliderbox.getChildren().addAll(labels, slider);
	        //add the box to main layout
	        mainLayout.getChildren().addAll(ButtonGrid, Sliderbox);
	        root.setCenter(mainLayout);

	        

	        primaryStage.initStyle(StageStyle.TRANSPARENT); //set stage to transparent, this make the default title bar disappear
	        Scene scene = new Scene(root, 650, 630);
	        scene.setFill(javafx.scene.paint.Color.TRANSPARENT); //set scene too transparent

	        root.getStyleClass().add("glass-pane");
	        
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        
            primaryStage.setResizable(false);//make the app window cant be resize
            
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	        ColorLinerTrack(slider); //a red line track in slider

	        centerOnScreen(primaryStage); //funct center the app at the screen when its first appear
	        
	        ///draggable window
	        root.setOnMousePressed(event -> {
	        		X_offSet = event.getSceneX();
	        		Y_offSet = event.getSceneY();
	        });
	        root.setOnMouseDragged(event -> {
	        		primaryStage.setX(event.getScreenX() - X_offSet);
	        		primaryStage.setY(event.getScreenY() - Y_offSet);
	        });
	        ///
	        
	    } 
	    catch(Exception e) { e.printStackTrace(); } //error catching
	}



	//main
	public static void main(String[] agrs) {
		//launch the app
		launch(agrs);
	}	
}
