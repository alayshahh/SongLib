//Alay Shah & Anshika Khare


/*
 	This is the main method, from here we launch the application and allow the viewController to handle all inputs/ actions of the user on the UI
 */

package app;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.viewController;


public class SongLib extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(getClass().getResource("/view/SongLibraryView.fxml")); //loader now will load the FXML we created
		AnchorPane root = (AnchorPane)loader.load();

		viewController viewController = 
				loader.getController();
		
		viewController.start(primaryStage);

		Scene scene = new Scene(root);
//		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Song Library");
		primaryStage.show(); 
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
