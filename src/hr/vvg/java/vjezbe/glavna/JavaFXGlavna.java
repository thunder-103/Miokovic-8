package hr.vvg.java.vjezbe.glavna;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class JavaFXGlavna extends Application {
	
	private static BorderPane root; 
	private Stage primaryStage;
	
	@Override
	public void start(Stage stage) {
		primaryStage = stage;
		try {
			BorderPane rootPane = (BorderPane) 
					FXMLLoader.load(JavaFXGlavna.class 
							.getResource("../javafx/glavna.fxml")); 
					root = rootPane; 
					Scene scene = new Scene(root, 650, 450); 
					scene.getStylesheets().add(getClass().getResource( 
							"../glavna/application.css").toExternalForm()); 
					primaryStage.setScene(scene); 
					primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public static void setCenterPane(BorderPane centerPane) { 
		root.setCenter(centerPane); }
	
}


//-fx-background-color: #400000;