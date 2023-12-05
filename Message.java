public class Message {
    String message;
    User sender;

    public Message(User sender, String message) {
        this.sender=sender;
        this.message=message;
    }


    public void setMessage(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getSender() {
        return sender;
    }

    public String toString() {
        return sender.getUserName()+ ": "+message;
    }
}
