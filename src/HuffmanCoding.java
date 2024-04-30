import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// Represents a node in the Huffman tree
class HuffmanNode {
    int frequency; // Frequency of the character
    char data; // Character data
    HuffmanNode left, right; // Left and right child nodes

    // Constructor to initialize a Huffman node with character data and frequency
    HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = null; // Initialize left node to null
        this.right = null; // Initialize right node to null
    }
}

// Comparator class for Huffman nodes based on their frequencies
class MyComparator implements Comparator<HuffmanNode> {
    // Compare method to compare two Huffman nodes based on their frequencies
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.frequency - y.frequency;
    }
}

// Huffman coding class for compression and decompression
public class HuffmanCoding {
    // Mapping of characters to their Huffman codes
    private static Map<Character, String> characterToCode = new HashMap<>();
    // Mapping of Huffman codes to characters
    private static Map<String, Character> codeToCharacter = new HashMap<>();

    // Method to build the Huffman tree and generate Huffman codes for characters
    public static void buildHuffmanTree(String message) {
        // Map to store frequencies of characters in the message
        Map<Character, Integer> frequencyMap = new HashMap<>();
        // Calculate frequencies of characters in the message
        for (char c : message.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Priority queue to hold Huffman nodes based on their frequencies
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(frequencyMap.size(), new MyComparator());

        // Add Huffman nodes for each character to the priority queue
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Build the Huffman tree by merging nodes until only one node remains in the queue
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            // Create a parent node with frequency equal to the sum of frequencies of left and right child nodes
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            // Add the parent node back to the queue
            queue.add(parent);
        }

        // Get the root node of the Huffman tree
        HuffmanNode root = queue.peek();
        // Generate Huffman codes for characters in the tree
        buildCodes(root, "");
    }

    // Method to recursively build Huffman codes for characters in the Huffman tree
    private static void buildCodes(HuffmanNode root, String code) {
        if (root == null)
            return;
        // If the node is a leaf node (contains a character), add its code to the mappings
        if (root.data != '\0') {
            characterToCode.put(root.data, code);
            codeToCharacter.put(code, root.data);
        }
        // Recursively build codes for left and right child nodes
        buildCodes(root.left, code + '0');
        buildCodes(root.right, code + '1');
    }

    // Method to compress a message using Huffman coding
    public static String compress(String message) {
        StringBuilder compressedMessage = new StringBuilder();
        // Convert each character in the message to its corresponding Huffman code
        for (char c : message.toCharArray()) {
            compressedMessage.append(characterToCode.get(c));
        }
        return compressedMessage.toString();
    }

    // Method to decompress a message using Huffman coding
    public static String decompress(String compressedMessage) {
        StringBuilder decompressedMessage = new StringBuilder();
        StringBuilder code = new StringBuilder();
        // Iterate through each bit in the compressed message
        for (char bit : compressedMessage.toCharArray()) {
            code.append(bit);
            // If the current code corresponds to a character, add it to the decompressed message
            if (codeToCharacter.containsKey(code.toString())) {
                decompressedMessage.append(codeToCharacter.get(code.toString()));
                code = new StringBuilder(); // Reset the code for the next character
            }
        }
        return decompressedMessage.toString();
    }

    // Method to print Huffman codes associated with each character
    public static void printHuffmanCodes() {
        for (Map.Entry<Character, String> entry : characterToCode.entrySet()) {
            System.out.println("Character: " + entry.getKey() + ", Huffman Code: " + entry.getValue());
        }
    }


    public static void main(String[] args) {
        String message = "Hello, world!";
        
        // Build Huffman tree and generate codes
        buildHuffmanTree(message);
        
        // Print Huffman codes
        System.out.println("Huffman Codes:");
        printHuffmanCodes();
        
        // Compress message
        String compressedMessage = compress(message);
        System.out.println("Compressed Message: " + compressedMessage);
        
        // Decompress message
        String decompressedMessage = decompress(compressedMessage);
        System.out.println("Decompressed Message: " + decompressedMessage);
    }
    
}
