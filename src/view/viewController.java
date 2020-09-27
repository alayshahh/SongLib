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
//import javafx.util.Pair;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

//import java.util.Comparator;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Button;







public class ViewController {
	
	@FXML ListView<Song> listView;
	@FXML TextField songName;
	@FXML TextField songArtist;
	@FXML TextField songAlbum;
	@FXML TextField songYear;
	@FXML ButtonBar addConfirm;
	@FXML ButtonBar editConfirm;
	@FXML ButtonBar addEditDel;
	@FXML Button add;
	@FXML Button edit;
	
	//CSS styling for textfield
	private static  String nonEditableTextField = "-fx-control-inner-background: #f4f4f4; -fx-background-insets: 0; -fx-background-radius: 0; -fx-background-color: -fx-text-box-border, -fx-control-inner-background;";
	
	
	//where we will populate the Songs
	private ObservableList<Song> obsList;
	
	
	public void start (Stage mainStage) {
		
		
		
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
		
		//hide add and edit confirm buttons
		addConfirm.setVisible(false);
		editConfirm.setVisible(false);
		
		
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
	
	public int has(ObservableList<Song> list, Song s) { //checks if the list has the Song and returns the song index if it does
		for ( int i =0; i< list.size(); i++) {
			Song cur = list.get(i);
			if(Song.equals(cur, s)) {
				return i;
			}
		}
		
		return -1;
		
	}
	
	
	public void showItem(Stage mainStage) {  //shows the selected item w correct UI
		//Show selected Item
		songName.setEditable(false);
		songArtist.setEditable(false);
		songAlbum.setEditable(false);
		songYear.setEditable(false);
		
		//easier to understand UI so it makes sense that they cannot edit 
		songName.setStyle(nonEditableTextField);
		songArtist.setStyle(nonEditableTextField);
		songAlbum.setStyle(nonEditableTextField);
		songYear.setStyle(nonEditableTextField);
		
		addConfirm.setVisible(false);
		editConfirm.setVisible(false);
		addEditDel.setDisable(false);
		
		if(listView.getSelectionModel().getSelectedItem()!= null) { 
		
			songName.setText(listView.getSelectionModel().getSelectedItem().getTitle());
			songArtist.setText(listView.getSelectionModel().getSelectedItem().getArtist());
			songAlbum.setText(listView.getSelectionModel().getSelectedItem().getAlbum());
			songYear.setText(listView.getSelectionModel().getSelectedItem().getYear());
		}else {
			songName.setText("");
			songArtist.setText("");
			songAlbum.setText("");
			songYear.setText("");
		}
		
	}
	
	public void editUI(ActionEvent e) {  //sets screen to enable editing songs or adding songs
		
		//Adjusting UI For Adding/Editing Song
		
		Button b = (Button)e.getSource();
		
		//edit the textfields to be editable and change UI
		
		songName.setEditable(true);
		songArtist.setEditable(true);
		songAlbum.setEditable(true);
		songYear.setEditable(true);
		
		//easier to understand UI so it makes sense that they cannot edit 
		songName.setStyle("");
		songArtist.setStyle("");
		songAlbum.setStyle("");
		songYear.setStyle("");
		
		songName.setPromptText("Title");
		songArtist.setPromptText("Artist");
		songAlbum.setPromptText("Album (Optional)");
		songYear.setPromptText("Year Released (Optional)");
		
		
		
		addEditDel.setDisable(true);
		
		
		//if editing the data will be in the text field
//		
		if (b==add) {
			addConfirm.setVisible(true);
			songName.setText("");
			songArtist.setText("");
			songAlbum.setText("");
			songYear.setText("");
			
		}else {
			editConfirm.setVisible(true);
			
			
		}
		
	
		
	}
	
	public void addMusic(ActionEvent e) {
		
		
		/* Cases:
		 
		 Song, artist, album? and year?
		 
		 They dont have song or artist -> invalid song name and/or artist name
		 
		 They have a non numerical year -> check if year is int -> Integer.parseInt()
		 
		 The song they want to add is already there
		 
		 */
		
		String name = songName.getText().trim();
		
		String artist = songArtist.getText().trim();
		
		if (artist.isEmpty()||name.isEmpty()) {
			//ALERT 
			System.out.println("ALERT EMPTY ARTSIT/SONG");
			return;
			
			
		}
		Song addThis = new Song(name, artist);
		
		String album = songAlbum.getText().trim();
		String year = songYear.getText().trim();
		
		if(!album.isEmpty()) {
			addThis.setAlbum(album);
		}
		if(!year.isEmpty()) {
			//check if its an integer
			for(int i =0; i<year.length(); i++) {
				if(year.charAt(i)<'0'||year.charAt(i)>'9') {
					//ALERT Wrong FORMAT FOR YEAR
					System.out.println("ALERT Incorrect format year");
					return;
				}
			}
			
			addThis.setYear(year);
		}
		
		
		
		
	}
	
	public void editMusic(ActionEvent e) {
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
