//Alay Shah & Anshika Khare


//This will handle all the action Events, populate the list, write to file etc. the main logic of the application


package view;

import java.util.Comparator;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.util.Comparator;
import javafx.event.ActionEvent;








public class viewController {
	
	@FXML ListView<Song> listView;
	@FXML TextField songName;
	@FXML TextField songArtist;
	@FXML TextField songAlbum;
	@FXML TextField songYear;
	
	//CSS styling for textfield
	private static  String nonEditableTextField = "-fx-control-inner-background: #f4f4f4; -fx-background-insets: 0; -fx-background-radius: 0; -fx-background-color: -fx-text-box-border, -fx-control-inner-background;";
	
	
	//where we will populate the Songs
	private ObservableList<Song> obsList;
	
	
	public void start (Stage mainStage) {
		
		
		//TODO get songs from file and write to file methods
		
		//songs to test methods for now
		
		Song x = new Song ("one", "x");
		Song y = new Song("two", "y");
		Song z = new Song("song", "z");
		Song w = new Song("one", "w");
		
		obsList = FXCollections.observableArrayList(x,y,z,w); //here we would populate it with the song array returned by the get from file method
		
		listView.setItems(obsList);
		
		//create a Comparator and give it a way to compare the Song objects
		Comparator<Song> byTitleAndArtist = Comparator.comparing(Song::toLowerCaseString);
		
		//use Collections method to sort instead of making our own sort method
		
		FXCollections.sort(obsList, byTitleAndArtist);
		
		//user cannot edit the names until they hit edit
		songName.setEditable(false);
		songArtist.setEditable(false);
		songAlbum.setEditable(false);
		songYear.setEditable(false);
		
		//easier to understand UI so it makes sense that they cannot edit 
		songName.setStyle(nonEditableTextField);
		songArtist.setStyle(nonEditableTextField);
		songAlbum.setStyle(nonEditableTextField);
		songYear.setStyle(nonEditableTextField);
		
		
		//automatically selects first item -> need to check if it is there in show method
		listView.getSelectionModel().select(0);
		showItem(mainStage);
		
		
		//sets listener for all items in the list and shows the item when clicked on
		listView.getSelectionModel()
		.selectedIndexProperty()
		.addListener(
				(obs, oldVal, newVal) ->
				showItem(mainStage));
		
		
		
	}
	
		
	
	
	public void showItem(Stage mainStage) {
		//Show selected Item
	}
	
	public void addSong(ActionEvent e) {
		//when user wants to add new song
	}
	
	public void editSong(ActionEvent e) {
		//user wants to edit current song
	}
	
	public void deleteSong(ActionEvent e) {
		//user wants to delete the current song
		if(obsList.isEmpty()) {
			return;
		}
		int curIndex = listView.getSelectionModel().getSelectedIndex();
		Song current = listView.getSelectionModel().getSelectedItem();
		Alert del = new Alert(AlertType.CONFIRMATION);
		del.setTitle("Delete Item");
		del.setHeaderText("Are you sure you want to delete "+ current.toString()+"?");
		Optional<ButtonType> result = del.showAndWait();
		if(result.get()==ButtonType.OK) {
			int newIndex = curIndex + 1;
			if(newIndex>=obsList.size()) {
				newIndex = curIndex-1;
			}
		listView.getSelectionModel().select(newIndex);
		obsList.remove(curIndex);
	}
		
	}
}
