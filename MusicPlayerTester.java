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


import java.util.Scanner;

/**
 * Contains a series of methods which test the Song Constructor and method within the song class,
 * the song class's playback capability, the functionality of the Songnode constructor(s) and
 * methods within the Songnode class, as well as the enqueue and dequeue methods of the
 * Playlist class.
 */
public class MusicPlayerTester {
    /**
     * tests the functionality of the song constructor
     *
     * @return true if the test verifies a correct functionality and false otherwise
     */
    public static boolean testSongConstructor() {
        try {
            Song s = new Song("akshay", "akshay", "akshay is cool");
            System.out.println("song constructor invalid constructor input");
            return false;
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            System.out.println("how tf u get a wrong exception bro what");
            return false;
        }

        try {
            Song s1 = new Song("akshay", "gona", "audio/1.mid");
            if (!s1.getTitle().equals("akshay")) {
                System.out.println("getTitle fails");
                return false;
            } else if (!s1.getArtist().equals("gona")) {
                System.out.println("getArtist fails");
                return false;
            } else if (!s1.toString().equals("\"akshay\" (0:6) by gona")) {
                System.out.println("toString(song) be busted fr");
                return false;
            }
        } catch (Exception e) {
            System.out.println("how the hell u get exception with valid file bru");
            return false;
        }
        return true;
    }

    /**
     * tests the functionality behind song playback
     *
     * @return true if the test verifies a correct functionality and false otherwise
     */
    public static boolean testSongPlayback() {
        Song s1 = null;
        try {
            s1 = new Song("akshay", "gona", "audio/1.mid");
        } catch (Exception e) {
            System.out.println("song constructor is wrong with valid input, wtf");
        }
        s1.play();
        try {
            Thread.sleep(1000);
            if (!s1.isPlaying()) {
                System.out.println("song aint playin");
                return false;
            }
        } catch (InterruptedException e) {
            return false;
        }
        s1.stop();
        //round 2 to test again
        s1.play();
        try {
            Thread.sleep(1000);
            if (!s1.isPlaying()) {
                System.out.println("cannot reopen song?, doesnt play");
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        s1.stop();
        return true;
    }

    /**
     * tests the functionality behind the Songnode constructors and other methods within the
     * Songnode class
     *
     * @return true if the test verifies a correct functionality and false otherwise
     */
    public static boolean testSongNode() {
        try {
            SongNode n = new SongNode(null);
            System.out.println("data being null should throw IAE");
            return false;
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            System.out.println("bro what how u get a completely diff exception");
            return false;
        }
        try {
            Song s11 = new Song("akshay", "gona", "audio/1.mid");
            Song s22 = new Song("is", "cool", "audio/2.mid");
            SongNode s2 = new SongNode(s22);
            SongNode s1 = new SongNode(s11, s2);
            if (!s1.getNext().equals(s2)) {
                System.out.println("constructor for two arg songnode is wrong");
                return false;
            }
        } catch (Exception e) {
            System.out.println("how the hell did u get a random exception");
            return false;
        }
        try {
            Song s1 = new Song("akshay", "gona", "audio/3.mid");
            Song s2 = new Song("is", "cool", "audio/4.mid");
            SongNode node1 = new SongNode(s1);
            SongNode node2 = new SongNode(s2);
            if (!node1.getSong().equals(s1)) {
                System.out.println("getSong fails");
                return false;
            }
            node1.setNext(node2);
            if (!node1.getNext().equals(node2)) {
                System.out.println("setNext or getNext fails");
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * tests the functionality behind the enqueue method in the playlist class
     *
     * @return true if the test verifies a correct functionality and false otherwise
     */
    public static boolean testEnqueue() {
        //testing blank playlist
        Playlist p = new Playlist();
        if (p.size() != 0) {
            return false;
        }
        if (p.isEmpty() != true) {
            return false;
        }
        if (p.peek() != null) {
            return false;
        }
        //test adding to playlist
        Song song = new Song("akshay", "gona", "audio/1.mid");
        p.enqueue(song);
        if (p.peek() != song) {
            System.out.println("enqueue fails");
            return false;
        }
        Song song2 = new Song("akshay", "gona", "audio/2.mid");
        p.enqueue(song2);
        p.dequeue();
        if (p.peek() != song2) {
            System.out.println("enqueue fails");
            return false;
        }
        return true;
    }

    /**
     * tests the functionality behind the dequeue method in the playlist class
     *
     * @return true if the test verifies a correct functionality and false otherwise
     */
    public static boolean testDequeue() {
        try {
            Playlist p = new Playlist();
            Song s1 = new Song("a", "b", "audio/1.mid");
            Song s2 = new Song("c", "d", "audio/2.mid");
            p.enqueue(s1);
            p.enqueue(s2);
            //creates sample songs and playlist
            if (p.dequeue() != s1) {
                System.out.println("dequeue does not work");
                return false;
                //test dequeue 1
            }
            if (p.size() != 1) {
                System.out.println("dequeue does not update size properly");
                return false;
                //test dequeue 2
            }
            if (p.peek() != s2) {
                System.out.println("dequeue does not remove elements properly");
                return false;
                //test dequeue 3
            }
            if (p.dequeue() != s2) {
                System.out.println("dequeue does not return string that should have been removed");
                return false;
                //test dequeue 4
            }
            if (p.size() != 0 || p.peek() != null) {
                System.out.println("dequeue fails to remove size and update elements");
                return false;
                //test dequeue 5
            }
            return true;
            //return true if and only if all conditions are passed
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * main method to run the testers and Music player
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("testSongConstructor:" + testSongConstructor());
        System.out.println("testSongPlayback:" + testSongPlayback());
        System.out.println("testSongNode:" + testSongNode());
        System.out.println("testEnqueue:" + testEnqueue());
        System.out.println("testDequeue:" + testDequeue());

        MusicPlayer300 test = new MusicPlayer300();
        test.runMusicPlayer300(new Scanner(System.in));
    }
}
