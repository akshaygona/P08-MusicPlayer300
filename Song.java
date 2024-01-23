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


/**
 * A representation of a single Song. Interfaces with the provided AudioUtility class, which uses
 * the javax.sound.sampled package to play audio to your computer's audio output device
 */
public class Song extends Object {
    private final String artist;
    //The duration of this song in number of seconds
    private final String title;
    //the artist of the song
    private final AudioUtility audioClip;
    //This song's AudioUtility interface to javax.sound.sampled
    private final int duration;
    //The title of the song

    /**
     * Initializes all instance data fields according to the provided values
     *
     * @param title    the title of the song, set to empty string if null
     * @param artist   the artist of this song, set to empty string if null
     * @param filepath the full relative path to the song file, begins with the "audio" directory
     *                 for P08
     * @throws IllegalArgumentException if the song file cannot be read
     */
    public Song(String title, String artist, String filepath) throws IllegalArgumentException {
        if (title == null)
            this.title = "";
        else
            this.title = title;

        if (artist == null)
            this.artist = "";
        else
            this.artist = artist;

        try {
            this.audioClip = new AudioUtility(filepath);
        } catch (Exception e) {
            throw new IllegalArgumentException("file cannot be used to play audio");
        }
        this.duration = this.audioClip.getClipLength();
    }

    /**
     * Tests whether this song is currently playing using the AudioUtility
     *
     * @return true if the song is playing, false otherwise
     */
    public boolean isPlaying() {
        return audioClip.isRunning();
    }

    /**
     * Accessor method for the song's title
     *
     * @return the title of this song
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Accessor method for the song's artist
     *
     * @return the artist of this song
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     * Uses the AudioUtility to start playback of this song, reopening the clip for playback
     * if necessary
     */
    public void play() {
        if (!audioClip.isReadyToPlay())
            audioClip.reopenClip();

        System.out.println("Playing " + this + "...");
        audioClip.startClip();
    }

    /**
     * Uses the AudioUtility to stop playback of this song
     */
    //TODO
    public void stop() {
        audioClip.stopClip();
    }

    /**
     * The title should be in quotes, the duration should be split out into minutes and seconds
     * (recall it is stored as seconds only!), and the artist should be preceded by the word "by".
     * It is intended for this assignment to leave single-digit seconds represented as 0:6, for
     * example, but if you would like to represent them as 0:06, this is also allowed.
     *
     * @return a formatted string representation of this Song
     */
    @Override public String toString() {
        String q = "\"";
        return (q + title + q + " (" + duration / 60 + ":" + duration % 60 + ")" + " by " + artist);
    }
}
