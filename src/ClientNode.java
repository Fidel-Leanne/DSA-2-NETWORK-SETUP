public class ClientNode {
    private String id;
    private ServerNode serverNode;
    private boolean huffmanTreeBuilt = false;

    public ClientNode(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    // Method to set the server node for the client
    public void setServerNode(ServerNode serverNode) {
        this.serverNode = serverNode;
    }

    // Method to send message to the server node with Huffman codes
    public void sendWithHuffmanCodes(String message) {
        // Ensure Huffman tree is built before sending message
        if (!huffmanTreeBuilt) {
            HuffmanCoding.buildHuffmanTree(message);
            huffmanTreeBuilt = true;
        }

        // Compress the message using Huffman coding
        String compressedMessage = HuffmanCoding.compress(message);
        System.out.println("Compressed message for " + id + ": " + compressedMessage);

        // Print Huffman codes associated with each character
        System.out.println("Huffman codes for " + id + ":");
        HuffmanCoding.printHuffmanCodes();

        // Send compressed message to the server node
        if (serverNode != null) {
            serverNode.brokerMessage(this, compressedMessage);
        } else {
            System.out.println("Client " + id + " is not connected to any server.");
        }
    }

    // Method to receive message from the server node
    public void receive(String sender, String message) {
        // Decompress the message using Huffman coding
        String decompressedMessage = HuffmanCoding.decompress(message);
        System.out.println("Message received by " + id + " from " + sender + ": " + decompressedMessage);
    }

}
