//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    p08 MusicPlayer300
// Course:   CS 300 Fall 2022
//
// Author:   Akshay Gona
// Email:    gona@wisc.edu
// Lecturer: Hobbes LeGault
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:    Varun Munagala, Sam Eron, Rishit Patil, helped debug and think about logic
// Online Sources:  stackoverflow, wikipedia, javadocs, oracle, YouTube videos on queues and stacks
//
///////////////////////////////////////////////////////////////////////////////


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A linked-queue based music player which plays Actual Music Files based on keyboard input in an
 * interactive console method. This music player can load playlists of music or add individual
 * song files to the queue.
 */
public class MusicPlayer300 extends Object {
    //Whether the current playback mode should be filtered by artist; false by default
    private final Playlist playlist;
    private String filterArtist;
    //The artist to play if filterPlay is true; should be null otherwise
    private boolean filterPlay;
    //the current playlist of songs

    /**
     * Creates a new MusicPlayer300 with an empty playlist
     */
    public MusicPlayer300() {
        playlist = new Playlist();
    }

    /**
     * Stops any song that is playing and clears out the playlist
     */
    public void clear() {
        playlist.dequeue().stop();
        while (playlist.peek() != null) {
            playlist.dequeue();
        }
    }

    /**
     * Loads a playlist from a provided file, skipping any individual songs which cannot be loaded.
     * Note that filenames in the provided files do NOT include the audio directory, and will need
     * that added before they are loaded.Print "Loading" and the song's title in quotes before you
     * begin loading a song, and an "X" if the load was unsuccessful for any reason.
     *
     * @param file - the file object to load
     * @throws FileNotFoundException - if the playlist file cannot be loaded
     */
    public void loadPlaylist(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            String l = s.nextLine();
            String[] data = l.split(",");
            String s0 = data[0];
            String s1 = data[1];
            String s2 = data[2];
            //array of strings with title, filename, and filepath
            System.out.println("Loading " + s0 + "\n");
            try {
                loadOneSong(s0, s1, s2);
                //try catch to see if the files can be loaded
            } catch (Exception e) {
                System.out.print("X");
            }
        }
    }

    /**
     * Loads a single song to the end of the playlist given the title, artist, and filepath.
     * Filepaths for P08 must refer to files in the audio directory.
     *
     * @param title    the title of the song
     * @param artist   the artist of this song
     * @param filepath the full relative path to the song file, begins with the "audio" directory
     *                 for P08
     * @throws IllegalArgumentException if the song file cannot be read
     */
    public void loadOneSong(String title, String artist, String filepath)
        throws IllegalArgumentException {
        this.playlist.enqueue(new Song(title, artist, filepath));
        // no explicit exception thrown since the song constructor throws an exception which
        // propagates
    }

    /**
     * Provides a string representation of all songs in the current playlist
     *
     * @return a string representation of all songs in the current playlist
     */
    public String printPlaylist() {
        return this.playlist.toString();
    }

    /**
     * Creates and returns the menu of options for the interactive console program.
     *
     * @return the formatted menu String
     */
    public String getMenu() {
        return "Enter one of the following options:\n"
            + "[A <filename>] to enqueue a new song file to the end of this playlist\n"
            + "[F <filename>] to load a new playlist from the given file\n"
            + "[L] to list all songs in the current playlist\n"
            + "[P] to start playing ALL songs in the playlist from the beginning\n"
            + "[P -t <Title>] to play all songs in the playlist starting from <Title>\n"
            + "[P -a <Artist>] to start playing only the songs in the playlist by Artist\n"
            + "[N] to play the next song\n" + "[Q] to stop playing music and quit the program\n";
    }

    /**
     * Stops playback of the current song (if one is playing) and advances to the next song in the
     * playlist.
     *
     * @throws IllegalStateException if the playlist is null or empty, or becomes empty at any time
     *                               during this method
     */
    public void playNextSong() throws IllegalStateException {
        if (this.playlist == null) {
            throw new IllegalStateException("playlist is null");
            //ISE to be thrown as specified in JD about when playlist becomes null or empty
        }
        if (playlist.isEmpty()) {
            throw new IllegalStateException("playlist is empty");
            //ISE to be thrown as specified in JD about when playlist becomes null or empty
        }
        playlist.peek().stop();
        playlist.dequeue();
        if (playlist.isEmpty()) {
            throw new IllegalStateException("playlist is empty");
            //ISE to be thrown as specified in JD about when playlist becomes null or empty
        }
        if (filterPlay) {
            while (!playlist.isEmpty() && !playlist.peek().getArtist().equals(filterArtist)) {
                //executes while playlist is not empty and the artist is not equal to filterArtis
                playlist.dequeue();
                if (playlist.isEmpty()) {
                    throw new IllegalStateException("playlist is empty");
                    //ISE to be thrown as specified in JD about when playlist becomes null or empty
                } else {
                    break;
                }
            }
        }
        playlist.peek().play();
    }

    /**
     * Interactive method to display the MusicPlayer300 menu and get keyboard input from the user.
     * See writeup for details.
     *
     * @param in keyboard input from user
     */
    public void runMusicPlayer300(Scanner in) {
        System.out.println(this.getMenu());
        System.out.print("> ");
        String temp = in.nextLine();
        while (!temp.equalsIgnoreCase("Q")) {
            temp = temp.trim();
            //case for Q
            if (temp.startsWith("A")) {
                System.out.println("Enter title: ");
                System.out.println("> ");
                String title = in.nextLine();
                System.out.println("Enter title: ");
                System.out.println("> ");
                String artist = in.nextLine();
                String filename = temp.substring(2);
                System.out.println("filename: " + filename);
                Song s1 = new Song(title, artist, filename);
                playlist.enqueue(s1);
                // case for F, checks
            }
            if (temp.startsWith("F")) {
                File temp1 = new File(temp.substring(2));
                try {
                    loadPlaylist(temp1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (temp.startsWith("L")) {
                //case for L
                System.out.println(playlist);
            }
            if (temp.startsWith("P")) {
                // case for P and the dependents on P
                if (!temp.contains("-t") || !temp.contains("-a")) {
                    playlist.peek().play();
                } else if (temp.contains("-t")) {
                    String title = temp.substring(6);
                    Song s2 = playlist.peek();
                    while (s2 != null) {
                        if (s2.getTitle().equals(title)) {
                            s2.play();
                            break;
                        }
                    }
                } else if (temp.contains("-a")) {
                    // case for A
                    filterPlay = true;
                    filterArtist = temp.substring(6);
                }
                System.out.println(getMenu());
                while (playlist.peek() != null) {
                    while (playlist.peek().isPlaying()) {
                    }
                    //continuity for returning menu back
                    playlist.dequeue();
                    if (filterPlay) {
                        while (!playlist.peek().getArtist().equals(filterArtist)) {
                            playlist.dequeue();
                        }
                    }
                    if (playlist.peek() != null) {
                        playlist.peek().play();
                    }
                }

            }
            if (temp.startsWith("N")) {
                playNextSong();
                //case for N
            }
            if (temp.startsWith("Q")) {
                System.out.println("Thank you!");
                break;
                //case for Q quit
            }
            System.out.println(getMenu());
            System.out.print("> ");
            temp = in.nextLine();
            //returns menu again, or finishes
        }
    }
}
