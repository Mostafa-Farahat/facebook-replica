import java.io.*;
import java.util.ArrayList;

public class Chat extends Message {
    private String chatName;
    private ArrayList<Message> messages;
    private ArrayList<User> participants;

    public Chat(String chatName,Message message) {
        super(message.sender,message.message);
        this.chatName = chatName;
        this.messages = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    // Getters and setters for chatName

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    // Getters for messages and participants

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    // Method to add message to the chat

    public void addMessage(Message message) {
        messages.add(message);
    }

    // Method to add participant to the chat

    public void addParticipant(User user) {
        participants.add(user);
    }

    // Method to remove participant from the chat

    public void removeParticipant(User user) {
        participants.remove(user);
    }

    // Method to save chat data to a file


    public void saveChatToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName,true))) {
            writer.println("Chat Name: " + chatName);
            writer.println("Messages:");
            for (Message message : messages) {
                writer.print(message.toString());
                writer.println();
            }
            writer.println("Participants:");
            for (User participant : participants) {
                writer.println(participant.getUserName());
            }
            writer.println(" ");
            System.out.println("Chat data saved to file: " + fileName);
        } catch (Exception error) {
            System.out.println("Error saving chat data");
        }
    }
//  Method to load chat data from a file

    public void loadChatFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Chat Name: ")) {
                    this.chatName = line.substring("Chat Name: ".length());
                    System.out.println(line);
                } else if (line.equals("Messages:")) {
                    while ((line = reader.readLine()) != null && !line.equals("Participants:")) {
                        System.out.println(line);
                    }
                }
            }
            System.out.println("Chat data loaded from file: " + fileName);
        } catch (Exception error) {
            System.out.println("Error loading chat data");
        }
    }
}
