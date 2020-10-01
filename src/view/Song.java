//Alay Shah & Anshika Khare

//Song object -> allows us to compare and set fields, etc.

package view;

public class Song {
	private String songTitle;
	private String songArtist;
	private String songAlbum;
	private String songYear;
	
	
	//general constructor we can set the album and artist after we verify that there isnt a duplicate entry & can validate the year field
	public Song (String title, String artist) {
		this.songTitle = title.trim();
		this.songArtist = artist.trim();
		this.songAlbum = "";
		this.songYear = "";
	}
	 //grab data
	public String getTitle() {
		return songTitle;
	}
	
	public String getArtist() {
		return songArtist;
	}
	
	public String getAlbum() {
		return songAlbum;
	}
	
	public String getYear() {
		return songYear;
	}
	
	//setting the variable
	public void setTitle(String songTitle) {
		this.songTitle = songTitle;
	}
	
	public void setArtist(String songArtist) {
		 this.songArtist = songArtist;
	}
	
	public void setAlbum(String songAlbum) {
		 this.songAlbum = songAlbum;
	}
	
	public void setYear(String songYear) {
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
	
	public String fileWrite() {
		return songTitle+"|"+songArtist+"|"+songAlbum+"|"+songYear; 
	}
	

}
