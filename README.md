# SocialNetwork
Creates a social network with basic features for friending, blocking, and messaging.
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
