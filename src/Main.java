public class Main {
    public static void main(String[] args) {
        // Create a star network
        Star starNetwork = new Star();

        // Insert client nodes into the network
        starNetwork.insertNode("Client1");
        starNetwork.insertNode("Client2");
        starNetwork.insertNode("Client3");

        // Get references to client nodes from the star network
        ClientNode client1 = starNetwork.getClientNode("Client1");
        ClientNode client2 = starNetwork.getClientNode("Client2");
        ClientNode client3 = starNetwork.getClientNode("Client3");

        // Send messages with Huffman codes
        String message1 = "Hello from Client 1!";
        String message2 = "Hello from Client 2!";
        String message3 = "Hello from Client 3!";

        // Ensure Huffman tree is built before sending messages
        client1.sendWithHuffmanCodes(message1);
        client2.sendWithHuffmanCodes(message2);
        client3.sendWithHuffmanCodes(message3);
    }
}