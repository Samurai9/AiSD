package AiSD.Task7;

public class AVLTreeV3 {

    private Node root;

    private class Node {
        private int value;
        private int height;
        private Node leftChild;
        private Node rightChild;

        private Node(int value) {
            this.value = value;
            height = 1;
        }
    }

    public void add(int key){
        root = add(root, key);
    }

    private Node add(Node node, int value) {

        if (node == null) {
            return new Node(value);
        }

        if (value <= node.value) {
            node.leftChild = add(node.leftChild, value);
        } else {
            node.rightChild = add(node.rightChild, value);
        }
        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;


        int balanceFactor = getBalance(node);
        // Малое правое вращение
        if (balanceFactor == 2 && value < node.leftChild.value) {
            return rightRotate(node);
        }

        // Малое левое вращение
        if (balanceFactor == -2 && value > node.rightChild.value) {
            return leftRotate(node);
        }

        //Большое правое вращение
        if (balanceFactor == 2 && value > node.leftChild.value) {
            node.leftChild = leftRotate(node.leftChild);
            return rightRotate(node);
        }

        //Большое левое вращение
        if (balanceFactor == -2 && value < node.rightChild.value) {
            node.rightChild = rightRotate(node.rightChild);
            return leftRotate(node);
        }

        return node;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.leftChild) - height(node.rightChild);
    }

    private Node rightRotate(Node a) {
        Node b = a.leftChild;
        Node c = b.rightChild;

        b.rightChild = a;
        a.leftChild = c;

        a.height = Math.max(height(a.leftChild), height(a.rightChild)) + 1;
        b.height = Math.max(height(b.leftChild), height(b.rightChild)) + 1;

        return b;
    }

    private Node leftRotate(Node a) {
        Node b = a.rightChild;
        Node c = b.leftChild;

        b.leftChild = a;
        a.rightChild = c;

        a.height = Math.max(height(a.leftChild), height(a.rightChild)) + 1;
        b.height = Math.max(height(b.leftChild), height(b.rightChild)) + 1;

        return b;
    }

    public void dfs(){
        dfs(root);
    }

    private void dfs(Node node){
        if(node != null) {
            System.out.print(node.value + " ");
        }
        if(node.leftChild != null) {
            dfs(node.leftChild);
        }
        if(node.rightChild != null) {
            dfs(node.rightChild);
        }
    }
}
