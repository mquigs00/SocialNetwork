package Network;

import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class SocialNetwork {
    // member data
    private ArrayList<Person> _network;     // the list of people in the network
    
    // member functions:
    public SocialNetwork() {
        _network = new ArrayList();
    }
    
    /**
     * Adds new people to the social network. Constructs a new person using the
     * entered name. If the network does not already contain the person's name, then
     * they are added to the social network and canCreate equals true.
     * 
     * @param first     the first name of the new user
     * @param last      the last name of the new user
     * @return  canCreate       true if the new user's name isn't already in the network
     */
    public boolean createPerson(String first, String last) {
        Person p = new Person(first, last);    // instantiates the person using their name 
        boolean canCreate = !_network.contains(p);   // true if the person's name isn't already in the network
        
        // if the person isn't already in the network, add them to it
        if (canCreate) {
            // if the network doesn't already contain someone with the same name, then add the new person
            _network.add(p);
        }
        
        return canCreate;
    }
    
    /**
     * Searches through the network for a specific person. Instantiates a person
     * using the entered name and then iterates through the list of network users
     * checking to see if any of them have the same name. If so, they are returned.
     * 
     * @param firstName     the first name of the person needed
     * @param lastName      the last name of the person needed
     * @return toReturn     the person the user was looking for
     */
    public Person findPerson(String firstName, String lastName) {
        Person target = new Person(firstName, lastName);    // instantiates a person with the given name
        
        Person toReturn = null; // the person to return. If we don't find the target, return null
        
        // if the network contains the person you're looking for, set toReturn equal to it
        if (_network.contains(target)) {
            int index = _network.indexOf(target); // get the index of the person
            
            toReturn = _network.get(index); // set toReturn equal to the person we're looking for
        }
        
        return toReturn;
    }
    
    /**
     * Sends a message from one person to another. Gets the recipient of the message
     * and then uses the receive message function to send the message.
     * 
     * @param   message     the text to be sent
     * @return  canSend     true if the sender is not blocked by the recipient
     */
    public boolean sendMessage(Message message) {
        boolean canSend = message.getRecipient() != null;
        
        if (canSend) {
            Person recipient = message.getRecipient();   // find the recipient of the message
            canSend = recipient.receiveMessage(message);
        }
        
        
        // call the receive message from the person class to send the message
        return canSend;
    }
    
    /**
     * Attempts to add a new friend using the newFriend method in the Person class. If 
     * the person requesting a friend is not blocked, then they will be added to each 
     * other's friends lists
     * 
     * @param initiator     the person requesting a friendship
     * @param recipient     the person being invited to friend
     * @return  true if the initiator is not blocked by the recipient, false if otherwise
     */
    public boolean requestFriend(Person initiator, Person recipient){
        boolean canFriend = initiator != null && recipient != null;
        
        if (canFriend) {
            // call the addFriend method from the Person class
            canFriend = initiator.addFriend(recipient);
        }
        
        return canFriend;
    }
    
    /**
     * Adds the recipient to the initiator's block list, given that they are not already
     * on it.
     * 
     * @param initiator the person attempting to block someone
     * @param recipient the person being blocked
     * @return 
     */
    public boolean block(Person initiator, Person recipient) {
        // call the block method from Person class to block the recipient
        boolean canBlock = initiator.block(recipient);
        return canBlock;
    }
    
    /**
     * Prints out a string containing all of the persons friends.
     * 
     * @param someone the person who's friends will be displayed
     */
    public void listFriends(Person someone) {
        // print out the result string of the getFriends method
        System.out.println(someone.getFriendsListString());
    }
    
    /**
     * Prints out a string containing all of the persons messages
     * 
     * @param someone the person who's messages will be displayed
     */
    public void listMessages(Person someone) {
        System.out.println(someone.getMessagesListString());
    }
    
    /**
     * Prints out a string containing all of the persons on the user's blocked list
     * 
     * @param someone the person who's blocked list will be displayed
     */
    public void listBlocked(Person someone) {
        System.out.println(someone.getBlockedListString());
    }
    
    /**
     * Removes two friends from each other's friends list
     * 
     * @param initiator the person attempting to unfriend someone
     * @param recipient the person being unfriended
     * 
     * @return unUnFriend   true if the initiator was allowed to unFriend the recipient
     */
    public boolean unFriend (Person initiator, Person recipient) {
        boolean canUnFriend = initiator.unFriend(recipient);
        return canUnFriend;
    }
    
    /**
     * Tells if two persons are on each others friends list.
     * 
     * @param person1   one of the two people
     * @param person2   the other person
     * 
     * @return areFriends   true if the two people are friends
     */
    public boolean areFriends (Person person1, Person person2) {
        boolean areFriends = false; // set areFriends to null originally
        boolean canCheck = person1 != null; // if the first person is null, don't check
        
        // if the first person is a person, call the areFriends method. It will return false if
        // they are not friends or the second person is null
        if (canCheck) {
            // calls the areFriends method from the Person class
            areFriends = person1.areFriends(person2);
        }
        
        return areFriends;
    }
    
}
