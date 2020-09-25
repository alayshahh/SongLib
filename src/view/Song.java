//Alay Shah & Anshika Khare

//Song object -> allows us to compare and set fields, etc.

package view;

public class Song {
	String title;
	String artist;
	String album;
	String year;
	
	
	//general constructor we can set the album and artist after we verify that there isnt a duplicate entry & can validate the year field
	public Song (String title, String artist) {
		this.title = title.trim();
		this.artist = artist.trim();
		this.album = "";
		this.year = "";
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
		return title + " by "+ artist;
	}
	

}
