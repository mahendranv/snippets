import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

class Node {
    char value;
    Node left;
    Node right;

    public Node(char value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

public class BSTGenerator {

    public static Node generateBST(String input) {
        Map<Character, Node> nodeMap = new HashMap<>();
        String[] lines = input.split("\n");

        for (String line : lines) {
            String[] values = line.trim().split("\\|");
            char key = values[0].charAt(0);
            char leftKey = values.length > 1 ? values[1].charAt(0) : '-';
            char rightKey = values.length > 2 ? values[2].charAt(0) : '-';

            Node node = nodeMap.computeIfAbsent(key, k -> new Node(key, null, null));

            if (leftKey != '-') {
                node.left = nodeMap.computeIfAbsent(leftKey, k -> new Node(leftKey, null, null));
            }

            if (rightKey != '-') {
                node.right = nodeMap.computeIfAbsent(rightKey, k -> new Node(rightKey, null, null));
            }
        }

        return nodeMap.get(lines[0].trim().split("\\s+")[0].charAt(0));
    }

    public static void main(String[] args) {
        String input2 = "A|B|C\n" +
                "B|D|E\n" +
                "C|-|F\n" +
                "D|G|-\n" +
                "E|-|H";

        String input3 = "A|B|-\n" +
                "B|C|D\n" +
                "D|-|E\n" +
                "E|F|G";

        String input = "A|B|C\n" +
                "B|D|-\n" +
                "D|F|-\n" +
                "F|H|-\n" +
                "C|-|E\n" +
                "E|-|G";

        Node root = generateBST(input);
        // Use the generated BST root as needed.
        System.out.println("Root = " + root);
        System.out.println("BFS:\n" + bfs(root));
        System.out.println("\nDFS:\n" + dfs(root));
    }

    private static String bfs(Node root) {
        StringBuilder sb = new StringBuilder();
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node n = q.poll();
            // System.out.print("-" + n.value);

            sb.append(n.value);
            if (n.left != null)
                q.add(n.left);

            if (n.right != null)
                q.add(n.right);
        }
        return sb.toString();
    }

    private static String dfs(Node root) {
        StringBuilder sb = new StringBuilder();
        Stack<Node> q = new Stack<Node>();
        q.push(root);

        while (!q.isEmpty()) {
            Node n = q.pop();

            if (n.right != null)
                q.push(n.right);

            sb.append(n.value);
            if (n.left != null)
                q.push(n.left);
        }
        return sb.toString();
    }
}
