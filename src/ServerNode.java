import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerNode {
    private List<ClientNode> clients;

    public ServerNode() {
        clients = new ArrayList<>();
    }

    // Method to add client nodes to the server node
    public void addClient(ClientNode client) {
        clients.add(client);
    }


//display of client destination options
//    public static void destinationOptions() {
//        Scanner scanner = new Scanner(System.in);
//
//        for(i=0, i< clients.length, i++){
//            System.out.println("Name of client ", clients[i]);
//        }
//
//        System.out.println("Choose an option:");
//        System.out.println("Opt 1:  Broadcast all connected Clients.");
//        System.out.println("Opt 2:  Send to specific a Client. ");
//        System.out.print("Enter your choice: ");
//
//        int choice = scanner.nextInt();
//
//
//        if (choice == 1) {
//            option1Logic();
//        } else if (choice == 2) {
//            option2Logic();
//        } else {
//            System.out.println("Invalid choice. Please select a valid option.");
//        }
//
//        scanner.close();
//    }

//    public static void option1Logic() {
//        System.out.println("You selected Option 1.");
//
//
//
//        public static void option2Logic() {
//            System.out.println("You selected Option 2.");}



    // Method to broker messages sent by client nodes
    public void brokerMessage(ClientNode sender, String message) {
        System.out.println("Message received by Server from " + sender.getId() + ": " + message);

        // Compress the message using Huffman coding
        String compressedMessage = HuffmanCoding.compress(message);

        // Forward compressed message to all other clients except the sender
        for (ClientNode client : clients) {
            if (client != sender) {
                client.receive(sender.getId(), compressedMessage);
            }
        }
    }
}