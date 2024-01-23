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
 * A FIFO linked queue of SongNodes, conforming to our QueueADT interface.
 */
public class Playlist extends Object implements QueueADT<Song> {
    private SongNode first;
    //The current first song in the queue; the next one up to play (front of the queue)
    private SongNode last;
    //The current last song in the queue; the most-recently added one (back of the queue)
    private int numSongs;
    //The number of songs currently in the queue

    /**
     * Constructs a new, empty playlist queue
     */
    public Playlist() {
        this.first = this.last = null;
        //declares a new list, with first and last being null, which means it is empty
        numSongs = 0;
        //number of songs initialized to 0
    }

    /**
     * Adds a new song to the end of the queue
     *
     * @param element the song to add to the Playlist
     */
    public void enqueue(Song element) {
        SongNode temp = new SongNode(element);
        //stores the song element in a temp node, waiting to be added to the queue
        if (size() == 0) {
            this.first = this.last = temp;
            //if the list is empty, the first and last are the same, which is the new element
        } else {
            this.last.setNext(temp);
            //sets the next of current last to temp
            this.last = temp;
        }
        //sets new last to temp
        numSongs++;
        //increases count since a new node was added
    }

    /**
     * Removes the song from the beginning of the queue
     *
     * @return the song that was removed from the queue, or null if the queue is empty
     */
    public Song dequeue() {
        if (this.first == null) {
            return null;
            //checks if list is empty, returns null if it is
        }
        SongNode temp = this.first;
        this.first = this.first.getNext();
        numSongs--;
        //stores the data of the first element before changing the first element to the one after
        //it, and then decreases numSongs by one, since one is removed
        if (this.first == null) {
            this.last = null;
        }
        //additional check to see if the list is empty, making sure that both first and last are
        //null
        return temp.getSong();
        //returns the data stored in temp
    }

    /**
     * Returns the song at the front of the queue without removing it
     *
     * @return the song that is at the front of the queue, or null if the queue is empty
     */
    public Song peek() {
        if (this.first == null)
            return null;
        //check if the list is null, returns null if it is
        return this.first.getSong();
        //returns the first song, without removing it
    }

    /**
     * Returns true if and only if there are no songs in this queue
     *
     * @return true if this queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return numSongs == 0;
        //no elements means emt
    }

    /**
     * Returns the number of songs in this queue
     *
     * @return the number of songs in this queue
     */
    public int size() {
        return numSongs;
    }

    /**
     * Creates and returns a formatted string representation of this playlist, with the string
     * version of each song in the list on a separate line. For example:
     *
     * @return the string representation of this playlist
     */
    @Override public String toString() {
        String s = "";
        for (SongNode cur = first; cur != null; cur = cur.getNext()) {
            s += cur.getSong().toString() + "\n";
        }
        return s;
        //using dequeue, get the string to concatenate to the string to be returned, making sure
        //to make a new line after every song
    }
}
