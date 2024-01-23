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
 * A singly-linked node for our linked queue, which contains a Song object
 */
public class SongNode extends Object {
    //The next SongNode in the queue
    private final Song song;
    private SongNode next;
    //The song object in the node

    /**
     * Constructs a single SongNode containing the given data, not linked to any other SongNodes
     *
     * @param data the Song for this node
     * @throws IllegalArgumentException if data is null
     */
    public SongNode(Song data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        this.song = data;
        this.next = null;
    }

    /**
     * Constructs a single SongNode containing the given data, linked to the specified SongNode
     *
     * @param data the Song for this node
     * @param next the next node in the queue
     * @throws IllegalArgumentException if data is null
     */
    public SongNode(Song data, SongNode next) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        this.song = data;
        this.next = next;
    }

    /**
     * Accessor method for this node's data
     *
     * @return the Song in this node
     */
    public Song getSong() {
        return this.song;
    }

    /**
     * Accessor method for the next node in the queue
     *
     * @return the SongNode following this one, if any
     */
    public SongNode getNext() {
        return this.next;
    }

    /**
     * Changes the value of this SongNode's next data field to the given value
     *
     * @param next the SongNode to follow this one; may be null
     */
    public void setNext(SongNode next) {
        this.next = next;
    }
}
