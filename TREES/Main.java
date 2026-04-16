import java.util.*;

class Node {
    int data;
    Node left, right;
    int hd;

    Node(int val) {
        data = val;
        left = right = null;
        hd = 0;
    }
}

public class Main {

    // INSERT
    static Node insert(Node root, int val) {
        if (root == null)
            return new Node(val);

        if (val < root.data)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);

        return root;
    }

    // INORDER
    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // PREORDER
    static void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // POSTORDER
    static void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    // LEVEL ORDER
    static void levelOrder(Node root) {
        if (root == null)
            return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            System.out.print(cur.data + " ");

            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }

    // TOP VIEW
    static void topView(Node root) {
        if (root == null)
            return;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Node> q = new LinkedList<>();

        root.hd = 0;
        q.add(root);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int h = cur.hd;

            if (!map.containsKey(h))
                map.put(h, cur.data);

            if (cur.left != null) {
                cur.left.hd = h - 1;
                q.add(cur.left);
            }
            if (cur.right != null) {
                cur.right.hd = h + 1;
                q.add(cur.right);
            }
        }

        for (int v : map.values())
            System.out.print(v + " ");
    }

    // LEFT VIEW
    static void leftView(Node root) {
        if (root == null)
            return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node cur = q.poll();

                if (i == 0)
                    System.out.print(cur.data + " ");

                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
            }
        }
    }

    // RIGHT VIEW
    static void rightView(Node root) {
        if (root == null)
            return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node cur = q.poll();

                if (i == size - 1)
                    System.out.print(cur.data + " ");

                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
            }
        }
    }

    // SEARCH
    static Node search(Node root, int val) {
        if (root == null || root.data == val)
            return root;

        if (val < root.data)
            return search(root.left, val);

        return search(root.right, val);
    }

    // LEAF COUNT
    static int leafCount(Node root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        return leafCount(root.left) + leafCount(root.right);
    }

    // FIND MIN
    static Node findMin(Node root) {
        while (root.left != null)
            root = root.left;
        return root;
    }

    // DELETE
    static Node delete(Node root, int key) {
        if (root == null)
            return null;

        if (key < root.data)
            root.left = delete(root.left, key);
        else if (key > root.data)
            root.right = delete(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;

            Node temp = findMin(root.right);
            root.data = temp.data;
            root.right = delete(root.right, temp.data);
        }
        return root;
    }

    public static void main(String[] args) {

        Node root = null;
        int[] arr = { 10, 5, 15, 3, 7, 12, 18 };

        for (int x : arr)
            root = insert(root, x);

        System.out.print("Inorder: ");
        inorder(root);

        System.out.print("\nPreorder: ");
        preorder(root);

        System.out.print("\nPostorder: ");
        postorder(root);

        System.out.print("\nLevel Order: ");
        levelOrder(root);

        System.out.print("\nTop View: ");
        topView(root);

        System.out.print("\nLeft View: ");
        leftView(root);

        System.out.print("\nRight View: ");
        rightView(root);

        System.out.print("\nLeaf Count: " + leafCount(root));

        Node res = search(root, 10);
        System.out.print("\nSearch 10: " + (res != null ? "Found" : "Not Found"));

        root = delete(root, 10);
        System.out.print("\nAfter Deletion: ");
        inorder(root);
    }
}