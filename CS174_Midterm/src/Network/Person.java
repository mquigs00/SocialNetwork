package Network;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Matt
 */
public class Person implements Friendable, Messagable{
    // member data:
    private final String _firstName;        // the first name of the person
    private final String _lastName;         // the last name of the person
    private ArrayList<Person> _friends;     // a list of the user's friends
    private ArrayList<Message> _messages;   // a list of the user's messages
    private ArrayList<Person> _blocked;     // a list of people blocked by the user
    
    
    // member functions
    
    //constructor
    public Person(String firstName, String lastName) {
        _firstName = firstName;
        _lastName = lastName;
        _friends = new ArrayList();
        _messages = new ArrayList();
        _blocked = new ArrayList();
    }
    
    /**
     * Makes two user's friends with each other. Only happens successfully if the two users are not already friends and
     * the person initiating the friendship isn't blocked by the friendee
     * 
     * @param friendee      the person receiving a friend request
     * @return canFriend    whether or not the two users may be on each other's friends list
     */
    @Override
    public boolean addFriend(Person friendee) {
        // if the friender is on the friendee's blocked list or they are already friends, they cannot friend request them
        boolean canFriend = (friendee != null) && !friendee.getBlockedList().contains(this) && !_friends.contains(friendee) ;
        
        // if canFriend is true, add each person to the other's friends list and send a message
        // from the friendee to the friender
        if (canFriend) {
            _friends.add(friendee);         // add the friendee to the user's friends list
            friendee._friends.add(this);    // add the user to the friendee's friends list
            
            // write a message from the friendee to the user telling them you're friends now
            Message newFriends = new Message(friendee, this, "We're now friends!");
            _messages.add(newFriends);
        }
        
        return canFriend;
    }
    
    /**
     * Removes two user's from each other's friends list. Can only happen if the two user's
     * are already friends
     * 
     * @param person        the person being unfriended
     * @return canUnFriend  true if they weren't already friends
     */
    @Override
    public boolean unFriend(Person person) {
        // you can's unfriend someone who you aren't already 
        boolean canUnFriend = _friends.contains(person);
        
        if (canUnFriend) {
            _friends.remove(person);    // remove the person from user's friend's list
            person._friends.remove(this);  // remove the user from the person's friends list
        }
        
        return canUnFriend;
    }
    
    /**
     * Adds a person to the the user's blocked list and removes their friendship if they
     * were friends already. Can only work if the recipient is not already on the user's
     * blocked list.
     * 
     * @param recipient     the person being blocked
     * @return canBlock     true if the user wasn't already blocked by the user
     */
    public boolean block(Person recipient) {
        // cannot block someone who is already blocked
        boolean canBlock = !_blocked.contains(recipient) && recipient != null;
        
        // if canBlock is true, unfriend the two users (if they are already friends) and add the
        // recipient to the user's blocked list
        if (canBlock) {
            // if they are already friends, unfriend them
            if (_friends.contains(recipient)) {
                unFriend(recipient);
            }
            // add the recipient to the user's blocked list
            _blocked.add(recipient);
        } else if (recipient == null) {
            System.out.println("Recipient is not a member of the social network!");
            System.out.println("");
        }
        
        return canBlock;
    }
    
    /**
     * Adds a new message to the recipients message list if the sender is not blocked.
     * 
     * @param m     the message to be received
     * @return      canReceive, true if the sender is not blocked by the recipient
     */
    @Override
    public boolean receiveMessage(Message m){
        // users can't send messages to people who have blocked them
        boolean canReceive = !_blocked.contains(m.getSender()) && m.getRecipient() != null;  
        
        // if canReceive is true, add the message the user's message list
        if (canReceive) {
            _messages.add(m);
        }
        
        return canReceive;
    }
    
    /**
     * Provides the list of the user's friends
     * 
     * @return _friends     the list of the user's friends
     */
    public ArrayList<Person> getFriendsList() {
        return _friends;
    }
    
    /**
     * Provides a string containing all of the user's friends
     * 
     * @return friends      a string containing all of the user's friends
     */
    public String getFriendsListString() {
        boolean canCheck = this != null;
        
        String friends = "";
        if (canCheck) {
            // make a heading for the list (ex: John Smith's Friends:)
            friends = _firstName + " " + _lastName + "'s Friends:" + "\r\n";

            // for every person in the friends list, get their name and add it to a new line of the friends string
            for (int i = 0; i < _friends.size(); i++) {
                Person friend = _friends.get(i);        // get the next friend
                String friendName = friend.getName();   // get their name
                friends += friendName + "\r\n";         // add it to the list and move to the next line
            }

        }
              
        return friends;
    }
    
    /**
     * Provides the user's list of messages.
     * 
     * @return _messages    the ArrayList of messages
     */
    public ArrayList<Message> getMessagesList() {
        return _messages;
    }
    
    /**
     * Provides a string containing all of the user's messages.
     * 
     * @return message  a string of all the user's messages
     */
    public String getMessagesListString() {
        // make a heading for the list (ex: John Smith's Messages:)
        String messages = _firstName + " " + _lastName + "'s Messages:" + "\r\n";
        
        // for every message in the user's message list, add its sender and text to the messages string
        for (int i = 0; i < _messages.size(); i++) {
            Message message = _messages.get(i);     // get the current message in the list
            String messageText = message.getText(); // get its text
            Person sender = message.getSender();    // get its sender
            String senderName = sender.getName();   // get the full name of the sender
            messages += senderName + ": " + messageText + "\r\n";   // add the sender and their message to the next line of the string
        }
        
        return messages;
    }
    
    /**
     * Provides the user's list of blocked persons
     * 
     * @return _blocked     the list of blocked users
     */
    public ArrayList<Person> getBlockedList() {
        return _blocked;
    }
    
    /**
     * Provides a string of the user's blocked list
     * 
     * @return blocked  a string of all the user's blocked people
     */
    public String getBlockedListString() {
        // make a heading for the list (ex: John Smith's Blocked List:)
        String blocked = _firstName + " " + _lastName + "'s Blocked List:" + "\r\n";
        
        // for every blocked person in the blocked, add their name to the blocked string
        for (int i = 0; i < _blocked.size(); i++) {
                Person blockedPerson = _blocked.get(i);         // get the next person in the list
                String blockedName = blockedPerson.getName();   // get their full name
                blocked += blockedName + "\r\n";                // add their name to the string and add a new line
        }
        
        return blocked;
    }
    
    
    /**
     * Returns the full name of the person.
     * 
     * @return fullName     a string of the person's first and last name
     */
    public String getName() {
        String fullName = _firstName + " " + _lastName;
        return fullName;
    }
    
    /**
     * Tells if two people are friends.
     * 
     * @param p             // one of the person's you are checking
     * @return  areFriends  // true if p is in the user's friends list
     */
    public boolean areFriends(Person p) {
        boolean areFriends = false;
        boolean canCheck = p != null;
        
        if (canCheck) {
            // if the friends list contains p, then they are friends
            areFriends = _friends.contains(p);
        } else {
            System.out.println("Person entered is not a member of the network!");
            System.out.println("");
        }
        
        return areFriends;
    }
    
    /**
     * Converts a string describing a person. Includes their 
     * 
     * @return  a string containing the person's name, friends, messages, and, blocked list on separate lines
     */
    @Override
    public String toString() {
        return ("Name: " + _firstName + " " + _lastName + "\r\n" +
                this.getFriendsListString() + "\r\n" +
                this.getMessagesListString() + "\r\n" +
                this.getBlockedListString());
    }
    
    
    /**
     * Performs a deep comparison of two persons, checking if their names are the same.
     * 
     * @param o     the object to be compared
     * @return      true if all member data is equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        // check if you are comparing o to itself
        if (this == o) {
            return true;
        }
        
        //check if o is null
        if (o == null) {
            return false;
        }
        
        //check of they are in different classes
        if (getClass() != o.getClass()) {
            return false;
        }
        
        //convert object o to Person to compare them
        Person person = (Person)o;
        
        // compares the unique ID's to see if they are equal
        return Objects.equals(_firstName, person._firstName)
                && Objects.equals(_firstName, person._firstName);
    }
}
