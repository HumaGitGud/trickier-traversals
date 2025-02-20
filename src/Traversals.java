import java.util.*;

public class Traversals {

  /**
   * Returns the sum of the values of all leaf nodes in the given tree of integers.
   * A leaf node is defined as a node with no children.
   * If node is null, this method returns 0.
   *
   * @param node the node of the tree
   * @return the sum of leaf node values, or 0 if the tree is null
   */
  public static int sumLeafNodes(TreeNode<Integer> node) {
    /*
    if node is null return 0
    create int sum variable
    if node.left is null and node.right is null add current node.value to sum
    add sum of left subtree to sum
    add sum of right subtree to sum
    return sum
     */
    if (node == null) return 0;

    int sum = 0;

    if (node.left == null && node.right == null) {
      sum += node.value;
    }

    sum += sumLeafNodes(node.left);
    sum += sumLeafNodes(node.right);
    return sum;
  }

  /**
   * Counts the number of internal nodes (non-leaf nodes) in the given tree of integers.
   * An internal node has at least one child.
   * If node is null, this method returns 0.
   *
   * @param node the node of the tree
   * @return the count of internal nodes, or 0 if the tree is null
   */
  public static int countInternalNodes(TreeNode<Integer> node) {
    /*
    if node is null return 0
    create int count variable
    if node.left OR node.right isn't null, its an internal node, so increment count
    add count of left subtree to count
    add count of right subtree to count
    return count
     */
    
    if (node == null) return 0;

    int count = 0;

    if (node.left != null || node.right != null) {
      count++;
    }

    count += countInternalNodes(node.left);
    count += countInternalNodes(node.right);
    return count;
  }

  /**
   * Creates a string by concatenating the string representation of each node's value
   * in a post-order traversal of the tree. For example, if the post-order visitation
   * encounters values "a", "b", and "c" in that order, the result is "abc".
   * If node is null, returns an empty string.
   *
   * @param node the node of the tree
   * @param <T>  the type of values stored in the tree
   * @return a post-order traversal string, or an empty string if the tree is null
   */
  public static <T> String buildPostOrderString(TreeNode<T> node) {
    /*
    if node is null return ""
    create result string variable
    traverse in post order
    recursively visit left and right subtrees while concatenating node.value
     */

    if (node == null) {
      return "";
    }

    String result = "";

    result += buildPostOrderString(node.left);
    result += buildPostOrderString(node.right);
    result += node.value;

    return result;
  }

  /**
   * Collects the values of all nodes in the tree level by level, from top to bottom.
   * If node is null, returns an empty list.
   *
   * @param node the node of the tree
   * @param <T>  the type of values stored in the tree
   * @return a list of node values in a top-to-bottom order, or an empty list if the tree is null
   */
  public static <T> List<T> collectLevelOrderValues(TreeNode<T> node) {
    /*
    create a list for results
    create a queue and add node
    if node is null return empty list
    while in queue isnt empty
      dequeue node
      add node value to result list
      enqueue left child if it exists
      enqueue right child if it exists
     */

    List<T> list = new ArrayList<>();

    if (node == null) return list;

    Queue<TreeNode<T>> queue = new LinkedList<>();
    queue.add(node);

    while (!queue.isEmpty()) {
      TreeNode<T> current = queue.poll();
      list.add(current.value);

      if (current.left != null) queue.add(current.left);
      if (current.right != null) queue.add(current.right);
    }

    return list;
  }

  /**
   * Counts the distinct values in the given tree.
   * If node is null, returns 0.
   *
   * @param node the node of the tree
   * @return the number of unique values in the tree, or 0 if the tree is null
   */
  public static int countDistinctValues(TreeNode<Integer> node) {
    /*
    if node is null return 0
    create a hashlist
    create a queue for traversal
    while in queue isnt empty
      dequeue node
      add node value to hashlist
      enqueue left child if it exists
      enqueue right child if it exists
    return hashlist size
     */

    if (node == null) return 0;

    Set<Integer> uniqueValues = new HashSet<>();
    Queue<TreeNode<Integer>> queue = new LinkedList<>();
    queue.add(node);

    while (!queue.isEmpty()) {
      TreeNode<Integer> current = queue.poll();
      uniqueValues.add(current.value);

      if (current.left != null) queue.add(current.left);
      if (current.right != null) queue.add(current.right);
    }

    return uniqueValues.size();
  }

  /**
   * Determines whether there is at least one root-to-leaf path in the tree
   * where each successive node's value is strictly greater than the previous node's value.
   * If node is null, returns false.
   *
   * @param node the node of the tree
   * @return true if there exists a strictly increasing root-to-leaf path, false otherwise
   */
  public static boolean hasStrictlyIncreasingPath(TreeNode<Integer> node) {
    /*
    if node is null return false
    if node is leaf return true
    if left node isnt null and its value is > than curr value
      traverse left node
    if right node isnt null and its value is > than curr value
      traverse right node
    save result in boolean variable and return
     */

    if (node == null) return false;
    if (node.left == null && node.right == null) return true;

    boolean leftPath = false;
    boolean rightPath = false;

    if (node.left != null && node.left.value > node.value) {
      leftPath = hasStrictlyIncreasingPath(node.left);
    }
    if (node.right != null && node.right.value > node.value) {
      rightPath = hasStrictlyIncreasingPath(node.right);
    }

    return leftPath || rightPath;
  }

  // OPTIONAL CHALLENGE
  /**
   * Checks if two trees have the same shape. Two trees have the same shape
   * if they have exactly the same arrangement of nodes, irrespective of the node values.
   * If both trees are null, returns true. If one is null and the other is not, returns false.
   *
   * @param nodeA the node of the first tree
   * @param nodeB the node of the second tree
   * @param <T>   the type of values stored in the trees
   * @return true if the trees have the same shape, false otherwise
   */
  public static <T> boolean haveSameShape(TreeNode<T> nodeA, TreeNode<T> nodeB) {
    if (nodeA == null && nodeB == null) return false;
    if (nodeA == null || nodeB == null) return false;

    boolean leftSame = haveSameShape(nodeA.left, nodeB.left);
    boolean rightSame = haveSameShape(nodeA.right, nodeB.right);
    
    return leftSame && rightSame;
  }


  // OPTIONAL CHALLENGE
  // Very challenging!
  // Hints:
  // List.copyOf may be helpful
  // Consider adding a helper method
  // Consider keeping the current path in a separate variable
  // Consider removing the current node from the current path after the node's subtrees have been traversed.
  /**
   * Finds all paths from the root to every leaf in the given tree.
   * Each path is represented as a list of node values from root to leaf.
   * The paths should be added pre-order.
   * If node is null, returns an empty list.
   * 
   * Example:
   *
   *         1
   *        / \
   *       2   3
   *      / \    \
   *     4   5    6
   * 
   * Expected output:
   *   [[1, 2, 4], [1, 2, 5], [1, 3, 6]]
   * 
   * @param node the root node of the tree
   * @return a list of lists, where each inner list represents a root-to-leaf path in pre-order
   */
  public static <T> List<List<T>> findAllRootToLeafPaths(TreeNode<T> node) {
    return null;
  }
}
