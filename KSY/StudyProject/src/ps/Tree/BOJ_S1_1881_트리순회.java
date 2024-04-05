package ps.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1881_트리순회 {
    static class Node{
        char data;
        Node left, right;
        public Node(char data){
            this.data = data;
        }
        public Node(char data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    static Node root = null;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine())-1;

        StringTokenizer st = new StringTokenizer(in.readLine());
        char parent = st.nextToken().charAt(0);
        char left = st.nextToken().charAt(0);
        char right = st.nextToken().charAt(0);
        root = new Node(parent, (left == '.')? null : new Node(left), (right == '.')? null : new Node(right));

        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            parent = st.nextToken().charAt(0);
            left = st.nextToken().charAt(0);
            right = st.nextToken().charAt(0);
            insertNode(root, parent, left, right);
        }

        //전위순회 : root부터 left가 null일때까지 계속 내려감 root -> left -> right
        preOrder(root);
        System.out.println();
        //중위순회 : left -> root -> right;
        inOrder(root);
        System.out.println();
        //후위순회 : left -> right -> root
        postOrder(root);
        System.out.println();
    }

    private static void preOrder(Node root) {
        System.out.print(root.data);
        if (root.left != null) preOrder(root.left);
        if (root.right != null) preOrder(root.right);
    }

    private static void inOrder(Node root) {
        if (root.left != null) inOrder(root.left);
        System.out.print(root.data);
        if (root.right != null) inOrder(root.right);
    }

    private static void postOrder(Node root) {
        if (root.left != null) postOrder(root.left);
        if (root.right != null) postOrder(root.right);
        System.out.print(root.data);
    }

    private static void insertNode(Node root, char parent, char left, char right) {
        if (root.data == parent){
            //찾았으면 넣어줌
            if (left != '.') root.left = new Node(left);
            if (right != '.') root.right = new Node(right);
            return;
        }

        //아니라면 내려감
        if (root.left != null) insertNode(root.left, parent, left, right);
        if (root.right != null) insertNode(root.right, parent, left, right);
    }


}
