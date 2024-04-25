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

    public static void buildHuffmanTree(String message) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : message.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(frequencyMap.size(), new MyComparator());

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            queue.add(parent);
        }

        HuffmanNode root = queue.peek();
        buildCodes(root, "");
    }

    private static void buildCodes(HuffmanNode root, String code) {
        if (root == null)
            return;
        if (root.data != '\0') {
            characterToCode.put(root.data, code);
            codeToCharacter.put(code, root.data);
        }
        buildCodes(root.left, code + '0');
        buildCodes(root.right, code + '1');
    }




}
