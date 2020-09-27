//Alay Shah & Anshika Khare

//Song object -> allows us to compare and set fields, etc.

package view;

public class Song {
	String songTitle;
	String songArtist;
	String songAlbum;
	String songYear;
	
	
	//general constructor we can set the album and artist after we verify that there isnt a duplicate entry & can validate the year field
	public Song (String title, String artist) {
		this.songTitle = title.trim();
		this.songArtist = artist.trim();
		this.songAlbum = "";
		this.songYear = "";
	}
	 //grab data
	public String title() {
		return songTitle;
	}
	
	public String artist() {
		return songArtist;
	}
	
	public String album() {
		return songAlbum;
	}
	
	public String year() {
		return songYear;
	}
	
	//setting the variable
	public void getSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}
	
	public void getSongArtist(String songArtist) {
		 this.songArtist = songArtist;
	}
	
	public void getSongAlbum(String songAlbum) {
		 this.songAlbum = songAlbum;
	}
	
	public void getSongYear(String songYear) {
		 this.songYear = songYear;
	}
	
	
	// to check if the song already exists in the lib
	public static boolean equals(Song s1, Song s2 ) {
		if(s1.compareTo(s2)==0) {
			return true;
		}
		return false;
	}
	
	//helps with sorting
	public int compareTo(Song t) {
		String cur = this.toString().toLowerCase();
		String that = t.toString().toLowerCase();
		return cur.compareTo(that);
	}
	
	//part of compareTo
	public String toLowerCaseString() {
		return this.toString().toLowerCase();
	}
	
	//for display
	public String toString() {
		return songTitle + " by "+ songArtist;
	}
	

}
