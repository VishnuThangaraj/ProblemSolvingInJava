import java.io.*;
import java.util.*;

/*
You are given a partially written BST class.
You are required to complete the body of size, sum, max, min and find function. 
The functions are expected to:

size - return the number of nodes in BST
sum - return the sum of nodes in BST
max - return the value of the node with the greatest value in BST
min - return the value of the node with the smallest value in BST
find - return true if there is a node in the tree equal to "data"

Note
Complete the given function. The input and output would be handled by the driver code.

Input Format
Input is managed for you.

Output Format
Output is managed for you.

Example 1
Input
19
50 25 12 n n 37 30 n n n 75 62 n 70 n n 87 n n
70

Output
9
448
87
12
true

Explanation
The number of nodes is 9.
The sum of values is 448.
The maximum value is 87.
The minimum value is 12.
The BST contains a node with value 70. Thus, true.

Example 2
Input
9
4 3 2 n n n 5 n n
8

Output
4
14
5
2
false

Explanation
The number of nodes is 4.
The sum of values is 14.
The maximum value is 5.
The minimum value is 2.
The BST does not contain a node with value 8. Thus, false.

Constraints
1 <= N <= 10000
1 <= A[i] <= 10000 , 'n'
*/

public class Main {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

//calculate the number of nodes in the BST
  public static int size(Node node) {
    if(node == null){
		return 0;
	}
	  //check left and right
	  return size(node.left)+size(node.right)+1;
  }

//sum of all the nodes in BST
  public static int sum(Node node) {
    if(node == null){
		return 0;
	}
	  int left = sum(node.left); //left nodes
	  int right = sum(node.right); //right nodes

	  return left+right+node.data;
  }

  public static int max(Node node) {
    if(node.right == null){
		return node.data;
	}
	  //max will be found in the right of the BST
	  return max(node.right);
  }

  public static int min(Node node) {
    if(node.left == null){
		return node.data;
	}
	  //min node will be found in the left of the BST
	  return min(node.left);
  }

  public static boolean find(Node node, int data){
    if(node == null){
		return false;
	}

	  if(node.data == data){
		  return true; //node found
	  }

	  //check for left and right nodes
	  return find(node.left,data) || find(node.right,data);
  }  

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    int data = Integer.parseInt(br.readLine());

    Node root = construct(arr);

    int size = size(root);
    int sum = sum(root);
    int max = max(root);
    int min = min(root);
    boolean found = find(root, data);

    System.out.println(size);
    System.out.println(sum);
    System.out.println(max);
    System.out.println(min);
    System.out.println(found);
  }

}
