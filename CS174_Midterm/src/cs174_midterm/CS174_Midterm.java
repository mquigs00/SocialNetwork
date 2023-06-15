package cs174_midterm;

import Network.SocialNetwork;
import Network.Person;
import Network.Message;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Creates a social network with basic features for friending, blocking, and messaging.
 * 
 * The Person class contains all of the information to create a person and allow them to friend,
 * block and message people. All member data, including the lists of their messages, friends, and
 * blocked persons are accessible through getter methods.
 * 
 * The SocialNetwork class utilizes the methods and of the Person class to create a network using an ArrayList.
 * Members are entered into the network by taking in a name and instantiating the person before adding them to list.
 * No two members can have the same name in order to avoid confusion. Members of the network can then friend, message,
 * and block each other.
 * 
 * The main calls the takeFile() method from the driver, which takes in a file containing instructions for how to set
 * up the network and verify that it works correctly. The first letter of each line in the file corresponds to a function
 * from the network class, which is called on the one or two names also provided on that line of the file. Methods
 * that check for lists of friends, blocked persons, and messages, as well as whether or not two persons are friends, print
 * to the terminal so the user can check that methods are working properly. The user is also notified if a method is called on
 * someone who is not a member of the network.
 * 
 * @author Matt Quigley, CS174, 3/30/21
 * 
 */
public class CS174_Midterm {

    public static void main(String[] args) throws FileNotFoundException { 
        
        // call take File to take in the test files and execute the social network
        takeFile("NetworkTest1.txt");
        takeFile("NetworkTest2.txt");
        takeFile("NetworkTest3.txt");
        takeFile("NetworkTest4.txt");
        takeFile("NetworkTest5.txt");
    }
    
    /**
     * Takes in a file containing instructions for the social network. Then loops
     * through each row of the file, calling different functions from the social network
     * class on the person(s) named in each row depending on the first character of the 
     * line
     * 
     * @param input the name of the file to be read from
     * 
     * @throws FileNotFoundException 
     */
    public static void takeFile(String input)   throws FileNotFoundException{
        
        // -- Setting up to read from a file
        // create a connection between the program and a file on disk
        File scoreFile = new File(input);
        
        // associate a Scanner with that file so we can read from it.
        Scanner in = new Scanner(scoreFile);
        
        SocialNetwork network = new SocialNetwork();    //instantiate the social network
        
        // because we can call the take file method on multiple files in our main, we want to print
        // the name of each file before showing the lists. We don't want the file type (.txt) though.
        int originalFileNameLength = input.length();    // get the length of the file name
        int nameMinusTypeLength = originalFileNameLength - 4;   // substract the last four charcters from the length (for ".txt")
        String fileName = input.substring(0, nameMinusTypeLength);  // chop the ".txt" off the end of the file name
        System.out.println(fileName + ":"); // print the file name before showing all of the lists
        
        // Iterate through the file reading the commands and calling the proper functions
        // Read Loop
        while (in.hasNextLine()){
            String line = in.nextLine();
            
            // split the line into its tokens which are separated by spaces
            String [] tokens = line.split(",");
            
            // Depending on the first character in the line, different methods will be called
            if (null != tokens[0]) 
            switch (tokens[0]) {
                // if the first letter is a 'P', create the person using their name and add them to the network
                case "P":
                    String firstName = tokens[1];
                    String lastName = tokens[2];
                    network.createPerson(firstName, lastName);
                    break;
                    
                    // if the first letter is a 'M', create a message using the names of the sender and recipient and the text.
                    // then send the message to the recipient
                case "M":
                    Person sender = network.findPerson(tokens[1], tokens[2]);
                    Person recipient = network.findPerson(tokens[3], tokens[4]);
                    String text = tokens[5];
                    Message newMessage = new Message(sender, recipient, text);
                    network.sendMessage(newMessage);
                    break;
                    
                    // if the first letter is a 'F', find the two members in the network using the names given and then friend them
                case "F":
                    Person friender = network.findPerson(tokens[1], tokens[2]);
                    Person friendee = network.findPerson(tokens[3], tokens[4]);
                    
                    if (friender == null) {
                        System.out.println(tokens[1] + " " + tokens[2] + " is not a member of the network!");
                        System.out.println("");
                    } else if (friendee == null) {
                        System.out.println(tokens[3] + " " + tokens[4] + " is not a member of the network!");
                        System.out.println("");
                    } else
                        network.requestFriend(friender, friendee);
                    
                    break;
                    
                    // if the first letter is a 'B', find the two persons in the network using the names given and then block the recipient from
                    // the initiator
                case "B":
                    Person blocker = network.findPerson(tokens[1], tokens[2]);
                    Person blockee = network.findPerson(tokens[3], tokens[4]);
                    boolean canBlock = blocker != null;
                    
                    if (canBlock) {
                        network.block(blocker, blockee);
                    } else {
                        System.out.println(tokens[1] + " " + tokens[2] + " is not a member of the network!");
                        System.out.println("");
                    }
              
                    break;
                    
                    // if the first letter is a 'L', find the person in the network and list their friends
                case "L":
                    boolean canListFriends = network.findPerson(tokens[1], tokens[2]) != null;
                    
                    if (canListFriends) {
                        Person friend = network.findPerson(tokens[1], tokens[2]);
                        network.listFriends(friend);
                    } else {
                        System.out.println(tokens[1] + " " + tokens[2] + " is not a member of the network!");
                        System.out.println("");
                    }
                    
                    break;
                    
                     // if the first letter is an 'E', find the person in the network and list their messages
                case "E":
                    boolean canListMessages = network.findPerson(tokens[1], tokens[2]) != null;
                    
                    if (canListMessages) {
                        Person messenger = network.findPerson(tokens[1], tokens[2]);
                        network.listMessages(messenger);
                    } else {
                        System.out.println(tokens[1] + " " + tokens[2] + " is not a member of the network!");
                        System.out.println("");
                    }
                    
                    break;
                    
                    // if the first letter is an 'R', find the person in the network and list the people they've blocked
                case "R":
                    boolean canListBlocked = network.findPerson(tokens[1], tokens[2]) != null;
                    
                    if (canListBlocked) {
                        Person block = network.findPerson(tokens[1], tokens[2]);
                        network.listBlocked(block);
                    } else {
                        System.out.println(tokens[1] + " " + tokens[2] + " is not a member of the network!");
                        System.out.println("");
                    }
                    
                    break;
                    
                    // if the first letter is a 'U', find the two people in the network and unfriend them
                case "U":
                    Person unfriender = network.findPerson(tokens[1], tokens[2]);
                    Person unfriended = network.findPerson(tokens[3], tokens[4]);
                    network.unFriend(unfriender, unfriended);
                    break;
                    
                    // if the first letter is a 'Q', find the two people in the network and check if they are friends
                case "Q":
                    Person p1 = network.findPerson(tokens[1], tokens[2]);
                    Person p2 = network.findPerson(tokens[3], tokens[4]);
                    if (network.areFriends(p1, p2)) {
                        System.out.println("Yes");
                        System.out.println("");
                    } else {
                        System.out.println("No");
                        System.out.println("");
                    }   break;
                default:
                    break;
            }
        }    
        
        // close the input.  This saves the file.
        in.close();
    }
}

