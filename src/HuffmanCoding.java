import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanNode {
    int frequency;
    char data;
    HuffmanNode left, right;

    HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = null; // Initialize left node to null
        this.right = null; // Initialize right node to null
    }
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.frequency - y.frequency;
    }
}

public class HuffmanCoding {

    private static Map<Character, String> characterToCode = new HashMap<>();
    private static Map<String, Character> codeToCharacter = new HashMap<>();
}
