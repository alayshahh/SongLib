//Alay Shah & Anshika Khare


//This will handle all the action Events, populate the list, write to file etc. the main logic of the application


package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	@FXML Button addNow;
	@FXML Button editNow;
	
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
		
		obsList = FXCollections.observableArrayList(); //here we would populate it with the song array returned by the get from file method
		
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
	
	public void makeFile() throws IOException{
		File songList = new File ("songList.txt");
		
		if (songList.exists()){
			songList.delete();
		}
		if(songList.length() == 0) {
			return;
		}
		songList.createNewFile();	
		
		BufferedWriter write = new BufferedWriter(new FileWriter(songList, true));
		write.append(' ');
		write.append((CharSequence) obsList);
		write.close();
	
		/*FileWriter insert = new FileWriter(songList);
		insert.write(obsList.size());
		insert.close();*/
	}
	
	public void readData() throws IOException {
		try {
			String input;
			FileReader readFromFile = new FileReader("songList.txt");
			BufferedReader read = new BufferedReader(readFromFile);
			
			while((input = read.readLine()) != null) {
				System.out.println(input);
			}
			read.close();
		}
		catch(Exception x){
			x.printStackTrace();
		}
		/*int length = obsList.size();
		FileWriter writeTo = new FileWriter("songList.txt");
		for(int i = 0; i < length;i++) {
			writeTo(obsList.get(songName))
		}*/
		
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
		songName.setPromptText("");
		songArtist.setPromptText("");
		songAlbum.setPromptText("");
		songYear.setPromptText("");
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
		
		if(listView.getSelectionModel().getSelectedItem()==null && b==edit) {
			return;
		}
		
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
	
	public void addMusic(ActionEvent e) { //allows user to add a song or edit the selected song
		
		
		/* Cases:
		 
		 Song, artist, album? and year?
		 
		 They dont have song or artist -> invalid song name and/or artist name
		 
		 They have a non numerical year -> check if year is int -> Integer.parseInt()
		 
		 The song they want to add is already there
		 
		 */
		
		String name = songName.getText().trim(); 
		
		String artist = songArtist.getText().trim();
		
		if (artist.isEmpty()||name.isEmpty()) { //has the user put in vilid song information?
			//ALERT 
			System.out.println("ALERT EMPTY ARTSIT/SONG");
			Alert illegalSong = new Alert(AlertType.INFORMATION);
			illegalSong.setTitle("Error");
			illegalSong.setHeaderText("Song title and artist are required");
			illegalSong.showAndWait();
			
			return;
			
			
		}
		Song addThis = new Song(name, artist); //create song object
		
		String album = songAlbum.getText().trim();
		String year = songYear.getText().trim();
		
		if(!album.isEmpty()) { //if they added an album, save it 
			addThis.setAlbum(album);
		}
		if(!year.isEmpty()) {  //if they added a year, validate the year
			//check if its an integer
			for(int i =0; i<year.length(); i++) {
				if(year.charAt(i)<'0'||year.charAt(i)>'9') {
					//ALERT Wrong FORMAT FOR YEAR
					System.out.println("ALERT Incorrect format year");
					Alert illegalSong = new Alert(AlertType.INFORMATION);
					illegalSong.setTitle("Error");
					illegalSong.setHeaderText("Year is not valid");
					illegalSong.showAndWait();
					return;
				}
			}
			
			addThis.setYear(year); //set year
		}
		
		//at this point all information the user has put in for the song is added and validated, if not they will get to edit
		
		Button b = (Button)e.getSource();  //check if they wnated to add the song or edit the song
		
		
		if(b==addNow) { 
			int i = has(obsList, addThis); //check if the song is already in the library
			if(i!=-1) { //if it is send alert and show where it exists
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Duplicate Item");
				alert.setHeaderText("You already have this song in your library.");
				alert.showAndWait();
				listView.getSelectionModel().select(i);
			}else { //if the song isnt in it add it and sort it and show the song
				obsList.add(addThis);
				Comparator<Song> byTitleAndArtist = Comparator.comparing(Song::toLowerCaseString);
				FXCollections.sort(obsList, byTitleAndArtist);
				listView.getSelectionModel().select(obsList.indexOf(addThis));
			}
		
		}else { //if they wanted to edit a given song
			int curIndex = listView.getSelectionModel().getSelectedIndex();
			int i = has(obsList, addThis); //checks if the song is already in the library
			if(i==-1||i == curIndex) {  //if the song isnt in the library or matches the one they wanted to edit then changes are saved
    			
				obsList.remove(curIndex);
    			obsList.add(addThis);
    			Comparator<Song> byTitleAndArtist = Comparator.comparing(Song::toLowerCaseString);
    			FXCollections.sort(obsList, byTitleAndArtist);
    			listView.getSelectionModel().select(obsList.indexOf(addThis));
 
    		
    		}else {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Duplicate Item");
    			alert.setHeaderText("You already have this song in your library.");
    			alert.showAndWait();
    			listView.getSelectionModel().select(i);
    		}
			
		}
		
	}
	
	public void cancelledAction() {
		System.out.println("Cancelled");
		int x = listView.getSelectionModel().getSelectedIndex();
		System.out.println(x);
		if (x==-1) {
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
			songName.setPromptText("");
			songArtist.setPromptText("");
			songAlbum.setPromptText("");
			songYear.setPromptText("");
			addConfirm.setVisible(false);
			editConfirm.setVisible(false);
			addEditDel.setDisable(false);
			addConfirm.setVisible(false);
			editConfirm.setVisible(false);
			addEditDel.setDisable(false);
			
		}else {
			listView.getSelectionModel().clearSelection();
			listView.getSelectionModel().select(x);
		}
		
		
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
