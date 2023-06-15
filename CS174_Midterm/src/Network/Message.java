package Network;

/**
 *
 * @author Matt
 */
public class Message{
    // member data
    private final Person _sender;
    private final Person _recipient;
    private final String _text;
 
    // member functions
    public Message(Person sender, Person recipient, String text) {
        _sender = sender;
        _recipient = recipient;
        _text = text;
    }
    
    public Person getSender() {
        return _sender;
    }
    
    public Person getRecipient() {
        return _recipient;
    }
    
    public String getText() {
        return _text;
    }
    
    @Override
    public String toString() {
        return ("Sender: " + _sender + "  Recipient: " + _recipient + "   Text: " + _text);
    }
}
