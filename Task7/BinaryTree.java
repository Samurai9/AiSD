package AiSD.Task7;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    Node root;
    int size;

    public BinaryTree(){
        size = 0;
    }


    public void add(int value){
        if (root == null){
            root = new Node(value);
            size++;
        }else {
            Node tempNode = root;
            boolean stopFlag = false;
            while (!stopFlag) {
                if (value <= tempNode.value) {
                    if (tempNode.leftChild == null) {
                        tempNode.leftChild = new Node(value);
                        tempNode.leftChild.parent = tempNode;
                        size++;
                        stopFlag = true;
                    } else {
                        tempNode = tempNode.leftChild;
                    }
                }else {
                    if (tempNode.rightChild == null) {
                        tempNode.rightChild = new Node(value);
                        tempNode.rightChild.parent = tempNode;
                        size++;
                        stopFlag = true;
                    } else {
                        tempNode = tempNode.rightChild;
                    }
                }
            }
        }
    }

    public void bfs(){
        bfs(root);
    }

    private void bfs(Node node){
        if(node != null) {
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            Node tempNode;
            while ((tempNode = queue.poll()) != null) {
                System.out.print(tempNode.value + " ");
                if (tempNode.leftChild != null) {
                    queue.add(tempNode.leftChild);
                }
                if (tempNode.rightChild != null) {
                    queue.add(tempNode.rightChild);
                }
            }
        }
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

    public int getSize(){
        return size;
    }

    private class Node{
        private Node leftChild;
        private Node rightChild;
        private Node parent;
        private Integer value;

        private Node(int value){
            this.value = value;
        }
    }
}
