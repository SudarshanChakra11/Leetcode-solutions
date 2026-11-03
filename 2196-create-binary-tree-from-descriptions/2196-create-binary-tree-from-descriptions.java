
import java.util.*;

class Solution {

    public TreeNode createBinaryTree(int[][] descriptions) {

        // Store all tree nodes
        Map<Integer, TreeNode> map = new HashMap<>();

        // Store values that appear as children
        Set<Integer> children = new HashSet<>();

        // Process every description
        for (int[] d : descriptions) {

            int parent = d[0];
            int child = d[1];
            int isLeft = d[2];

            // Create parent node if it doesn't exist
            if (!map.containsKey(parent)) {
                map.put(parent, new TreeNode(parent));
            }

            // Create child node if it doesn't exist
            if (!map.containsKey(child)) {
                map.put(child, new TreeNode(child));
            }

            // Connect parent and child
            if (isLeft == 1) {
                map.get(parent).left = map.get(child);
            } else {
                map.get(parent).right = map.get(child);
            }

            // Mark this node as a child
            children.add(child);
        }

        // Find the root node
        for (int node : map.keySet()) {

            if (!children.contains(node)) {
                return map.get(node);
            }
        }

        return null;
    }
}
